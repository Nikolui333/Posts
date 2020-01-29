package com.example.posts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class postsList extends AppCompatActivity implements Serializable {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DBHelper mDataBaseHelper;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);

        recyclerView = (RecyclerView) findViewById(R.id.rv_num); //находим список

        layoutManager = new LinearLayoutManager(this);  //задаёт способ распределения элементов списка (в данном случае горизонтально сверху вниз)
        recyclerView.setLayoutManager(layoutManager);

        mDataBaseHelper = new DBHelper(this);

        if(this.mDataBaseHelper == null){
            this.mDataBaseHelper = new DBHelper(this);
        }

        SQLiteDatabase db = mDataBaseHelper.getReadableDatabase();//чтение базы данных
        String sql = "select name, text from textTable";//получение доступа к данным в таблице
        Cursor c = db.rawQuery(sql, new String[]{});//извлечение данных из таблицы
        List<TextRow> myDataset = new ArrayList<>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int idName= c.getColumnIndex("name");
                    int idText= c.getColumnIndex("text");
                    myDataset.add(new TextRow(c.getString(idName),c.getString(idText)));
                } while (c.moveToNext());
            }
            c.close();
        }

        try {
            mAdapter = new MyAdapter(myDataset, this);
            recyclerView.setAdapter(mAdapter);
        }catch (Exception ex){

        }
    }
}