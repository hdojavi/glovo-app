package com.jhernando.glovo.product;

import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.network.ApiClient;
import com.jhernando.glovo.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class productsModel implements productsContract.Model {

    @Override
    public void getProducts(final OnProductsListener OnProductsListener, final int id) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Product>> call = apiService.getProducts(id);
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> products = response.body();
                OnProductsListener.onFinished(products);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                OnProductsListener.onFailure(null);
            }
        });
    }

    @Override
    public void getSearchProducts(final OnProductsListener OnProductsListener, int id, String name) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Product>> call = apiService.getSearchProducts(id, name);
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> products = response.body();
                OnProductsListener.onFinished(products);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                OnProductsListener.onFailure(null);
            }
        });
    }
}
