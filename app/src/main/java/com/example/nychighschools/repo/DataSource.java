package com.example.nychighschools.repo;

import com.example.nychighschools.data.SATScores;
import com.example.nychighschools.data.SchoolsResponse;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface DataSource {

    Single<List<SchoolsResponse>> getSchoolsSearch();
    void addAlbum(SchoolsResponse albumsResponse);

    Single<List<SATScores>> getScoresSearch(String dbn);
    void addAlbum(SATScores satScores);
}
