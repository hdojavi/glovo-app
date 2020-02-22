package com.jhernando.glovo.userLogin;

import com.jhernando.glovo.model.User;

public interface userLoginContract {

    interface Model {
        interface OnLoginListener {
            void onFinished(User user);

            void onFailure(Throwable t);
        }

        void getUserLogin(
                OnLoginListener OnLoginListener,
                User user);
    }

    interface View {
        void successLogin(User user);

        void failureLogin(Throwable t);

    }

    interface Presenter {
        void checkLogin(User user);
    }

}
