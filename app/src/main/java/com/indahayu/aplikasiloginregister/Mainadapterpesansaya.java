package com.indahayu.aplikasiloginregister;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Mainadapterpesansaya extends RecyclerView.Adapter<Mainadapterpesansaya.ViewHolder> {
    private Context context;
    private List<Mainmodelpesansaya> items;



    public Mainadapterpesansaya(Context context, List<Mainmodelpesansaya> items) {
        this.context    = context ;
        this.items    = items ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_mainpesansaya, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Mainmodelpesansaya pesan = items.get(position);
        holder.textpaket.setText("Nama Paket : " + pesan.getNama_paket());
        holder.texttglpesan.setText("Tanggal Pesan : "+pesan.getTgl_booking());
        holder.texttglacara.setText("Tanggal Acara : "+pesan.getTgl_acara());
        holder.textstatus.setText("Status Pemesanan : "+pesan.getStatus());
        holder.textidbooking.setText("Id booking : "+pesan.getId_booking());
        holder.textketerangan.setText("Keterangan : "+pesan.getKeterangan_bayar());


        holder.ll_layout_utama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailPesananSayaActivity.class);
                intent.putExtra("Nama Paket", pesan.getNama_paket());
                intent.putExtra("Tanggal Pesan", pesan.getTgl_booking());
                intent.putExtra("Tanggal Acara", pesan.getTgl_acara());
                intent.putExtra("Harga", pesan.getHarga());
                intent.putExtra("Id booking", pesan.getId_booking());
                intent.putExtra("Keterangan", pesan.getKeterangan_bayar());
                intent.putExtra("Status Pemesanan", pesan.getStatus());
                intent.putExtra("Bukti Bayar", pesan.getBukti_bayar());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textpaket,texttglacara,texttglpesan,textharga,textstatus,textidbooking,textketerangan;

        public LinearLayout ll_layout_utama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textpaket = itemView.findViewById(R.id.txtpaket);
            texttglpesan = itemView.findViewById(R.id.txttglpesan);
            texttglacara = itemView.findViewById(R.id.txttglacara);
            textharga = itemView.findViewById(R.id.txtharga);
            textstatus = itemView.findViewById(R.id.txtstatus);
            textidbooking = itemView.findViewById(R.id.txtid);
            textketerangan = itemView.findViewById(R.id.txtket);
            ll_layout_utama = itemView.findViewById(R.id.ll_layout_utama);
        }
    }


}
