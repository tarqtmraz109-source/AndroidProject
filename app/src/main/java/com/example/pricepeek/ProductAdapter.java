package com.example.pricepeek;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_PRODUCT = 1;

    private List<Object> items;
    private List<Object> itemsFull;
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    public ProductAdapter(List<Object> items, OnProductClickListener listener) {
        this.items = items;
        this.itemsFull = new ArrayList<>(items);
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof String) {
            return TYPE_HEADER;
        }
        return TYPE_PRODUCT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_section_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_short_prudect, parent, false);
            return new ProductViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.tvHeader.setText((String) items.get(position));
        } else if (holder instanceof ProductViewHolder) {
            ProductViewHolder productHolder = (ProductViewHolder) holder;
            Product product = (Product) items.get(position);
            productHolder.tvName.setText(product.getName());
            productHolder.tvShop.setText(product.getShop());
            productHolder.tvPrice.setText(product.getPrice());
            productHolder.imgProduct.setImageResource(product.getImageRes());
            productHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onProductClick(product);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(String text) {
        items.clear();
        if (text.isEmpty()) {
            items.addAll(itemsFull);
        } else {
            text = text.toLowerCase();
            for (Object obj : itemsFull) {
                if (obj instanceof String) {
                    String header = (String) obj;
                    items.add(header);
                } else if (obj instanceof Product) {
                    Product p = (Product) obj;
                    if (p.getName().toLowerCase().contains(text) ||
                        p.getShop().toLowerCase().contains(text)) {
                        items.add(p);
                    }
                }
            }
            removeEmptyHeaders();
        }
        notifyDataSetChanged();
    }

    private void removeEmptyHeaders() {
        List<Object> toRemove = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof String) {
                boolean hasNext = (i + 1 < items.size()) && items.get(i + 1) instanceof Product;
                if (!hasNext) {
                    toRemove.add(items.get(i));
                }
            }
        }
        items.removeAll(toRemove);
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;
        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvSectionHeader);
        }
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvShop, tvPrice;
        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvShop = itemView.findViewById(R.id.tvShopName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
