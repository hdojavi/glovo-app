package com.jhernando.glovo.userLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jhernando.glovo.MainActivity;
import com.jhernando.glovo.R;
import com.jhernando.glovo.model.User;
import com.jhernando.glovo.userRegister.userRegisterActivity;

import java.security.MessageDigest;

import static com.jhernando.glovo.md5.Md5.md5;

public class userLoginActivity extends AppCompatActivity implements userLoginContract.View {

    Button btnLogin;
    Button btnRegister;
    private userLoginPresenter userLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userLoginPresenter = new userLoginPresenter(this);
        btnLogin = findViewById(R.id.buttonLoginL);
        btnRegister = findViewById(R.id.buttonRegisterL);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLogin();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });

    }

    private void goLogin() {
        EditText email = findViewById(R.id.emailLogin);
        EditText password = findViewById(R.id.passLogin);

        User user = new User(email.getText().toString(), md5(password.getText().toString()));
        userLoginPresenter.checkLogin(user);
    }

    private void goRegister() {
        Intent intent = new Intent(userLoginActivity.this, userRegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void successLogin(User user) {
        if (user.getId() > 0) {
            SharedPreferences userDetails = getSharedPreferences("userdetails", MODE_PRIVATE);
            SharedPreferences.Editor edit = userDetails.edit();
            edit.putInt("userId", user.getId());
            edit.putString("username", user.getNickname());
            edit.putString("email", user.getEmail());
            edit.putString("pass", user.getPassword());
            edit.putString("tlfn", user.getTlfn());

            edit.apply();

            Toast.makeText(this, "Usuario correcto", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failureLogin(Throwable t) {
        Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();

    }

}
