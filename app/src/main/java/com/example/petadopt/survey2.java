package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class survey2 extends AppCompatActivity {
    private Button button6;
    private Button buttonyes1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey2);

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpage4();
            }
        });
        buttonyes1 = (Button) findViewById(R.id.buttonyes1);
        buttonyes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensurvey3();
            }
        });
    }

    public void openpage4() {
        Intent intent = new Intent(this, page4.class);
        startActivity(intent);
    }

    public void opensurvey3() {
        Intent intent = new Intent(this, survey3.class);
        startActivity(intent);
    }
}