package com.soros.androidstudynotes.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.soros.androidstudynotes.R;
import com.soros.androidstudynotes.animation.drawableanimation.DrawableAnimationActivity;
import com.soros.androidstudynotes.animation.propertyanimation.PropertyAnimationActivity;
import com.soros.androidstudynotes.animation.viewanimation.ViewAnimationActivity;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void startViewAnimation(View v) {
        Intent intent = new Intent();
        intent.setClass(this, ViewAnimationActivity.class);
        startActivity(intent);
    }

    public void startPropertyAnimation(View v) {
        Intent intent = new Intent();
        intent.setClass(this, PropertyAnimationActivity.class);
        startActivity(intent);
    }

    public void startDrawableAnimation(View v) {
        Intent intent = new Intent();
        intent.setClass(this, DrawableAnimationActivity.class);
        startActivity(intent);
    }
}
