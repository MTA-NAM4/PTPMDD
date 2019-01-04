package com.itproject.hoadt.b5MoveLoop;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by HoaDT on 10/12/2018.
 * khẩu pháo di chuyển trái, phải và bắn
 */

public class Reactangle {
    private int x;
    private int size;
    private int stepX = 15;//bước di chuyển
    private Paint paint;
    //kích thước màn chơi
    private int width;
    private int height;

    public Reactangle(int Color) {
        paint = new Paint(Color);
    }

    public void setBound(int width, int height) {
        this.width = width;
        this.height = height;

        x = width / 2;
        size = width / 20;
    }

    public boolean moveLeft() {
        //50 là chiều rộng khẩu pháo
        if (x - stepX >= size / 2) {
            x = x - stepX;
            return true;
        }
        return false;
    }

    public boolean moveRight() {
        if (x + stepX <= width - size / 2) {
            x = x + stepX;
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x - size / 2, height / 2 - size / 2, x + size / 2, height / 2 + size / 2, paint);
    }

    public int getX() {
        return x;
    }
}
