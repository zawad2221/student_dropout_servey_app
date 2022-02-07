package com.example.studentsurvey.api;

import com.example.studentsurvey.model.StudentDetails;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @Headers({"Accept: application/json"})
    @POST("/predict/")
    Call<StudentDetails> saveInfo(@Body StudentDetails studentDetails);

}
