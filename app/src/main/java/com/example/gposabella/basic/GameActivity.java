package com.example.gposabella.basic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    private Button bottone1;
    private Button bottone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bottone1 = (Button) findViewById(R.id.bottone1);
        bottone2 = (Button) findViewById(R.id.bottone2);
        bottone1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent openPage1 = new Intent(GameActivity.this,GetPhotoActivity.class);
// passo all'attivazione dell'activity page1.java
                startActivity(openPage1);
            }
        });

        bottone2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent openPage1 = new Intent(GameActivity.this,MainActivity.class);
// passo all'attivazione dell'activity page1.java
                startActivity(openPage1);
            }
        });
    }
}
