package com.jhernando.glovo.userProfile;

import android.widget.TabHost;

import com.jhernando.glovo.model.User;

public interface userProfileContract {

    interface Model {
        interface OnUpdateListener {
            void onFinished(User user);

            void onFailure(Throwable t);
        }

        void getUserUpdate(
                OnUpdateListener OnUpdateListener,
                User user);
    }

    interface View {
        void successUpdate(User user);

        void failureUpdate(Throwable t);

    }

    interface Presenter {
        void checkUpdate(User user);
    }

}
