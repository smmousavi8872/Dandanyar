package com.developer.smmmousavi.dandanyar.presistence.dao;

import com.developer.smmmousavi.dandanyar.model.Question;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface QuestionDAO {

    @Insert(onConflict = IGNORE)
    long[] insertQuestions(Question... questions);
    // in case of success: {id1, id2, id3, id4}
    // in case of failure: {-1, id2, -1, -1}

    @Insert(onConflict = REPLACE)
    void insertQuestion(Question question);

    @Update
    void updateQuestion(Question question);

    @Delete
    void deleteQuestion(Question question);

    @Query("UPDATE questions SET text = :text, categoryId = :categoryId, res_True_Id = :mResTrueId, " +
        "res_False_Id = :mResFlaseId, isFirst = :isFirst, isLast = :isLast , status = :status " + "WHERE id = :questionId ")
    void updateQuestion(long questionId, long categoryId, String text, String mResTrueId, String mResFlaseId, boolean isFirst, boolean isLast, int status);

    @Query("SELECT * FROM questions WHERE categoryId = :categoryId")
    LiveData<Question> getFrirstCategoryQuestion(long categoryId);

    @Query("SELECT * FROM questions WHERE id = :qId")
    LiveData<Question> getQuestionById(long qId);

    @Query("DELETE FROM questions")
    void deleteAll();
}
