import android.app.Application;

import ua.dnigma.edrquery.data.DBHelper;

/**
 * Created by Даниил on 22.01.2018.
 */

public class MyApplication extends Application {

    DBHelper dbHelper;
    private boolean full;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public DBHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(this);
        }
        return dbHelper;
    }
}
