/*
Check https://developer.android.com/training/data-storage/room/accessing-data
for advanced documentation on DAO Implementation
 */

package com.example.projectalexandria.data.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface ShelfEntryDAO {

    /*
     * Add support for basic Insert, Update & Delete
     * SQLite Operations
     */
    @Insert(onConflict = REPLACE)
    void insertShelfEntry(ShelfEntry shelfentry);

    @Update
    void updateShelfEntry(ShelfEntry shelfentry);

    @Delete
    void deleteShelfEntry(ShelfEntry shelfentry);


    /*
     * Add support for specific queries with SQLite syntax
     * Remember that you have to override the DAO init if
     * you want to implement PRAGMA.
     * NOTE! ":variable" refers to the variable taken as input by the decorated function
     *       while "variable" refers to the name of the column of the implemented table
     *       and "table name" refers to the name of the table itself
     */
    @Query("SELECT * FROM shelfentry")
    Cursor getAll();

    @Query("SELECT * FROM shelfentry WHERE datatype = :datatype")
    Cursor getByDataType(DataTypes datatype);

    @Query("SELECT * FROM shelfentry WHERE title = :title")
    Cursor getByTitle(String title);

    @Query("SELECT * FROM shelfentry WHERE author = :author")
    Cursor getByAuthor(String author);

    @Query("SELECT * FROM shelfentry WHERE genre = :genre")
    Cursor getByGenre(String genre);

    /*
     * Implementing search with pattern matching
     * using SQLite LIKE syntax
     */
    @Query("SELECT * FROM shelfentry WHERE title LIKE :title")
    Cursor searchByTitle(String title);

    @Query("SELECT * FROM shelfentry WHERE author LIKE :author")
    Cursor searchByAuthor(String author);

    @Query("SELECT * FROM shelfentry WHERE genre LIKE :genre")
    Cursor searchByGenre(String genre);

    /*
     * Support for queried removals. It's not supposed to be exposed
     * in the UI for now but it might be useful someday.
     */
    @Query("DELETE FROM shelfentry WHERE title = :title")
    Cursor deleteByTitle(String title);

    @Query("DELETE FROM shelfentry WHERE author = :author")
    Cursor deleteByAuthor(String author);

    @Query("DELETE FROM shelfentry WHERE genre = :genre")
    Cursor deleteByGenre(String genre);

}
