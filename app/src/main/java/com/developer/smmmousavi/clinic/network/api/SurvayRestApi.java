package com.developer.smmmousavi.clinic.network.api;

import com.developer.smmmousavi.clinic.network.bodies.UserSignUpBody;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.CategoriesResponse;
import com.developer.smmmousavi.clinic.network.responses.CategoryByIdResponse;
import com.developer.smmmousavi.clinic.network.responses.FirstQuestionResponse;
import com.developer.smmmousavi.clinic.network.responses.PostQuestionResponse;
import com.developer.smmmousavi.clinic.network.responses.UserResponse;

import androidx.lifecycle.LiveData;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SurvayRestApi {

    @POST("api/user/SignUp")
    LiveData<ApiResponse<UserResponse>> userSignUp(@Body UserSignUpBody body);

    @POST("api/user/login")
    @FormUrlEncoded
    LiveData<ApiResponse<UserResponse>> userSignIn(
        @Field("username") String userName,
        @Field("password") String password
    );

    @GET("api/category/Get")
    LiveData<ApiResponse<CategoriesResponse>> getCategories();

    @GET("api/category/GetCategoryById")
    LiveData<ApiResponse<CategoryByIdResponse>> getCategoryById(
        @Header("categoryId") long categoryId
    );

    @GET("api/user/getUser")
    LiveData<ApiResponse<UserResponse>> getUserById(@Query("userId") long userId);

    @GET("/api/question/GetFirstCategoryQuestion")
    LiveData<ApiResponse<FirstQuestionResponse>> getFristQuestion(
        @Header("categoryId") long categoryId
    );

    @POST("api/userQuestion/PostUserQuestion")
    @FormUrlEncoded
    LiveData<ApiResponse<PostQuestionResponse>> postUserQuestion(
        @Field("userId") long userId,
        @Field("questionId") long questionId,
        @Field("userAnswer") boolean userAnswer
    );
}
