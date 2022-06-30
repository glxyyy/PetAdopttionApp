package com.example.petadopt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends FirebaseRecyclerAdapter<petviewer, Adapter.personsViewholder> {
    ArrayList<String> deleteValues = new ArrayList<>();
    DatabaseReference dRef;
    FirebaseDatabase firebaseDatabase;
    private ImageView imgview;
    private RecyclerViewOnclickListener listener;
    public Adapter(@NonNull FirebaseRecyclerOptions<petviewer> options)
    {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull personsViewholder holder, int position, @NonNull petviewer model)
    {

        dRef = FirebaseDatabase.getInstance().getReference("petTable");


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("petTable");
        reference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                String values = snapshot.toString();
                //Toast.makeText(getActivity(), values,Toast.LENGTH_SHORT).show();
//                Toast.makeText(Adapter.this, values, Toast.LENGTH_SHORT).show();
                String[] checkDelete = values.split("isDeleted=");
                for (int x = 1; x < checkDelete.length; x++)
                {
                    deleteValues.add(checkDelete[x].split(", ")[0]);
                    if(checkDelete[x].split(", ")[0].equals("No")){
                        Picasso.get().load(model.getpetIMGurl()).into(holder.petIMGurl);
                        holder.petAge.setText(model.getPetAge());
                        holder.petName.setText(model.getPetName());
                        holder.petGender.setText(model.getPetGender());
                        holder.petBehaviour.setText(model.getPetBehaviour());
                        holder.petHistory.setText(model.getPetHistory());
                        holder.petCategory.setText(model.getPetCategory());
                        holder.petContact.setText(model.getPetContact());
                        holder.isDeleted.setText(model.getIsDeleted());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });


//        dRef = FirebaseDatabase.getInstance().getReference("petTable");
//        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                String isDeleted = snapshot.child("isDeleted").getValue().toString();
//                if (snapshot.exists()) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        String isDeleted = dataSnapshot.child("isDeleted").getValue().toString();
//                        if (isDeleted.equals("Yes")) {
//                            String petName = dataSnapshot.child("petName").getValue().toString();
//                            holder.petName.setText(petName);
//
//                        }
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        query.addListenerForSingleValueEvent(valueEventListener);

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custrom_view, parent, false);
                return new Adapter.personsViewholder(view);

    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder extends RecyclerView.ViewHolder {

        ImageView petIMGurl;
        TextView petName, petAge, petCategory, petGender, petBehaviour, petHistory, petContact, isDeleted;

        public personsViewholder(@NonNull View itemView)
        {

            super(itemView);
            petName = itemView.findViewById(R.id.tvPetName);
            petAge = itemView.findViewById(R.id.tvPetAge);
            petCategory = itemView.findViewById(R.id.tvPetCategory);
            petGender = itemView.findViewById(R.id.tvPetGender);
            petBehaviour = itemView.findViewById(R.id.tvPetBehaviour);
            petHistory = itemView.findViewById(R.id.tvPetHistory);
            petContact = itemView.findViewById(R.id.tvPetContact);
            petIMGurl = itemView.findViewById(R.id.imgPetIMG);
            isDeleted = itemView.findViewById(R.id.tvpetIsDeleted);

        }
    }
    public interface RecyclerViewOnclickListener{
        void onClick(View v, int position);
    }
}