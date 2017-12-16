package com.ugurcangursen.guideofistanbul;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PostActivity extends AppCompatActivity {

    ImageView mSelectedIMG;
    EditText mBaslik,mAciklama,mAdres;
    Spinner kategori;
    ArrayAdapter<CharSequence> kadapter;
    Button mGonder;
    GuideDB guideDB;
    Bitmap mmSelectedIMG;
    String spintext;

    final int PICK_IMAGE = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mSelectedIMG = (ImageView) findViewById(R.id.selectimage);
        mBaslik = (EditText) findViewById(R.id.baslik);
        mAciklama = (EditText) findViewById(R.id.aciklama);
        mAdres = (EditText) findViewById(R.id.adres);
        kategori = (Spinner) findViewById(R.id.kategori);
        mGonder = (Button) findViewById(R.id.gonder);
        guideDB = new GuideDB(this);

        kadapter = ArrayAdapter.createFromResource(this,R.array.karegori_array, android.R.layout.simple_spinner_item);
        kadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kategori.setAdapter(kadapter);

        mSelectedIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        PostActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        PICK_IMAGE
                );
            }
        });

        // Veri Kaydetme Başlangıç
        mGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    /////
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    mmSelectedIMG.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    byte[] byteArray = outputStream.toByteArray();

                    //////
                    //////
                    kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            spintext = kategori.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    ////////
                    guideDB.addGuide(mBaslik.getText().toString(),
                            mAciklama.getText().toString(),
                            mAdres.getText().toString(),
                            kategori.getSelectedItem().toString(),
                            byteArray);
                    Toast.makeText(getApplicationContext(),"Added successfully!",Toast.LENGTH_LONG).show();
                }catch (Exception e){e.printStackTrace();}

            }
        });
        // Veri Kaydetme Son
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PICK_IMAGE){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,PICK_IMAGE);
            }
            else {
                Toast.makeText(getApplicationContext(),"İznin Yok",Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {

            Uri uri = data.getData();

            try {
                mmSelectedIMG = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                mSelectedIMG.setImageBitmap(mmSelectedIMG);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
