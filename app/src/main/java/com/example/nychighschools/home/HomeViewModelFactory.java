package com.example.nychighschools.home;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.example.nychighschools.repo.DataSource;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private final DataSource schoolsRepository;

    public HomeViewModelFactory(DataSource schoolsRepository) {
        this.schoolsRepository = schoolsRepository;
    }

    @NonNull //keeps the instance of HomeViewModel
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(HomeViewModel.class)){
            return (T) new HomeViewModel(schoolsRepository );
        }throw new IllegalArgumentException("modelClass has to be of type"
                + HomeViewModel.class.getSimpleName());
    }
}
