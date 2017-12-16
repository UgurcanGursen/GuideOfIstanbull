package com.ugurcangursen.guideofistanbul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView hotels,art,food,shopping;
    GuideDB guideDB;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotels = (ImageView) findViewById(R.id.hotels);
        art = (ImageView) findViewById(R.id.art);
        food = (ImageView) findViewById(R.id.food);
        shopping = (ImageView) findViewById(R.id.shopping);
        guideDB = new GuideDB(this);

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hotelintent = new Intent(MainActivity.this, HotelsActivity.class);
                startActivity(hotelintent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodintent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(foodintent);
            }
        });

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artintent = new Intent(MainActivity.this, ArtActivity.class);
                startActivity(artintent);
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopintent = new Intent(MainActivity.this, ShoppingActivity.class);
                startActivity(shopintent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_add){

            startActivity(new Intent(MainActivity.this,PostActivity.class));
        }

        if (item.getItemId() == R.id.delete){
            guideDB.deleteAll();
        }

        return super.onOptionsItemSelected(item);
    }
}
