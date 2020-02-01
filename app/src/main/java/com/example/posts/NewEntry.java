package com.example.posts;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewEntry extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnClear;
    EditText etName, etText;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        btnAdd = (Button) findViewById(R.id.buttonSave);
        btnAdd.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnDell);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.editText2);
        etText = (EditText) findViewById(R.id.editText1);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String text = etText.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {

            case R.id.buttonSave:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_TEXT, text);

                database.insert(DBHelper.TABLE_TEXT, null, contentValues);
                break;

            case R.id.btnDell:
                database.delete(DBHelper.TABLE_TEXT, null, null);
                break;
        }
        dbHelper.close();
    }
}

