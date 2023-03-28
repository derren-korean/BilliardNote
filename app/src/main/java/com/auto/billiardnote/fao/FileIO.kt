package com.auto.billiardnote.fao

import android.content.Context
import com.auto.billiardnote.fao.FileIO
import java.io.*

class FileIO private constructor(context: Context) {
    init {
        val filename = "BilliardNote.txt"
        val filepath = context.filesDir.path
        file = File(filepath, filename)
    }

    companion object {
        @Volatile
        private var INSTANCE: FileIO? = null
        private var file: File? = null
        var myData = ""
            private set

        // public static method to retrieve the singleton instance
        fun getInstance(context: Context) {
            // Check if the instance is already created
            if (INSTANCE == null) {
                // synchronize the block to ensure only one thread can execute at a time
                synchronized(FileIO::class.java) {
                    // check again if the instance is already created
                    if (INSTANCE == null) {
                        // create the singleton instance
                        INSTANCE = FileIO(context)
                    }
                }
            }
            // return the singleton instance
        }

        fun write(noteInfo: String) {
            myData = noteInfo
            try {
                val fos = FileOutputStream(file!!.path)
                fos.write(myData.toByteArray())
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            myData = ""
        }

        fun read() {
            try {
                val fis = FileInputStream(file!!.path)
                val `in` = DataInputStream(fis)
                val br = BufferedReader(InputStreamReader(`in`))
                var strLine: String
                while (br.readLine().also { strLine = it } != null) {
                    myData = myData + strLine
                }
                `in`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}