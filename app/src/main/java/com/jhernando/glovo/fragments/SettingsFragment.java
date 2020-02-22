package com.jhernando.glovo.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jhernando.glovo.R;
import com.jhernando.glovo.userLogin.userLoginActivity;
import com.jhernando.glovo.userRegister.userRegisterActivity;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings_fragment, null);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        Button btnRegister = view.findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), userLoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), userRegisterActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
