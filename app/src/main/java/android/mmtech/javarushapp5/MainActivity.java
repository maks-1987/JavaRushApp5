package android.mmtech.javarushapp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int priceSummary;

    /**
     * This app displays an order form to order coffee.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        priceSummary = calculatePrice();
        boolean hasCream = checkCream(view);
        boolean hasChocolate = checkChocolate(view);
        displayMessage(createOrderSummary(priceSummary, hasCream, hasChocolate));
    }

    /**
     * Создает чек-переменную значение false - put, true - no put
     */
    public boolean checkCream(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.checked_whipped_cream);
        boolean hasCream = whippedCream.isChecked(); // проверяет состояние чек-бокса
        Log.v("MainActivity", "checkBox status is " + hasCream);
        return hasCream;
    }

    public boolean checkChocolate(View view) {
        CheckBox chocolate = (CheckBox) findViewById(R.id.checked_chocolate);
        boolean hasChocolate = chocolate.isChecked(); // проверяет состояние чек-бокса
        Log.v("MainActivity", "checkBox status is " + hasChocolate);
        return hasChocolate;
    }

    String createOrderSummary(int priceSummary, boolean hasWhippedCream, boolean hasChocolate) {
        EditText editText = (EditText) findViewById(R.id.name_edit_text);
        String name = editText.getText().toString();

        //int price = 0;
        if (hasWhippedCream == true) {
            priceSummary = quantity * 5 + quantity * 2;
        }
        if (hasChocolate == true) {
            priceSummary = quantity * 5 + quantity * 3;
        }

        return "Name: " + name + "\n" + "Add whipped cream? (2$) " + hasWhippedCream + "\n" +
                "Add chocolate? (3$) " + hasChocolate + "\n" + "Quantity: " + quantity + "\n" +
                "Total: $" + priceSummary + "\n" + "Thank you!";
    }

    public void incrementOrder(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrementOrder(View view) {
        quantity = quantity - 1;
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
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Рассчитать цену
     *
     * @param "quantity" количество чашек
     * @return возвращ уже рассчитанную цену
     */
    private int calculatePrice() {
        priceSummary = quantity * 5;
        return priceSummary;

    }

}

