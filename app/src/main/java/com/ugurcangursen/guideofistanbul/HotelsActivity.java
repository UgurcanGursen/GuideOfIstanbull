package com.ugurcangursen.guideofistanbul;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;


public class HotelsActivity extends AppCompatActivity {

    RecyclerView hotels_list;
    ArrayList<Guide> guides;
    GuideAdapter mAdapter = null;
    GuideDB guideDB = new GuideDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);


        hotels_list = (RecyclerView) findViewById(R.id.hotels_list);
        hotels_list.setLayoutManager(new LinearLayoutManager(this));
        hotels_list.setItemAnimator(new DefaultItemAnimator());
        guides = new ArrayList<>();
        mAdapter = new GuideAdapter(this,guides);
        hotels_list.setAdapter(mAdapter);

        /////////////////////////
        Cursor cursor = guideDB.getHotelsGuides();
        while (cursor.moveToNext()){

            String post_baslik = cursor.getString(0);
            String post_aciklama = cursor.getString(1);
            String post_adres = cursor.getString(2);
            byte [] post_image = cursor.getBlob(3);
            Guide g = new Guide(post_baslik,post_aciklama,post_adres,post_image);
            guides.add(g);

        }

    }
}
