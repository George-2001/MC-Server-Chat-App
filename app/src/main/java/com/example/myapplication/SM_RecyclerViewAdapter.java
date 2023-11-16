package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SM_RecyclerViewAdapter extends RecyclerView.Adapter<SM_RecyclerViewAdapter.MyViewHolder> {

    boolean boot = true;

    Context context;
    ArrayList<com.example.myapplication.ServerModel> serverModelArrayList;

    public SM_RecyclerViewAdapter(Context context, ArrayList<com.example.myapplication.ServerModel> serverModelArrayList) {
        this.context = context;
        this.serverModelArrayList = serverModelArrayList;
    }

    public void setArrayList(ArrayList<com.example.myapplication.ServerModel> serverModelArrayList) {
        this.serverModelArrayList = serverModelArrayList;
    }


    // Inflates the layout (Gives a look to our rows)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(view);
    }

    //Assigns values to the views we created in server_entity layout file
    //based on position of recycler view
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.textView.setText(serverModelArrayList.get(position).getserverName());
        holder.textView2.setText("Players online: "+serverModelArrayList.get(position).getPlayerCount() + "/" + serverModelArrayList.get(position).getMaxPlayers());
        holder.textView3.setText(serverModelArrayList.get(position).getVersion());
        holder.imageView.setImageBitmap(serverModelArrayList.get(position).getImage());
    }

    // Used to know number of items you want displayed
    @Override
    public int getItemCount() {
        return serverModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //grabbing the views from our server_entity layout file
        //Kinda like onCreate method

        ImageView imageView;
        TextView textView;
        TextView textView2;
        TextView textView3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }
    }

}
