package com.example.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class GameFragment extends Fragment {

    private Button leftNumber;
    private Button rightNumber;
    private Button equalNumber;
    private TextView scoreShowing;
    private TextView gameLevel;

    private int gameLevelInt = 0;
    private int savedRightNumber;
    private int savedLeftNumber;
    private int userPointInt = 0;
    private boolean gameInProgress = false;
    CountDownTimer countDownTimer;

    private final int FINISHED_GAME = 10;
    private final int LEFT_BUTTON = 0;
    private final int RIGHT_BUTTON = 1;
    private final int EQUAL_BUTTON = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        final int[] counter_left = {0};
        final int[] counter_right = {0};
        final int[] counter_equal = {0};

        configureViews(leftNumber, counter_left, LEFT_BUTTON, "leftNum");
        configureViews(rightNumber, counter_right, RIGHT_BUTTON, "rightNum");
        configureViews(equalNumber, counter_equal, EQUAL_BUTTON, "equalNum");

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long remainingTime) {
                Log.d("Generate", "countDownTimer" + remainingTime);
                gameLevel.setText(getString(R.string.Remaining_time,(int)remainingTime/1000));
            }

            @Override
            public void onFinish() {
                Log.d("Generate", "countDownTimer onFinish");
                gameInProgress = false;
                gameLevel.setText(getString(R.string.Finished));
            }
        };
        countDownTimer.start();
        generateOneLevel();
        gameInProgress = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    private void configureViews(Button btn, final int[] counter, final int BUTTON, final String str) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Generate", str + " Clicked for " + (counter[0] + 1) + " times");
                evaluateAndContinueGame(BUTTON);
                counter[0]++;
            }
        });
    }

    private void evaluateAndContinueGame(int whatPressed) {
        if (gameInProgress == false) {
            return;
        }
        evaluate(whatPressed);
        scoreShowing.setText(getString(R.string.score_text, userPointInt));
        generateOneLevel();
    }

    private void evaluate(int whatPressed) {
        if (whatPressed == LEFT_BUTTON) {
            if (savedLeftNumber > savedRightNumber)
                userPointInt++;
        } else if (whatPressed == RIGHT_BUTTON) {
            if (savedRightNumber > savedLeftNumber)
                userPointInt++;
        } else if (whatPressed == EQUAL_BUTTON) {
            if (savedRightNumber == savedLeftNumber)
                userPointInt++;
        }
    }

    private void findViews(View view) {
        leftNumber = view.findViewById(R.id.left_num);
        rightNumber = view.findViewById(R.id.right_num);
        equalNumber = view.findViewById(R.id.equal);
        scoreShowing = view.findViewById(R.id.user_score);
        gameLevel = view.findViewById(R.id.game_level);
    }

    private int generateInt() {
        Random random = new Random();
        int Int = random.nextInt();
        if (Int < 0) Int *= -1;
        Int %= 100;
        return Int + 1;
    }

    private void generateOneLevel() {
        if (gameInProgress == false) {
            return;
        }
        gameLevelInt++;
        //gameLevel.setText(getString(R.string.Round, String.valueOf(gameLevelInt)));

        savedLeftNumber = generateInt();
        savedRightNumber = generateInt();
        leftNumber.setText(String.valueOf(savedLeftNumber));
        rightNumber.setText(String.valueOf(savedRightNumber));
    }
}
