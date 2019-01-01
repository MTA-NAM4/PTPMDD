
package com.hoadt.test1.mediaplayer;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.hoadt.test1.R;

/**
 * tạo âm thanh cho bài game
 * hsau tìm về soundpool
 */

public class MediaActivity extends AppCompatActivity {

    RotateAnimation rotateAnimation;
    MediaPlayer player;
    ImageView imageView;
//    SoundPool

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        imageView = findViewById(R.id.imageView);

//        rotateAnimation.setDuration(5000);

//        imageView.setAnimation(rotateAnimation);
    }

    public void Play(View view) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.mo);
        }
        player.start();
    }

    public void Pause(View view) {
        if (player != null) {
            player.pause();
            findViewById(R.id.imageView).setRotation(2);
        }
    }

    public void Stop(View view) {
        if (player != null) {
            player = null;
        }
    }
}
