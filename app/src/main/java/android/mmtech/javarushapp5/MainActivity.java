package android.mmtech.javarushapp5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    /**
     * This app displays an order form to order coffee.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    String editTextName() {
        EditText editText = (EditText) findViewById(R.id.name_edit_text);
        String name = editText.getText().toString();
        return(name);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String name = editTextName();
        boolean hasCream = checkCream();
        boolean hasChocolate = checkChocolate();
        int price = calculatePrice();
        String message = createOrderSummary(name, price, hasCream, hasChocolate);
        //displayMessage(message);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Создает чек-переменную значение false - put, true - no put
     */
    public boolean checkCream() {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.checked_whipped_cream);
        boolean hasCream = whippedCream.isChecked(); // проверяет состояние чек-бокса
        Log.v("MainActivity", "checkBox status is " + hasCream);
        return hasCream;
    }

    public boolean checkChocolate() {
        CheckBox chocolate = (CheckBox) findViewById(R.id.checked_chocolate);
        boolean hasChocolate = chocolate.isChecked(); // проверяет состояние чек-бокса
        Log.v("MainActivity", "checkBox status is " + hasChocolate);
        return hasChocolate;
    }

    String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String priceMessage = "Name: " + name;
        return "Name: " + name + "\nAdd whipped cream? (2$) " + hasWhippedCream +
                "\nAdd chocolate? (3$) " + hasChocolate + "\nQuantity: " + quantity +
                "\nTotal: $" + price + "\n" + getString(R.string.thank_you);
    }

    /**
     * Увелич кол-во чашек на единицу и выводит текст Тоста внизу экр
     */
    public void incrementOrder(View view) {
        if (quantity >= 15) {
            Toast toast = Toast.makeText(getApplicationContext(), "Ты шо, опписяешси!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (quantity < 15) {
            quantity = quantity + 1;
        }
        displayQuantity(quantity);
    }

    /**
     * Уменьш кол-во чашек на единицу и выводит текст Тоста внизу экр
     */
    public void decrementOrder(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
        }
        if (quantity <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Ты шо, куда меньше то?", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    /*private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/

    /**
     * Рассчитать цену
     * @param "quantity" количество чашек
     * @return возвращ уже рассчитанную цену
     */
    private int calculatePrice() {
        int oneCoffee = 5;
        int priceWithToppings = toppingsPrice() + oneCoffee;
        int totalPrice = priceWithToppings * quantity;

        return totalPrice;
    }

    /**
     * Считает дополнит ингры в кофе и вернет сумму их для одной чашки
     */
    private int toppingsPrice() {
        int priceToppings = 0;
        if (checkCream()) priceToppings += 2;
        if (checkChocolate()) priceToppings += 3;

        return priceToppings;
    }

}

