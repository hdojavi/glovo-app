package com.jhernando.glovo.userRegister;

import com.jhernando.glovo.model.User;
import com.jhernando.glovo.userProfile.userProfileContract;

public class userRegisterPresenter implements userRegisterContract.Presenter {
    private userRegisterContract.View userRegisterView;
    private userRegisterModel userRegisterModel;


    public userRegisterPresenter(userRegisterContract.View userRegisterView) {
        this.userRegisterView = userRegisterView;
        userRegisterModel = new userRegisterModel();
    }

    @Override
    public void checkRegister(User user) {
        userRegisterModel.getUserRegister(
                new userRegisterContract.Model.OnRegisterListener() {
                    @Override
                    public void onFinished(User user) {
                        userRegisterView.successRegister(user);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        userRegisterView.failureRegister(t);

                    }
                }, user);
    }
}
