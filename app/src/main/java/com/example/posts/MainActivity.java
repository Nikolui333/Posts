package com.example.posts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonNew(View view) {
        Intent intent = new Intent(this, NewEntry.class);
        startActivity(intent);
    }

    public void buttonList(View view) {
        Intent intent = new Intent(this, postsList.class);
        startActivity(intent);
    }
}
