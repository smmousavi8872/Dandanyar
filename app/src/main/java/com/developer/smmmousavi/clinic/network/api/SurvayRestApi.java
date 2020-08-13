package com.developer.smmmousavi.clinic.network.api;

import com.developer.smmmousavi.clinic.network.bodies.PostUserQuestionBody;
import com.developer.smmmousavi.clinic.network.bodies.UserSignUpBody;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.CategoriesResponse;
import com.developer.smmmousavi.clinic.network.responses.FirstCategoryQuestionResponse;
import com.developer.smmmousavi.clinic.network.responses.PostQuestionResponse;
import com.developer.smmmousavi.clinic.network.responses.UserSignInResponse;
import com.developer.smmmousavi.clinic.network.responses.UserSignUpResponse;

import androidx.lifecycle.LiveData;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SurvayRestApi {

    @POST("api/user/SignUp")
    LiveData<ApiResponse<UserSignUpResponse>> userSignUp(@Body UserSignUpBody body);

    @POST("api/user/login")
    @FormUrlEncoded
    LiveData<ApiResponse<UserSignInResponse>> userSignIn(@Field("username") String userName, @Field("password") String password);

    @GET("api/category/Get")
    LiveData<ApiResponse<CategoriesResponse>> getCategories();

    @GET("/api/question/GetFirstCategoryQuestion")
    LiveData<ApiResponse<FirstCategoryQuestionResponse>> getFristCategoryQuestion(
        @Header("categoryId") long categoryId
    );

    @POST("api/userQuestion/PostUserQuestion")
    LiveData<ApiResponse<PostQuestionResponse>> postUserQuestion(@Body PostUserQuestionBody body);
}
