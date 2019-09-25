package android.mmtech.javarushapp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

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
        createOrderSummary(price);
    }

    void createOrderSummary(int price) {
        String name = "Kaptain Max";
        String message = "Name: " + name + "\n" + "Quantity: " + quantity + "\n" +
                         "Total: $" + price + "\n" + "Thank you!";
        displayMessage(message);

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
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

//    private void displayPrice(int price) {
//        String message = "Total: $" + price + "\n" + "Thank You!";
//        displayMessage(message);
//    }

    /**
     * Рассчитать цену
     * @param quantity количество чашек
     * @return возвращ уже рассчитанную цену
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return  price;
    }

}
