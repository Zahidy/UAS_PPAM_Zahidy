package com.uasppam.pizzarest;

import static com.uasppam.pizzarest.R.drawable.*;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    TextView receiver_display_name;
    ImageView store_location;
    TextView store_name;
    String braga = "Braga";
    String ciwalk = "Cihampelas Walk";
    Uri gmmIntentUri;
    Group location_maps;
    Button send_button;
    TextView send_display_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        receiver_display_name = findViewById(R.id.display_name_id);
        // create the get Intent object
        Intent userIntent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String display_name = userIntent.getStringExtra("display_name");
        // display the string into textView
        String default_text = receiver_display_name.getText().toString();
        receiver_display_name.setText(default_text + display_name);

        //Change Image By Location
        String receiver_store_name = userIntent.getStringExtra("store_name");
        store_location = findViewById(R.id.store_photos_id);
        store_name = findViewById(R.id.store_name_id);
        if (Objects.equals(receiver_store_name, braga)) {
            store_location.setImageResource(R.drawable.braga);
            store_name.setText(receiver_store_name);
            gmmIntentUri = Uri.parse("geo:-6.9179874654195554, 107.60880651943341=braga+city+walk");
        } else if (Objects.equals(receiver_store_name, ciwalk)) {
            store_location.setImageResource(R.drawable.ciwalk);
            store_name.setText(receiver_store_name);
            gmmIntentUri = Uri.parse("geo:-6.89515500124214, 107.60421780896839=cihampelas+walk");
        }

        //Open Maps Location
        location_maps = findViewById(R.id.location_group_id);
        int[] refIds = location_maps.getReferencedIds();
        for (int id : refIds) {
            findViewById(id).setOnClickListener(v -> {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });
        }

        send_button = findViewById(R.id.button_submit_id);
        send_display_name = findViewById(R.id.input_nama_id);
        send_button.setOnClickListener(v -> {
            // get the value which input by user in EditText and convert it to string
            String str = userIntent.getStringExtra("display_name");
            String selectedItem= userIntent.getStringExtra("store_name");
            // Create the Intent object of this class Context() to Second_activity class
            Intent menuIntent = new Intent(getApplicationContext(), ThirdActivity.class);
            // now by putExtra method put the value in key, value pair key is
            // message_key by this key we will receive the value, and put the string
            menuIntent.putExtra("display_name", str);
            menuIntent.putExtra("store_name", selectedItem);
            // start the Intent
            startActivity(menuIntent);
        });
    }
}