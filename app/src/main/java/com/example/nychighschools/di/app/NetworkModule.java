package com.example.nychighschools.di.app;


import android.app.Application;
import com.example.nychighschools.Constants;
import com.example.nychighschools.net.SchoolsService;
import com.example.nychighschools.repo.DataSource;
import com.example.nychighschools.repo.RemoteDataSource;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

import static com.example.nychighschools.Constants.API_TIMEOUT;
import static com.example.nychighschools.Constants.CACHE_SIZE;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Cache provideCache(Application application){
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {//Logger
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOKHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor)  {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }
    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public SchoolsService provideAlbumsService(Retrofit retrofit) {
        return retrofit.create(SchoolsService.class);
    }

    @Provides
    @Singleton
    @Remote
    public DataSource provideRemoteDataSource(SchoolsService schoolsService) {
        return new RemoteDataSource(schoolsService);
    }


}
