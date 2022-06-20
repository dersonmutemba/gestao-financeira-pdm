package mz.ac.isutc.gestaofinanceira;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Helper {

    public static long getLastMovimentoID (File file) {
        if(file.exists()) {
            try{
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                long id = objectInputStream.readLong();
                objectInputStream.close();
                fileInputStream.close();
                return id;
            }
            catch (Exception e) {
                Log.e("IO Error", Log.getStackTraceString(e.fillInStackTrace()));
            }
        }
        return 1000;
    }

    public static void writeLastMovimentoID (File file, long id) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeLong(id);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (Exception e) {
            Log.e("IO Error", Log.getStackTraceString(e.fillInStackTrace()));
        }
    }

}
