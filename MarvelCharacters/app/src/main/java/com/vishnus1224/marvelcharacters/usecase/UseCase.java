package com.vishnus1224.marvelcharacters.usecase;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Base class for a UseClass. Every UseCase class will extend this class and define functionality for the
 * buildUseCase method.
 * Created by Vishnu on 4/29/2016.
 */
public abstract class UseCase {

    /**
     * Reference to the subscription.
     */
    private Subscription subscription;

    /**
     * Subclasses should provide a concrete implementation for this method.
     * @return Observable that emits data required by the use case subclass.
     */
    protected abstract Observable buildUseCase();

    /**
     * Executes the use case on an IO thread and returns the result on the main thread.
     * @param subscriber Subscriber listening to the Observable.
     */
    public void execute(Subscriber subscriber){

        subscription = buildUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * Unsubscribe the current subscription.
     */
    public void unSubscribe(){

        if(subscription != null && !subscription.isUnsubscribed()){

            subscription.unsubscribe();
        }

    }
}
