package com.example.nychighschools.net;

import com.example.nychighschools.data.SATScores;
import com.example.nychighschools.data.SchoolsResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

import static com.example.nychighschools.Constants.NYC_SCHOOLS_ENDPOINT;
import static com.example.nychighschools.Constants.SAT_SCORES;

public interface SchoolsService  {

    @GET(NYC_SCHOOLS_ENDPOINT)
    Single<List<SchoolsResponse>>getSchoolList();

    @GET(SAT_SCORES)
    Single<List<SATScores>>getSATScores(@Query("dbn") String dbn);
}
