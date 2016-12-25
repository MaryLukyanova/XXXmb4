package com.example.igor.classmates;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import 	android.content.ContentValues;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends ListActivity {
    String[] act = { "Info", "Add record", "Edit last record"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, act);
        setListAdapter(adapter);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete("mytable", null, null);

        ContentValues cv = new ContentValues();
        SQLiteDatabase dbLSQ = dbHelper.getWritableDatabase();
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm");
        String[] NSFN = new String[]{"Aleksandr", "Keyko", "Iosifovich",
                "Svetlana", "Voltok", "Panasanavafa",
                "Sergey", "Serega", "Popovich",
                "Rakhmed", "Rakhmud", "Eduardovich",
                "Solon" ,"Solonovich", "Feeeeedya"};
        for(int i=0; i<14;) {
            cv.put("name", NSFN[i]);
            cv.put("name1", NSFN[i+1]);
            cv.put("name2", NSFN[i+2]);
            i+=3;
            cv.put("data", String.valueOf(format1.format(new Date())));
            long rowID = dbLSQ.insert("mytable", null, cv);
        }



        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, Info.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, Add.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, Edit.class);
                        startActivity(intent2);
                        break;
                }
                Toast.makeText(getApplicationContext(), "Your choose " +
                                parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        getListView().setOnItemClickListener(itemListener);
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "name1 text,"
                    + "name2 text,"
                    + "data text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}


