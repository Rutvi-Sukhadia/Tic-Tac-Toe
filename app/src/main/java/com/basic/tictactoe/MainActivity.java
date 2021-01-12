package com.basic.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameInitialize();
    }

    //Player representations:
    //0 - O
    //1 - X
    int activePlayer;

    //Game State Representation:
    //0 - O
    //1 - X
    //2 - Blank
    int[] gameState = new int[9];

    //Wining Positions:
    int winingPositions[][] = {{0,1,2}, {3,4,5}, {6,7,8},   //Horizontal positions
                               {0,3,6}, {1,4,7}, {2,5,8},   //Vertical positions
                               {0,4,8}, {2,4,6}};           //Cross
    boolean gameActive;

    public void gameInitialize()
    {
        activePlayer=0;
        for(int i=0; i < 9; i++)
            gameState[i] = 2;
        TextView status = findViewById(R.id.status);
        status.setText("O's Turn - Tap to play");
        gameActive = true;
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }
    public void gameCheckWin()
    {
        String winnerStr;
        for(int[] winPosition: winingPositions)
        {
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]]!=2)
            {
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "O has won";
                }
                else{
                    winnerStr = "X has won";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                return;
            }
        }
        boolean gameOver = false;
        for(int i=0;i<9;i++)
        {
            if(gameState[i]==2){
                return;
            }
        }
        gameActive = false; //Nobody won
        winnerStr = "Nobody won.. Click reset to play again";
        TextView status = findViewById(R.id.status);
        status.setText(winnerStr);
        return;
    }
    public void onTap(View view)
    {
        ImageView ig = (ImageView)view;
        int cellNo = Integer.parseInt(ig.getTag().toString());
        if(gameState[cellNo]==2 && gameActive)
        {
            gameState[cellNo] = activePlayer;
            if(activePlayer==1)
            {    ig.setImageResource(R.drawable.x);
                activePlayer=0;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else
            {
                ig.setImageResource(R.drawable.o);
                activePlayer=1;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
        }
        else
        {
            //Cell is not empty
            String msg;
            if(gameActive)
                msg = "This position is already filled";
            else
                msg = "Game over.. Click Reset to play again!";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        gameCheckWin();

    }

    public void gameReset(View view)
    {
        gameInitialize();
    }
}