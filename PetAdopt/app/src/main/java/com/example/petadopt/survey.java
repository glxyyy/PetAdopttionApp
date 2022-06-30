package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class survey extends AppCompatActivity {
    private Button button5;
    private Button buttonyes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpage4();
            }
        });
        buttonyes = (Button) findViewById(R.id.buttonyes);
        buttonyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensurvey2();
            }
        });
    }

    public void openpage4() {
        Intent intent = new Intent(this, page4.class);
        startActivity(intent);
    }

    public void opensurvey2() {
        Intent intent = new Intent(this, survey2.class);
        startActivity(intent);
    }
}