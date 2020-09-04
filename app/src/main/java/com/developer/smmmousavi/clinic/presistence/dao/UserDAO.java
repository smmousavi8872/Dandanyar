package com.developer.smmmousavi.clinic.presistence.dao;

import com.developer.smmmousavi.clinic.model.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDAO {

    @Insert(onConflict = IGNORE)
    long[] insertUsers(User... users);
    // in case of success: {id1, id2, id3, id4}
    // in case of failure: {-1, id2, -1, -1}

    @Insert(onConflict = REPLACE)
    void insertUsers(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("UPDATE users SET firstName = :firstName, lastName = :lastName, userName= :userName," +
        " password =:password " + "WHERE id = :userId ")
    void updateUser(long userId, String firstName, String lastName, String userName, String password);

    @Query("SELECT * FROM users WHERE id = :userId")
    LiveData<User> getUserById(long userId);

    @Query("SELECT * FROM users WHERE userName = :userName AND password = :password")
    LiveData<User> getUserByBody(String userName, String password);

    @Query("DELETE from users")
    void deleteAll();

}
