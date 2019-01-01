package com.hoadt.test1.customview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by HoaDT on 10/12/2018.
 * vẽ đạn
 */
public class Bullet {
    private int x;
    private int y;
    private int radius = 5;
    private int stepY = 15;
    private Paint paint;
    //hình tròn tâm x,y bán kính radius

    public Bullet(int color, int x, int y) {
        this.x = x;
        this.y = y;
        this.paint = new Paint(color);
    }

    public boolean move() {
        y = y - stepY;
        if (y < radius)
            return false;

        return true;
    }

    public void remove(){

    }
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    public RectF getRectF() {
        return new RectF(x - radius, y - radius, x + radius, y + radius);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
