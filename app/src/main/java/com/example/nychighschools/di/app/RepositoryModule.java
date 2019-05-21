package com.example.nychighschools.di.app;

import com.example.nychighschools.repo.DataSource;
import com.example.nychighschools.repo.SchoolsRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    @Repository
    public DataSource provideRepository(@Remote DataSource remoteDataSource) {
        return new SchoolsRepository(remoteDataSource);
    }
}
