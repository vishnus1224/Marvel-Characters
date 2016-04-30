package com.vishnus1224.marvelcharacters.delegate;

import android.text.TextUtils;
import android.widget.SearchView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Delegate to notify the calling class when the query in the search view is changed.
 * Created by Vishnu on 4/30/2016.
 */
public class SearchViewQueryObservableDelegate {

    private Subscription subscription;

    @Inject
    public SearchViewQueryObservableDelegate() {

    }

    /**
     * Notify the subscriber of a query text change if the query has not changed for delayInMillis time interval.
     * @param searchView SearchView instance.
     * @param delayInMillis delay in milliseconds for which the query should not change.
     * @param subscriber Subscriber instance.
     */
    public void queryTextChangeObservable(final SearchView searchView, long delayInMillis, Subscriber<String> subscriber) {

        subscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {

                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        subscriber.onNext(s);

                        return true;
                    }
                });

            }
        }).debounce(delayInMillis, TimeUnit.MILLISECONDS)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .subscribe(subscriber);
    }


    /**
     * Unsubscribe the current subscription.
     */
    public void unSubscribe() {

        if (subscription != null && !subscription.isUnsubscribed()) {

            subscription.unsubscribe();

        }

    }
}
