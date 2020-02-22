package com.jhernando.glovo.orderDetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jhernando.glovo.MainActivity;
import com.jhernando.glovo.R;
import com.jhernando.glovo.adapter.CartAdapter;
import com.jhernando.glovo.adapter.OrderLineAdapter;
import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.OrderLine;
import com.jhernando.glovo.model.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class orderLinesActivity extends AppCompatActivity implements orderLinesContract.View {
    private SharedPreferences userDetails;
    private orderLinesPresenter orderLinesPresenter;

    private ArrayList<OrderLine> orderLines;
    private Order thisOrder;

    private ImageView btnBack;

    private TextView nameBusiness;
    private TextView numTlfn;
    private TextView horaEntrega;
    private TextView spinnerPayment;
    private TextView totalPrice;

    private RecyclerView orderLineRecycler;
    private OrderLineAdapter orderLineAdapter;

    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        orderLines = new ArrayList<>();
        Intent intent = getIntent();
        thisOrder = (Order) intent.getSerializableExtra("order");

        userDetails = getSharedPreferences("userdetails", MODE_PRIVATE);

        orderLinesPresenter = new orderLinesPresenter(this);


        nameBusiness = findViewById(R.id.nameBusiness);
        nameBusiness.setText("Tu encargo a " + thisOrder.getBusiness().getName());

        numTlfn = findViewById(R.id.numTlfn);
        numTlfn.setText(userDetails.getString("tlfn", null));

        horaEntrega = findViewById(R.id.horaEntrega);
        horaEntrega.setText(thisOrder.getOrderDate().substring(0, thisOrder.getOrderDate().length() - 3));

        spinnerPayment = findViewById(R.id.spinnerPayment);
        spinnerPayment.setText(thisOrder.getOrderMethodName());

        totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText(String.format("%.2f", thisOrder.getPriceTotal()).replace('.', ',') + "€");

        btnBack = findViewById(R.id.arrowBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initUI();

        orderLinesPresenter.getOrderLines(thisOrder.getId());
    }

    private void initUI() {
        orderLineRecycler = findViewById(R.id.OrderLinesRecycler);

        orderLineAdapter = new OrderLineAdapter(this, orderLines);

        mLayoutManager = new LinearLayoutManager(this);
        orderLineRecycler.setLayoutManager(mLayoutManager);
        orderLineRecycler.setItemAnimator(new DefaultItemAnimator());
        orderLineRecycler.setAdapter(orderLineAdapter);
    }


    @Override
    public void successOrderLine(ArrayList<OrderLine> orderLines) {
        this.orderLines.addAll(orderLines);
        orderLineAdapter.notifyDataSetChanged();
    }

    @Override
    public void failureOrderLine(Throwable t) {
        Toast.makeText(this, "Error en detalles...", Toast.LENGTH_SHORT).show();

    }
}
