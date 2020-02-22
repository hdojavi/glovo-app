package com.jhernando.glovo.orderDetails;

import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.OrderLine;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.network.ApiClient;
import com.jhernando.glovo.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class orderLinesModel implements orderLinesContract.Model {

    @Override
    public void getOrderLines(final OnGetOrderLineListener OnGetOrderLineListener, final int orderId) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<OrderLine>> call = apiService.getOrderLines(orderId);
        call.enqueue(new Callback<ArrayList<OrderLine>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderLine>> call, Response<ArrayList<OrderLine>> response) {
                ArrayList<OrderLine> orderLines = response.body();
                OnGetOrderLineListener.onFinished(orderLines);
            }

            @Override
            public void onFailure(Call<ArrayList<OrderLine>> call, Throwable t) {
                OnGetOrderLineListener.onFailure(null);
            }
        });
    }

}
