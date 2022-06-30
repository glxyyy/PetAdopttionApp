package com.example.petadopt;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class petsVH extends RecyclerView.ViewHolder
{
    public TextView petName, petAge, petCategory,petGender,petBehaviour,petHistory,petContact,isDeleted , petIMGurl;
    public petsVH(@NonNull View itemView) {
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
