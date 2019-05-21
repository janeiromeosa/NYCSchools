package com.example.nychighschools.di.scores;

import com.example.nychighschools.di.app.AppComponent;
import com.example.nychighschools.scores.DetailsActivity;
import dagger.Component;

@Component(modules = ScoreDetailsModule.class, dependencies = AppComponent.class)
@ScoreDetailsScope
public interface ScoreDetailsComponent {
    void inject(DetailsActivity detailsActivity);
}
