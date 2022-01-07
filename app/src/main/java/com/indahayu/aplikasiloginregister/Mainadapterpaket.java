package com.indahayu.aplikasiloginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Mainadapterpaket extends RecyclerView.Adapter<Mainadapterpaket.ViewHolder> {

    private List<Mainmodelpaket.Result> results;
    private Context context;
    private AdapterListener listener;

    public Mainadapterpaket(Context context, List<Mainmodelpaket.Result> results, AdapterListener listener) {
        this.results    = results ;
        this.context    = context ;
        this.listener   = listener ;
    }

    @NonNull
    @Override
    public Mainadapterpaket.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mainpaket,
                        parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Mainadapterpaket.ViewHolder viewHolder, int i) {
        final Mainmodelpaket.Result result = results.get(i);
        viewHolder.textView.setText( "Paket " +result.getNama_paket() );
//        Picasso.get()
//                .load("http://192.168.235.22/DyahAyuSalon/assets/images/"+result.getGambar()  )
//                .placeholder(R.drawable.img_placeholder)
//                .error(R.drawable.img_placeholder)
//                .fit(). centerCrop()
//                .into(viewHolder.imageView);
        Glide.with(context)
                .load("https://ws-tif.com/dyah-ayu-salon/assets/images/"+result.getGambar())
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
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    public void setData(List<Mainmodelpaket.Result> newResults) {
        results.clear();
        results.addAll(newResults);
        notifyDataSetChanged();
    }

    public interface AdapterListener {
        void onClick(Mainmodelpaket.Result result);
    }
}

