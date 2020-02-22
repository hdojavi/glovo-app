package com.jhernando.glovo.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jhernando.glovo.R;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.orderProducts.orderProductsActivity;
import com.jhernando.glovo.product.productsActivity;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductsViewHolder> {

    private ArrayList<Product> products;
    private int card;
    private orderProductsActivity orderProductActivity;

    public CartAdapter(orderProductsActivity orderProductActivity, ArrayList<Product> products) {
        this.products = products;
        card = R.layout.card_products;
        this.orderProductActivity = orderProductActivity;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(card, viewGroup, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ProductsViewHolder holder, final int position) {
        Product product = products.get(position);
        holder.nameCard.setText(product.getName());
        holder.descCard.setText(product.getDescription());
        String price = String.format("%.2f", product.getPrice()).replace('.', ',') + "€";
        holder.priceCard.setText(price);
        holder.productNumber.setText(product.getQuantity() + "x");
        holder.addProduct.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCard;
        private TextView descCard;
        private TextView priceCard;
        private TextView productNumber;
        private ImageView addProduct;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            descCard = itemView.findViewById(R.id.descCard);
            priceCard = itemView.findViewById(R.id.priceCard);
            productNumber = itemView.findViewById(R.id.productNumber);
            addProduct = itemView.findViewById(R.id.addProduct);
        }
    }
}
