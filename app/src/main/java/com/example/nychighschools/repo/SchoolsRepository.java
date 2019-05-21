package com.example.nychighschools.repo;

import com.example.nychighschools.data.SATScores;
import com.example.nychighschools.data.SchoolsResponse;
import io.reactivex.Single;

import java.util.List;

public class SchoolsRepository implements DataSource {

    private final DataSource remoteDataSource;

    public SchoolsRepository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Single<List<SchoolsResponse>> getSchoolsSearch() {
        return remoteDataSource.getSchoolsSearch();

    }

    @Override
    public void addAlbum(SchoolsResponse albumsResponse) {

    }

    @Override
    public Single<List<SATScores>> getScoresSearch(String dbn)
    {
        return remoteDataSource.getScoresSearch(dbn);
    }

    @Override
    public void addAlbum(SATScores satScores) {

    }
}
