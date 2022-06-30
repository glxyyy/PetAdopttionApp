package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petadopt.databinding.ActivityInterviewBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class interview extends AppCompatActivity {
    DatabaseReference root;
    FirebaseDatabase rootNode;
    TextView FullName, Address, Birthday, Email, Contact, q1, q2, q3, q4, q5;
    private Button submit;
    private DatabaseReference databaseReference;
    private FirebaseAuth m;
    ActivityInterviewBinding binding3;
    String mCurrentUserID3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference();
        m = FirebaseAuth.getInstance();
        mCurrentUserID3 = m.getCurrentUser().getUid();
        setContentView(R.layout.activity_interview);
        //getSupportActionBar().hide();//yes
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//yes
        FullName = findViewById(R.id.FullName);
        Address = findViewById(R.id.Address);
        Birthday = findViewById(R.id.Birthday);
        Email = findViewById(R.id.Email);
        Contact = findViewById(R.id.Contact);
        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);
        q5 = findViewById(R.id.q5);
        binding3 = ActivityInterviewBinding.inflate(getLayoutInflater());
        setContentView(binding3.getRoot());
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FullNames = binding3.FullName.getText().toString();
                String Address = binding3.Address.getText().toString();
                String Birthday = binding3.Birthday.getText().toString();
                String Email = binding3.FullName.getText().toString();
                String Contact = binding3.Contact.getText().toString();
                String q1 = binding3.q1.getText().toString();
                String q2 = binding3.q2.getText().toString();
                String q3 = binding3.q3.getText().toString();
                String q4 = binding3.q4.getText().toString();
                String q5 = binding3.q5.getText().toString();

                updateData(FullNames, Address, Birthday, Email, Contact, q1, q2 ,q3, q4,q5);
//                inputanswers();
                  gonext();
            }
        });
        submit = (Button) findViewById(R.id.buttonback);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
    }

    private void updateData(String fullNames, String addresss, String birthdays, String emails, String contacts, String q1s, String q2s, String q3s, String q4s, String q5s) {
        HashMap answers = new HashMap();
        answers.put("FullName", fullNames);
        answers.put("Address", addresss);
        answers.put("Birthday", birthdays);
        answers.put("Email", emails);
        answers.put("Contact", contacts);
        answers.put("q1", q1s);
        answers.put("q2", q2s);
        answers.put("q3", q3s);
        answers.put("q4", q4s);
        answers.put("q5", q5s);

        root = FirebaseDatabase.getInstance().getReference("answersTable");
        root.child(mCurrentUserID3).updateChildren(answers).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){

                    binding3.FullName.setText("");
                    binding3.Address.setText("");
                    binding3.Birthday.setText("");
                    binding3.Email.setText("");
                    binding3.Contact.setText("");
                    binding3.q1.setText("");
                    binding3.q2.setText("");
                    binding3.q3.setText("");
                    binding3.q4.setText("");
                    binding3.q5.setText("");


                    Toast.makeText(interview.this, "Update Success", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(interview.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void gonext() {
        Intent intent = new Intent(this, petselect.class);
        startActivity(intent);
    }

    public void inputanswers() {
        String FullName = this.FullName.getText().toString().trim();
        String Address = this.Address.getText().toString().trim();
        String Birthday = this.Birthday.getText().toString().trim();
        String Email = this.Email.getText().toString().trim();
        String Contact = this.Contact.getText().toString().trim();
        String q1 = this.q1.getText().toString().trim();
        String q2 = this.q2.getText().toString().trim();
        String q3 = this.q3.getText().toString().trim();
        String q4 = this.q4.getText().toString().trim();
        String q5 = this.q5.getText().toString().trim();

        root = FirebaseDatabase.getInstance().getReference().child("answersTable");
        String id = root.push().getKey();
        root.child(id).child("q1").setValue(q1);
        root.child(id).child("q2").setValue(q2);
        root.child(id).child("q3").setValue(q3);
        root.child(id).child("q4").setValue(q4);
        root.child(id).child("q5").setValue(q5);
        root.child(id).child("FullName").setValue(FullName);
        root.child(id).child("Address").setValue(Address);
        root.child(id).child("Birthday").setValue(Birthday);
        root.child(id).child("Email").setValue(Email);
        root.child(id).child("Contact").setValue(Contact);

        Toast.makeText(this, "it works", Toast.LENGTH_SHORT).show();
    }

    public void goback() {
        Intent intent = new Intent(this, page4.class);
        startActivity(intent);
    }
}