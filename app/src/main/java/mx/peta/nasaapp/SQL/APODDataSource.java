package mx.peta.nasaapp.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.design.widget.Snackbar;
//import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mx.peta.nasaapp.model.Apod;

/**
 * Created by rayo on 8/16/16.
 */
public class APODDataSource {
    private SQLiteDatabase db;

    public APODDataSource(Context context) {
        SqLiteHelper helper = new SqLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public int cuantosRegistros(String tableName) {
        String count   = "SELECT count(*) FROM " + tableName;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        mcursor.close();
        return icount;
    }

    public void writeAPOD(Apod apod) {
        String QUERY = "select " +
                SqLiteHelper.APP_COLUMN_ID            + ", " +
                SqLiteHelper.APP_TITLE                +
                " from " + SqLiteHelper.APP_TABLE_NAME + " where " + SqLiteHelper.APP_TITLE +
                " = ?";
        // rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});
        Cursor cursor = db.rawQuery(QUERY, new String[] {apod.getTitle()});
        if (cursor.moveToFirst()) {
            // No hay que hacer nada el APOD ya está en la base de datos
        } else {
            db.beginTransaction();
            try {
                ContentValues values = new ContentValues();
                values.put(SqLiteHelper.APP_COPYRIGHT, apod.getCopyright());
                values.put(SqLiteHelper.APP_DATE, apod.getDate());
                values.put(SqLiteHelper.APP_EXPLANATION, apod.getExplanation());
                values.put(SqLiteHelper.APP_HDURL, apod.getHdurl());
                values.put(SqLiteHelper.APP_MEDIA_TYPE, apod.getMediaType());
                values.put(SqLiteHelper.APP_SERVICE_VERSION, apod.getServiceVersion());
                values.put(SqLiteHelper.APP_TITLE, apod.getTitle());
                values.put(SqLiteHelper.APP_URL, apod.getUrl());
                //Log.d("SQL", apod.getExplanation());
                //Log.d("SQL", apod.getUrl());
                db.insert(SqLiteHelper.APP_TABLE_NAME, null, values);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }

    public List<Apod> getAllItems()
    {
        List<Apod> apodList = new ArrayList<>();
        Cursor cursor = db.query(SqLiteHelper.APP_TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Apod apod = new Apod();
            apod.setId(cursor.getInt(cursor.getColumnIndexOrThrow(SqLiteHelper.APP_COLUMN_ID)));
            apod.setCopyright(cursor.getString(cursor.getColumnIndexOrThrow(SqLiteHelper.APP_COPYRIGHT)));
            apod.setDate(cursor.getString(cursor.getColumnIndexOrThrow(SqLiteHelper.APP_DATE)));
            apod.setExplanation(cursor.getString(cursor.getColumnIndexOrThrow(SqLiteHelper.APP_EXPLANATION)));
            apod.setHdurl(cursor.getString(cursor.getColumnIndexOrThrow(SqLiteHelper.APP_HDURL)));
            apod.setMediaType(cursor.getString(cursor.getColumnIndexOrThrow(SqLiteHelper.APP_MEDIA_TYPE)));
            apod.setServiceVersion(cursor.getString(cursor.getColumnIndexOrThrow(SqLiteHelper.APP_SERVICE_VERSION)));
            apod.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(SqLiteHelper.APP_TITLE)));
            apod.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(SqLiteHelper.APP_URL)));
            apodList.add(apod);
        }
        return apodList;
    }
}
