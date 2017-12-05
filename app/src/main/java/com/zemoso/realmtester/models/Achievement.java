package com.zemoso.realmtester.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zemoso on 4/12/17.
 */

public class Achievement extends RealmObject {

    private String certification;
    private String courses;
    @PrimaryKey
    private int id;
    private String extraCurricular;

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtraCurricular() {
        return extraCurricular;
    }

    public void setExtraCurricular(String extraCurricular) {
        this.extraCurricular = extraCurricular;
    }
}
