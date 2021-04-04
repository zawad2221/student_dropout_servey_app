package com.example.studentsurvey.api;

import android.content.Context;

import com.example.studentsurvey.R;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    Context context;

    public ApiModule(Context context) {
        this.context = context;
    }

    @Provides
    public Api provideApi(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build().create(Api.class);
    }
}
