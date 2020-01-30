package com.example.posts;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

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

    public void buttonRandomnicity(View view){

        //try {
            DBHelper mDataBaseHelper = new DBHelper(this);
            SQLiteDatabase db = mDataBaseHelper.getReadableDatabase();//чтение базы данных
            long rowCount  = DatabaseUtils.queryNumEntries(db, "textTable"); // получаем колличество строк в базе данных
            rowCount++;
            int rand = (int) ((int) rowCount * Math.random()); // получаем случайное число, которое меньше либо равно колличеству строк в базе данных

            if (rand<1){ // для того, чтобы программа не обращалась к не существующей строке
                rand++;
            }

            Cursor cursor = db.query("textTable", // полйчаем нужные данные из базы данных
                    new String[] {"name", "text"},
                    "_id = ?",
                    new String[] {Integer.toString(rand)},
                    null, null, null);

            Intent intent = new Intent(this, UploadText.class);

            if(cursor.moveToFirst()){
                String name = cursor.getString(0);
                String text = cursor.getString(1);

                intent.putExtra("name", name); // передаём данные в новую активность
                intent.putExtra("text", text);
                startActivity(intent);
            }
            cursor.close();
            db.close();

      /*  }
            catch(Exception e) {

            }*/
    }

}

