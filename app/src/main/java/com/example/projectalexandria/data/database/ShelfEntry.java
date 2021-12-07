/*
Check https://developer.android.com/training/data-storage/room/defining-data
for advanced documentation about defining data tables
 */

package com.example.projectalexandria.data.database;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.File;


/**
 * Represent one record of ShelfEntry Table
 */
@Entity
public class ShelfEntry {

    public static final String COLUMN_ID = BaseColumns._ID;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(index = true)
    public String title;

    @ColumnInfo(index = true)
    public String author;

    @ColumnInfo(index = true)
    public String genre;

    /*
     * cover and file are references to the location in the filesystem of
     * the effective files and covers displayed in the Library View.
     * The covers are kept in a cache inside the internal storage of the app
     * while the files can be everywhere in the external storage of the device.
     */
    @ColumnInfo
    public String cover;

    @ColumnInfo
    public String file;

    @ColumnInfo
    public DataTypes datatype;

    public ShelfEntry(String title, String author, String genre, String cover, String file) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
