package com.hoadt.test1.customview;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hoadt.test1.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLeft;
    private Button btnRight;
    private Button btnShoot;
    private DrawView drawView;
    private boolean sound_on = false;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        btnShoot = findViewById(R.id.btnShoot);
        drawView = findViewById(R.id.drawview);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnShoot.setOnClickListener(this);

        btnLeft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                drawView.moveLeft();
                return true;
            }
        });

        btnRight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                drawView.moveRight();
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeft: {
                drawView.moveLeft();
                break;
            }
            case R.id.btnRight: {
                drawView.moveRight();
                break;
            }
            case R.id.btnShoot: {
                drawView.shoot();
                break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sound_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_sound) {
            if (sound_on) {
                drawView.stopSound();
                item.setIcon(R.drawable.ic_volume_off);
            } else {
               drawView.playSound();
                item.setIcon(R.drawable.ic_volume_up);
            }
            sound_on = !sound_on;
        }
        return true;
    }
}
