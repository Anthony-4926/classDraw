package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class RhowResult extends Activity {
    private DisplayMetrics metric = new DisplayMetrics();
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhow_result);
        if(DataBase.row.size()==0){
            DataBase.row = DataBase.getStudents();
        }
        Random random=new Random();
        int chosenStudent = random.nextInt(DataBase.row.size()-1);
        TextView textView = findViewById(R.id.chosen_student);

        textView.setText(DataBase.row.get(chosenStudent).getName());
        mediaPlayer = MediaPlayer.create(RhowResult.this, DataBase.row.get(chosenStudent).getSound());
        mediaPlayer.start();
//        Toast.makeText(RhowResult.this, DataBase.row.get(chosenStudent).getName(), Toast.LENGTH_SHORT).show();
        DataBase.row.remove(chosenStudent);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RhowResult.this.finish();
            }
        }, 5000);
    }
}
