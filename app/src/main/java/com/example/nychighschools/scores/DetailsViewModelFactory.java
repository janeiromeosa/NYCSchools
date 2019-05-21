package com.example.nychighschools.scores;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.example.nychighschools.repo.DataSource;


public class DetailsViewModelFactory implements ViewModelProvider.Factory {

    private final DataSource schoolsRepository;

    public DetailsViewModelFactory(DataSource schoolsRepository) {
        this.schoolsRepository = schoolsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(schoolsRepository);
        }
        throw new IllegalArgumentException("modelClass has to be of type "
                + DetailsViewModel.class.getSimpleName());
    }
}
