package com.example.nychighschools.scores;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.nychighschools.data.SATScores;
import com.example.nychighschools.data.SchoolsResponse;
import com.example.nychighschools.repo.DataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class DetailsViewModel extends ViewModel {

    private final DataSource schoolsRepository;
    private MutableLiveData<List<SATScores>> scoresObservable;
    private CompositeDisposable compositeDisposable;

    public DetailsViewModel(DataSource schoolsRepository) {
        this.schoolsRepository = schoolsRepository;
        scoresObservable = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    public LiveData<List<SATScores>> getScoresObservable() {
        return scoresObservable;
    }

    public void getSATScoresDetails(String dbn){
        compositeDisposable.add(schoolsRepository.getScoresSearch(dbn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scoresObservable::setValue));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
