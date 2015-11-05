package com.example.s.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TicTacMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button topleft, topmiddle, topright, middleleft, middle_middle, middleright, bottomleft, bottom_middle, bottomright;
    private Button[] bArray;
    private Button retry;

    boolean turn = true;
    int turn_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_main);

        topleft = (Button) findViewById(R.id.topleft);
        topmiddle = (Button) findViewById(R.id.topmiddle);
        topright = (Button) findViewById(R.id.topright);
        middleleft = (Button) findViewById(R.id.middleleft);
        middle_middle = (Button) findViewById(R.id.middle_middle);
        middleright = (Button) findViewById(R.id.middleright);
        bottomleft = (Button) findViewById(R.id.bottomleft);
        bottom_middle = (Button) findViewById(R.id.bottom_middle);
        bottomright = (Button) findViewById(R.id.bottomright);

        bArray = new Button[]{topleft, topmiddle, topright, middleleft, middle_middle, middleright, bottomleft, bottom_middle, bottomright};

        for (Button b : bArray) {
            b.setOnClickListener(this);
        }

        retry = (Button) findViewById(R.id.button_retry);
        retry.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_retry)
        {
            resetButtons(true);
            for (Button b : bArray) {
                if (true) {
                    b.setClickable(true);
                }
            }
        }
        else {
            Button b = (Button) v;
            buttonClicked(b);
        }
    }

    private void buttonClicked(Button b) {
        if (turn) {
            b.setText(R.string.symbol_X);
            b.setBackgroundColor(Color.RED);
        } else {
            b.setText(R.string.symbol_O);
            b.setBackgroundColor(Color.BLUE);
        }

        turn_count++;
        b.setClickable(false);
        turn = !turn;

        checkWinner();
    }

    private void checkWinner(){
        boolean winner = false;
        if (topleft.getText() == topmiddle.getText() && topmiddle.getText() == topright.getText() && !topleft.isClickable())
        {
            winner = true;
        }
        else if (middleleft.getText() == middle_middle.getText() && middle_middle.getText() == middleright.getText() && !middleleft.isClickable())
        {
            winner = true;
        }
        else if (bottomleft.getText() == bottom_middle.getText() && bottom_middle.getEditableText() == bottomright.getText() && !bottomleft.isClickable())
        {
            winner = true;
        }

        else if (topleft.getText() == middleleft.getText() && middleleft.getText() == bottomleft.getText() && !topleft.isClickable())
        {
            winner = true;
        }
        else if (topmiddle.getText() == middle_middle.getText() && middle_middle.getText() == bottom_middle.getText() && !topmiddle.isClickable())
        {
            winner = true;
        }
        else if (topright.getText() == middleright.getText() && middleright.getText() == bottomright.getText() && !topright.isClickable())
        {
            winner = true;
        }

        else if (topleft.getText() == middle_middle.getText() && middle_middle.getText() == bottomright.getText() && !topleft.isClickable())
        {
            winner = true;
        }
        else if (topright.getText() == middle_middle.getText() && middle_middle.getText() == bottomleft.getText() && !topright.isClickable())
        {
            winner = true;
        }

        if (winner)
        {
            if (!turn){
                toast("X wins!");
            }
            else {
                toast("O wins!");
            }
            for (Button b : bArray) {
                b.setClickable(false);
            }
        }
        else if (turn_count == 9)
        {
            toast("IT'S' A DRAW!");
        }
    }


    private void resetButtons(boolean disable) {
        for (Button b : bArray) {
            if (disable) {
                b.setBackgroundResource(android.R.drawable.btn_default);
                b.setText(" ");
                turn_count = 0;
            }
        }
    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }




}