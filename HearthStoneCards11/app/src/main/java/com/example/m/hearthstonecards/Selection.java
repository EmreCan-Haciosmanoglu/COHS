package com.example.m.hearthstonecards;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class Selection extends AppCompatActivity {
    private static Context mContext;
    private static ProgressDialog progressDialog;
    String[] values=new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        progressDialog=new ProgressDialog(mContext);
        setContentView(R.layout.activity_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText attack=(EditText)findViewById(R.id.attackEdit);
        final EditText price=(EditText)findViewById(R.id.priceEdit);
        final EditText durabilty=(EditText)findViewById(R.id.durabilityEdit);
        final EditText health=(EditText)findViewById(R.id.heathEdit);
        final Switch collectible=(Switch)findViewById(R.id.collectibleSwitch);



        Button button=(Button)findViewById(R.id.SearchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values[0]=attack.getText().toString();
                values[1]=durabilty.getText().toString();
                values[2]=price.getText().toString();
                values[3]=health.getText().toString();


                myTask myTask=new myTask(mContext,values,collectible.isChecked());
                myTask.execute();

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
    public static void dismissProgressDialog(){
        progressDialog.dismiss();
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
}
