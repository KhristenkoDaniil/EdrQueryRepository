package ua.dnigma.edrquery.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ua.dnigma.edrquery.model.Item;

/**
 * Created by Даниил on 04.01.2018.
 */

public class EdrInterestTableUpdate {

    private DBHelper dbHelper;

    public EdrInterestTableUpdate(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public void addInterestingEDRItemToDB(Item item) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getSqLiteDatabase();
        sqLiteDatabase.beginTransaction();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EdrInterestSchema.ID, item.getId());
        contentValues.put(EdrInterestSchema.FIRST_NAME, item.getFirstname());
        contentValues.put(EdrInterestSchema.LAST_NAME, item.getLastname());
        contentValues.put(EdrInterestSchema.PLACE_OF_WORK, item.getPlaceOfWork());
        contentValues.put(EdrInterestSchema.POSITION, item.getPosition());
        contentValues.put(EdrInterestSchema.LINK_PDF, item.getLinkPDF());

        sqLiteDatabase.insert(EdrInterestSchema.TABLE_NAME, EdrInterestSchema.COMEMNTS,
                contentValues);

        sqLiteDatabase.endTransaction();
//        sqLiteDatabase.setTransactionSuccessful();

        sqLiteDatabase.close();
    }

    public void deleteNotInterestingEDRItemFromDB(String id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getSqLiteDatabase();
//TODO add transaction
        sqLiteDatabase.delete(EdrInterestSchema.TABLE_NAME, EdrInterestSchema.ID + id,
                null);

        sqLiteDatabase.close();
    }

//    public void deleteNotInterestingEDRItemFromDB(String id) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getSqLiteDatabase();
//
//        sqLiteDatabase.delete(EdrInterestSchema.TABLE_NAME, EdrInterestSchema.ID + "=?",
//                new String[] {id});
//    }




}




