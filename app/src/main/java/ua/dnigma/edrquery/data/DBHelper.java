package ua.dnigma.edrquery.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Даниил on 18.12.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "edrInterestDB";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EdrInterestSchema.CREATE_TABLE_COMPANY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase getSqLiteDatabase() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase;
    }
}
