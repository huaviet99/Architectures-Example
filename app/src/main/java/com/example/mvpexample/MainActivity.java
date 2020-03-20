package com.example.mvpexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter presenter;

    Button btnLogin;
    EditText edtUsername;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViewsById();
        btnLogin.setOnClickListener(onClickListener);

        presenter = new MainPresenterImpl();
    }

    private void setupViewsById() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            presenter.login(username, password);
        }
    };

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "LOGIN SUCCEED", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
    }
}
