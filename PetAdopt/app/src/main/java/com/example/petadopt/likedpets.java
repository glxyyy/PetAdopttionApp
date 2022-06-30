package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class likedpets extends AppCompatActivity {
    private Button buttonpets;
    private Button buttonfinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likedpets);

    }
    public void goback(){
        Intent intent = new Intent(this, petselect.class);
        startActivity(intent);
    }
    public void gofinal(){
        Intent intent = new Intent(this, petselect.class);
        startActivity(intent);
    }
}