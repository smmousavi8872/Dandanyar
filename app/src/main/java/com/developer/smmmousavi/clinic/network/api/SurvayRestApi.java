package com.developer.smmmousavi.clinic.network.api;

import com.developer.smmmousavi.clinic.network.bodies.PostUserQuestionBody;
import com.developer.smmmousavi.clinic.network.bodies.UserSignInBody;
import com.developer.smmmousavi.clinic.network.bodies.UserSignUpBody;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.CategoriesResponse;
import com.developer.smmmousavi.clinic.network.responses.FirstCategoryQuestionResponse;
import com.developer.smmmousavi.clinic.network.responses.PostQuestionResponse;
import com.developer.smmmousavi.clinic.network.responses.UserSignInResponse;
import com.developer.smmmousavi.clinic.network.responses.UserSignUpResponse;

import androidx.lifecycle.LiveData;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SurvayRestApi {

    @POST("api/user/SignUp")
    LiveData<ApiResponse<UserSignUpResponse>> userSignUp(@Body UserSignUpBody body);

    @POST("api/user/login")
    LiveData<ApiResponse<UserSignInResponse>> userSignIn(@Body UserSignInBody bdoy);

    @GET("api/category/Get")
    LiveData<ApiResponse<CategoriesResponse>> getCategories();

    @GET("api/question/GetFirstCategoryQuestion")
    LiveData<ApiResponse<FirstCategoryQuestionResponse>> getFristCategoryQuestion(
        @Query("categoryId") String categoryId
    );

    @POST("api/userQuestion/PostUserQuestion")
    LiveData<ApiResponse<PostQuestionResponse>> postUserQuestion(@Body PostUserQuestionBody body);


}
