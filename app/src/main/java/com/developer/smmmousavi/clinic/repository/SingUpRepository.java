package com.developer.smmmousavi.clinic.repository;

import android.content.Context;

import com.developer.smmmousavi.clinic.constants.Constants;
import com.developer.smmmousavi.clinic.model.User;
import com.developer.smmmousavi.clinic.network.AppExecutors;
import com.developer.smmmousavi.clinic.network.bodies.UserSignUpBody;
import com.developer.smmmousavi.clinic.network.factory.SurvayRestApiFactory;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.UserSignUpResponse;
import com.developer.smmmousavi.clinic.network.util.NetworkBoundResource;
import com.developer.smmmousavi.clinic.network.util.Resource;
import com.developer.smmmousavi.clinic.sharepref.StringSharedPref;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class SingUpRepository {


    private static final String TAG = "TAG";
    private static SingUpRepository sInstance;
    private StringSharedPref mSharedPref;


    public static SingUpRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SingUpRepository(context);
            return sInstance;
        }
        return sInstance;
    }

    private SingUpRepository(Context context) {
        mSharedPref = StringSharedPref.getInstance(context);
    }

    public LiveData<Resource<User>> signUpRequest(String firstName, String lastName, String username, String password) {
        return new NetworkBoundResource<User, UserSignUpResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull UserSignUpResponse item) {
                if (item.getUser() != null && item.getStatus().equals("ok")) {
                    mSharedPref.writeStringInSP(Constants.SHARED_PREF_USER_NAME_LABEL, item.getUser().getUserName());
                    mSharedPref.writeStringInSP(Constants.SHARED_PREF_USER_PASS_LABEL, item.getUser().getPassword());
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
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserSignUpResponse>> createCall() {
                UserSignUpBody body = new UserSignUpBody(firstName, lastName, username, password);
                return SurvayRestApiFactory.create()
                    .userSignUp(body);
            }
        }.getAsLiveData();

    }
}
