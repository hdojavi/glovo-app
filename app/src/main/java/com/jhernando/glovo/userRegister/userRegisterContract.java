package com.jhernando.glovo.userRegister;

import com.jhernando.glovo.model.User;

public interface userRegisterContract {

    interface Model {
        interface OnRegisterListener {
            void onFinished(User user);

            void onFailure(Throwable t);
        }

        void getUserRegister(
                OnRegisterListener OnRegisterListener,
                User user);
    }

    interface View {
        void successRegister(User user);

        void failureRegister(Throwable t);

    }

    interface Presenter {
        void checkRegister(User user);
    }

}
