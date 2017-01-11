package com.googleappliedcs.radhikakalaiselvan.scarnes_dice;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;
import android.os.Handler;
import java.util.logging.LogRecord;

public class RollDiceActivity extends AppCompatActivity implements View.OnClickListener {
    Button rollButton, holdButton, resetButton;
    int computerPlayerTurn = 0;
    TextView turnScore, userScore, compScore, whoseTurn;
    ImageView dice;

    int userOverallScore = 0, currentTurnScore = 0, compOverallScore = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);
        rollButton = (Button) findViewById(R.id.rollbutton);
        holdButton = (Button) findViewById(R.id.holdbutton);
        resetButton = (Button) findViewById(R.id.resetbutton);
        turnScore = (TextView) findViewById(R.id.turnscore);
        userScore = (TextView) findViewById(R.id.playerscore);
        dice = (ImageView) findViewById(R.id.dice);
        compScore = (TextView) findViewById(R.id.compscore);
        whoseTurn = (TextView) findViewById(R.id.whoseTurn);
        rollButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.holdbutton:
                userOverallScore += currentTurnScore;
                userScore.setText(String.valueOf(userOverallScore));
                currentTurnScore = 0;
                turnScore.setText(String.valueOf(0));
                computerPlayerTurn = 1;
                Log.d("User hold", userOverallScore + "");
                whoseTurn.setText("Computer's Turn buddy !");
                break;
            case R.id.rollbutton:
                Random rand = new Random();
                int rNum = rand.nextInt(6) + 1;
                String faceName = "dice" + rNum;
                int iconResId = getResources().getIdentifier(faceName, "drawable", getPackageName());
                dice.setImageResource(iconResId);
                currentTurnScore += rNum;
                if (rNum == 1) {
                    currentTurnScore = 0;
                    computerPlayerTurn = 1;
                    whoseTurn.setText("Computer's Turn buddy !");
                }
                turnScore.setText(String.valueOf(currentTurnScore));
                Log.d("USER turn ", rNum + "");
                break;
            case R.id.resetbutton:
                currentTurnScore = 0;
                userOverallScore = 0;
                compOverallScore = 0;
                compScore.setText(String.valueOf(0));
                turnScore.setText(String.valueOf(0));
                userScore.setText(String.valueOf(0));
                break;
        }
        if (computerPlayerTurn == 1) {
            //disable button
            holdButton.setEnabled(false);
            rollButton.setEnabled(false);
            playComputersTurn();
            holdButton.setEnabled(true);
            rollButton.setEnabled(true);
        }
    }

    public void playComputersTurn() {
        int rNum = 0;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                handler.postDelayed(this, 500);
            }
        }, 500);

        while (currentTurnScore < 8) {
            Random rand = new Random();
            rNum = rand.nextInt(6) + 1;
            String faceName = "dice" + rNum;
            int iconResId = getResources().getIdentifier(faceName, "drawable", getPackageName());
            dice.setImageResource(iconResId);
            currentTurnScore += rNum;
            Log.d("Computer turn ", rNum + "");
            if (rNum == 1) {
                currentTurnScore = 0;
                computerPlayerTurn = 0;
                whoseTurn.setText("Its your turn buddy !");
                break;
            }
            turnScore.setText(String.valueOf(currentTurnScore));
        }
        if (currentTurnScore > 8) {
            compOverallScore += currentTurnScore;
            Log.d("Computer over all score", compOverallScore + "");
            currentTurnScore = 0;
            computerPlayerTurn = 0;
            compScore.setText(String.valueOf(compOverallScore));
            whoseTurn.setText("Its your turn buddy !");
        }
    }

}
