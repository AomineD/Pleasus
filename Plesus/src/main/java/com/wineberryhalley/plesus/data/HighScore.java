package com.wineberryhalley.plesus.data;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {
    File data = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator);
    File file = new File(data, "highscore.txt");
    private String highScore = "";

    public String readHighScore() {
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                highScore =br.readLine();
                br.close();
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return highScore;
    }

    public void writeHighScore(String highestScore) {
        try {
            Log.e("MAIN", "writeHighScore: "+file.getAbsolutePath() );
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(String.valueOf(highestScore));
            bw.close();
        } catch (IOException e) {
            Log.e("MAIN", "writeHighScore: "+e.getMessage() );
            e.printStackTrace();
        }
    }
}
