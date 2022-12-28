package com.uasppam.pizzarest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    TextView NamaPemesan;
    TextView LokasiStore;
    TextView Pesanan;
    TextView OrderConfirm;
    Button menu_button_order;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String Delivery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent userIntent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String display_name = userIntent.getStringExtra("display_name");
        String store_name = userIntent.getStringExtra("store_name");
        String menu_name = userIntent.getStringExtra("menu_name");
        String price = userIntent.getStringExtra("price");
        String nama = display_name;
        String store = store_name;
        String menu = menu_name;



        NamaPemesan = findViewById(R.id.NamaPemesan);
        LokasiStore = findViewById(R.id.LokasiStore);
        Pesanan = findViewById(R.id.Pesanan);
        OrderConfirm = findViewById(R.id.OrderConfirm);

        display_name = NamaPemesan.getText().toString() + display_name;
        NamaPemesan.setText(display_name);

        store_name = LokasiStore.getText().toString() + store_name;
        LokasiStore.setText(store_name);

        menu_name = menu_name + Pesanan.getText().toString() ;
        Pesanan.setText(menu_name);

        menu_button_order = (Button)findViewById(R.id.menu_button_order);
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);

        menu_button_order.setOnClickListener(v -> {
            radioButton= (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
            Delivery = radioButton.getText().toString();
            String orderConfirm = "Terima kasih " + nama + " sudah memesan ditoko cabang " + store +" kami, pesanan " + menu + " anda akan dikirim menggunakan " + Delivery;
            OrderConfirm.setText(orderConfirm);
            OrderConfirm.setVisibility(View.VISIBLE);
        });





    }
}