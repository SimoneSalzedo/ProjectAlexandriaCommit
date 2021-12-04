package LibraryServices;

import android.media.Image;
import android.widget.ImageView;

import java.io.File;


public class ShelfEntry {
    private String Title;
    private String Autore;
    private ImageView Image;

    public ShelfEntry(String title, String autore, ImageView Image) {
        Title = title;
        Autore = autore;
        this.Image = Image;
    }
}
