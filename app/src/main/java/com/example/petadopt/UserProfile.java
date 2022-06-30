package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petadopt.databinding.ActivityUserProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    TextView FullName, Address, Birthday, Email, Contact, q1, q2, q3, q4, q5;
    ActivityUserProfileBinding binding4;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    String mCurrentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        DatabaseReference reference;
        mAuth = FirebaseAuth.getInstance();
        mCurrentUserId = mAuth.getCurrentUser().getUid();
        binding4 = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding4.getRoot());
        FullName = findViewById(R.id.uFullname);
        Address = findViewById(R.id.uAddress);
        Birthday = findViewById(R.id.uBirthday);
        Email = findViewById(R.id.uEmail);
        Contact = findViewById(R.id.uContact);
        q1 = findViewById(R.id.uq1);
        q2 = findViewById(R.id.uq2);
        q3 = findViewById(R.id.uq3);
        q4 = findViewById(R.id.uq4);
        q5 = findViewById(R.id.uq5);
        showAllUserData();
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.UploadPet:
                    gotoupload();
                    return true;

                case R.id.ViewPets:;
                    openuploadedpet();
                    return true;

                case R.id.UpdatePet:;
                    gotoupload2();
                    return true;

            }
            return false;
        });
    }
    private void openuploadedpet() {
        Intent intent = new Intent(this, petselect.class);
        startActivity(intent);
    }
    public void gotoupload() {
        Intent intent = new Intent(this, uploadpet.class);
        startActivity(intent);
    }
    public void gotoupload2() {
        Intent intent = new Intent(this, uploadedpets.class);
        startActivity(intent);
    }
    private void showAllUserData() {
        reference = FirebaseDatabase.getInstance().getReference().child("answersTable");
        reference.child(mCurrentUserId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {

                        Toast.makeText(UserProfile.this, "Data Read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String fullname = String.valueOf(dataSnapshot.child("FullName").getValue());
                        String address = String.valueOf(dataSnapshot.child("Address").getValue());
                        String birthday = String.valueOf(dataSnapshot.child("Birthday").getValue());
                        String email = String.valueOf(dataSnapshot.child("Email").getValue());
                        String contact = String.valueOf(dataSnapshot.child("Contact").getValue());
                        String q1 = String.valueOf(dataSnapshot.child("q1").getValue());
                        String q2 = String.valueOf(dataSnapshot.child("q2").getValue());
                        String q3 = String.valueOf(dataSnapshot.child("q3").getValue());
                        String q4 = String.valueOf(dataSnapshot.child("q4").getValue());
                        String q5 = String.valueOf(dataSnapshot.child("q5").getValue());

                        binding4.uFullname.setText(fullname);
                        binding4.uAddress.setText(address);
                        binding4.uBirthday.setText(birthday);
                        binding4.uEmail.setText(email);
                        binding4.uContact.setText(contact);
                        binding4.uq1.setText(q1);
                        binding4.uq2.setText(q2);
                        binding4.uq3.setText(q3);
                        binding4.uq4.setText(q4);
                        binding4.uq5.setText(q5);

                    } else {

                        Toast.makeText(UserProfile.this, "Data does not exist", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(UserProfile.this, "Cannot Read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}