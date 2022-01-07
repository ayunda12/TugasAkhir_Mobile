package com.indahayu.aplikasiloginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdaptergaleri extends RecyclerView.Adapter<MainAdaptergaleri.ViewHolder> {

    private List<Mainmodelgaleri.Result> results;
    private Context context;
    private AdapterListener listener;

    public MainAdaptergaleri(Context context, List<Mainmodelgaleri.Result> results, AdapterListener listener) {
        this.results    = results ;
        this.context    = context ;
        this.listener   = listener ;
    }

    @NonNull
    @Override
    public MainAdaptergaleri.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_maingaleri,
                        parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdaptergaleri.ViewHolder viewHolder, int i) {
        final Mainmodelgaleri.Result result = results.get(i);
//        viewHolder.textView.setText( result.getKeterangan() );
//        Picasso.get()
//                .load("http://192.168.235.22/DyahAyuSalon/"+result.getFoto()  )
//                .placeholder(R.drawable.img_placeholder)
//                .error(R.drawable.img_placeholder)
//                .fit(). centerCrop()
//                .into(viewHolder.imageView);
        Glide.with(context)
                .load("https://ws-tif.com/dyah-ayu-salon/assets/images/"+result.getFoto()  )
                .placeholder(R.drawable.img_placeholder)
                .centerCrop()
                .into(viewHolder.imageView);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        //TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            //textView = itemView.findViewById(R.id.textView);
        }
    }

    public void setData(List<Mainmodelgaleri.Result> newResults) {
        results.clear();
        results.addAll(newResults);
        notifyDataSetChanged();
    }

    public interface AdapterListener {
        void onClick(Mainmodelgaleri.Result result);
    }
}

