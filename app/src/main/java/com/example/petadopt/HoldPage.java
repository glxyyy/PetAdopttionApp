package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HoldPage extends AppCompatActivity {
    private Button button6;
    private Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_page);
        button9 = (Button) findViewById(R.id.buttongonext);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openwait();
            }
        });
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openpage4();
            }
        });

    }

    public void openpage4() {
        Intent intent = new Intent(this, page4.class);
        startActivity(intent);
    }

    public void openwait() {
        Intent intent = new Intent(this, interview.class);
        startActivity(intent);
    }
}