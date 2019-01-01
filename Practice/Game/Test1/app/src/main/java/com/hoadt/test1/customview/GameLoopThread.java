package com.hoadt.test1.customview;

import android.graphics.Canvas;

/**
 * Created by HoaDT on 10/19/2018.
 * đảm nhiệm việc điều khiển hiển thị các đối tượng view
 */
public class GameLoopThread extends Thread {
    private DrawView drawView;
    private boolean running = false;

    public GameLoopThread(DrawView view) {
        this.drawView = view;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        super.run();
        while (running) {
            Canvas canvas = null;
            try {
                canvas = drawView.getHolder().lockCanvas();
                //lockCanvas:
                //tránh việc canvas có nhiều luồng can thiệp vào
                //synchronized: toàn bộ công việc trong này thực hiện xong thì mới thực hiện cái khác
                synchronized (drawView.getHolder()) {
                    if (canvas != null) {
                        drawView.draw(canvas);
                    }
                }
            } finally {//luôn thực hiện khối này
                if (canvas != null) {
                    //mở khóa canvas
                    drawView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
