package com.example.nychighschools.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import com.example.nychighschools.Constants;
import com.example.nychighschools.MyApplication;
import com.example.nychighschools.R;
import com.example.nychighschools.data.SATScores;
import com.example.nychighschools.data.SchoolsResponse;
import com.example.nychighschools.databinding.ActivityMainBinding;
import com.example.nychighschools.di.home.DaggerHomeComponent;
import com.example.nychighschools.di.home.HomeViewModule;
import com.example.nychighschools.scores.DetailsActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements OnSchoolSelected {

    @Inject
    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        //utility class to create viewDataBinding from layouts

        DaggerHomeComponent.builder()
                .appComponent(((MyApplication)getApplication()).getAppComponent())
                .homeViewModule(new HomeViewModule(this))
                .build()
                .inject(this);

        activityMainBinding.setProgressVisibility(homeViewModel.getProgressObservable());
        //i seriously dont know what this does

        SchoolsAdapter schoolsAdapter = new SchoolsAdapter(this);
        //creates a new instance of adapter
        activityMainBinding.rvResults.setLayoutManager(new LinearLayoutManager(this));
        //layout manager created linear layout and passes context so that view can be created in UI
        activityMainBinding.rvResults.setAdapter(schoolsAdapter);
        //sets up link between adapter and generated class

        homeViewModel.getSchoolsObservable()
                .observe(this, data ->  schoolsAdapter.setData(data));

        //observes/subscribes to observarable in homeviewmodel and passes the list data from
        // there to the recycler view
        homeViewModel.performSearch();

    }

    @Override
    public void onSchoolSelected(SchoolsResponse schoolsResponse) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.KEY_SCHOOL_SCORES, schoolsResponse.getDbn());
        startActivity(intent);
    }
}
