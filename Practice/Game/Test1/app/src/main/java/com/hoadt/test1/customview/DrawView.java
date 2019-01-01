package com.hoadt.test1.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hoadt.test1.R;

import java.util.ArrayList;

/**
 * Created by HoaDT on 10/12/2018.
 * SerfuceView: giao việc hiển thị cho 1 luồng riêng=> việc hiển thị các đối tượng đồ họa nhanh chóng hơn
 */

public class DrawView extends SurfaceView {
    private Context context;
    private int width;
    private int height;
    private AndroidGuy androidGuy;
    private Cannon cannon;
    private ArrayList<Bullet> bulletArrayList;
    private ArrayList<Explosion> explosionArrayList;

    private GameLoopThread gameLoopThread;
    private SurfaceHolder holder;

    MediaPlayer player;

    private int sound_background, soundBG;
    private SoundPool soundPool;
    private int sound_shoot, sound_boom, sound_up, sound_down;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        androidGuy = new AndroidGuy(context);
        cannon = new Cannon(Color.RED);
        bulletArrayList = new ArrayList<>();
        explosionArrayList = new ArrayList<>();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(5)//thiet lap so luong am thanh toi da trong soundpool
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        sound_shoot = soundPool.load(context, R.raw.shoot, 1);
        sound_boom = soundPool.load(context, R.raw.boom, 1);
        sound_up = soundPool.load(context, R.raw.beep4, 1);
        sound_down = soundPool.load(context, R.raw.beep4, 1);

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
                if (androidGuy != null && cannon != null) {
                    width = w;
                    height = h;
                    androidGuy.setBound(width, height);
                    cannon.setBound(width, height);
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
        androidGuy.setBound(width, height);
        cannon.setBound(width, height);
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

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        drawGameBoard(canvas);
//        try {
//            Thread.sleep(30);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        invalidate();
//    }

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

        cannon.draw(canvas);
        androidGuy.draw(canvas);
        for (int i = bulletArrayList.size() - 1; i >= 0; i--) {
            Bullet bullet = bulletArrayList.get(i);
            bullet.draw(canvas);
            if (RectF.intersects(androidGuy.getRectF(), bullet.getRectF())) {
                //bắn trúng
                androidGuy.reset();
                bulletArrayList.remove(i);
                Explosion explosion = new Explosion(bullet.getX(), bullet.getY(), context);
                explosionArrayList.add(explosion);
                soundPool.play(sound_boom, 1, 1, 1, 0, 1);
//                player = MediaPlayer.create(context, R.raw.boom);
//                player.start();
            } else {
                //ko trúng thì đạn tiếp tục bay lên
                if (!bullet.move()) {
                    soundPool.play(sound_up, 1, 1, 1, 0, 1);
                    bulletArrayList.remove(i);
                }
            }
        }
        for (int i = explosionArrayList.size() - 1; i >= 0; i--) {
            Explosion explosion = explosionArrayList.get(i);
            if (explosion.draw(canvas) == false)
                explosionArrayList.remove(i);

        }
        if (androidGuy.move() == false) {
            androidGuy.reset();
            soundPool.play(sound_down, 1, 1, 1, 0, 1);
        }
    }

    public void moveLeft() {
        cannon.moveLeft();
    }

    public void moveRight() {
        cannon.moveRight();
    }

    public void shoot() {
        Bullet bullet = new Bullet(Color.GREEN, cannon.getX(), height - 100);
        bulletArrayList.add(bullet);
        soundPool.play(sound_shoot, 1, 1, 1, 0, 1);
//        player = MediaPlayer.create(context, R.raw.shoot);
//        player.start();
    }

    public void stopSound() {
        if (player != null) {
            player.stop();
            player.release();//gp bo nho player
            player = null;
        }
    }

    public void playSound() {
        if (player == null) {
            player = MediaPlayer.create(context, R.raw.mo);
        }
        player.start();
    }

}
