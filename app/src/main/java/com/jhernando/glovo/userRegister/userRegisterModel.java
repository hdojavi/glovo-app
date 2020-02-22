package com.jhernando.glovo.userRegister;

import com.jhernando.glovo.model.User;
import com.jhernando.glovo.network.ApiClient;
import com.jhernando.glovo.network.ApiInterface;
import com.jhernando.glovo.userProfile.userProfileContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userRegisterModel implements userRegisterContract.Model {

    @Override
    public void getUserRegister(final OnRegisterListener OnRegisterListener, final User user) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiService.userRegister(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                OnRegisterListener.onFinished(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                OnRegisterListener.onFailure(null);

            }
        });
    }
}
