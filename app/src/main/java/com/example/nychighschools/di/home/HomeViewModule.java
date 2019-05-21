package com.example.nychighschools.di.home;

import android.arch.lifecycle.ViewModelProviders;
import com.example.nychighschools.di.app.Repository;
import com.example.nychighschools.home.HomeViewModel;
import com.example.nychighschools.home.HomeViewModelFactory;
import com.example.nychighschools.home.MainActivity;
import com.example.nychighschools.repo.DataSource;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeViewModule {

    private final MainActivity mainActivity;

    public HomeViewModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Provides
    @HomeViewScope
    public HomeViewModelFactory provideHomeViewModelFactory(@Repository DataSource schoolsRepository) {
        return new HomeViewModelFactory(schoolsRepository);
    }

    @Provides
    @HomeViewScope
    public HomeViewModel provideHomeViewModel(HomeViewModelFactory homeViewModelFactory){
        return ViewModelProviders.of(mainActivity, homeViewModelFactory).get(HomeViewModel.class);
    }

}
