package com.example.nychighschools.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import com.example.nychighschools.data.SchoolsResponse;
import com.example.nychighschools.repo.DataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private ObservableBoolean progressObservable;
    private MutableLiveData<List<SchoolsResponse>> schoolsObservable;
    private final DataSource schoolsRepository;
    private CompositeDisposable compositeDisposable;


    public HomeViewModel(DataSource schoolsRepository) {
        this.schoolsObservable = new MutableLiveData<>();
        this.progressObservable = new ObservableBoolean();
        this.schoolsRepository = schoolsRepository;
        this.compositeDisposable = new CompositeDisposable();
    }
    public ObservableBoolean getProgressObservable(){return progressObservable;}

    public LiveData<List<SchoolsResponse>> getSchoolsObservable(){return schoolsObservable;}

    public void performSearch() {
        compositeDisposable.add(schoolsRepository.getSchoolsSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> progressObservable.set(true))
                .doOnEvent((success, failure) -> progressObservable.set(false))
                .subscribe(schoolsObservable::setValue, throwable -> throwable.printStackTrace()));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
