package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import data.CardContract;

public class CardDBHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "card.db";



    public CardDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String SQL_CREATE_CARD_TABLE = "CREATE TABLE " + CardContract.CardEntry.TABLE_NAME + " (" +
                CardContract.CardEntry.COLUMN_ID                + " INTEGER PRIMARY KEY," +
                CardContract.CardEntry.COLUMN_InfoID            + " INTEGER , " +
                CardContract.CardEntry.COLUMN_TYPE              + " TEXT , " +
                CardContract.CardEntry.COLUMN_RARITY            + " TEXT , " +
                CardContract.CardEntry.COLUMN_NAME              + " TEXT , " +
                CardContract.CardEntry.COLUMN_CARD_CLASS        + " TEXT , " +
                CardContract.CardEntry.COLUMN_IMG_URL           + " TEXT , " +
                " FOREIGN KEY (" + CardContract.CardEntry.COLUMN_InfoID + ") REFERENCES " +
                CardContract.CardInfoEntry.TABLE_NAME + " (" + CardContract.CardInfoEntry.COLUMN_ID + ") "+ ");";


        final String SQL_CREATE_INFO_TABLE = "CREATE TABLE " + CardContract.CardEntry.TABLE_NAME + " (" +

                CardContract.CardInfoEntry.COLUMN_ID                + " INTEGER PRIMARY KEY," +
                CardContract.CardInfoEntry.COLUMN_TEXT              + " TEXT , " +
                CardContract.CardInfoEntry.COLUMN_COST              + " INTEGER , " +
                CardContract.CardInfoEntry.COLUMN_HEALTH            + " INTEGER , " +
                CardContract.CardInfoEntry.COLUMN_ATTACK            + " INTEGER , " +
                CardContract.CardInfoEntry.COLUMN_DURABILITY        + " INTEGER , " +
                CardContract.CardInfoEntry.COLUMN_ARMOR             + " INTEGER , " +
                CardContract.CardInfoEntry.COLUMN_IS_COLLECTIBLE    + " BLOB  " + " );";

        db.execSQL(SQL_CREATE_CARD_TABLE);
        db.execSQL(SQL_CREATE_INFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CardContract.CardEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CardContract.CardInfoEntry.TABLE_NAME);

        onCreate(db);

    }
}
