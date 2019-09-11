package android.mmtech.javarushapp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
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
        int numberOfCoffees = 2;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * 5);
    }

    public void incrementOrder(View view) {
        int quantity = 3;
        display(quantity);
        //displayPrice(quantity++);
    }

    public void decrementOrder(View view) {
        int quantity = 1;
        display(quantity);
        //displayPrice(quantity--);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Отображает цену на экран
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}
