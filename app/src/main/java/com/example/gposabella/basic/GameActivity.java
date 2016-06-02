package com.example.gposabella.basic;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;

public class GameActivity extends Activity {

    private Button bottone1;
    private Button bottone2;
    private Bitmap out;
    private static int RESULT_LOAD_IMG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bottone1 = (Button) findViewById(R.id.bottone1);
        bottone2 = (Button) findViewById(R.id.bottone2);
    /*    bottone1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent openPage1 = new Intent(GameActivity.this,GetPhotoActivity.class);

                startActivityForResult(openPage1, 1);
            }
        });*/
        bottone1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent    galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });


        bottone2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if( Heap.getBitmap()==null){
                    Toast.makeText(GameActivity.this, "Devi scegliere una immagine",
                            Toast.LENGTH_LONG).show();

                    return;
                }
                Intent openPage1 = new Intent(GameActivity.this,MainActivity.class);


                startActivity(openPage1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                Bitmap   finalBitmap = BitmapFactory
                        .decodeFile(imgDecodableString);


                Heap.setBitmap(finalBitmap);


            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
}
