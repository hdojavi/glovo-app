package com.jhernando.glovo.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jhernando.glovo.MainActivity;
import com.jhernando.glovo.R;

import static android.content.Context.MODE_PRIVATE;

public class UserSettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_usersettings_fragment, null);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences userDetails = getContext().getSharedPreferences("userdetails", MODE_PRIVATE);
                SharedPreferences.Editor editor = userDetails.edit();
                editor.clear().commit();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
