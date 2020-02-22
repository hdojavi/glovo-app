package com.jhernando.glovo.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jhernando.glovo.R;
import com.jhernando.glovo.business.businessActivity;
import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.product.productsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private ArrayList<Product> products;
    private int card;
    private productsActivity productActivity;

    public ProductsAdapter(productsActivity productActivity, ArrayList<Product> products) {
        this.products = products;
        card = R.layout.card_products;
        this.productActivity = productActivity;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(card, viewGroup, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductsViewHolder holder, final int position) {
        Product product = products.get(position);
        holder.nameCard.setText(product.getName());
        holder.descCard.setText(product.getDescription());
        String price = String.format("%.2f", product.getPrice()).replace('.', ',') + "€";
        holder.priceCard.setText(price);

        final TextView numberProduct = holder.productNumber;
        final ImageView removeProduct = holder.removeProduct;

        holder.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productActivity.addProductsItemClick(position, numberProduct, removeProduct);
            }
        });
        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productActivity.removeProductsItemClick(position, numberProduct, removeProduct);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCard;
        private TextView descCard;
        private TextView priceCard;
        private ImageView addProduct;
        private TextView productNumber;
        private ImageView removeProduct;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            descCard = itemView.findViewById(R.id.descCard);
            priceCard = itemView.findViewById(R.id.priceCard);
            addProduct = itemView.findViewById(R.id.addProduct);
            productNumber = itemView.findViewById(R.id.productNumber);
            removeProduct = itemView.findViewById(R.id.removeProduct);
        }
    }
}
