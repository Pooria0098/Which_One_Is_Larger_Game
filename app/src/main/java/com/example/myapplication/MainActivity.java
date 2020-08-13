package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startGame;
    Button shoeBestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        configure();

    }

    private void findViews() {
        startGame = findViewById(R.id.start_game);
        shoeBestScore = findViewById(R.id.show_best_score);
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
        shoeBestScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BestScoreFragment bestScoreFragment = new BestScoreFragment();
                getSupportFragmentManager().beginTransaction().
                        add(R.id.fragment_container,bestScoreFragment).
                        addToBackStack(null).
                        commit();
            }
        });
    }
}