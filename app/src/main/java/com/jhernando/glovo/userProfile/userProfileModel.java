package com.jhernando.glovo.userProfile;

import com.jhernando.glovo.model.User;
import com.jhernando.glovo.network.ApiClient;
import com.jhernando.glovo.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userProfileModel implements userProfileContract.Model {

    @Override
    public void getUserUpdate(final OnUpdateListener OnUpdateListener, final User user) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiService.userUpdate(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                OnUpdateListener.onFinished(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                OnUpdateListener.onFailure(null);
            }
        });
    }
}
