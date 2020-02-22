package com.jhernando.glovo.userProfile;

import com.jhernando.glovo.model.User;
import com.jhernando.glovo.userRegister.userRegisterModel;

public class userProfilePresenter implements userProfileContract.Presenter {
    private userProfileContract.View userProfileView;
    private userProfileModel userProfileModel;


    public userProfilePresenter(userProfileContract.View userProfileView) {
        this.userProfileView = userProfileView;
        userProfileModel = new userProfileModel();
    }

    @Override
    public void checkUpdate(User user) {
        userProfileModel.getUserUpdate(
                new userProfileContract.Model.OnUpdateListener() {
                    @Override
                    public void onFinished(User user) {
                        userProfileView.successUpdate(user);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        userProfileView.failureUpdate(t);
                    }
                }, user);
    }
}
