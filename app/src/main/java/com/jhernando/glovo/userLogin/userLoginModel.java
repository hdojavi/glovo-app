package com.jhernando.glovo.userLogin;

import com.jhernando.glovo.model.User;
import com.jhernando.glovo.network.ApiClient;
import com.jhernando.glovo.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userLoginModel implements userLoginContract.Model {

    @Override
    public void getUserLogin(final OnLoginListener OnLoginListener, final User user) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiService.userLogin(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                OnLoginListener.onFinished(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                OnLoginListener.onFailure(null);
            }
        });
    }
}
