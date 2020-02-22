package com.jhernando.glovo.business;

import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.User;
import com.jhernando.glovo.network.ApiClient;
import com.jhernando.glovo.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class businessModel implements businessContract.Model {

    @Override
    public void getBusinesses(final OnBusinessListener OnBusinessListener, final int id) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Business>> call = apiService.getBusinesses(id);
        call.enqueue(new Callback<ArrayList<Business>>() {
            @Override
            public void onResponse(Call<ArrayList<Business>> call, Response<ArrayList<Business>> response) {
                ArrayList<Business> businesses = response.body();
                OnBusinessListener.onFinished(businesses);
            }

            @Override
            public void onFailure(Call<ArrayList<Business>> call, Throwable t) {
                OnBusinessListener.onFailure(null);
            }
        });
    }

    @Override
    public void getSearchBusinessesCategory(final OnBusinessListener OnBusinessListener, int id, String name) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Business>> call = apiService.getSearchBusinessesCategory(id, name);
        call.enqueue(new Callback<ArrayList<Business>>() {
            @Override
            public void onResponse(Call<ArrayList<Business>> call, Response<ArrayList<Business>> response) {
                ArrayList<Business> businesses = response.body();
                OnBusinessListener.onFinished(businesses);
            }

            @Override
            public void onFailure(Call<ArrayList<Business>> call, Throwable t) {
                OnBusinessListener.onFailure(null);
            }
        });
    }

    @Override
    public void getSearchBusinesses(final OnBusinessListener OnBusinessListener, String name) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Business>> call = apiService.getSearchBusinesses(name);
        call.enqueue(new Callback<ArrayList<Business>>() {
            @Override
            public void onResponse(Call<ArrayList<Business>> call, Response<ArrayList<Business>> response) {
                ArrayList<Business> businesses = response.body();
                OnBusinessListener.onFinished(businesses);
            }

            @Override
            public void onFailure(Call<ArrayList<Business>> call, Throwable t) {
                OnBusinessListener.onFailure(null);
            }
        });
    }
}
