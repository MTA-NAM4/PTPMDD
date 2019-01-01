package com.itproject.hoadt.b4Reactangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.itproject.hoadt.b4rectangle.R;

/**
 * Created by HoaDT on 10/12/2018.
 * SerfuceView: giao việc hiển thị cho 1 luồng riêng=> việc hiển thị các đối tượng đồ họa nhanh chóng hơn
 */

public class DrawView extends SurfaceView {
    private int width;
    private int height;
    private Reactangle reactangle;

    private GameLoopThread gameLoopThread;
    private SurfaceHolder holder;
    private SoundPool soundPool;
    private int sound_warning;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        reactangle = new Reactangle(Color.RED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)//thiet lap so luong am thanh toi da trong soundpool
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        sound_warning = soundPool.load(context, R.raw.sound_warning, 1);

        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
                if (reactangle != null) {
                    width = w;
                    height = h;
                    reactangle.setBound(width, height);
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //dừng vẽ
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();//vòng lặp kết thúc khi game thực hiện xong
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        reactangle.setBound(width, height);
    }

    /**
     * Khác với onDraw game cũ:
     * - ko có sleep tạm dừng
     * - ko có invalidate() vẽ lại view-> giao cho Thread
     *
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawGameBoard(canvas);
    }

    /**
     * Vẽ lại màn hình game
     *
     * @param canvas
     */
    public void drawGameBoard(Canvas canvas) {
        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint2);
        reactangle.draw(canvas);
    }

    public void moveLeft() {
        if (!reactangle.moveLeft()) {
            soundPool.play(sound_warning, 1, 1, 1, 0, 1);
        }
    }

    public void moveRight() {
        if (!reactangle.moveRight()) {

            soundPool.play(sound_warning, 1, 1, 1, 0, 1);
        }
    }
}
