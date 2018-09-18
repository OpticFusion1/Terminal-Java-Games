package com.stuin.instant_chess;

import android.view.View;
import android.widget.TextView;
import gameloader.Game;
import gameloader.base.Point;

public class Square extends TextView {
    private Point point;
    private GameActivity activity;

    public Square(GameActivity activity, Point point) {
        super(activity);
        this.point = point;
        this.activity = activity;
    }

    OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Game.select(point);
        }
    };
}
