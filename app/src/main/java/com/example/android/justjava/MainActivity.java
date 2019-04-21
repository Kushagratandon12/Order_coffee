/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.util.Log.v;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_main );
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedcreamCheckBox = (CheckBox) findViewById( R.id.checkbox_check );
        boolean checkedCream = (whippedcreamCheckBox.isChecked());
        Log.v( "MainActivity", "Has whipped cream " + checkedCream );

        CheckBox chocolateBox = (CheckBox) findViewById( R.id.checkbox2_check );
        boolean checkchoco = (chocolateBox.isChecked());
        Log.v( "MainActivity ", "Has Chocolate " + checkchoco );

        EditText descriptionview = (EditText) findViewById( R.id.description_view );
        Editable name = (descriptionview.getText());
        Log.v( "MainActivity", "Name Of The User " + name );

        int price = calculateprice( checkedCream, checkchoco );
        String priceMessage = displaysumaryorder( price, checkedCream, checkchoco, name );

        Intent intent = new Intent( Intent.ACTION_SENDTO );
        intent.setData( Uri.parse( "mailto:" ) ); // only email apps should handle this
        intent.putExtra( Intent.EXTRA_SUBJECT, "Just Java Order  for  " + name );
        intent.putExtra( Intent.EXTRA_TEXT, priceMessage );
        if (intent.resolveActivity( getPackageManager() ) != null) {
            startActivity( intent );
        }

        displayMessage( priceMessage );


    }

    /**
     * @return the total price of the order ;
     */
    private int calculateprice(boolean addwhippedcream, boolean addchocolate) {
        int basevalue = 9;
        if (addwhippedcream) {
            basevalue++;

        }

        if (addchocolate) {
            basevalue = basevalue + 2;
        }

        return basevalue * quantity;
    }

    /**
     * @param price        takes the value of calculate price ;
     * @param checkedCream boolean returns the value as true or false depending upon the checkedbox
     * @param checkedChoco boolean that returns value as true or false depending upon the checkedbox
     * @return
     */
    private String displaysumaryorder(int price, boolean checkedCream, boolean checkedChoco, Editable descname) {
        String priceMessage = "Name " + descname;
        priceMessage += "\nAdd Whipped Cream ? " + checkedCream;
        priceMessage += "\nAdd Chocolate ? " + checkedChoco;
        priceMessage += "\nQuantity " + quantity;
        priceMessage += "\nTotal : - " + price;
        priceMessage += "\nThank You";
        return priceMessage;
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText( this, " You Cannot Order More Than 100 ", Toast.LENGTH_LONG ).show();
            return;
        }
        quantity = quantity + 1;
        display( quantity );

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText( this, " You Cannot Order Less Than 1 ", Toast.LENGTH_LONG ).show();
            return;
        }
        quantity = quantity - 1;

        display( quantity );

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById( R.id.quantity_text_view );
        quantityTextView.setText( "" + number );
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById( R.id.price_text_view );
        priceTextView.setText( NumberFormat.getCurrencyInstance().format( number ) );
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById( R.id.price_text_view );
        priceTextView.setText( message );
    }



}