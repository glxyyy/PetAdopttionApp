package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petadopt.databinding.ActivityUploadedpetsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class uploadedpets extends AppCompatActivity {

    ActivityUploadedpetsBinding binding;
    TextView petname, petage, petgender, petbehave, pethistory, petcategory, petcontact, petadopted;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    String mCurrentUserId;
    private Button btnup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadedpets);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUserId = mAuth.getCurrentUser().getUid();
        binding = ActivityUploadedpetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        petname = findViewById(R.id.petNameUps);
        petage = findViewById(R.id.petAgeUps);
        petgender = findViewById(R.id.petGenderUps);
        petbehave = findViewById(R.id.petBehaveUps);
        pethistory = findViewById(R.id.petHistoryUps);
        petcategory = findViewById(R.id.petCategoryUps);
        petcontact = findViewById(R.id.petContactUps);
        petadopted = findViewById(R.id.petAdopted);
        showAllUserData();
        binding.btnUpdatePet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String petname = binding.petNameUps.getText().toString();
                String petage = binding.petAgeUps.getText().toString();
                String petgender = binding.petGenderUps.getText().toString();
                String petbehave = binding.petBehaveUps.getText().toString();
                String pethistory = binding.petHistoryUps.getText().toString();
                String petcategory = binding.petCategoryUps.getText().toString();
                String petcontact = binding.petContactUps.getText().toString();
                String petadopted = binding.petAdopted.getText().toString();


                updateData(petname, petage, petgender, petbehave, pethistory, petcategory, petcontact, petadopted);

                showAllUserData();
            }
        });
//        btnup = (Button) findViewById(R.id.btnmanuuploader);
//        btnup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openuploadedpet();
//            }
//        });
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.UploadPet2:
                    gotoupload();
                    return true;

                case R.id.ViewPets:;
                    openuploadedpet();
                    return true;

                case R.id.UserProfile:;
                    openuploadedpet();
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
    private void openuploadedpet2() {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }

    private void updateData(String petname, String petage, String petgender, String petbehave, String pethistory, String petcategory, String petcontact, String petadopted) {

        HashMap pets = new HashMap();
        pets.put("petName",petname);
        pets.put("petAge",petage);
        pets.put("petGender",petgender);
        pets.put("petBehaviour",petbehave);
        pets.put("petHistory",pethistory);
        pets.put("petCategory",petcategory);
        pets.put("petContact",petcontact);
        pets.put("isDeleted",petadopted);

        reference = FirebaseDatabase.getInstance().getReference("petTable");
        reference.child(mCurrentUserId).updateChildren(pets).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){

                    binding.petNameUps.setText("");
                    binding.petAgeUps.setText("");
                    binding.petGenderUps.setText("");
                    binding.petBehaveUps.setText("");
                    binding.petHistoryUps.setText("");
                    binding.petCategoryUps.setText("");
                    binding.petContactUps.setText("");
                    binding.petAdopted.setText("");
                    Toast.makeText(uploadedpets.this, "Update Success", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(uploadedpets.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showAllUserData() {
        reference = FirebaseDatabase.getInstance().getReference().child("petTable");
        reference.child(mCurrentUserId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()) {

                    if(task.getResult().exists()){

                        Toast.makeText(uploadedpets.this, "Data Read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String _petname = String.valueOf(dataSnapshot.child("petName").getValue());
                        String _petage = String.valueOf(dataSnapshot.child("petAge").getValue());
                        String _petbehave = String.valueOf(dataSnapshot.child("petBehaviour").getValue());
                        String _petgen = String.valueOf(dataSnapshot.child("petGender").getValue());
                        String _pethist = String.valueOf(dataSnapshot.child("petHistory").getValue());
                        String _petcateg = String.valueOf(dataSnapshot.child("petCategory").getValue());
                        String _petcont = String.valueOf(dataSnapshot.child("petContact").getValue());
                        String _petadopted = String.valueOf(dataSnapshot.child("isDeleted").getValue());

                        binding.petNameUps.setText(_petname);
                        binding.petAgeUps.setText(_petage);
                        binding.petBehaveUps.setText(_petbehave);
                        binding.petGenderUps.setText(_petgen);
                        binding.petHistoryUps.setText(_pethist);
                        binding.petCategoryUps.setText(_petcateg);
                        binding.petContactUps.setText(_petcont);
                        binding.petAdopted.setText(_petadopted);

                    }else {

                        Toast.makeText(uploadedpets.this, "Data does not exist", Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(uploadedpets.this, "Cannot Read", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
