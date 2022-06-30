package com.example.petadopt;

public class NewUserCreate {
    String lgUser, lgPass;

    public NewUserCreate(String lgUser, String lgPass) {
        this.lgUser = lgUser;
        this.lgPass = lgPass;
    }

    public String getLgUser() {
        return lgUser;
    }

    public void setLgUser(String lgUser) {
        this.lgUser = lgUser;
    }
}
