package com.example.listingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    //Initialise the list item here
    private ArrayList<ProductModel> arrayListAllProducts;
    //Creating context for toast
    private Context context;

    public ProductAdapter(ArrayList<ProductModel> arrayListAllProducts, Context context) {
        this.arrayListAllProducts = arrayListAllProducts;
        this.context = context;
    }

    //View holder(it calls the created recycler View)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //All the click listener is done here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewProductName.setText(arrayListAllProducts.get(position).getProductName());
        holder.textViewProductPrice.setText("$"+String.valueOf(arrayListAllProducts.get(position).getProductPrice()));
        holder.textViewProductType.setText(arrayListAllProducts.get(position).getProductType());
        holder.textViewProductTax.setText(String.valueOf(arrayListAllProducts.get(position).getProductTax()));
        Picasso.with(context).load(arrayListAllProducts.get(position).getProductImage().isEmpty()? null : arrayListAllProducts.get(position).getProductImage())
                .placeholder(R.mipmap.img_missing)
                .error(R.mipmap.img_missing)
                .into(holder.imageViewProductImage);
    }

    @Override
    public int getItemCount() {
        return arrayListAllProducts.size();
    }

    //Every view inside the recycler view is declared and initialised here
    public class ViewHolder extends RecyclerView.ViewHolder{
        //Declaration
        private TextView textViewProductName, textViewProductPrice, textViewProductType, textViewProductTax;
        private ImageView imageViewProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.tv_product_name);
            textViewProductPrice = itemView.findViewById(R.id.tv_product_price);
            textViewProductType = itemView.findViewById(R.id.tv_product_type);
            textViewProductTax = itemView.findViewById(R.id.tv_product_tax);
            imageViewProductImage =  itemView.findViewById(R.id.ic_product_image);
        }
    }
}
