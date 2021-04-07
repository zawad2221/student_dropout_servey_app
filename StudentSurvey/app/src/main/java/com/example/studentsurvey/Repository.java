package com.example.studentsurvey;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.studentsurvey.api.Api;
import com.example.studentsurvey.api.ApiComponent;
import com.example.studentsurvey.api.ApiModule;
import com.example.studentsurvey.api.DaggerApiComponent;
import com.example.studentsurvey.model.StudentDetails;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static Repository instance;
    public static Repository getInstance(){
        if(instance==null){
            instance = new Repository();
        }
        return instance;
    }

    public MediatorLiveData<StudentDetails> saveInfo(Context context, StudentDetails studentDetails){
        ApiComponent apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule(context))
                .build();
        Api api = apiComponent.getApi();
        MediatorLiveData<StudentDetails> result = new MediatorLiveData<>();

        Call<StudentDetails> call = api.saveInfo(studentDetails);
        call.enqueue(new Callback<StudentDetails>() {
            @Override
            public void onResponse(Call<StudentDetails> call, Response<StudentDetails> response) {
                if(response.isSuccessful()){
                    result.setValue(response.body());
                    Log.d(context.getString(R.string.DEBUGING_TAG),"success result");
                }
            }

            @Override
            public void onFailure(Call<StudentDetails> call, Throwable t) {
                Log.d(context.getString(R.string.DEBUGING_TAG),"success failed: "+t.getMessage());
                StudentDetails studentDetail = new StudentDetails();
                studentDetail.setResult(-1);
                result.setValue(studentDetail);
            }
        });

        return result;
    }
}
