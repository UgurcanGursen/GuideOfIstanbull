package com.ugurcangursen.guideofistanbul;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ArtActivity extends AppCompatActivity {

    RecyclerView art_list;
    ArrayList<Guide> guides;
    GuideAdapter mAdapter = null;
    GuideDB guideDB = new GuideDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);

        art_list = (RecyclerView) findViewById(R.id.art_list);
        art_list.setLayoutManager(new LinearLayoutManager(this));
        art_list.setItemAnimator(new DefaultItemAnimator());
        guides = new ArrayList<>();
        mAdapter = new GuideAdapter(this,guides);
        art_list.setAdapter(mAdapter);

        Cursor cursor = guideDB.getArtGuides();
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
