package android.mmtech.javarushapp5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
        String hasCream = checkCream();
        String hasChocolate = checkChocolate();
        int price = calculatePrice();
        String message = createOrderSummary(name, price, hasCream, hasChocolate);
        /** создаст интент с возможнстью открыть на выбор плюс текст из createOrderSummary */
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.just_java_order_for) + " " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Создает чек-переменную значение false - put, true - no put
     */
    public String checkCream() {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.checked_whipped_cream);
        if (whippedCream.isChecked()) {
            return "Yes";
        } else {
            return "No";
        }
        //Log.v("MainActivity", "checkBox status is " + hasCream);
    }

    public String checkChocolate() {
        CheckBox chocolate = (CheckBox) findViewById(R.id.checked_chocolate);
        if (chocolate.isChecked()) {
            return "Yes";
        } else {
            return "No";
        }
        //Log.v("MainActivity", "checkBox status is " + hasChocolate);
    }

    String createOrderSummary(String name, int price, String hasWhippedCream, String hasChocolate) {
        return "Name: " + name + "\nAdd whipped cream? (2$) " + hasWhippedCream +
                "\nAdd chocolate? (3$) " + hasChocolate + "\nQuantity: " + quantity +
                "\nTotal: $" + price + "\n" + getString(R.string.thank_you);
    }

    /**
     * Увелич кол-во чашек на единицу и выводит текст Тоста внизу экр
     */
    public void incrementOrder(View view) {
        if (quantity < 10) {
            quantity++;
        } else if (quantity == 10) {
            Toast toast = Toast.makeText(getApplicationContext(), "Ты шо, опписяешси!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        displayQuantity(quantity);
    }

    /**
     * Уменьш кол-во чашек на единицу и выводит текст Тоста внизу экр
     */
    public void decrementOrder(View view) {
        if (quantity > 0) {
            quantity--;
        } else if (quantity == 0) {
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
        quantityTextView.setText(String.valueOf(number));
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
        if (checkCream().equals("Yes")) priceToppings += 2;
        if (checkChocolate().equals("Yes")) priceToppings += 3;

        return priceToppings;
    }

}

