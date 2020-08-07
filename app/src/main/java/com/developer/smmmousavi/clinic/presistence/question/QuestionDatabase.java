package com.developer.smmmousavi.clinic.presistence.question;

import android.content.Context;


import com.developer.smmmousavi.clinic.model.Question;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Question.class}, version = 1)
@TypeConverters({ListConverter.class})
public abstract class QuestionDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "recipes_db";

    public abstract QuestionDAO getRecipeDao();

    private static QuestionDatabase sInstance;

    public static QuestionDatabase getInstance(final Context context) {
        if (sInstance == null)
            sInstance = Room.databaseBuilder(
                context.getApplicationContext(),
                QuestionDatabase.class,
                DATABASE_NAME).build();
        return sInstance;
    }

}
