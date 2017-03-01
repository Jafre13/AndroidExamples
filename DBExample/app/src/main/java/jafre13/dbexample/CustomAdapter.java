package jafre13.dbexample;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


/**
 * Created by jakob on 8/23/2016.
 */
public class CustomAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;
    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        cursorInflater = LayoutInflater.from(context);
        return cursorInflater.inflate(R.layout.name_layout,viewGroup,false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView id_tv = (TextView) view.findViewById(R.id.id_text_view);
        TextView fname_tv = (TextView) view.findViewById(R.id.fname_text_view);
        TextView lname_tv = (TextView) view.findViewById(R.id.lname_text_view);
        id_tv.setText(cursor.getString(cursor.getColumnIndex("_id")));
        fname_tv.setText(cursor.getString(cursor.getColumnIndex("fname")));
        lname_tv.setText(cursor.getString(cursor.getColumnIndex("lname")));

    }
}
