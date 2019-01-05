package com.example.m.hearthstonecards;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import android.util.Log;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;



import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.m.hearthstonecards.action.FOO";
    private static final String ACTION_BAZ = "com.example.m.hearthstonecards.action.BAZ";
    public String URL = "";
    public String[] mValues=Selection.values;
    public Boolean mSwitch=Selection.Switch;

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.m.hearthstonecards.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.m.hearthstonecards.extra.PARAM2";

    public MyIntentService() {

        super("MyIntentService");


    }



    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        ReminderTasks.executeTask(this, action);


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




        return;
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
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
        Selection.dismissProgressDialog();
        Selection.intentFromSelection();
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


}
