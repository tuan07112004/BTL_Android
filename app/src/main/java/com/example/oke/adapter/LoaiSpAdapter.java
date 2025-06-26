package com.example.oke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
<<<<<<< HEAD
import com.bumptech.glide.Glide;


=======

import com.bumptech.glide.Glide;
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
import com.example.oke.R;
import com.example.oke.model.LoaiSp;

import java.util.List;

public class LoaiSpAdapter  extends BaseAdapter {
    List<LoaiSp> array;
    Context context;

    public LoaiSpAdapter( Context context,List<LoaiSp> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
<<<<<<< HEAD


=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    public class ViewHolder{
        TextView texttensp;
        ImageView imghinhanh;

    }
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
<<<<<<< HEAD
            view = layoutInflater.inflate(R.layout.item_sanpham, null);
=======
            view = layoutInflater.inflate(R.layout.item_sanpham,null);
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
            viewHolder.texttensp = view.findViewById(R.id.item_tensp);
            viewHolder.imghinhanh = view. findViewById(R.id.item_image);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.texttensp.setText(array.get(i).getTensanpham());
        Glide.with(context).load(array.get(i).getHinhanh()).into(viewHolder.imghinhanh);



        return view;

    }
}