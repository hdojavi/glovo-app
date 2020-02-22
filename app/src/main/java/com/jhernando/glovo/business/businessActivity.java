package com.jhernando.glovo.business;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jhernando.glovo.MainActivity;
import com.jhernando.glovo.R;
import com.jhernando.glovo.adapter.BusinessAdapter;
import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.product.productsActivity;

import java.util.ArrayList;

public class businessActivity extends AppCompatActivity implements businessContract.View, businessItemClickListener {

    private businessPresenter businessPresenter;
    private RecyclerView businessRecycler;
    private ArrayList<Business> businesses;
    private BusinessAdapter businessAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView btnBack;
    private ImageView searchBtn;
    private TextView searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        btnBack = findViewById(R.id.arrowBack);

        searchBtn = findViewById(R.id.buttonSearch);
        searchQuery = findViewById(R.id.searchQuery);

        businessPresenter = new businessPresenter(this);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        final String query = intent.getStringExtra("query");
        if (query != null) {
            businessPresenter.searchBusinesses(query);
        } else {
            businessPresenter.checkBusinesses(id);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch(id);
            }
        });

        initUI();

        if (id == 1) {
            searchQuery.setHint(R.string.hint_searchSupermarket);
        } else if (id == 2) {
            searchQuery.setHint(R.string.hint_searchRestaurant);
        } else if (id == 3) {
            searchQuery.setHint(R.string.hint_searchBreakfast);
        } else {
            searchQuery.setHint(R.string.hint_search);
        }
    }

    private void initUI() {
        businessRecycler = findViewById(R.id.BusinessRecycler);

        businesses = new ArrayList<>();
        businessAdapter = new BusinessAdapter(this, businesses);

        mLayoutManager = new LinearLayoutManager(this);
        businessRecycler.setLayoutManager(mLayoutManager);
        businessRecycler.setItemAnimator(new DefaultItemAnimator());
        businessRecycler.setAdapter(businessAdapter);
    }


    private void goSearch(int id) {
        String query = searchQuery.getText().toString();
        if (query.isEmpty()) {
            businessPresenter.checkBusinesses(id);
        } else {
            businessPresenter.searchBusinessesCategory(id, query);
        }
    }

    @Override
    public void successBusiness(ArrayList<Business> businesses) {
        if (!businesses.isEmpty()) {
            this.businesses.clear();
            this.businesses.addAll(businesses);
            businessAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No hay coincidencias con tu busqueda...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failureBusiness(Throwable t) {
        Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successSearchBusinessesCategory(ArrayList<Business> businesses) {
        if (!businesses.isEmpty()) {
            this.businesses.clear();
            this.businesses.addAll(businesses);
            businessAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No hay coincidencias con tu busqueda...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failureSearchBusinessCategory(Throwable t) {
        Toast.makeText(this, "Error en la busqueda home...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successSearchBusiness(ArrayList<Business> businesses) {
        if (!businesses.isEmpty()) {
            this.businesses.clear();
            this.businesses.addAll(businesses);
            businessAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No hay coincidencias con tu busqueda...", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void failureSearchBusiness(Throwable t) {
        Toast.makeText(this, "Error en la busqueda...", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onBusinessItemClick(int position) {
        if (position == -1) {
            return;
        }
        Business business = businesses.get(position);
        Intent intent = new Intent(this, productsActivity.class);
        intent.putExtra("business", business);
        startActivity(intent);
    }
}
