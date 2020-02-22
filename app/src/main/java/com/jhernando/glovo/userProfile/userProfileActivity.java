package com.jhernando.glovo.userProfile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jhernando.glovo.MainActivity;
import com.jhernando.glovo.R;
import com.jhernando.glovo.md5.Md5;
import com.jhernando.glovo.model.User;
import com.jhernando.glovo.userLogin.userLoginActivity;

import static android.content.Context.MODE_PRIVATE;

public class userProfileActivity extends Fragment implements userProfileContract.View {
    private userProfilePresenter userProfilePresenter;
    private SharedPreferences userDetails;
    TextView username;
    TextView tlfn;
    TextView email;
    TextView pass;
    TextView passConfirm;
    Button btnUpdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile_fragment, null);
        userProfilePresenter = new userProfilePresenter(this);

        username = view.findViewById(R.id.nickChange);
        tlfn = view.findViewById(R.id.tlfnChange);
        email = view.findViewById(R.id.emailChange);
        pass = view.findViewById(R.id.passChange);
        passConfirm = view.findViewById(R.id.confirmPassChange);

        btnUpdate = view.findViewById(R.id.buttonUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goUpdate();
            }
        });

        userDetails = getContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        username.setText(userDetails.getString("username", null));
        tlfn.setText(userDetails.getString("tlfn", null));

        return view;
    }

    private void goUpdate() {
        userDetails = getContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        if (TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(pass.getText()) || TextUtils.isEmpty(passConfirm.getText())) {
            Toast.makeText(getContext(), "Todos los campos requeridos", Toast.LENGTH_SHORT).show();
        } else {
            if (pass.getText().toString().equals(passConfirm.getText().toString())) {
                if (username.getText().toString().equals(userDetails.getString("username", null)) && tlfn.getText().toString().equals(userDetails.getString("tlfn", null))) {
                    Toast.makeText(getContext(), "No hay cambios en tus datos", Toast.LENGTH_SHORT).show();
                } else {
                    String userPass = Md5.md5(pass.getText().toString());
                    if (userPass.equals(userDetails.getString("pass", null)) && email.getText().toString().equals(userDetails.getString("email", null))) {
                        User user = new User(userDetails.getInt("userId", 0), null, username.getText().toString(), null, tlfn.getText().toString());
                        userProfilePresenter.checkUpdate(user);
                    } else {
                        Toast.makeText(getContext(), "Email o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(getContext(), "Las contraseñas no coindicen", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void successUpdate(User user) {
        if (user.getId() > 0) {
            Toast.makeText(getContext(), "Datos actualizados", Toast.LENGTH_SHORT).show();
            SharedPreferences userDetails = getContext().getSharedPreferences("userdetails", MODE_PRIVATE);
            SharedPreferences.Editor edit = userDetails.edit();
            edit.putInt("userId", user.getId());
            edit.putString("username", user.getNickname());
            edit.putString("email", user.getEmail());
            edit.putString("pass", user.getPassword());
            edit.putString("tlfn", user.getTlfn());
            edit.apply();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), user.getNickname(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failureUpdate(Throwable t) {
        Toast.makeText(getContext(), "Fallo al actualizar", Toast.LENGTH_SHORT).show();

    }
}
