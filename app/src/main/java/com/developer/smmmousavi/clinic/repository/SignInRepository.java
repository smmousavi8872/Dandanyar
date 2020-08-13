package com.developer.smmmousavi.clinic.repository;

import android.content.Context;
import android.util.Log;

import com.developer.smmmousavi.clinic.model.User;
import com.developer.smmmousavi.clinic.network.AppExecutors;
import com.developer.smmmousavi.clinic.network.bodies.UserSignInBody;
import com.developer.smmmousavi.clinic.network.factory.SurvayRestApiFactory;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.UserSignInResponse;
import com.developer.smmmousavi.clinic.network.util.NetworkBoundResource;
import com.developer.smmmousavi.clinic.network.util.Resource;
import com.developer.smmmousavi.clinic.presistence.dao.UserDAO;
import com.developer.smmmousavi.clinic.presistence.db.Database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class SignInRepository {


    private static final String TAG = "TAG";
    private static SignInRepository sInstance;
    private UserDAO mUserDAO;


    public static SignInRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SignInRepository(context);
            return sInstance;
        }
        return sInstance;
    }

    private SignInRepository(Context context) {
        mUserDAO = Database.getInstance(context).getUserDao();
    }


    public LiveData<Resource<User>> signInRequest(UserSignInBody body) {
        return new NetworkBoundResource<User, UserSignInResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull UserSignInResponse item) {
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
            protected LiveData<ApiResponse<UserSignInResponse>> createCall() {
                return SurvayRestApiFactory.create()
                    .userSignIn(body.getUserName(), body.getPassword());
            }
        }.getAsLiveData();

    }
}
