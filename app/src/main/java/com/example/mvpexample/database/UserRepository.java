package com.example.mvpexample.database;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Viet Hua on 04/25/2020.
 */
public class UserRepository {
    private UserDao userDao;
    private UserDatabase userDatabase;

    public UserRepository(Context context) {
        userDatabase = UserDatabase.getInstance(context);
        this.userDao = userDatabase.userDao();
    }


    public Completable login(final String email, final String password){
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
              if(userDao.isLoginSuccess(email,password) == 1){
                  emitter.onComplete();
              } else {
                  emitter.onError(new Throwable());
              }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
