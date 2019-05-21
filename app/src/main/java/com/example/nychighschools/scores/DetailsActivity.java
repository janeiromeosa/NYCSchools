package com.example.nychighschools.scores;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.BindView;
import com.example.nychighschools.Constants;
import com.example.nychighschools.MyApplication;
import com.example.nychighschools.R;
import com.example.nychighschools.data.SATScores;
import com.example.nychighschools.databinding.ActivityMainBinding;
import com.example.nychighschools.databinding.ItemScoresBinding;
import com.example.nychighschools.di.scores.DaggerScoreDetailsComponent;
import com.example.nychighschools.di.scores.ScoreDetailsModule;

import javax.inject.Inject;

public class DetailsActivity extends AppCompatActivity {


    @Inject
    DetailsViewModel detailsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ItemScoresBinding itemScoresBinding = DataBindingUtil
                .setContentView(this, R.layout.item_scores);

        DaggerScoreDetailsComponent.builder()
                .appComponent(((MyApplication)getApplication()).getAppComponent())
                .scoreDetailsModule(new ScoreDetailsModule(this))
                .build()
                .inject(this);

//        itemScoresBinding.setScores(new SATScores());

        String dbn = getIntent().getStringExtra(Constants.KEY_SCHOOL_SCORES);

        detailsViewModel.getSATScoresDetails(dbn);
        detailsViewModel.getAlbumsObservable()
                .observe(this, data -> itemScoresBinding.setScores(new SATScores()));
    }
}
