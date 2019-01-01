package com.hoadt.test1.customview;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by HoaDT on 10/12/2018.
 * khẩu pháo di chuyển trái, phải và bắn
 */

public class Cannon {
    private int x;
    private int stepX = 15;//bước di chuyển
    private Paint paint;
    //kích thước màn chơi
    private int width;
    private int height;

    public Cannon(int Color) {
        paint = new Paint(Color);
    }

    public void setBound(int width, int height) {
        this.width = width;
        this.height = height;

        x = width / 2;
    }

    public void moveLeft() {
        //50 là chiều rộng khẩu pháo
        if (x - stepX >= 50) {
            x = x - stepX;
        } else {

        }
    }

    public void moveRight() {
        if (x + stepX <= width - 50) {
            x = x + stepX;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(x, height, x, height - 100, paint);
        canvas.drawRect(x - 25, height - 50, x + 25, height - 25, paint);
        canvas.drawRect(x - 50, height - 25, x + 50, height, paint);
    }

    public int getX() {
        return x;
    }
}
