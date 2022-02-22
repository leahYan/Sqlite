package com.jovanovic.stefan.sqlitetutorial;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

    private static final String TAG = "CustomAdapter";
    private Context context;
    private Activity activity;
    List<String> sample_id;
    List<String> pulp_id;
    List<String> carton_id;
    List<String> box_id;
    String sampleList;
    List<String> sampleListAll;

    //constructor
    CustomAdapter(Activity activity, Context context, List<String> sample_id, List<String> pulp_id, List<String> carton_id,
                  List<String> box_id) {
        this.activity = activity;
        this.context = context;
        this.sample_id = sample_id;
        this.pulp_id = pulp_id;
        this.carton_id = carton_id;
        this.box_id = box_id;
        this.sampleList = sampleList;
        this.sampleListAll = new ArrayList<>(box_id);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint("RecyclerView")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder,  int position) {
        // holder.sample_id_txt.setText(String.valueOf(sample_id.get(position)));
        // holder.pulp_id_txt.setText(String.valueOf(pulp_id.get(position)));
        // holder.carton_id_txt.setText(String.valueOf(carton_id.get(position)));
        // holder.box_id_txt.setText(String.valueOf(box_id.get(position)));
        holder.sample_id_txt.setText(String.valueOf(sample_id.get(position)));
        holder.sample_id_txt.setText(sample_id.get(position));
        holder.pulp_id_txt.setText(String.valueOf(pulp_id.get(position)));
        holder.pulp_id_txt.setText(pulp_id.get(position));
        holder.carton_id_txt.setText(String.valueOf(carton_id.get(position)));
        holder.carton_id_txt.setText(carton_id.get(position));
        holder.box_id_txt.setText(String.valueOf(box_id.get(position)));
        holder.box_id_txt.setText(box_id.get(position));
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
        return box_id.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        @Override //run on background thread
        protected FilterResults performFiltering(CharSequence charSequence) {

            FilterResults filterResults = new FilterResults();

            if (charSequence.toString().isEmpty()==false) {
                List<String> filteredList = new ArrayList<String>();
                for (String box : box_id) {
                    if (box.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(box);
                        System.out.println("matching found:");
                        System.out.println("pulp"+pulp_id);
                        System.out.println("carton"+carton_id);
                        System.out.println("box"+box_id);
                    }
                    else {System.out.println("boxid"+box.toLowerCase());
                        System.out.println("searchid"+charSequence.toString().toLowerCase());
                        System.out.println("not match");
                    }

                }

                //box_id.clear();
                //box_id.addAll((Collection<? extends String>) filterResults.values);
                filterResults.values = filteredList;
                filterResults.count = filteredList.size();
                System.out.println("filterresults->"+filterResults.count);

                System.out.println("boxsize->"+box_id.size());
                System.out.println("SLsize->"+sampleListAll.size());
            } else {


                System.out.println("not match2");
            }


            return filterResults;
        }

        ////// //run on a ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults Results) {
           if(Results != null && Results.count > 0) {
               System.out.println("DataSetChanged");
               notifyDataSetChanged();
           }

        }
        ////// //run on a ui thread

    };


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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    sampleListAll.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });
        }

    }


}
