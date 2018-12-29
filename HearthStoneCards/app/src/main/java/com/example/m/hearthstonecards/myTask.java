package com.example.m.hearthstonecards;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import data.CardContract;

public class myTask extends AsyncTask<Void ,Void,Void> {
    ContentResolver mResolver;
    public String URL = "";
    public Context mContext;
    public String[] mValues;
    public Boolean mSwitch;

    public myTask(Context context,String[] values,Boolean Switch){
        mContext=context;
        mValues=values;
        mResolver = mContext.getContentResolver();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Selection.showProgressDialog();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        setURL();
        try
        {
            HttpResponse<JsonNode> response;
            response = Unirest.get(URL)
                    .header("X-Mashape-Key", "3BNNcA7YhAmshdhzxe2TulPnRS02p1OV7wQjsn3v2ADuMrMatn")
                    .asJson();
            getCards(response);



        }
        catch (Exception e)
        {
            Log.v("LOG_ERROR","doInBackground :: " + e.getMessage());
        }




        return null;
    }
    private void setURL(){
        String Attack=mValues[0];
        String Durability=mValues[1];
        String Price=mValues[2];
        String Health=mValues[3];
        Boolean Collectible=mSwitch;
        String Locale="enUs";
        URL="https://omgvamp-hearthstone-v1.p.mashape.com/cards?"
                +(Attack.equals("")       ?"":("attack="+Attack+"&"))
                +(Durability.equals("")   ?"":("durability="+Durability+"&"))
                +(Price.equals("")        ?"":("attack="+Price+"&"))
                +(Health.equals("")       ?"":("health="+Health+"&"))
                +(Collectible             ?"":("collectible=1&"))
                +(Locale);
        /*
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String attack       = sharedPreferences.getString("attack"      , "-1");
        boolean collectible  = sharedPreferences.getBoolean("collectible" , false);
        String cost         = sharedPreferences.getString("cost"        , "-1");
        String durability   = sharedPreferences.getString("durability"  , "-1");
        String health       = sharedPreferences.getString("health"      , "-1");
        String locale       = sharedPreferences.getString("locale"      , "enUS");
        String price       = sharedPreferences.getString("price"      , "-1");


        URL = "https://omgvamp-hearthstone-v1.p.mashape.com/cards?"
                +(attack.equals("-1")       ?"":("attack="+attack+"&"))
                +(collectible               ?"":("collectible=1&"))
                +(cost.equals("-1")         ?"":("cost="+cost+"&"))
                +(durability.equals("-1")   ?"":("durability="+durability+"&"))
                +(health.equals("-1")       ?"":("health="+health+"&"))
                +(price.equals("-1")       ?"":("attack="+price+"&"))
                +(locale);
                */

    }
    private void getCards(HttpResponse<JsonNode> node){
        Card.cards = new ArrayList<Card>();
        try {
            JSONObject data = node.getBody().getObject();
            JSONArray[] cardArrays = {
                    data.getJSONArray(Helper.CARD_GROUPS[0]),
                    data.getJSONArray(Helper.CARD_GROUPS[1]),
                    data.getJSONArray(Helper.CARD_GROUPS[2]),
                    data.getJSONArray(Helper.CARD_GROUPS[3]),
                    data.getJSONArray(Helper.CARD_GROUPS[4]),
                    data.getJSONArray(Helper.CARD_GROUPS[5]),
                    data.getJSONArray(Helper.CARD_GROUPS[6]),
                    data.getJSONArray(Helper.CARD_GROUPS[7]),
                    data.getJSONArray(Helper.CARD_GROUPS[8]),
                    data.getJSONArray(Helper.CARD_GROUPS[9]),
                    data.getJSONArray(Helper.CARD_GROUPS[10]),
                    data.getJSONArray(Helper.CARD_GROUPS[11]),
                    data.getJSONArray(Helper.CARD_GROUPS[12]),
                    data.getJSONArray(Helper.CARD_GROUPS[13]),
                    data.getJSONArray(Helper.CARD_GROUPS[14]),
                    data.getJSONArray(Helper.CARD_GROUPS[15]),
                    data.getJSONArray(Helper.CARD_GROUPS[16]),
                    data.getJSONArray(Helper.CARD_GROUPS[17]),
                    data.getJSONArray(Helper.CARD_GROUPS[18]),
                    data.getJSONArray(Helper.CARD_GROUPS[19]),
                    data.getJSONArray(Helper.CARD_GROUPS[20]),
                    data.getJSONArray(Helper.CARD_GROUPS[21]),
                    data.getJSONArray(Helper.CARD_GROUPS[22]),
                    data.getJSONArray(Helper.CARD_GROUPS[23]),
            };

            for (int i = 0; i < cardArrays.length; i++) {
                for (int j = 0; j < cardArrays[i].length(); j++) {
                    JSONObject card = cardArrays[i].getJSONObject(j);

                    Card c = new Card();

                    c.setID(card.getString(Helper.DATA_CARD_ID));
                    if (card.has(Helper.DATA_CARD_RARITY))
                        c.setRarity(card.getString(Helper.DATA_CARD_RARITY));
                    if (card.has(Helper.DATA_CARD_TYPE))
                        c.setType(card.getString(Helper.DATA_CARD_TYPE));
                    if (card.has(Helper.DATA_CARD_NAME))
                        c.setName(card.getString(Helper.DATA_CARD_NAME));
                    if (card.has(Helper.DATA_CARD_SET))
                        c.setCardClass(card.getString(Helper.DATA_CARD_SET));
                    if (card.has(Helper.DATA_CARD_IMG_URL))
                        c.setImgURL(card.getString(Helper.DATA_CARD_IMG_URL));
                    if (card.has(Helper.DATA_CARD_TEXT))
                        c.setText(card.getString(Helper.DATA_CARD_TEXT));
                    if (card.has(Helper.DATA_CARD_COST))
                        c.setCost(card.getInt(Helper.DATA_CARD_COST));
                    if (card.has(Helper.DATA_CARD_HEALTH))
                        c.setHealth(card.getInt(Helper.DATA_CARD_HEALTH));
                    if (card.has(Helper.DATA_CARD_ATTACK))
                        c.setAttack(card.getInt(Helper.DATA_CARD_ATTACK));
                    if (card.has(Helper.DATA_CARD_IS_COLLECTIBLE))
                        c.setCollectible(card.getBoolean(Helper.DATA_CARD_IS_COLLECTIBLE));
                    if (card.has(Helper.DATA_CARD_DURABILITY))
                        c.setDurability(card.getInt(Helper.DATA_CARD_DURABILITY));
                    if (card.has(Helper.DATA_CARD_ARMOR))
                        c.setArmor(card.getInt(Helper.DATA_CARD_ARMOR));

                    Card.cards.add(c);
/*
                    if (type.equals(Helper.CARD_TYPES[3])) {

                    } else if (type.equals(Helper.CARD_TYPES[4])) {

                    } else if (type.equals(Helper.CARD_TYPES[5])) {

                    } else {
                        Log.v("type", type);
                    }*/
                }
            }
        } catch (Exception e) {
            Log.v("ERROR", e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Selection.dismissProgressDialog();
        Selection.intentFromSelection();
        Log.v("Sule",String.valueOf(Card.cards.size()));

    }

    public long addCard(Card card){

        String[] projectedColumns = {CardContract.CardEntry._ID};
        String selectedString = CardContract.CardEntry.COLUMN_ID + "=?";
        String[] selectionArgs = {CardContract.CardEntry.COLUMN_ID};
        Cursor locationCursor;
        long locationId;
        locationCursor = mResolver.query(CardContract.CardEntry.CONTENT_URI,
                projectedColumns,
                selectedString,
                selectionArgs,
                null);


        if (locationCursor.moveToFirst()){
            int locationIdIndex = locationCursor.getColumnIndex(CardContract.CardEntry._ID);
            locationId = locationCursor.getLong(locationIdIndex);
        }
        else{
        Uri insertedUri;
        ContentValues cardValues = new ContentValues();

        cardValues.put(CardContract.CardEntry.COLUMN_ID , card.getID());
        cardValues.put(CardContract.CardEntry.COLUMN_InfoID , card.getID());
        cardValues.put(CardContract.CardEntry.COLUMN_TYPE , card.getType());
        cardValues.put(CardContract.CardEntry.COLUMN_RARITY , card.getRarity());
        cardValues.put(CardContract.CardEntry.COLUMN_NAME , card.getName());
        cardValues.put(CardContract.CardEntry.COLUMN_CARD_CLASS , card.getCardClass());
        cardValues.put(CardContract.CardEntry.COLUMN_IMG_URL , card.getImgURL());


        insertedUri = mResolver.insert(CardContract.CardEntry.CONTENT_URI, cardValues);
        locationId = ContentUris.parseId(insertedUri);
        }
        return locationId;
    }

    public long addInfo(Card card){

        String[] projectedColumns = {CardContract.CardInfoEntry._ID};
        String selectedString = CardContract.CardInfoEntry.COLUMN_ID + "=?";
        String[] selectionArgs = {CardContract.CardInfoEntry.COLUMN_ID};
        Cursor locationCursor;
        long locationId;
        locationCursor = mResolver.query(CardContract.CardInfoEntry.CONTENT_URI,
                projectedColumns,
                selectedString,
                selectionArgs,
                null);


        if (locationCursor.moveToFirst()){
            int locationIdIndex = locationCursor.getColumnIndex(CardContract.CardInfoEntry._ID);
            locationId = locationCursor.getLong(locationIdIndex);
        }
        else {
            Uri insertedUri;
            ContentValues infoValues = new ContentValues();

            infoValues.put(CardContract.CardInfoEntry.COLUMN_ID, card.getID());
            infoValues.put(CardContract.CardInfoEntry.COLUMN_TEXT, card.getText());
            infoValues.put(CardContract.CardInfoEntry.COLUMN_COST, card.getCost());
            infoValues.put(CardContract.CardInfoEntry.COLUMN_HEALTH, card.getHealth());
            infoValues.put(CardContract.CardInfoEntry.COLUMN_ATTACK, card.getAttack());
            infoValues.put(CardContract.CardInfoEntry.COLUMN_DURABILITY, card.getDurability());
            infoValues.put(CardContract.CardInfoEntry.COLUMN_ARMOR, card.getArmor());
            infoValues.put(CardContract.CardInfoEntry.COLUMN_IS_COLLECTIBLE, card.isCollectible());


            insertedUri = mResolver.insert(CardContract.CardInfoEntry.CONTENT_URI, infoValues);
            locationId = ContentUris.parseId(insertedUri);
        }
        return locationId;
    }

}
