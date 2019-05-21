package com.example.nychighschools.repo;

import com.example.nychighschools.data.SchoolsResponse;
import com.example.nychighschools.data.SATScores;
import com.example.nychighschools.net.SchoolsService;
import io.reactivex.Single;

import java.util.List;

public class RemoteDataSource implements DataSource  {

    private final SchoolsService schoolsService;

    public RemoteDataSource(SchoolsService schoolsService) {
        this.schoolsService = schoolsService;
    }

    @Override
    public Single<List<SchoolsResponse>> getSchoolsSearch() {
        return schoolsService.getSchoolList();
    }

    @Override
    public void addAlbum(SchoolsResponse albumsResponse) {

    }

    @Override
    public Single<List<SATScores>> getScoresSearch(String dbn) {
        return schoolsService.getSATScores(dbn);
    }


    @Override
    public void addAlbum(SATScores satScores) {

    }
}
