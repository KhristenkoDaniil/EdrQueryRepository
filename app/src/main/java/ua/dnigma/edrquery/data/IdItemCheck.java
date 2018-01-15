package ua.dnigma.edrquery.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Даниил on 11.01.2018.
 */

public class IdItemCheck {
    DBHelper dbHelper;

    public IdItemCheck(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public boolean  idItemCheckInDb(String id) {


        SQLiteDatabase sqLiteDatabase = dbHelper.getSqLiteDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + EdrInterestSchema.TABLE_NAME +
                " WHERE " + EdrInterestSchema.ID + " = " + id, null);
        return cursor.getCount()>0;


    }
}
