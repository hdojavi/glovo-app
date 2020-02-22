package com.jhernando.glovo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jhernando.glovo.R;
import com.jhernando.glovo.model.OrderLine;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.orderDetails.orderLinesActivity;
import com.jhernando.glovo.orderProducts.orderProductsActivity;

import java.util.ArrayList;

public class OrderLineAdapter extends RecyclerView.Adapter<OrderLineAdapter.OrderLineViewHolder> {

    private ArrayList<OrderLine> orderLine;
    private int card;
    private orderLinesActivity orderLineActivity;

    public OrderLineAdapter(orderLinesActivity orderLineActivity, ArrayList<OrderLine> orderLine) {
        this.orderLine = orderLine;
        card = R.layout.card_products;
        this.orderLineActivity = orderLineActivity;
    }

    @NonNull
    @Override
    public OrderLineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(card, viewGroup, false);
        return new OrderLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderLineAdapter.OrderLineViewHolder holder, final int position) {
        OrderLine oL = orderLine.get(position);
        holder.nameCard.setText(oL.getProductName());
        String price = String.format("%.2f", oL.getPrice()).replace('.', ',') + "€";
        holder.priceCard.setText(price);
        holder.productNumber.setText(oL.getQuantity() + "x");
        holder.addProduct.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return orderLine.size();
    }

    public class OrderLineViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCard;
        private TextView priceCard;
        private TextView productNumber;
        private ImageView addProduct;

        public OrderLineViewHolder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            priceCard = itemView.findViewById(R.id.priceCard);
            productNumber = itemView.findViewById(R.id.productNumber);
            addProduct = itemView.findViewById(R.id.addProduct);
        }
    }
}
