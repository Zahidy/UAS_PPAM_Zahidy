package com.uasppam.pizzarest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

//    private String[] SubjectValues;
    private Context context;
    private List<Posts> postsList;
    String display_name;
    String store_name;

    AdapterRecyclerView(Context context1, List<Posts> postsList, String display_name1, String store_name1) {

//        SubjectValues = SubjectValues1;
        this.postsList = postsList;
        context = context1;
        display_name = display_name1;
        store_name = store_name1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        TextView descView;
        CardView menuCard;

        ViewHolder(View v) {

            super(v);

            textView = v.findViewById(R.id.menu_title_id);
            imageView = v.findViewById(R.id.display_picture_menu_id);
            descView = v.findViewById(R.id.menu_desc_id);
            menuCard = v.findViewById(R.id.card_menu_id);

        }
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.menu_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(postsList.get(position).getFoodName());
        holder.descView.setText(postsList.get(position).getDetails());
        holder.menuCard.setOnClickListener(v -> {
            String _id = postsList.get(position).getId().toString();
            String str = holder.textView.getText().toString();
            String desc = postsList.get(position).getDetails();
            String price = postsList.get(position).getPrice();
            // Create the Intent object of this class Context() to Second_activity class
            Intent menuIntent = new Intent(context.getApplicationContext(), MenuActivity.class);
            menuIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // now by putExtra method put the value in key, value pair key is
            // menu_name by this key we will receive the value, and put the string
            menuIntent.putExtra("menu_name", str);
            menuIntent.putExtra("id", _id);
            menuIntent.putExtra("desc", desc);
            menuIntent.putExtra("price", price);
            menuIntent.putExtra("display_name", display_name);
            menuIntent.putExtra("store_name", store_name);
            // start the Intent
            context.startActivity(menuIntent);
        });


        if (postsList.get(position).getId() == 1){
            holder.imageView.setImageResource(R.drawable.pizza_pepperoni);
        } else if (postsList.get(position).getId() == 2){
            holder.imageView.setImageResource(R.drawable.spaghetti);
        } else if (postsList.get(position).getId() == 3){
            holder.imageView.setImageResource(R.drawable.burger);
        } else if (postsList.get(position).getId() == 4){
            holder.imageView.setImageResource(R.drawable.steak);
        } else if (postsList.get(position).getId() == 5){
            holder.imageView.setImageResource(R.drawable.fries);
        }

//        int[] refIds = holder.menuGroup.getReferencedIds();
//        for (int id : refIds) {
//            holder.textView.findViewById(id).setOnClickListener(v -> {
//                String str = holder.textView.getText().toString();
//                // Create the Intent object of this class Context() to Second_activity class
//                Intent menuIntent = new Intent(context.getApplicationContext(), MenuActivity.class);
//                // now by putExtra method put the value in key, value pair key is
//                // menu_name by this key we will receive the value, and put the string
//                menuIntent.putExtra("menu_name", str);
//                // start the Intent
//                context.startActivity(menuIntent);
//            });
//        }
    }

    @Override
    public int getItemCount() {

        return postsList.size();
    }
}