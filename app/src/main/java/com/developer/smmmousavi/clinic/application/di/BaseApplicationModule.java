package com.developer.smmmousavi.clinic.application.di;


import com.developer.smmmousavi.clinic.constants.Constants;
import com.developer.smmmousavi.clinic.network.util.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BaseApplicationModule {

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClientInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
            // connection timeout: establish connection to server
            // read timeout between each byte read from server
            // write timeout between each byte sent to server
            .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(false)
            .addInterceptor(logging)
            .build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofitInstance(OkHttpClient httpClient) {
        return new Retrofit.Builder()
            .baseUrl(Constants.FLOWCHART_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(new LiveDataCallAdapterFactory())
            .client(httpClient)
            .build();
    }

}