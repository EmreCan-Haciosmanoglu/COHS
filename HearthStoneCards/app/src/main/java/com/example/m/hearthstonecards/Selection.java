package com.example.m.hearthstonecards;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Selection extends AppCompatActivity   {
    private static Context mContext;
    private static ProgressDialog progressDialog;
    private static ProgressDialog progressDialog2;
    static String[] values=new String[4];
    static boolean Switch;
    NetworkChangeReceiver mNetworkReceiver;
    IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(mNetworkReceiver, mIntentFilter);
        mNetworkReceiver=new NetworkChangeReceiver();
        ReminderUtilities.scheduleChargingReminder(this);



        Intent intentAlarm = new Intent(this, AlarmReceiver.class);

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //set the notification to repeat every fifteen minutes
        long startTime = 1*60*250; // 2 min
        // set unique id to the pending item, so we can call it when needed
        PendingIntent pi = PendingIntent.getBroadcast(this, 12345, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime() +
                startTime, 60*1000, pi);

        Intent intentAlarm2 = new Intent(this, AlarmReceiver2.class);

        AlarmManager alarmManager2 = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //set the notification to repeat every fifteen minutes
        long startTime2 = 1*60*250; // 2 min
        // set unique id to the pending item, so we can call it when needed
        PendingIntent pi2 = PendingIntent.getBroadcast(this, 12345, intentAlarm2, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager2.setInexactRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime() +
                startTime2, 60*1000, pi2);







        mContext=this;
        progressDialog=new ProgressDialog(mContext);
        progressDialog2=new ProgressDialog(mContext);
        setContentView(R.layout.activity_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText attack=(EditText)findViewById(R.id.attackEdit);
        final EditText price=(EditText)findViewById(R.id.priceEdit);
        final EditText durabilty=(EditText)findViewById(R.id.durabilityEdit);
        final EditText health=(EditText)findViewById(R.id.heathEdit);
        final Switch collectible=(Switch)findViewById(R.id.collectibleSwitch);
        Switch=collectible.isChecked();




        Button button=(Button)findViewById(R.id.SearchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                values[0]=attack.getText().toString();
                values[1]=durabilty.getText().toString();
                values[2]=price.getText().toString();
                values[3]=health.getText().toString();


                //  myTask myTask=new myTask(mContext,values,collectible.isChecked());
                // myTask.execute();
                Intent myService=new Intent(Selection.this,MyIntentService.class);
                mContext.startService(myService);

            }
        });




    }
    public static void intentFromSelection(){
        Intent intent=new Intent(mContext,RecyclerActivity.class);
        mContext.startActivity(intent);

    }
    public static void showProgressDialog(){

        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

    }
    public static void wifiProgressDialog(){

        progressDialog2.setTitle("No Connection");
        progressDialog2.setMessage("Waiting Connection");
        progressDialog2.show();

    }

    public void incrementWater(View view) {

        Intent incrementWaterCountIntent = new Intent(this, MyIntentService.class);
        incrementWaterCountIntent.setAction(ReminderTasks.ACTION_INCREMENT_WATER_COUNT);
        startService(incrementWaterCountIntent);
    }
    public static void dismissProgressDialog(){
        progressDialog.dismiss();
    }
    public static void dismissProgressDialog2(){
        progressDialog2.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mNetworkReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mNetworkReceiver, mIntentFilter);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        /** Cleanup the shared preference listener **/
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

    }
    public void testNotification(View view) {
        NotificationUtils.remindUserBecauseCharging(this);
    }




    private class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    Log.v("Sule","connected");
                    dismissProgressDialog2();

                } else {
                    Log.v("Sule","Başına connected");
                    wifiProgressDialog();
                }
            }



        }

    }
}