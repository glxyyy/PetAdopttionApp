package com.example.petadopt;

public class petviewer {

    private String petName;
    private String petAge;
    private String petCategory;
    private String petGender;
    private String petBehaviour;
    private String petHistory;
    private String petContact;
    private String petIMGurl;
    private String isDeleted;

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public petviewer(String petName, String petAge, String petCategory, String petGender, String petBehaviour, String petHistory, String petContact, String imgPetIMG) {
        this.petName = petName;
        this.petAge = petAge;
        this.petCategory = petCategory;
        this.petGender = petGender;
        this.petBehaviour = petBehaviour;
        this.petHistory = petHistory;
        this.petContact = petContact;
        this.petIMGurl = imgPetIMG;
        this.isDeleted = isDeleted;
    }

    public String getPetContact() {
        return petContact;
    }

    public void setPetContact(String petContact) {
        this.petContact = petContact;
    }

    public String getpetIMGurl() {
        return petIMGurl;
    }

    public void setpetIMGurl(String petIMGurl) {
        this.petIMGurl = petIMGurl;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetBehaviour() {
        return petBehaviour;
    }

    public void setPetBehaviour(String petBehaviour) {
        this.petBehaviour = petBehaviour;
    }

    public String getPetHistory() {
        return petHistory;
    }

    public void setPetHistory(String petHistory) {
        this.petHistory = petHistory;
    }

    public petviewer(){

    }


}