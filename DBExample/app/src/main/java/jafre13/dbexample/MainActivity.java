package jafre13.dbexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    DBHandler dbHandler;
    EditText fname_tv,lname_tv, id_tv;
    Button addButton, deleteButton;
    SimpleCursorAdapter simpleCursorAdapter;


    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DBHandler(this);
        lv = (ListView) findViewById(R.id.listView);
        id_tv = (EditText) findViewById(R.id.delete_id);
        lname_tv = (EditText) findViewById(R.id.lname_edit_text);
        fname_tv = (EditText) findViewById(R.id.fname_edit_text);
        addButton = (Button) findViewById(R.id.add_name_button);
        deleteButton = (Button) findViewById(R.id.delete_name_button);
        updateNames();
    }

    public void updateNames(){
        db = dbHandler.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id,fname,lname FROM NAMES",null);
        cursor.moveToFirst();
        TextView test = (TextView) findViewById(R.id.text);
        CustomAdapter adapter = new CustomAdapter(this,cursor,0);
        lv.setAdapter(adapter);

//        while(!cursor.isAfterLast()){
//            dostuff();
//            cursor.moveToNext();
//        }
    }

    public void getNames(View view){
       updateNames();
    }

    public void deleteNameClick(View view) {
        db = dbHandler.getWritableDatabase();
        int id = Integer.parseInt(id_tv.getText().toString());
        String[] whereAt = new String[]{String.valueOf(id)};
        db.delete("NAMES","_id = ?",whereAt);
        updateNames();
    }

    public void addNameClick(View view) {
        db = dbHandler.getWritableDatabase();
        String fname = fname_tv.getText().toString();
        String lname = lname_tv.getText().toString();

        ContentValues values = new ContentValues();
        values.put("fname",fname);
        values.put("lname",lname);

        db.insert("NAMES",null,values);
        db.close();
        updateNames();

    }





}
