package com.example.oke.adapter;

import android.content.Context;
<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
<<<<<<< HEAD
import com.example.oke.Interface.ItemClickListener;
=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
<<<<<<< HEAD
//import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.example.oke.R;

import com.example.oke.utils.Utils;

import java.text.DecimalFormat;
import java.util.List;
import com.example.oke.model.SanPhamMoi;
=======
import com.example.oke.Interface.ItemClickListener;
import com.example.oke.R;
import com.example.oke.model.SanPhamMoi;
import com.example.oke.utils.Utils;
import com.example.oke.activity.ChiTietActivity;

import java.text.DecimalFormat;
import java.util.List;
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962

public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;

    public SanPhamMoiAdapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
<<<<<<< HEAD
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp_moi,parent,false);

=======
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp_moi, parent, false);
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamMoi sanPhamMoi = array.get(position);
        holder.txtten.setText(sanPhamMoi.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
<<<<<<< HEAD
        holder.txtgia.setText("Giá:"+decimalFormat.format(sanPhamMoi.getGia())+"Đ");
        if (sanPhamMoi.getHinhanh().contains("http")){
            Glide.with(context).load(sanPhamMoi.getHinhanh()).into(holder.imghinhanh);
        }else {
            Glide.with(context).load(Utils.BASE_URL+"images/"+sanPhamMoi.getHinhanh()).into(holder.imghinhanh);
        }
=======
        holder.txtgia.setText("Giá:" + decimalFormat.format(Double.parseDouble(sanPhamMoi.getGiasp())) + "Đ");

        if (sanPhamMoi.getHinhanh().contains("http")) {
            Glide.with(context).load(sanPhamMoi.getHinhanh()).into(holder.imghinhanh);
        } else {
            Glide.with(context).load(Utils.BASE_URL + "images/" + sanPhamMoi.getHinhanh()).into(holder.imghinhanh);
        }

        // Thiết lập ItemClickListener
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if (!isLongClick) {
                    Intent intent = new Intent(context, ChiTietActivity.class);
                    intent.putExtra("chitiet", array.get(pos));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

<<<<<<< HEAD

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
=======
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        TextView txtgia, txtten;
        ImageView imghinhanh;
        private ItemClickListener itemClickListener;

<<<<<<< HEAD

=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtgia = itemView.findViewById(R.id.itemsp_gia);
            txtten = itemView.findViewById(R.id.itemsp_ten);
            imghinhanh = itemView.findViewById(R.id.itemsp_image);
            itemView.setOnClickListener(this);
<<<<<<< HEAD


=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
<<<<<<< HEAD
            itemClickListener.onClick(view,getAdapterPosition(),false);

=======
            if (itemClickListener != null) {
                itemClickListener.onClick(view, getAdapterPosition(), false);
            }
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        }
    }
}