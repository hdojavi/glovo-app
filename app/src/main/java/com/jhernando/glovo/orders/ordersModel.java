package com.jhernando.glovo.orders;

import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.network.ApiClient;
import com.jhernando.glovo.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ordersModel implements ordersContract.Model {

    @Override
    public void getOrders(final OnOrdersListener OnOrdersListener, final int userId) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Order>> call = apiService.getOrdersUser(userId);
        call.enqueue(new Callback<ArrayList<Order>>() {
            @Override
            public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                ArrayList<Order> orders = response.body();
                OnOrdersListener.onFinished(orders);
            }

            @Override
            public void onFailure(Call<ArrayList<Order>> call, Throwable t) {
                OnOrdersListener.onFailure(null);
            }
        });
    }
}
