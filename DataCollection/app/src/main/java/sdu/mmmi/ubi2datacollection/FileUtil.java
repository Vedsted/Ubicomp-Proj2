package sdu.mmmi.ubi2datacollection;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    private static final String TAG = "FileUtil";
    private final File file;

    public FileUtil(String fileName, String folderToPlaceIn){
        File root = new File(Environment.getExternalStorageDirectory(), folderToPlaceIn);
        if (!root.exists()) {
            root.mkdirs();
        }
        this.file = new File(root, fileName);
    }

    public void appendToFile(String toAppend){
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(toAppend);
            writer.flush();
            writer.close();

            Log.i(TAG, "Wrote following to file: " + toAppend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
