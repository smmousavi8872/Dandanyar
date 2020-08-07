package com.developer.smmmousavi.clinic.presistence.db;

import android.content.Context;

import com.developer.smmmousavi.clinic.model.Question;
import com.developer.smmmousavi.clinic.presistence.dao.CategoryDAO;
import com.developer.smmmousavi.clinic.presistence.dao.QuestionDAO;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@androidx.room.Database(entities = {Question.class}, version = 1)
@TypeConverters({ListConverter.class})
public abstract class Database extends RoomDatabase {

    public static final String DATABASE_NAME = "survay_db";

    private static Database sInstance;

    public abstract QuestionDAO getQuestionDao();

    public abstract CategoryDAO geCategoryDao();

    public static Database getInstance(final Context context) {
        if (sInstance == null)
            sInstance = Room.databaseBuilder(
                context.getApplicationContext(),
                Database.class,
                DATABASE_NAME).build();
        return sInstance;
    }

}
