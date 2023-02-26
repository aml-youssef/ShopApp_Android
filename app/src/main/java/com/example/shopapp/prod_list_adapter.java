package com.example.shopapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class prod_list_adapter extends RecyclerView.Adapter<prod_list_adapter.prod_viewhplder> {

    Context context;
    private prod_viewhplder.Clistener clistener;
    private ArrayList<Product> prod_list;
    public static class prod_viewhplder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView prod_iv;
        TextView tv_prodname,tv_price;
        Clistener clistener;
        public prod_viewhplder(@NonNull View itemView, Clistener clistener) {
            super(itemView);
            prod_iv=itemView.findViewById(R.id.prod_iv);
            tv_price=itemView.findViewById(R.id.tv_price);
            tv_prodname=itemView.findViewById(R.id.tv_prodname);
            itemView.setOnClickListener(this);
            this.clistener=clistener;
        }

        @Override
        public void onClick(View view) {
            clistener.onclick(getAdapterPosition());
        }

        public interface Clistener
        {
            void onclick(int position);
        }

    }


    public prod_list_adapter(Context context, ArrayList<Product> prod_list, prod_viewhplder.Clistener clistener) {
        this.prod_list = prod_list;
        this.context=context;
        this.clistener=clistener;
    }

    @NonNull
    @Override
    public prod_viewhplder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflactor=LayoutInflater.from(parent.getContext());
        View view=inflactor.inflate(R.layout.product_list_item, parent , false);
        return new prod_viewhplder(view,clistener);
    }

    @Override
    public void onBindViewHolder(@NonNull prod_viewhplder holder, int position) {
        //  holder.itemView.setTag(prod_list.get(position));
        holder.tv_prodname.setText(prod_list.get(position).P_name);
        holder.tv_price.setText("price "+prod_list.get(position).P_price+" EGP");
        byte[] product_image=prod_list.get(position).P_photo;
        Bitmap bitmap= BitmapFactory.decodeByteArray(product_image, 0, product_image.length);
        holder.prod_iv.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return prod_list.size();
    }


}
