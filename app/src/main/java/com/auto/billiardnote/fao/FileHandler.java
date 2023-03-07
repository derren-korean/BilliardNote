package com.auto.billiardnote.fao;

import android.content.Context;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileHandler {

    private static volatile FileHandler INSTANCE = null;
    private static File file = null;
    private static String myData = "test";

    private FileHandler(Context context) {
        String filename = "SampleFile.txt";
        String filepath = context.getFilesDir().getPath();
        this.file = new File(filepath, filename);
    }

    // public static method to retrieve the singleton instance
    public static FileHandler getInstance(Context context) {
        // Check if the instance is already created
        if(INSTANCE == null) {
            // synchronize the block to ensure only one thread can execute at a time
            synchronized (FileHandler.class) {
                // check again if the instance is already created
                if (INSTANCE == null) {
                    // create the singleton instance
                    INSTANCE = new FileHandler(context);
                }
            }
        }
        // return the singleton instance
        return INSTANCE;
    }

    public static void write() {
        try {
            FileOutputStream fos = new FileOutputStream(file.getPath());
            fos.write("myData".getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            FileInputStream fis = new FileInputStream(file.getPath());
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMyData() {
        return myData;
    }
}

