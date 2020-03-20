package com.example.mvpexample;

public class MainPresenterImpl implements MainContract.Presenter {
    MainContract.View mView;

    @Override
    public void attachView(MainContract.View v) {
        this.mView = v;
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void login(String username, String password) {
        if(username.equals("test") && password.equals("123")){
            mView.onLoginSuccess();
        } else{
            mView.onLoginFailed();
        }
    }
}
