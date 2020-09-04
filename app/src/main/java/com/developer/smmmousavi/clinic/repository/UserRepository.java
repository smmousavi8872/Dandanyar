package com.developer.smmmousavi.clinic.repository;

import android.content.Context;
import android.util.Log;

import com.developer.smmmousavi.clinic.model.User;
import com.developer.smmmousavi.clinic.network.AppExecutors;
import com.developer.smmmousavi.clinic.network.bodies.UserSignInBody;
import com.developer.smmmousavi.clinic.network.bodies.UserSignUpBody;
import com.developer.smmmousavi.clinic.network.factory.SurvayRestApiFactory;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.UserResponse;
import com.developer.smmmousavi.clinic.network.util.NetworkBoundResource;
import com.developer.smmmousavi.clinic.network.util.Resource;
import com.developer.smmmousavi.clinic.presistence.dao.UserDAO;
import com.developer.smmmousavi.clinic.presistence.db.Database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class UserRepository {

    private static final String TAG = "TAG";
    private static UserRepository sInstance;
    private UserDAO mUserDAO;


    public static UserRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserRepository(context);
            return sInstance;
        }
        return sInstance;
    }

    private UserRepository(Context context) {
        mUserDAO = Database.getInstance(context).getUserDao();
    }


    public LiveData<Resource<User>> signUpRequest(UserSignUpBody body) {
        return new NetworkBoundResource<User, UserResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull UserResponse item) {
                if (item.getUser() != null) {
                    mUserDAO.deleteAll();
                    User[] userArr = new User[1];
                    userArr[0] = item.getUser();
                    int index = 0;
                    for (long rowId : mUserDAO.insertUsers(userArr)) {
                        // if category already exists.
                        if (rowId == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT... This recipe is already in the cache");
                            mUserDAO.updateUser(
                                userArr[index].getId(),
                                userArr[index].getFirstName(),
                                userArr[index].getLastName(),
                                userArr[index].getUserName(),
                                userArr[index].getPassword()
                            );
                        }
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                // set the interval of request.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                return mUserDAO.getUserByBody(body.getUserName(), body.getPassword());
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserResponse>> createCall() {
                return SurvayRestApiFactory.create()
                    .userSignUp(body);
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<User>> signInRequest(UserSignInBody body) {
        return new NetworkBoundResource<User, UserResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull UserResponse item) {
                if (item.getUser() != null) {
                    User[] userArr = new User[1];
                    userArr[0] = item.getUser();
                    int index = 0;
                    for (long rowId : mUserDAO.insertUsers(userArr)) {
                        // if category already exists.
                        if (rowId == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT... This user is already in the cache");
                            mUserDAO.updateUser(
                                userArr[index].getId(),
                                userArr[index].getFirstName(),
                                userArr[index].getLastName(),
                                userArr[index].getUserName(),
                                userArr[index].getPassword()
                            );
                        }
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                // set the interval of request.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                return mUserDAO.getUserByBody(body.getUserName(), body.getPassword());
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserResponse>> createCall() {
                return SurvayRestApiFactory.create()
                    .userSignIn(body.getUserName(), body.getPassword());
            }
        }.getAsLiveData();

    }

    public LiveData<Resource<User>> getUserById(long userId) {
        return new NetworkBoundResource<User, UserResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull UserResponse item) {
                Log.d(TAG, "saveCallResult: status -> " + item.getStatus());
                if (item.getUser() != null) {
                    User[] userArr = new User[1];
                    userArr[0] = item.getUser();
                    int index = 0;
                    for (long rowId : mUserDAO.insertUsers(userArr)) {
                        // if category already exists.
                        if (rowId == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT... This user is already in the cache");
                            mUserDAO.updateUser(
                                userArr[index].getId(),
                                userArr[index].getFirstName(),
                                userArr[index].getLastName(),
                                userArr[index].getUserName(),
                                userArr[index].getPassword()
                            );
                        }
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                // set the interval of request.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                return mUserDAO.getUserById(userId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserResponse>> createCall() {
                return SurvayRestApiFactory.create()
                    .getUserById(userId);
            }
        }.getAsLiveData();

    }


}
