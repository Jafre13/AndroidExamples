package jafre13.dbexample;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by jakob on 8/23/2016.
 */
public class DBHandler extends SQLiteOpenHelper  {
    private static final String DB_NAME ="Names_Examples";
    private static final int VERSION = 1;
    public DBHandler(Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS NAMES (" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " fname VARCHAR," +
                " lname VARCHAR);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS NAMES");
        onCreate(db);
    }


}
