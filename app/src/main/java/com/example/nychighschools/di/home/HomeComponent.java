package com.example.nychighschools.di.home;

import com.example.nychighschools.di.app.AppComponent;
import com.example.nychighschools.home.MainActivity;
import dagger.Component;

@Component(modules = HomeViewModule.class, dependencies = AppComponent.class)
@HomeViewScope //created a scope for the mainactivity and only the main component
public interface HomeComponent {
    void inject(MainActivity mainActivity);

}
