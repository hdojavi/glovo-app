package com.jhernando.glovo.orders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jhernando.glovo.R;
import com.jhernando.glovo.adapter.BusinessAdapter;
import com.jhernando.glovo.adapter.OrdersAdapter;
import com.jhernando.glovo.business.businessPresenter;
import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.orderDetails.orderLinesActivity;
import com.jhernando.glovo.userProfile.userProfilePresenter;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ordersActivity extends Fragment implements ordersContract.View, ordersItemClickListener {
    private View view;
    private SharedPreferences userDetails;

    private ordersPresenter orderPresenter;
    private RecyclerView orderRecycler;
    private ArrayList<Order> orders;
    private OrdersAdapter ordersAdapter;


    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_order_fragment, null);
        orderPresenter = new ordersPresenter(this);

        initUI();

        userDetails = getContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        orderPresenter.checkOrders(userDetails.getInt("userId", 0));

        return view;
    }

    private void initUI() {
        orderRecycler = view.findViewById(R.id.OrdersRecycler);

        orders = new ArrayList<>();
        ordersAdapter = new OrdersAdapter(this, orders);

        mLayoutManager = new LinearLayoutManager(getContext());
        orderRecycler.setLayoutManager(mLayoutManager);
        orderRecycler.setItemAnimator(new DefaultItemAnimator());
        orderRecycler.setAdapter(ordersAdapter);
    }

    @Override
    public void successOrders(ArrayList<Order> orders) {
        this.orders.addAll(orders);
        ordersAdapter.notifyDataSetChanged();
    }

    @Override
    public void failureOrders(Throwable t) {
        Toast.makeText(getContext(), "Error en los pedidos", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onOrderItemClick(int position) {
        Order order = orders.get(position);
        Intent intent = new Intent(getContext(), orderLinesActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
}
