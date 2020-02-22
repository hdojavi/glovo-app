package com.jhernando.glovo.orderProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.jhernando.glovo.MainActivity;
import com.jhernando.glovo.R;
import com.jhernando.glovo.adapter.CartAdapter;
import com.jhernando.glovo.adapter.ProductsAdapter;
import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.orders.ordersPresenter;
import com.jhernando.glovo.product.productsContract;
import com.jhernando.glovo.product.productsItemClickListener;
import com.jhernando.glovo.product.productsPresenter;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class orderProductsActivity extends AppCompatActivity implements orderProductsContract.View {
    private SharedPreferences userDetails;
    private orderProductsPresenter orderProductsPresenter;

    private ArrayList<Product> productCart;
    private ArrayList<Product> newCart;
    private Business thisBusiness;

    private ImageView btnBack;
    private TextView nameBusiness;
    private Button buttonOrder;
    private Spinner spinnerPayment;

    private TextView horaEntrega;
    private TextView numTlfn;
    private TextView productPrice;
    private TextView shippingPrice;
    private TextView totalPrice;

    private RecyclerView productsRecycler;
    private CartAdapter cartAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private int paymentMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_products);

        Intent intent = getIntent();
        productCart = (ArrayList<Product>) intent.getSerializableExtra("cart");
        newCart = new ArrayList<>();
        thisBusiness = (Business) intent.getSerializableExtra("business");

        orderProductsPresenter = new orderProductsPresenter(this);

        btnBack = findViewById(R.id.arrowBack);
        buttonOrder = findViewById(R.id.buttonOrders);
        nameBusiness = findViewById(R.id.nameBusiness);

        spinnerPayment = findViewById(R.id.spinnerPayment);
        List<String> payments = new ArrayList<>();
        payments.add(0, "Elige tu metodo de pago");
        payments.add("Paypal");
        payments.add("Tarjeta de crédito");
        payments.add("En mano");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, payments);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerPayment.setAdapter(dataAdapter);
        spinnerPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paymentMethod = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                paymentMethod = 1;
            }
        });

        horaEntrega = findViewById(R.id.horaEntrega);
        DateFormat hm = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 1);
        cal.add(Calendar.MINUTE, 30);
        String date = hm.format(cal.getTime());
        horaEntrega.setText("Aproximadamente en 30 minutos ( " + date + " )");

        numTlfn = findViewById(R.id.numTlfn);
        userDetails = getSharedPreferences("userdetails", MODE_PRIVATE);
        numTlfn.setText(userDetails.getString("tlfn", null));

        productPrice = findViewById(R.id.productsPrice);
        shippingPrice = findViewById(R.id.shippingPrice);
        totalPrice = findViewById(R.id.totalPrice);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newCart = arrayNoDuplicates(productCart);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String methodPay = spinnerPayment.getSelectedItem().toString();
                if (methodPay.equals("Elige tu metodo de pago")) {
                    methodPay = "En mano";
                }
                Order order = new Order(thisBusiness.getId(), thisBusiness, userDetails.getInt("userId", 0), paymentMethod, methodPay, thisBusiness.getShippingPrice());
                //orderProductsPresenter.addOrder(order);
                Toast.makeText(getApplicationContext(), "NOT IMPLEMENTED", Toast.LENGTH_SHORT).show();
            }
        });
        nameBusiness.setText("PEDIR EN " + thisBusiness.getName().toUpperCase());
        double productsPrice = 0;
        for (
                Product p : productCart) {
            productsPrice += p.getPrice();
        }
        productPrice.setText(String.format("%.2f", productsPrice).replace('.', ',') + " €");
        shippingPrice.setText(String.format("%.2f", thisBusiness.getShippingPrice()).replace('.', ',') + " €");
        totalPrice.setText(String.format("%.2f", productsPrice + thisBusiness.getShippingPrice()).replace('.', ',') + " €");

        initUI();

    }

    private void initUI() {
        productsRecycler = findViewById(R.id.ProductsRecycler);

        cartAdapter = new CartAdapter(this, newCart);

        mLayoutManager = new LinearLayoutManager(this);
        productsRecycler.setLayoutManager(mLayoutManager);
        productsRecycler.setItemAnimator(new DefaultItemAnimator());
        productsRecycler.setAdapter(cartAdapter);
    }

    private ArrayList<Product> arrayNoDuplicates(ArrayList<Product> cart) {
        ArrayList<Product> newCart = new ArrayList<>();
        Map<Integer, Integer> noDuplicates = new HashMap<>();

        int i = -1;
        for (Product p : cart) {
            if (noDuplicates.containsKey(p.getId())) {
                Product product = new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), newCart.get(noDuplicates.get(p.getId())).getQuantity(), p.getBusinessId());
                product.setPrice(product.getPrice() + newCart.get(noDuplicates.get(p.getId())).getPrice());
                product.setQuantity(product.getQuantity() + 1);
                newCart.set(noDuplicates.get(p.getId()), product);
            } else {
                i++;
                noDuplicates.put(p.getId(), i);
                Product product = new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getQuantity(), p.getBusinessId());
                product.setQuantity(1);
                newCart.add(product);
            }
        }
        return newCart;
    }

    @Override
    public void successAddOrder(Order order) {
        orderProductsPresenter.addOrderLine(order.getId(), newCart);
    }

    @Override
    public void failureAddOrder(Throwable t) {
        Toast.makeText(this, "Error al meter Order", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void successAddOrderLine(Order order) {
        Order updateOrder = new Order(order.getId(), order.getPriceTotal());
        orderProductsPresenter.updateOrder(updateOrder);
    }

    @Override
    public void failureAddOrderLine(Throwable t) {
        Toast.makeText(this, "Error al meter OrderLine", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void successUpdateOrder(Order order) {
        Toast.makeText(this, "Pedido finalizado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void failureUpdateOrder(Throwable t) {
        Toast.makeText(this, "Error al actualizar Order", Toast.LENGTH_SHORT).show();

    }
}
