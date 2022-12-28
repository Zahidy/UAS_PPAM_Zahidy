package com.uasppam.pizzarest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Button send_button;
    EditText send_display_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.store_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, R.layout.location_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.location_item);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.text_main), PorterDuff.Mode.SRC_ATOP);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        send_button = findViewById(R.id.button_submit_id);
        send_display_name = findViewById(R.id.input_nama_id);
        send_button.setOnClickListener(v -> {
            // get the value which input by user in EditText and convert it to string
            String str = send_display_name.getText().toString();
            String selectedItem=spinner.getSelectedItem().toString();
            // Create the Intent object of this class Context() to Second_activity class
            Intent userIntent = new Intent(getApplicationContext(), SecondActivity.class);
            // now by putExtra method put the value in key, value pair key is
            // message_key by this key we will receive the value, and put the string
            userIntent.putExtra("display_name", str);
            userIntent.putExtra("store_name", selectedItem);
            // start the Intent
            startActivity(userIntent);
        });
    }

}