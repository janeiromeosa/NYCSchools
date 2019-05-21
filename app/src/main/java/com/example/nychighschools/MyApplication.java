package com.example.nychighschools;

import android.app.Application;
import com.example.nychighschools.di.app.AppComponent;
import com.example.nychighschools.di.app.DaggerAppComponent;

public class MyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

    }
    public AppComponent getAppComponent(){
        return appComponent;
    }

}
