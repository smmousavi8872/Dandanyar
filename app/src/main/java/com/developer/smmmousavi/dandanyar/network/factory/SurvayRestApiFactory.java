package com.developer.smmmousavi.dandanyar.network.factory;


import com.developer.smmmousavi.dandanyar.constants.Constants;
import com.developer.smmmousavi.dandanyar.network.util.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SurvayRestApiFactory {

    private SurvayRestApiFactory() {
    }

    public static com.developer.smmmousavi.dandanyar.network.api.SurvayRestApi create() {
        OkHttpClient client = provideOkHttpClientInstance();
        Retrofit retrofit = provideRetrofitInstance(client);
        return retrofit.create(com.developer.smmmousavi.dandanyar.network.api.SurvayRestApi.class);
    }

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

    static Retrofit provideRetrofitInstance(OkHttpClient httpClient) {
        return new Retrofit.Builder()
            .baseUrl(Constants.SURVAY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(new LiveDataCallAdapterFactory())
            .client(httpClient)
            .build();
    }
}
