package ua.dnigma.edrquery.data;

/**
 * Created by Даниил on 18.12.2017.
 */

public class EdrInterestSchema {

    public static final String TABLE_NAME = "EdrInterestTable";
    public static final String ID = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PLACE_OF_WORK = "placeOfWork";
    public static final String POSITION = "position";
    public static final String LINK_PDF = "LinkPDF";

    public static final String CREATE_TABLE_COMPANY = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " ("
            + ID + " TEXT, "
            + FIRST_NAME + " TEXT, "
            + LAST_NAME + " TEXT, "
            + PLACE_OF_WORK + " TEXT, "
            + POSITION + " TEXT, "
            + LINK_PDF + " TEXT);";

}
