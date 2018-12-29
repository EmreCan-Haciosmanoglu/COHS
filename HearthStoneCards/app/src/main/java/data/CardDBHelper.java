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
                CardContract.CardEntry.COLUMN_TYPE              + " TEXT , " +
                CardContract.CardEntry.COLUMN_RARITY            + " TEXT , " +
                CardContract.CardEntry.COLUMN_NAME              + " TEXT , " +
                CardContract.CardEntry.COLUMN_CARD_CLASS        + " TEXT , " +
                CardContract.CardEntry.COLUMN_IMG_URL           + " TEXT , " +
                CardContract.CardEntry.COLUMN_TEXT              + " TEXT , " +
                CardContract.CardEntry.COLUMN_COST              + " INTEGER , " +
                CardContract.CardEntry.COLUMN_HEALTH            + " INTEGER , " +
                CardContract.CardEntry.COLUMN_ATTACK            + " INTEGER , " +
                CardContract.CardEntry.COLUMN_DURABILITY        + " INTEGER , " +
                CardContract.CardEntry.COLUMN_ARMOR             + " INTEGER , " +
                CardContract.CardEntry.COLUMN_IS_COLLECTIBLE    + " BLOB  " +
                " );";
        //final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " ...

        db.execSQL(SQL_CREATE_CARD_TABLE);
        //db.execSQL(SQL_CREATE_WEATHER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CardContract.CardEntry.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + CardContract.OtherEntry.TABLE_NAME);

        onCreate(db);

    }
}
