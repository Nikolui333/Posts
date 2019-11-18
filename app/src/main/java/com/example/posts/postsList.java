package com.example.posts;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class postsList extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DBHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);
        recyclerView = (RecyclerView) findViewById(R.id.rv_num);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        SQLiteDatabase db = mDataBaseHelper.getReadableDatabase();
        String sql = "select name, text from text";
        Cursor c = db.rawQuery(sql, new String[]{});
        List<TextRow> myDataset = new ArrayList<>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int idId= c.getColumnIndex("_id");
                    int idName= c.getColumnIndex("name");
                    int idText= c.getColumnIndex("text");
                    myDataset.add(new TextRow(c.getInt(idId),c.getString(idName),c.getString(idText)));
                } while (c.moveToNext());
            }
            c.close();
        }

        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        if(this.mDataBaseHelper == null){
            this.mDataBaseHelper = new DBHelper(this);
        }
        db = mDataBaseHelper.getWritableDatabase();
        db.execSQL("insert into text (_id,name,text)values(1,'MyFirstName','MyFirstText')");
    }
}
