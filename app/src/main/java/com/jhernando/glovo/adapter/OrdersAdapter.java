package com.jhernando.glovo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jhernando.glovo.R;
import com.jhernando.glovo.business.businessActivity;
import com.jhernando.glovo.business.businessPresenter;
import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.orders.ordersActivity;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private ArrayList<Order> orders;
    private int card;
    private ordersActivity orderActivity;

    public OrdersAdapter(ordersActivity orderActivity, ArrayList<Order> orders) {
        this.orders = orders;
        card = R.layout.card_orders;
        this.orderActivity = orderActivity;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(card, viewGroup, false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.OrdersViewHolder holder, final int position) {
        Order order = orders.get(position);
        holder.businessCard.setText(order.getBusiness().getName());
        holder.dateCard.setText(order.getOrderDate().substring(0, order.getOrderDate().length()-3));
        holder.methodCard.setText(order.getOrderMethodName());
        String totalPrice = String.format("%.2f", order.getPriceTotal()).replace('.', ',') + "€";
        holder.priceCard.setText(totalPrice);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderActivity.onOrderItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {
        private TextView businessCard;
        private TextView dateCard;
        private TextView methodCard;
        private TextView priceCard;
        private Button btnDetail;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            businessCard = itemView.findViewById(R.id.businessCard);
            dateCard = itemView.findViewById(R.id.dateCard);
            methodCard = itemView.findViewById(R.id.methodCard);
            priceCard = itemView.findViewById(R.id.priceCard);
            btnDetail = itemView.findViewById(R.id.btnDetails);
        }
    }
}
