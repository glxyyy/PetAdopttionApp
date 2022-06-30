package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class survey3 extends AppCompatActivity {
    private Button button7;
    private Button buttons2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey3);

        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpage4();
            }
        });
        buttons2 = (Button) findViewById(R.id.buttonyes2);
        buttons2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openwait();
            }
        });
    }

    public void openpage4() {
        Intent intent = new Intent(this, page4.class);
        startActivity(intent);
    }

    public void openwait() {
        Intent intent = new Intent(this, HoldPage.class);
        startActivity(intent);
    }
}