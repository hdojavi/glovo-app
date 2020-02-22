package com.jhernando.glovo.orderProducts;

import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.network.ApiClient;
import com.jhernando.glovo.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class orderProductsModel implements orderProductsContract.Model {

    @Override
    public void addOrder(final OnAddOrderListener OnAddOrderListener, final Order order) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Order> call = apiService.addOrders(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Order order = response.body();
                OnAddOrderListener.onFinished(order);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                OnAddOrderListener.onFailure(null);
            }
        });
    }


    @Override
    public void addOrderLine(final OnAddOrderLineListener OnAddOrderLineListener, int id,  ArrayList<Product> products) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Order> call = apiService.addOrdersLine(id, products);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Order order = response.body();
                OnAddOrderLineListener.onFinished(order);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                OnAddOrderLineListener.onFailure(null);
            }
        });
    }

    @Override
    public void updateOrder(final OnUpdateOrderListener OnUpdateOrderListener, Order order) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Order> call = apiService.orderUpdate(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Order order = response.body();
                OnUpdateOrderListener.onFinished(order);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                OnUpdateOrderListener.onFailure(null);
            }
        });
    }
}
