package com.example.nstuhelpo;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdaptercp extends RecyclerView.Adapter<MyHoldercp> implements Filterable {
    Context c;
    ArrayList<Modelcp> players,filterList;
    CustomFiltercp filter;

    public MyAdaptercp(Context ctx,ArrayList<Modelcp> players)
    {
        this.c=ctx;
        this.players=players;
        this.filterList=players;
    }
    @Override
    public MyHoldercp onCreateViewHolder(ViewGroup parent, int viewType) {
        //CONVERT XML TO VIEW OBJ
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.modelcp,null);
        //HOLDER
        MyHoldercp holder = new MyHoldercp(v);
        return holder;
    }
    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHoldercp holder, int position) {
        //BIND DATA
        holder.nameTxt.setText(players.get(position).getName());
        holder.sourceTxt.setText(players.get(position).getSource());
        holder.serialTxt.setText(players.get(position).getSerial());
        holder.img1.setImageResource(players.get(position).getImg1());

        Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);

        //IMPLEMENT CLICK LISTENER
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                if (players.get(pos).getName().equals("From Dhaka")){
                    // Toast.makeText(c, "Home...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(c, TrainShedule.class);
                    intent.putExtra("key",0);
                    //  intent.putExtra("contentTv", "This is Battery detail...");
                    c.startActivity(intent);
                }
                else if (players.get(pos).getName().equals("Contacts")){
                    Toast.makeText(c, "Contacts...", Toast.LENGTH_SHORT).show();
                }
                else if (players.get(pos).getName().equals("Images")){
                    Toast.makeText(c, "Images...", Toast.LENGTH_SHORT).show();
                }
                else if (players.get(pos).getName().equals("Videos")){
                    Toast.makeText(c, "Videos...", Toast.LENGTH_SHORT).show();
                }
                else if (players.get(pos).getName().equals("Mails")){
                    Toast.makeText(c, "Mails...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    //GET TOTAL NUM OF PLAYERS
    @Override
    public int getItemCount() {
        return players.size();
    }
    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFiltercp(filterList,this);
        }
        return filter;
    }
}