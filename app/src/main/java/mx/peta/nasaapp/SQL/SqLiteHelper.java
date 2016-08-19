package mx.peta.nasaapp.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rayo on 8/15/16.
 */
public class SqLiteHelper extends SQLiteOpenHelper {
    private final static String APP_DATABASE_NAME           = "SpaceImagesFavoritos";
    private final static int    APP_DATABASE_VERSION        = 1;
    public static final String  APP_TABLE_NAME              = "APOD_Table";
    public static final String  APP_COLUMN_ID               = BaseColumns._ID;
    public static final String  APP_COPYRIGHT               = "copyright";
    public static final String  APP_DATE                    = "date";
    public static final String  APP_EXPLANATION             = "explanetion";
    public static final String  APP_HDURL                   = "hdurl";
    public static final String  APP_MEDIA_TYPE              = "media_type";
    public static final String  APP_SERVICE_VERSION         = "service_version";
    public static final String  APP_TITLE                   = "title";
    public static final String  APP_URL                     = "url";

    private static final String CREATE_APP_TABLE = "create table " + APP_TABLE_NAME + "(" +
            APP_COLUMN_ID +                 " integer primary key autoincrement, " +
            APP_COPYRIGHT +                 " text not null, " +
            APP_DATE +                      " text not null, " +
            APP_EXPLANATION +               " text not null, " +
            APP_HDURL +                     " text not null, " +
            APP_MEDIA_TYPE +                " text not null, " +
            APP_SERVICE_VERSION +           " text not null, " +
            APP_TITLE +                     " text not null, " +
            APP_URL +                       " text not null ) ";

    public SqLiteHelper (Context context) {
        super(context, APP_DATABASE_NAME, null, APP_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_APP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
