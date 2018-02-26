package ua.dnigma.edrquery.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ua.dnigma.edrquery.model.Item;

import static android.content.ContentValues.TAG;

/**
 * Created by Даниил on 04.01.2018.
 */

public class EdrFavoriteTableDao {

    private DBHelper dbHelper;

    public EdrFavoriteTableDao(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void addInterestingEDRItemToDB(Item item) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getSqLiteDatabase();
        ContentValues contentValues = new ContentValues();
//        long rawID;

        contentValues.put(EdrInterestSchema.ID, item.getId());
        contentValues.put(EdrInterestSchema.FIRST_NAME, item.getFirstname());
        contentValues.put(EdrInterestSchema.LAST_NAME, item.getLastname());
        contentValues.put(EdrInterestSchema.PLACE_OF_WORK, item.getPlaceOfWork());
        contentValues.put(EdrInterestSchema.POSITION, item.getPosition());
        contentValues.put(EdrInterestSchema.LINK_PDF, item.getLinkPDF());

        sqLiteDatabase.insert(EdrInterestSchema.TABLE_NAME, EdrInterestSchema.COMMENTS,
                contentValues);
//        Log.d(TAG, "addInterestingEDRItemToDB: " + rawID + " " + item.getLastname());

        sqLiteDatabase.close();
    }


    public void deleteNotInterestingEDRItemFromDB(String id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getSqLiteDatabase();

        sqLiteDatabase.delete(EdrInterestSchema.TABLE_NAME, EdrInterestSchema.ID + "=?",
                new String[]{id});

        sqLiteDatabase.close();
    }

    public boolean isContainsId(String id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getSqLiteDatabase();

        Cursor cursor = sqLiteDatabase.query(EdrInterestSchema.TABLE_NAME, new String[]{EdrInterestSchema.ID},
                EdrInterestSchema.ID + " =?", new String[]{id}, null, null, null);

        cursor.moveToFirst();
        cursor.moveToNext();
        return cursor.getCount() > 0;
    }

    public List<Item> showFavoriteItems() {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getSqLiteDatabase();

        Cursor cursor = sqLiteDatabase.query(EdrInterestSchema.TABLE_NAME, null,
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Item favoriteItem = new Item();
            favoriteItem.setId(cursor.getString(0));
            favoriteItem.setFirstname(cursor.getString(1));
            favoriteItem.setLastname(cursor.getString(2));
            favoriteItem.setPlaceOfWork(cursor.getString(3));
            favoriteItem.setPosition(cursor.getString(4));
            favoriteItem.setLinkPDF(cursor.getString(5));
            favoriteItem.setComents(cursor.getString(6));

            itemList.add(favoriteItem);
        }

        sqLiteDatabase.close();

        return itemList;
    }

    public void updateComments(String id, String comment) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getSqLiteDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EdrInterestSchema.COMMENTS, comment);
        sqLiteDatabase.update(EdrInterestSchema.TABLE_NAME, contentValues,
                EdrInterestSchema.ID + "=?", new String[]{id});

        sqLiteDatabase.close();

    }
}




