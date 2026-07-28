package com.example.pricepeek;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    private List<ShopModel> shops;
    private OnShopClickListener listener;

    public interface OnShopClickListener {
        void onShopClick(ShopModel shop);
    }

    public ShopAdapter(List<ShopModel> shops, OnShopClickListener listener) {
        this.shops = shops;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_short_shop, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        ShopModel shop = shops.get(position);
        holder.tvShopName.setText(shop.getName());
        holder.tvPrice.setText(shop.getRating());
        holder.imgShop.setImageResource(shop.getImageRes());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onShopClick(shop);
        });
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {
        TextView tvShopName, tvPrice;
        ImageView imgShop;

        ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvShopName = itemView.findViewById(R.id.ShopName);
            tvPrice = itemView.findViewById(R.id.Price);
            imgShop = itemView.findViewById(R.id.imgShop);
        }
    }
}
