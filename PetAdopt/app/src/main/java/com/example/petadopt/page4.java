package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class page4 extends AppCompatActivity {
    FirebaseDatabase rootdir;
    private FirebaseAuth auth;
    private Button button4;
    private Button buttonsgup;
    private TextView lgUser;
    private TextView lgPass;
    private RecyclerView recyclerView;
    String mCurrentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        lgUser = (TextView) findViewById(R.id.lgUser);
        auth = FirebaseAuth.getInstance();
        mCurrentUserId = auth.getCurrentUser().getUid();
        lgPass = (TextView) findViewById(R.id.lgPass);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
                //opensurvey();
            }
        });
        buttonsgup = (Button) findViewById(R.id.buttonsgup);
        buttonsgup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openwait();
            }
        });

    }

    public void opensurvey() {
        Intent intent = new Intent(this, survey.class);
        startActivity(intent);
    }

    public void openwait() {
        Intent intent = new Intent(this, loginpage.class);
        startActivity(intent);
    }

    public void loginuser() {
        String lgUser, lgPass;
        lgUser = this.lgUser.getText().toString();
        lgPass = this.lgPass.getText().toString();
        auth.createUserWithEmailAndPassword(lgUser, lgPass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                NewUserCreate NewUserCreate = new NewUserCreate(lgUser, lgPass);
                Toast.makeText(page4.this, "Signup Complete", Toast.LENGTH_SHORT).show();
                opensurvey();
                FirebaseDatabase.getInstance().getReference("user") // "user" is the name of the node to insert data into -- creates new node if name doesn't exist yet
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(NewUserCreate).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(page4.this, "input complete", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(page4.this, "Email already used", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(page4.this, "Email already used", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(page4.this, mCurrentUserId, Toast.LENGTH_SHORT).show();
    }
}