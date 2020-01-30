package com.example.posts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UploadText extends AppCompatActivity {

    TextView textView;

    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_text);

        textView = (TextView) findViewById(R.id.name);
        textView2 = (TextView) findViewById(R.id.UpText);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String text = intent.getStringExtra("text");

        textView.setText(name);
        textView2.setText(text);

        textView2.setSelected(true);

    }
}
