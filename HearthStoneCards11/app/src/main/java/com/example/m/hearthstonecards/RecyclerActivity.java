package com.example.m.hearthstonecards;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerActivity extends AppCompatActivity {
    public static Context mContext;


    private Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment=new NameFragment();

                    break;
                case R.id.navigation_dashboard:

                    fragment=new RecyclerFragment();
                    break;
                case R.id.navigation_notifications:

                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mContext=this;
        fragment=new NameFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

        Toast.makeText(getApplicationContext(),String.valueOf(Card.cards.size())+" card found",Toast.LENGTH_LONG).show();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
