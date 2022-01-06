package com.indahayu.aplikasiloginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ModelRecycler> dataModelArrayList;


    public RetrofitAdapter(Context ctx, ArrayList<ModelRecycler> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public RetrofitAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.retro_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RetrofitAdapter.MyViewHolder holder, int position) {

        Picasso.get().load(dataModelArrayList.get(position).getGambar()).into(holder.iv);
        holder.nama.setText(dataModelArrayList.get(position).getNama());
        holder.keterangan.setText(dataModelArrayList.get(position).getKeterangan());

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView keterangan, nama;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            keterangan = (TextView) itemView.findViewById(R.id.keterangan);
            nama = (TextView) itemView.findViewById(R.id.nama);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }

    }
}