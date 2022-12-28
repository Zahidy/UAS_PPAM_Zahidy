package com.uasppam.pizzarest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    TextView receiver_menu_name;
    ImageView menuImages;
    TextView menuDesc;
    TextView menuPrice;
    Button backButton;
    Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        receiver_menu_name = findViewById(R.id.menu_item_text);
        // create the get Intent object
        Intent userIntent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String display_name = userIntent.getStringExtra("display_name");
        String store_name = userIntent.getStringExtra("store_name");
        String menu_name = userIntent.getStringExtra("menu_name");
        String _id = userIntent.getStringExtra("id");
        String price = userIntent.getStringExtra("price");
        String desc = userIntent.getStringExtra("desc");
        // display the string into textView
        receiver_menu_name.setText(menu_name);

        //Change Image
        menuImages = findViewById(R.id.menu_big_picture);
        menuDesc = findViewById(R.id.menu_item_desc);
        menuPrice = findViewById(R.id.menu_item_price);

        //Desc and Price
        menuDesc.setText(desc);
        menuPrice.setText("Rp. " + price);

        if (_id.equals("1")){
            menuImages.setImageResource(R.drawable.pizza_pepperoni);
        } else if (_id.equals("2")){
            menuImages.setImageResource(R.drawable.spaghetti);
        } else if (_id.equals("3")){
            menuImages.setImageResource(R.drawable.burger);
        } else if (_id.equals("4")){
            menuImages.setImageResource(R.drawable.steak);
        } else if (_id.equals("5")){
            menuImages.setImageResource(R.drawable.fries);
        }

        orderButton = findViewById(R.id.menu_button_order);
        orderButton.setOnClickListener(v -> {
            // get the value which input by user in EditText and convert it to string
            // Create the Intent object of this class Context() to Second_activity class
            Intent menuIntent = new Intent(getApplicationContext(), OrderActivity.class);
            // now by putExtra method put the value in key, value pair key is
            // message_key by this key we will receive the value, and put the string
            menuIntent.putExtra("display_name", display_name);
            menuIntent.putExtra("store_name", store_name);
            menuIntent.putExtra("menu_name", menu_name);
            menuIntent.putExtra("price", price);
            // start the Intent
            startActivity(menuIntent);
        });

        backButton = findViewById(R.id.menu_button_back);
        backButton.setOnClickListener(
                v -> {
                    Intent i = new Intent(MenuActivity.this,ThirdActivity.class);
                    i.putExtra("display_name", display_name);
                    i.putExtra("store_name", store_name);
                    startActivity(i);
                }
        );
    }
}