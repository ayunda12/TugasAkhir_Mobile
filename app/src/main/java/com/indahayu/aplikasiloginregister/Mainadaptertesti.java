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

public class Mainadaptertesti extends RecyclerView.Adapter<Mainadaptertesti.ViewHolder> {

    private List<Mainmodeltesti.Result> results;
    private Context context;
    private Mainadaptertesti.AdapterListener listener;

    public Mainadaptertesti(Context context, List<Mainmodeltesti.Result> results, Mainadaptertesti.AdapterListener listener) {
        this.results    = results ;
        this.context    = context ;
        this.listener   = listener ;
    }

    @NonNull
    @Override
    public Mainadaptertesti.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new Mainadaptertesti.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_maintesti,
                        parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Mainadaptertesti.ViewHolder viewHolder, int i) {
        final Mainmodeltesti.Result result = results.get(i);
        viewHolder.textView.setText(result.getKeterangan() );
        viewHolder.textView1.setText( result.getNama() );
        viewHolder.texttgl.setText( result.getTgl_upload() );

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
        TextView textView,textView1,texttgl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView1);
            texttgl = itemView.findViewById(R.id.texttgl);

        }
    }

    public void setData(List<Mainmodeltesti.Result> newResults) {
        results.clear();
        results.addAll(newResults);
        notifyDataSetChanged();
    }

    public interface AdapterListener {
        void onClick(Mainmodeltesti.Result result);
    }
}
