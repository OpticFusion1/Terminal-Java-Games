package com.stuin.instant_chess;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import gameloader.Game;
import gameloader.GameList;

public class GameActivity extends Activity {
    private TextView[][] board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Display.load(this);

        new GameList().set(2);
        Game.setup();

        findViewById(R.id.Relative).post(new Runnable() {
            @Override
            public void run() {
                makeBoard();
            }
        });
    }

    void setBoard() {
        if(Game.redTurn) findViewById(R.id.Relative).setBackgroundColor(Color.BLACK);
        else findViewById(R.id.Relative).setBackgroundColor(Color.WHITE);

        boolean black = true;
        for(TextView[] l : board) {
            black = !black;
            for (TextView t : l) {
                black = !black;
                t.setText("");
                t.setOnClickListener(null);
                t.setBackgroundColor(0);
            }
        }
        for(Piece p : set) p.showPosition(win);
    }

    @Override
    public void onBackPressed() {

    }

    private void makeBoard() {
        int scale = findViewById(R.id.Relative).getHeight();
        if(scale > findViewById(R.id.Relative).getWidth()) scale = findViewById(R.id.Relative).getWidth();
        scale = scale / 8;

        board = new TextView[Game.size][Game.size];

        boolean black = true;
        GridLayout gridLayout = (GridLayout) findViewById(R.id.ChessBoard);
        int x = 0;
        int y = 0;
        int i = 0;
        for(TextView[] l : board) {
            black = !black;
            for(TextView t : l) {
                t = new TextView(this);
                t.setTextSize(scale / 4);
                t.setId(i);
                t.setWidth(scale);
                t.setHeight(scale);
                black = !black;

                FrameLayout frameLayout = new FrameLayout(this);
                frameLayout.setMinimumHeight(scale);
                frameLayout.setMinimumWidth(scale);
                frameLayout.addView(t);

                if (black) frameLayout.setBackgroundColor(Color.BLACK);
                else frameLayout.setBackgroundColor(Color.WHITE);

                gridLayout.addView(frameLayout);
                board[x][y] = t;
                x++;
                i++;
            }
            y++;
            x = 0;
        }

        setBoard();
    }
}
