package com.example.petadopt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petadopt.databinding.ActivityUploadpetBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class uploadpet extends AppCompatActivity {
    private Button btnSelect, btnUpload;
    private ImageView imageView2;
    private Button buttonmenuback;
    DatabaseReference root;
    FirebaseDatabase rootNode;
    private Button submit2;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;
    FirebaseStorage storage;
    StorageReference storageReference;
    EditText petname_, petage_, petgender_, petbehaviour_, pethistory_, petcategory_, petcontact_;
    TextView isdeleted;
    public Uri imgurl;
    String mCurrentUserId2;
    ActivityUploadpetBinding binding2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpet);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference();
        mAuth = FirebaseAuth.getInstance();
        mCurrentUserId2 = mAuth.getCurrentUser().getUid();
        //getSupportActionBar().hide();//yes
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//yes
        petname_ = findViewById(R.id.eTXTpetname);
        petage_ = findViewById(R.id.eTXTpetage);
        petgender_ = findViewById(R.id.eTXTpetgender);
        petbehaviour_ = findViewById(R.id.eTXTpetbehave);
        pethistory_ = findViewById(R.id.eTXTpethistory);
        petcategory_ = findViewById(R.id.eTXTpetcateg);
        petcontact_ = findViewById(R.id.eTxtpetcontact);
        isdeleted = findViewById(R.id.txtViewisDeleted);

//        btnUpload = findViewById(R.id.buttonuploadimg);
        imageView2 = findViewById(R.id.petimg);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        binding2 = ActivityUploadpetBinding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());

        // on pressing btnUpload uploadImage() is called
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//
//            }
//        });
//        buttonmenuback = findViewById(R.id.buttonmenuback);
//        buttonmenuback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                backtomenu();
//            }
//        });
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.ViewPets:
                    backtomenu();
                    return true;

                case R.id.UpdatePet:
                    openuploadedpet();
                    return true;

                case R.id.UserProfile:
                    openuploadedpet();
                    return true;

            }
            return false;
        });
        submit2 = findViewById(R.id.buttonsubmt);
        binding2.buttonsubmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String petname = binding2.eTXTpetname.getText().toString();
                String petage = binding2.eTXTpetage.getText().toString();
                String petgender = binding2.eTXTpetgender.getText().toString();
                String petbehave = binding2.eTXTpetbehave.getText().toString();
                String pethistory = binding2.eTXTpethistory.getText().toString();
                String petcategory = binding2.eTXTpetcateg.getText().toString();
                String petcontact = binding2.eTxtpetcontact.getText().toString();
                String isdeleted = binding2.txtViewisDeleted.getText().toString();
                String petimgs = filePath.toString();


                updateData(petname, petage, petgender, petbehave, pethistory, petcategory, petcontact, petimgs, isdeleted);
                uploadImage();
                //showAllUserData();
            }
        });
        btnSelect = findViewById(R.id.buttonselectimg);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SelectImage();
            }
        });
    }
    private void openuploadedpet() {
        Intent intent = new Intent(this, uploadedpets.class);
        startActivity(intent);
    }
    private void openuploadedpe2t() {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }


    private void updateData(String petname, String petage, String petgender, String petbehave, String pethistory, String petcategory, String petcontact, String petimgs, String isdeleted) {
        HashMap pets = new HashMap();
        pets.put("petName",petname);
        pets.put("petAge",petage);
        pets.put("petGender",petgender);
        pets.put("petBehaviour",petbehave);
        pets.put("petHistory",pethistory);
        pets.put("petCategory",petcategory);
        pets.put("petContact",petcontact);
        pets.put("petIMGurl",petimgs);
        pets.put("isDeleted",isdeleted);



        root = FirebaseDatabase.getInstance().getReference("petTable");
        root.child(mCurrentUserId2).updateChildren(pets).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){

                    binding2.eTXTpetname.setText("");
                    binding2.eTXTpetage.setText("");
                    binding2.eTXTpetgender.setText("");
                    binding2.eTXTpetbehave.setText("");
                    binding2.eTXTpethistory.setText("");
                    binding2.eTXTpetcateg.setText("");
                    binding2.eTxtpetcontact.setText("");
                    binding2.txtViewisDeleted.setText("");
                    filePath.toString();

                    Toast.makeText(uploadpet.this, "Update Success", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(uploadpet.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void backtomenu(){
        Intent intent = new Intent(this, petselect.class);
        startActivity(intent);
    }
    private void submitpet(){
        Intent intent = new Intent(this, petselect.class);
        startActivity(intent);
    }
    private void SelectImage()
    {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                imageView2.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

//    public void inputanswers() {
//        String PetName = this.PetName.getText().toString().trim();
//        String PetAge = this.PetAge.getText().toString().trim();
//        String PetGender = this.PetGender.getText().toString().trim();
//        String PetBehaviour = this.PetBehaviour.getText().toString().trim();
//        String PetHistory = this.PetHistory.getText().toString().trim();
//        String PetCategory = this.PetCategory.getText().toString().trim();
//        String PetContact = this.PetContact.getText().toString().trim();
//        String UserID = this.mCurrentUserId;
//        String petIMGurl = this.filePath.toString();
//
//        root = FirebaseDatabase.getInstance().getReference().child("petTable");
//        String id = root.push().getKey();
//        root.child(id).child("petName").setValue(PetName);
//        root.child(id).child("petAge").setValue(PetAge);
//        root.child(id).child("petGender").setValue(PetGender);
//        root.child(id).child("petBehaviour").setValue(PetBehaviour);
//        root.child(id).child("petHistory").setValue(PetHistory);
//        root.child(id).child("petCategory").setValue(PetCategory);
//        root.child(id).child("petContact").setValue(PetContact);
//        root.child(id).child("userID").setValue(UserID);
//        root.child(id).child("petIMGurl").setValue(petIMGurl);
//
//        Toast.makeText(this, "it works", Toast.LENGTH_SHORT).show();
//    }

    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(uploadpet.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(uploadpet.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }
}