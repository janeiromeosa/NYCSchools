package com.example.nychighschools.di.app;

import android.app.Application;
import com.example.nychighschools.repo.DataSource;
import dagger.BindsInstance;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {NetworkModule.class, RepositoryModule.class})
@Singleton
public interface AppComponent {

    @Repository
    DataSource repository();

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);
    }
}
