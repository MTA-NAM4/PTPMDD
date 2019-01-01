package com.hoadt.test1.soundpool;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.hoadt.test1.R;

/**
 * Created by HoaDT on 11/2/2018. - Nhạc sỹ dương cầm
 */
public class SoundPoolActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private int sound1, sound2, sound3, sound4;
    private int sound1Stream, sound2Stream, sound3Stream, sound4Stream;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(4)//thiet lap so luong am thanh toi da trong soundpool
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        }
        //hàm load trả về 1 luồng âm thanh
        sound1 = soundPool.load(this, R.raw.boom, 1);//priority: giá trị mức độ ưu tiên càng thấp thì càng cao
        sound2 = soundPool.load(this, R.raw.mo, 1);
        sound3 = soundPool.load(this, R.raw.music, 1);
        sound4 = soundPool.load(this, R.raw.shoot, 1);

    }

    public void play(View v) {
        switch (v.getId()) {
            case R.id.sound1Button:
                sound1Stream = soundPool.play(sound1, 100, 100, 1, 0, 1);
                soundPool.pause(sound2Stream);
                soundPool.pause(sound3Stream);
                soundPool.pause(sound4Stream);
                break;
            case R.id.sound2Button:
                sound2Stream = soundPool.play(sound2, 100, 100, 1, 0, 1);
                soundPool.pause(sound1Stream);
                soundPool.pause(sound3Stream);
                soundPool.pause(sound4Stream);
                break;
            case R.id.sound3Button:
                sound3Stream = soundPool.play(sound3, 100, 100, 1, 0, 1);
                soundPool.pause(sound1Stream);
                soundPool.pause(sound2Stream);
                soundPool.pause(sound4Stream);
                break;
            case R.id.sound4Button:
                sound4Stream = soundPool.play(sound4, 100, 100, 1, 0, 1);
                soundPool.pause(sound1Stream);
                soundPool.pause(sound2Stream);
                soundPool.pause(sound3Stream);
                break;
        }
    }

}
