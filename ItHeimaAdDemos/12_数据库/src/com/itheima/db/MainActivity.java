package com.itheima.db;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        MySqliteOpenHelper helper = new MySqliteOpenHelper(this);
        
        SQLiteDatabase db = helper.getWritableDatabase();
    }

}
