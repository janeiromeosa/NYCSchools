package com.example.nychighschools.di.scores;

import android.arch.lifecycle.ViewModelProviders;
import com.example.nychighschools.di.app.Repository;
import com.example.nychighschools.repo.DataSource;
import com.example.nychighschools.scores.DetailsActivity;
import com.example.nychighschools.scores.DetailsViewModel;
import com.example.nychighschools.scores.DetailsViewModelFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ScoreDetailsModule {

    private final DetailsActivity detailsActivity;

    public ScoreDetailsModule(DetailsActivity detailsActivity) {
        this.detailsActivity = detailsActivity;
    }


    @Provides
    @ScoreDetailsScope
    public DetailsViewModelFactory provideDetailsViewModelFactory(@Repository DataSource schoolsRepository) {
        return new DetailsViewModelFactory(schoolsRepository);
    }

    @Provides
    @ScoreDetailsScope
    public DetailsViewModel provideDetailsViewModel(DetailsViewModelFactory detailsViewModelFactory){
        return ViewModelProviders.of(detailsActivity, detailsViewModelFactory)
                .get(DetailsViewModel.class);
    }

}
