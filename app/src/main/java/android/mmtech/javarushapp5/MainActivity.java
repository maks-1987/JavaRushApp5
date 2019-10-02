package android.mmtech.javarushapp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

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
        int price = calculatePrice();
        boolean hasCream = check(view);
        displayMessage(createOrderSummary(price, hasCream));
    }

    /**
     * Создает чек-переменную значение false - put, true - no put
     */
    public boolean check(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.checked_whipped_cream);
        boolean hasCream = whippedCream.isChecked(); // проверяет состояние чек-бокса
        Log.v("MainActivity", "checkBox status is " + hasCream);
        return hasCream;
    }

    String createOrderSummary(int price, boolean hasWhippedCream) {
        boolean isWhippedCream = hasWhippedCream;
        String name = "Kaptain Max";
        String message = "Name: " + name + "\n" + "Add whipped cream? " + isWhippedCream +
                "\n" + "Quantity: " + quantity + "\n" + "Total: $" + price + "\n" +
                "Thank you!";
        return message;
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
     * @param "quantity" количество чашек
     * @return возвращ уже рассчитанную цену
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return  price;
    }


}
