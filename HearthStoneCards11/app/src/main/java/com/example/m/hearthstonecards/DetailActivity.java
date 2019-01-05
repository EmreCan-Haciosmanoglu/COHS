package com.example.m.hearthstonecards;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras=getIntent().getExtras();
        TextView Name=findViewById(R.id.detailNameInfo);
        TextView Id=findViewById(R.id.DetailCardIDInfo);
        TextView Rarity=findViewById(R.id.detailCardRarityInfo);
        TextView Class=findViewById(R.id.detailCardClassInfo);
        ImageView imageView=findViewById(R.id.imageView3);

        Name.setText(extras.getString("Name"));
        Id.setText(extras.getString("ID"));
        Rarity.setText(extras.getString("Rarity"));
        Class.setText(extras.getString("Class"));

        String URL="https://art.hearthstonejson.com/v1/render/latest/enUS/512x/"+extras.getString("ID")+".png";
        Picasso.get().load(URL).into(imageView);


    }

}
