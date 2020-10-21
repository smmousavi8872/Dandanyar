package com.developer.smmmousavi.dandanyar.presistence.dao;

import com.developer.smmmousavi.dandanyar.model.Category;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CategoryDAO {

    @Insert(onConflict = IGNORE)
    long[] insertCategories(Category... categories);
    // in case of success: {id1, id2, id3, id4}
    // in case of failure: {-1, id2, -1, -1}

    @Insert(onConflict = REPLACE)
    void insertCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Query("UPDATE categories SET title = :title " + "WHERE id = :categoryId ")
    void updateCategory(long categoryId, String title);

    @Query("SELECT * FROM categories WHERE title LIKE '%' || :query " +
        "ORDER BY id DESC LIMIT (:pageNumber * 30)")
    LiveData<List<Category>> searchCategories(String query, int pageNumber);

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    LiveData<Category> getCategoryById(long categoryId);

    @Query("SELECT * FROM categories")
    LiveData<List<Category>> getAllCategories();

    @Query("DELETE from categories")
    void deleteAll();


}
