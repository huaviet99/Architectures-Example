package com.example.mvpexample.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Viet Hua on 04/25/2020.
 */
@Database(entities = UserModel.class, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String TAG = UserDatabase.class.getSimpleName();
    public static UserDatabase instance;

    public abstract UserDao userDao();

    public static UserDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            final UserDao userDao = instance.userDao();
            final Completable completable = Completable.create(new CompletableOnSubscribe() {
                @Override
                public void subscribe(CompletableEmitter emitter) throws Exception {
                    userDao.insert(new UserModel("test1@gmail.com", "123"));
                    userDao.insert(new UserModel("test2@gmail.com", "123"));
                    userDao.insert(new UserModel("test3@gmail.com", "123"));
                    userDao.insert(new UserModel("test4@gmail.com", "123"));
                    userDao.insert(new UserModel("test5@gmail.com", "123"));
                    emitter.onComplete();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            compositeDisposable.add(completable.subscribeWith(new UserObserver()));
        }
    };

    private static class UserObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
