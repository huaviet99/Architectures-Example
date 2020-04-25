package com.example.mvpexample.mvvm;

import android.content.Context;
import com.example.mvpexample.database.UserRepository;
import io.reactivex.Completable;

/**
 * Created by Viet Hua on 04/25/2020.
 */
public class MainViewModel {
    private UserRepository repository;

    public MainViewModel(Context context) {
        repository = new UserRepository(context);
    }


    Completable login(String email, String password) {
        return repository.login(email, password);
    }

}
