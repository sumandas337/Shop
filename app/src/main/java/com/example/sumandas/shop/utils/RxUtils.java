package com.example.sumandas.shop.utils;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;


/**
 * Created by sumandas on 29/01/2017.
 */

public class RxUtils {

    public static <T> Observable<T> buildIO(Observable<T> observable) {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public static <T> Observable<T> buildComputation(Observable<T> observable) {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation());
    }


    public static <T> Observable<T> buildTest(Observable<T> observable) {
        return observable
                .observeOn(Schedulers.immediate())
                .subscribeOn(Schedulers.immediate());
    }


    public static void unSubscribe(CompositeSubscription subscription) {
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        subscription.unsubscribe();
    }


    @NonNull
    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> field) {
        return Observable.create(subscriber -> {
            subscriber.onNext(field.get());
            final android.databinding.Observable.OnPropertyChangedCallback callback = new android.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable observable, int i) {
                    subscriber.onNext(field.get());
                }
            };
            field.addOnPropertyChangedCallback(callback);
            subscriber.add(Subscriptions.create(() -> field.removeOnPropertyChangedCallback(callback)));
        });
    }


    public static Observable<String> fromView(EditText editText) {
        final PublishSubject<String> subject = PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                subject.onNext(s.toString());
            }
        });
        return subject;
    }

}
