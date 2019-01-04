package com.itproject.hoadt.b5MoveLoop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.itproject.hoadt.b4rectangle.R;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgLeft;
    private ImageView imgRight;
    private ImageView imgShoot;
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        imgLeft = findViewById(R.id.imgLeft);
        imgRight = findViewById(R.id.imgRight);
        imgShoot = findViewById(R.id.imgPause);
        drawView = findViewById(R.id.drawview);
        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);
        imgShoot.setOnClickListener(this);

        imgLeft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                drawView.moveLeft();
                return true;
            }
        });

        imgRight.setOnLongClickListener(new View.OnLongClickListener() {
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
            case R.id.imgLeft: {
                drawView.moveLeft();
                break;
            }
            case R.id.imgRight: {
                drawView.moveRight();
                break;
            }
            case R.id.imgPause:{
                drawView.pause();
                break;
            }
        }
    }

}
