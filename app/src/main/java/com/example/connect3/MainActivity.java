package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {



    boolean gameIsActive = true;

    //0 = yellow 1 = red
    int activePlayer = 0;

    //2 = unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8} ,{0,4,8}, {2,4,6}, {0,3,6}, {1,4,7} ,{2,5,8}};


    public void dropIn(View view) {

        ImageView piece = (ImageView) view;

        int tappedPiece = Integer.parseInt(piece.getTag().toString());

        if (gameState[tappedPiece] == 2 && gameIsActive == true) {

            gameState[tappedPiece] = activePlayer;

            piece.setTranslationY(-1500f);

            if (activePlayer == 0) {
                piece.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            } else {
                piece.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            piece.animate().translationYBy(1500f).alpha(1f).setDuration(300);


            for (int[] winningPosition: winningPositions) {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2){


                    String winner = "Red";
                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    LinearLayout layout = (LinearLayout)findViewById(R.id.winningLayout);


                    gameIsActive = false;



                    if(gameState[winningPosition[0]] == 0){
                        winner = "Yellow";
                    }

                    winnerMessage.setText(winner + " Has Won!");

                    Log.i("win", String.valueOf(gameState[winningPosition[0]]));



                    layout.setVisibility(View.VISIBLE);


                }
            }
        }
    }

    public void playAgain (View view){

        LinearLayout layout = (LinearLayout)findViewById(R.id.winningLayout);

        layout.setVisibility(View.INVISIBLE);

        gameIsActive = true;

        activePlayer = 0;

        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        LinearLayout firstRow = (LinearLayout) findViewById(R.id.row1Layout);
        for(int i = 0; i < firstRow.getChildCount(); i++){
            ((ImageView) firstRow.getChildAt(i)).setImageResource(0);
        }

        LinearLayout secondRow = (LinearLayout) findViewById(R.id.row2Layout);
        for(int i = 0; i < secondRow.getChildCount(); i++){
            ((ImageView) secondRow.getChildAt(i)).setImageResource(0);
        }

        LinearLayout thirdRow = (LinearLayout) findViewById(R.id.row3Layout);
        for(int i = 0; i < thirdRow.getChildCount(); i++){
            ((ImageView) thirdRow.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}