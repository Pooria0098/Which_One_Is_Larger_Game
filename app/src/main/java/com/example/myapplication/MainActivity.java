package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        configure();
//        GameFragment gameFragment=new GameFragment();
//        getSupportFragmentManager().beginTransaction().
//                add(R.id.fragment_container,gameFragment).
//                addToBackStack(null).commit();
    }

    private void findViews() {
        startGame = findViewById(R.id.start_game);
    }

    private void configure() {
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameFragment gameFragment = new GameFragment();
                getSupportFragmentManager().beginTransaction().
                        add(R.id.fragment_container, gameFragment).
                        addToBackStack(null).
                        commit();
            }
        });
    }
}