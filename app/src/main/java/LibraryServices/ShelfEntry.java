package LibraryServices;

import android.media.Image;
import android.provider.MediaStore;
import android.util.Xml;
import android.widget.ImageView;

import java.io.File;


public class ShelfEntry {
    private String title;
    private String autore;
    private ImageView image;
    private String name;

    public ShelfEntry(String title, String autore, ImageView image) {
        this.title = title;
        this.autore = autore;
        this.image = image;
    }

    public ShelfEntry(String name){
        this.name=name;
        setup();
    }

    public void setup(){

    }
}
