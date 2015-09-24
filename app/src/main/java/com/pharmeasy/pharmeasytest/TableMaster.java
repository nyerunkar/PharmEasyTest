package com.pharmeasy.pharmeasytest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nilesh on 24/09/15.
 */
public class TableMaster extends SQLiteOpenHelper
{
    private static final int 	DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pharmeasy.db";

    public static final String MASTER_DATA	=   "master_DATA";

    private static final String DATA_KEY_ID = "key_id";
    private static final String DATA_id = "id";
    private static final String DATA_hkpDrugCode = "hkpDrugCode";
    private static final String DATA_mfId = "mfId";
    private static final String DATA_label = "label";
    private static final String DATA_name = "name";
    private static final String DATA_type = "type";
    private static final String DATA_packSize = "packSize";
    private static final String DATA_manufacture = "manufacturer";
    private static final String DATA_uPrice = "uPrice";
    private static final String DATA_oPrice = "oPrice";
    private static final String DATA_mrp = "mrp";
    private static final String DATA_su = "su";
    private static final String DATA_slug = "slug";
    private static final String DATA_packform = "packForm";
    private static final String DATA_form = "form";
    private static final String DATA_imgurl = "imgUrl";
    private static final String DATA_uip = "uip";
    private static final String DATA_generics = "generics";
    private static final String DATA_productsForBand = "productsForBrand";
    private static final String DATA_discountPerc = "discountPerc";
    private static final String DATA_pForm = "pForm";
    private static final String DATA_available = "available";

    private final String CREATE_MASTER_DATA = "CREATE TABLE " + MASTER_DATA + "("+ DATA_KEY_ID + " INTEGER PRIMARY KEY," +DATA_id+ " TEXT,"+DATA_hkpDrugCode+ " TEXT,"+DATA_mfId+ " TEXT,"+DATA_label+ " TEXT,"+DATA_name+ " TEXT,"+DATA_type+ " TEXT,"+DATA_packSize+ " TEXT,"+DATA_manufacture+ " TEXT,"+DATA_uPrice+ " TEXT,"+DATA_oPrice+ " TEXT,"+DATA_mrp+ " TEXT,"+DATA_su+ " TEXT,"+DATA_slug+ " TEXT,"+DATA_packform+ " TEXT,"+DATA_form+ " TEXT,"+DATA_imgurl+ " TEXT,"+DATA_uip+ " TEXT,"+DATA_generics+ " TEXT,"+DATA_productsForBand+ " TEXT,"+DATA_discountPerc+ " TEXT,"+DATA_pForm+ " TEXT,"+DATA_available+ " TEXT"+")";

    private Context context;



    public TableMaster(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_MASTER_DATA);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int arg1, int arg2)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MASTER_DATA);
    }

    public long addRecordDB_MASTER_DATA(String sDATA_id,String sDATA_hkpDrugCode,String sDATA_mfId,String sDATA_label,String sDATA_name,String sDATA_type,String sDATA_packSize,String sDATA_manufacture,String sDATA_uPrice,String sDATA_oPrice,String sDATA_mrp,String sDATA_su,String sDATA_slug,String sDATA_packform,String sDATA_form,String sDATA_imgurl,String sDATA_uip,String sDATA_generics,String sDATA_productsForBand,String sDATA_discountPerc,String sDATA_pForm,String sDATA_available)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DATA_id,sDATA_id);
        values.put(DATA_hkpDrugCode,sDATA_hkpDrugCode);
        values.put(DATA_mfId,sDATA_mfId);
        values.put(DATA_label,sDATA_label);
        values.put(DATA_name,sDATA_name);
        values.put(DATA_type,sDATA_type);
        values.put(DATA_packSize,sDATA_packSize);
        values.put(DATA_manufacture,sDATA_manufacture);
        values.put(DATA_uPrice,sDATA_uPrice);
        values.put(DATA_oPrice,sDATA_oPrice);
        values.put(DATA_mrp,sDATA_mrp);
        values.put(DATA_su,sDATA_su);
        values.put(DATA_slug,sDATA_slug);
        values.put(DATA_packform,sDATA_packform);
        values.put(DATA_form,sDATA_form);
        values.put(DATA_imgurl,sDATA_imgurl);
        values.put(DATA_uip,sDATA_uip);
        values.put(DATA_generics,sDATA_generics);
        values.put(DATA_productsForBand,sDATA_productsForBand);
        values.put(DATA_discountPerc,sDATA_discountPerc);
        values.put(DATA_pForm,sDATA_pForm);
        values.put(DATA_available,sDATA_available);
        long k = db.insert(MASTER_DATA, null, values);
//        db.close();
        return k;
    }

    public long truncateTable(String sTableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int k = db.delete(sTableName, null, null);
        return k;
    }

    public String[][] getAllData(String sTable)
    {
        String[][] saData = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + sTable, null);
        saData = new String[cursor.getCount()][cursor.getColumnCount()];
        int r = 0;
        if (cursor.moveToFirst())
        {
            do
            {
                for (int i = 0; i < cursor.getColumnCount(); i++)
                {
                    saData[r][i] = cursor.getString(i);
                }
                r++;
            }
            while (cursor.moveToNext());
        }
        return saData;
    }
}
