package com.uasppam.pizzarest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    TextView receiver_display_name;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    List<Posts> postsList = new ArrayList<>();
    String[] subjects = {
            "Pepperoni Pizza", "Shrimp Pizza", "Smoked Salmon Pizza", "Margherita Pizza"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        receiver_display_name = findViewById(R.id.display_name_menu_id);
        // create the get Intent object
        Intent menuIntent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String display_name = menuIntent.getStringExtra("display_name");
        String store_name = menuIntent.getStringExtra("store_name");
        // display the string into textView
        String default_text = receiver_display_name.getText().toString();
        receiver_display_name.setText(default_text + display_name);

        //Recycler
        context = getApplicationContext();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new AdapterRecyclerView(context, postsList, display_name, store_name);
        recyclerView.setAdapter(recyclerViewAdapter);

        fetchPosts();
    }

    private void fetchPosts(){
        RetrofitClient.getRetrofitClient().getPosts().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.isSuccessful() && response.body() != null){
                    postsList.addAll(response.body());
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(ThirdActivity.this, "Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error + ", t.getMessage());
            }
        });
    }
}