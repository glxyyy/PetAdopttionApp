package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class petselect extends AppCompatActivity {

//    TextView  petAgeTXT, petCategoryTXT, petGenderTXT, petHistoryTXT, petNameTXT, petBehaviourTXT;
//    ArrayList<String> petAgeTXTt = new ArrayList<String>();
//    ArrayList<String> petCategoryTXTt = new ArrayList<String>();
//    ArrayList<String> petGenderTXTt = new ArrayList<String>();
//    ArrayList<String> petHistoryTXTt = new ArrayList<String>();
//    ArrayList<String> petNameTXTt = new ArrayList<String>();
//    ArrayList<String> petBehaviourTXTt = new ArrayList<String>();
//    DatabaseReference reference;
//    String test;
//    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private StorageReference mStorageReference;
    private FirebaseAuth mAuth;
    String mCurrentUserId;
    private Button button6;
    private Button buttonpostpet;
    private Button buttonuoploadedpet;
    Adapter adapter;
//    ArrayList<String> pets;
    private RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petselect);

        // Create a instance of the database and get
        // its reference
        mbase = FirebaseDatabase.getInstance().getReference().child("petTable");
//        DatabaseReference getImage = databaseReference.child("image");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUserId = mAuth.getCurrentUser().getUid();
        recyclerView = findViewById(R.id.cycler);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<petviewer> options = new FirebaseRecyclerOptions.Builder<petviewer>().setQuery(mbase, petviewer.class).build();
        adapter = new Adapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
//        setContentView(R.layout.activity_petselect);
//        petAgeTXT = (TextView) findViewById(R.id.tvPetAge);
//        petCategoryTXT = (TextView) findViewById(R.id.tvPetCategory);
//        petGenderTXT = (TextView) findViewById(R.id.tvPetGender);
//        petHistoryTXT = (TextView) findViewById(R.id.tvPetHistory);
//        petNameTXT = (TextView) findViewById(R.id.tvPetName);
//        petBehaviourTXT = (TextView) findViewById(R.id.tvPetBehaviour);
//        firebaseDatabase = FirebaseDatabase.getInstance();

//        buttonuoploadedpet = (Button) findViewById(R.id.buttonupdatepet);
//        buttonuoploadedpet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openuploadedpet();
//            }
//        });
        //viewpet();
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpage4();
            }
        });
//        buttonpostpet = findViewById(R.id.buttonpostpet);
//        buttonpostpet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                gotoupload();
//            }
//        });
        //Toast.makeText(petselect.this,mCurrentUserId , Toast.LENGTH_SHORT).show();
        //Toast.makeText(petselect.this, , Toast.LENGTH_SHORT).show();
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.UploadPet:
                    gotoupload();
                    return true;

                case R.id.UpdatePet:
                    openuploadedpet();
                    return true;

                case R.id.UserProfile:
                    gotoupload2();
                    return true;

            }
            return false;
        });
    }

    private void openuploadedpet() {
        Intent intent = new Intent(this, uploadedpets.class);
        startActivity(intent);
    }

    public void gotoupload() {
        Intent intent = new Intent(this, uploadpet.class);
        startActivity(intent);
    }
    public void gotoupload2() {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();

    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();

    }

//    public void viewpet() {
//        reference = FirebaseDatabase.getInstance().getReference().child("petTable");
//        Query query = reference.orderByChild("petName");
//        ArrayList<String> status = new ArrayList<String>();
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for(DataSnapshot snapshot1: snapshot.getChildren()){
////                    petviewer p = snapshot1.getValue(petviewer.class);
////                    //pets.add(p);
////                }
//                String values = snapshot.toString();
//                String[] petAgeTXTt_query = test.split(" petAge=");
//                String[] petCategoryTXTt_query = test.split(" petCategory=");
//                String[] petGenderTXTt_query = test.split(" petGender=");
//                String[] petHistoryTXTt_query = test.split(" petHistory=");
//                String[] petNameTXTt_query = test.split("petName=");
//                String[] petBehaviourTXTt_query = test.split(" petBehaviour=");
//
//                for (int x = 1; x < petAgeTXTt_query.length; x++) {
//                    petAgeTXTt.add(((petAgeTXTt_query[x].split(",")[0]).replace("}", "").replace(" ", "")));
//                }
//                for (int x = 1; x < petCategoryTXTt_query.length; x++) {
//                    petCategoryTXTt.add(((petCategoryTXTt_query[x].split(",")[0]).replace("}", "").replace(" ", "")));
//                }
//                for (int x = 1; x < petGenderTXTt_query.length; x++) {
//                    petGenderTXTt.add(((petGenderTXTt_query[x].split(",")[0]).replace("}", "").replace(" ", "")));
//                }
//                for (int x = 1; x < petHistoryTXTt_query.length; x++) {
//                    petHistoryTXTt.add(((petHistoryTXTt_query[x].split(",")[0]).replace("}", "").replace(" ", "")));
//                }
//                for (int x = 1; x < petNameTXTt_query.length; x++) {
//                    petNameTXTt.add(((petNameTXTt_query[x].split(",")[0]).replace("}", "").replace(" ", "")));
//                }
//                for (int x = 1; x < petBehaviourTXTt_query.length; x++) {
//                    petBehaviourTXTt.add(((petBehaviourTXTt_query[x].split(",")[0]).replace("}", "").replace(" ", "")));
//                }
//                //Toast.makeText(petselect.this, test, Toast.LENGTH_SHORT).show();
//
//                petAgeTXT.setText(petAgeTXTt.get(0));
//                petCategoryTXT.setText(petCategoryTXTt.get(0));
//                petGenderTXT.setText(petGenderTXTt.get(0));
//                petHistoryTXT.setText(petHistoryTXTt.get(0));
//                petNameTXT.setText(petNameTXTt.get(0));
//                petBehaviourTXT.setText(petBehaviourTXTt.get(0));
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }

    public void openpage4() {
        Intent intent = new Intent(this, page4.class);
        startActivity(intent);
    }

    public void buttonLikedPets() {
        Intent intent = new Intent(this, likedpets.class);
        startActivity(intent);
    }

}