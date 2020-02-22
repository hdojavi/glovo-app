package com.jhernando.glovo.userRegister;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jhernando.glovo.R;
import com.jhernando.glovo.model.User;
import com.jhernando.glovo.userLogin.userLoginActivity;

public class userRegisterActivity extends AppCompatActivity implements userRegisterContract.View {

    Button btnRegister;
    Button btnLogin;

    private userRegisterPresenter userRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userRegisterPresenter = new userRegisterPresenter(this);
        btnRegister = findViewById(R.id.buttonRegisterR);
        btnLogin = findViewById(R.id.buttonLoginR);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLogin();
            }
        });
    }

    private void goLogin() {
        Intent intent = new Intent(this, userLoginActivity.class);
        startActivity(intent);
    }

    private void goRegister() {

        EditText email = findViewById(R.id.emailRegister);
        EditText nick = findViewById(R.id.nickRegister);
        EditText password = findViewById(R.id.passRegister);
        EditText confPassword = findViewById(R.id.confirmPassRegister);

        if (TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(nick.getText()) || TextUtils.isEmpty(password.getText()) || TextUtils.isEmpty(confPassword.getText())) {
            Toast.makeText(this, "Todos los campos requeridos", Toast.LENGTH_SHORT).show();
        } else {
            if (password.getText().toString().equals(confPassword.getText().toString())) {
                User user = new User(email.getText().toString(), nick.getText().toString(), password.getText().toString());
                userRegisterPresenter.checkRegister(user);
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void successRegister(User user) {
        Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, userLoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void failureRegister(Throwable t) {
        Toast.makeText(this, "Register fallido", Toast.LENGTH_SHORT).show();
    }
}
