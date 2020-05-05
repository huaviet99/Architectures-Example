package com.example.mvpexample.mvvm;

import android.content.Context;
import android.util.Log;

import com.example.mvpexample.database.UserRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Viet Hua on 04/25/2020.
 */
public class MainViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLoggedIn;
    private UserRepository repository;

    public MainViewModel() {
        isLoggedIn = new MutableLiveData<>();
    }

    public void setContext(Context context) {
        repository = new UserRepository(context);
    }

    public LiveData<Boolean> getIsLoggedIn(String email, String password) {
        repository.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("TEST","onComplete");
                        isLoggedIn.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TEST","onError");
                        isLoggedIn.setValue(false);
                    }
                });
        return isLoggedIn;
    }

}
