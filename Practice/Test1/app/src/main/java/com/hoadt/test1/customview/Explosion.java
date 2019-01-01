package com.hoadt.test1.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.hoadt.test1.R;

/**
 * Created by HoaDT on 10/12/2018.
 * Lớp lưu trữ đạn nổ
 */
public class Explosion {
    private int x;
    private int y;
    private Bitmap explosion;
    private int count = 24;//1s show 24 hình ảnh

    public Explosion(int x, int y, Context context) {
        this.x = x;
        this.y = y;
        this.explosion = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion),
                50,
                50,
                false);
    }

    public boolean draw(Canvas canvas) {
        count--;
        if (count >= 0) {
            canvas.drawBitmap(explosion, x, y, null);
            return true;
        }
        return false;
    }
}
