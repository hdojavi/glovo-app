package com.jhernando.glovo.userLogin;

import com.jhernando.glovo.model.User;

public class userLoginPresenter implements userLoginContract.Presenter {
    private userLoginContract.View userView;
    private userLoginModel userLoginModel;


    public userLoginPresenter(userLoginContract.View userView) {
        this.userView = userView;
        userLoginModel = new userLoginModel();
    }

    @Override
    public void checkLogin(User user) {
        userLoginModel.getUserLogin(
                new userLoginContract.Model.OnLoginListener() {
                    @Override
                    public void onFinished(User user) {
                        userView.successLogin(user);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        userView.failureLogin(t);
                    }
                }, user);
    }
}
