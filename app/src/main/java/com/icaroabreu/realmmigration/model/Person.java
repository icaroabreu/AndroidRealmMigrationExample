package com.icaroabreu.realmmigration.model;

import io.realm.RealmObject;

/**
 * Created by Icaro Abreu on 05/05/2015.
 */
public class Person extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
