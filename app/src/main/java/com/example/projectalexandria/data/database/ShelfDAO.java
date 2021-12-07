/*
Check https://developer.android.com/training/data-storage/room/accessing-data
for advanced documentation on DAO Implementation
 */

package com.example.projectalexandria.data.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ShelfDAO {

    /*
     * Add support for basic Insert, Update & Delete
     * SQLite Operations
     */
    @Insert(onConflict = REPLACE)
    void insertEntry(ShelfEntry entry);

    @Update
    void updateEntry(ShelfEntry entry);

    @Delete
    void deleteEntry(ShelfEntry entry);


    /*
     * Add support for specific queries with SQLite syntax
     * Remember that you have to override the DAO init if
     * you want to implement PRAGMA.
     * NOTE! ":variable" refers to the variable taken as input by the decorated function
     *       while "variable" refers to the name of the column of the implemented table
     *       and "table name" refers to the name of the table itself
     */
    @Query("SELECT * FROM shelfentry")
    LiveData<List<ShelfEntry>> getAll();

    @Query("SELECT * FROM shelfentry WHERE datatype = :datatype")
    LiveData<List<ShelfEntry>> getByDataType(DataTypes datatype);

    @Query("SELECT * FROM shelfentry WHERE title = :title")
    LiveData<List<ShelfEntry>> getByTitle(String title);

    @Query("SELECT * FROM shelfentry WHERE author = :author")
    LiveData<List<ShelfEntry>> getByAuthor(String author);

    @Query("SELECT * FROM shelfentry WHERE genre = :genre")
    LiveData<List<ShelfEntry>> getByGenre(String genre);

    /*
     * Implementing search with pattern matching
     * using SQLite LIKE syntax
     */
    @Query("SELECT * FROM shelfentry WHERE title LIKE :title")
    LiveData<List<ShelfEntry>> searchByTitle(String title);

    @Query("SELECT * FROM shelfentry WHERE author LIKE :author")
    LiveData<List<ShelfEntry>> searchByAuthor(String author);

    @Query("SELECT * FROM shelfentry WHERE genre LIKE :genre")
    LiveData<List<ShelfEntry>> searchByGenre(String genre);

    /*
     * Support for queried removals bu other attributes.
     * It's not supposed to be exposed in the UI for now
     * but it might be useful someday.
     */
    @Query("DELETE FROM shelfentry WHERE title = :title")
    void deleteByTitle(String title);

    @Query("DELETE FROM shelfentry WHERE author = :author")
    void deleteByAuthor(String author);

    @Query("DELETE FROM shelfentry WHERE genre = :genre")
    void deleteByGenre(String genre);

}
