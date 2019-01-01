package com.hoadt.test1.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.hoadt.test1.R;

/**
 * Created by HoaDT on 10/12/2018.
 */
public class AndroidGuy {
    private int x;
    private int y;
    private int stepY = 5;//buoc di chuyển
    private Bitmap androidGuy;
    private int width;
    private int height;
    private Context context;

    public AndroidGuy(Context context) {
        this.context = context;
        androidGuy = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher),
                50,
                50,
                false);//true làm trơn - phóng to
    }

    public boolean move() {
        y = y + stepY;
        if (y > height) {
            return false;
        }

        return true;
    }

    //khi bị bắn thì reset lại vị trí của hình vẽ
    public void reset() {
        y = 0;
        x = (int) ((width - 50) * Math.random());//sát bên phải màn hình
    }

    //gọi đến 1 lần
    public void setBound(int width, int height) {
        this.width = width;
        this.height = height;

        y = 0;
        x = (int) ((width - 50) * Math.random());
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(androidGuy, x, y, null);
    }

    //lưu trữ các thông tin về hình chữ nhật
    public RectF getRectF() {
        return new RectF(x, y, x + 50, y + 50);
    }
}
