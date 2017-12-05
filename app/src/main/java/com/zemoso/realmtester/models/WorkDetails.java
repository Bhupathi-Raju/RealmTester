package com.zemoso.realmtester.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zemoso on 4/12/17.
 */

public class WorkDetails extends RealmObject {
    @PrimaryKey
    private int id;
    private String nameOfCompany;
    private int yearsWorked;
    private String currentRole;
    private String position;
    private Long  companyPhone;

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public int getYearsWorked() {
        return yearsWorked;
    }

    public void setYearsWorked(int yearsWorked) {
        this.yearsWorked = yearsWorked;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(Long companyPhone) {
        this.companyPhone = companyPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
