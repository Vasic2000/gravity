package ru.vasic2000.gravity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.vasic2000.my_framework.LoopFW;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoopFW loopFW = new LoopFW();
        loopFW.startGame();
    }
}