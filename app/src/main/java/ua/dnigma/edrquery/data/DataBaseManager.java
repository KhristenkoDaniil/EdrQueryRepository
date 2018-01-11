package ua.dnigma.edrquery.data;

import android.content.Context;

/**
 * Created by Даниил on 11.01.2018.
 */

public class DataBaseManager {

    private Context context;
    private static DataBaseManager dataBaseManager;
    EdrFavoriteTableDao edrFavoriteTableDao;


    private DataBaseManager(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.context = context;
        this.edrFavoriteTableDao = new EdrFavoriteTableDao(dbHelper);
    }



    public static DataBaseManager getInstance(Context context) {
        if (dataBaseManager == null) {
            dataBaseManager = new DataBaseManager(context);
        }
        return dataBaseManager;
    }

    public EdrFavoriteTableDao getEdrFavoriteTableDao() {
        return edrFavoriteTableDao;
    }
}
