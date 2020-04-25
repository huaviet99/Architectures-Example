package com.example.mvpexample.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpexample.R;

import io.reactivex.observers.DisposableCompletableObserver;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private EditText edtEmail, edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        setupViewById();
        btnLogin.setOnClickListener(onClickListener);

    }

    private void setupViewById() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            mainViewModel.login(email, password).subscribeWith(new LoginObserver());
        }
    };

    private class LoginObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();

        }
    }

}
