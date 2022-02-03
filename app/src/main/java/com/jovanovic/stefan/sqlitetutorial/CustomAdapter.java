package com.jovanovic.stefan.sqlitetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList sample_id, pulp_id, carton_id, box_id;

    //constructor
    CustomAdapter(Activity activity, Context context, ArrayList sample_id, ArrayList pulp_id, ArrayList carton_id,
                  ArrayList box_id){
        this.activity = activity;
        this.context = context;
        this.sample_id = sample_id;
        this.pulp_id = pulp_id;
        this.carton_id = carton_id;
        this.box_id = box_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.sample_id_txt.setText(String.valueOf(sample_id.get(position)));
        holder.pulp_id_txt.setText(String.valueOf(pulp_id.get(position)));
        holder.carton_id_txt.setText(String.valueOf(carton_id.get(position)));
        holder.box_id_txt.setText(String.valueOf(box_id.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(sample_id.get(position)));
                intent.putExtra("title", String.valueOf(pulp_id.get(position)));
                intent.putExtra("author", String.valueOf(carton_id.get(position)));
                intent.putExtra("pages", String.valueOf(box_id.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return sample_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView sample_id_txt, pulp_id_txt, carton_id_txt, box_id_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sample_id_txt = itemView.findViewById(R.id.sample_id_txt);
            pulp_id_txt = itemView.findViewById(R.id.pulp_id_txt);
            carton_id_txt = itemView.findViewById(R.id.carton_id_txt);
            box_id_txt = itemView.findViewById(R.id.box_id_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
