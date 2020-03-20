package com.example.mvpexample;

public interface MainContract {
    interface View {
        void onLoginSuccess();
        void onLoginFailed();
    }
    interface Presenter {
        void attachView(View v);
        void dropView();
        void login(String username,String password);
    }
}
