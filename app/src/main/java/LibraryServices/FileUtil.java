package LibraryServices;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Path;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;

import com.example.projectalexandria.ListItemAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class FileUtil {
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    private FileUtil() {

    }

    public static File from(Context context, Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] splitName = splitFileName(fileName);
        File tempFile = File.createTempFile(splitName[0], splitName[1]);
        tempFile = rename(tempFile, fileName);
        tempFile.deleteOnExit();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(tempFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream != null) {
            copy(inputStream, out);
            inputStream.close();
        }

        if (out != null) {
            out.close();
        }
        return tempFile;
    }

    private static String[] splitFileName(String fileName) {
        String name = fileName;
        String extension = "";
        int i = fileName.lastIndexOf(".");
        if (i != -1) {
            name = fileName.substring(0, i);
            extension = fileName.substring(i);
        }

        return new String[]{name, extension};
    }

    protected static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf(File.separator);
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private static File rename(File file, String newName) {
        File newFile = new File(file.getParent(), newName);
        if (!newFile.equals(file)) {
            if (newFile.exists() && newFile.delete()) {
                Log.d("FileUtil", "Delete old " + newName + " file");
            }
            if (file.renameTo(newFile)) {
                Log.d("FileUtil", "Rename file to " + newName);
            }
        }
        return newFile;
    }

    private static long copy(InputStream input, OutputStream output) throws IOException {
        long count = 0;
        int n;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    private void checkExternalMedia(){
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Can't read or write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        System.out.println("\n\nExternal Media: readable="
                +mExternalStorageAvailable+" writable="+mExternalStorageWriteable);
    }

    public static void writeToSDFile(Context context,File inputFile){
        File dir = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Alexandria");
        if(!checkAlexandriaDir()){
        dir.mkdirs();}
        File file = new File(dir, inputFile.getName());
        ShelfEntry entry = new ShelfEntry(inputFile.getName());
        try {

            //COPIA IL FILE, DALLA CARTELLA SCELTA DELL'UTENTE NELLA CARTELLA ALEXANDRIA

            FileInputStream i = new FileInputStream(inputFile);
            FileOutputStream f = new FileOutputStream(file);
            copy(i,f);
            f.close();
            i.close();
            //CREARE LA CARTELLA INTERNAMENTE ALLO STORAGE DELL'APP e PASSARCI I PARAMETRI E LA FOTO DENTRO
            //System.out.println(context.getFilesDir());
            entry.setup();
            //altri metodi per costruire i file di testo e la directory e l'immagine

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("******* File not found. Did you" +
                    " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nFile written to "+file);
    }

    public static boolean checkAlexandriaDir(){
        File testdir = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Alexandria","test");
        testdir.mkdir();
        if(testdir.isDirectory()){
            testdir.delete();
            System.out.println("LA CARTELLA ERA GIA' PRESENTE E NON E' STATA CREATA");
            return true;
        }else{
            System.out.println("LA CARTELLA NON ERA PRESENTE ED SARA' CREATA");
            return false;
        }
    }

    //METODI DA ESEGUIRE IN MULTITHREAD SU SPLASH
    public static List<File> listLocalFilesAndDirsAllLevels(File baseDir) {

        List<File>  collectedFilesAndDirs   = new ArrayList<>();
        Deque<File> remainingDirs           = new ArrayDeque<>();

        if(baseDir.exists()) {
            remainingDirs.add(baseDir);

            while(!remainingDirs.isEmpty()) {
                File dir = remainingDirs.removeLast();
                List<File> filesInDir = Arrays.asList(dir.listFiles());
                for(File fileOrDir : filesInDir)  {
                    collectedFilesAndDirs.add(fileOrDir);
                    if(fileOrDir.isDirectory()) {
                        remainingDirs.add(fileOrDir);
                    }
                }
            }
        }

        return collectedFilesAndDirs;
    }

    public static ArrayList<ShelfEntry> instantiateEntriesArray(){
        List<File> list= listLocalFilesAndDirsAllLevels(new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Alexandria"));
        System.out.println("Gli elementi nella cartella Alexandria sono: " + list.size());
        ArrayList<ShelfEntry> localentries= new ArrayList<>();
        for (File item : list)
        {

            //estrarre nome, autore e imageview
            //TODO RETURN METHOD AND FOR CICLE
        }
        return localentries;
    }
}