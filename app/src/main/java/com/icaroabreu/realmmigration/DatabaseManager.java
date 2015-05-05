package com.icaroabreu.realmmigration;

import android.content.Context;

import com.icaroabreu.realmmigration.model.Person;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by Icaro Abreu on 05/05/2015.
 */
public class DatabaseManager {

    private Realm realm;

    public DatabaseManager(Context ctx)
    {
        try {
            realm = Realm.getInstance(ctx);
        }catch (RealmMigrationNeededException e){
            String realmPath = new File(ctx.getFilesDir(), Realm.DEFAULT_REALM_NAME).getAbsolutePath();
            Realm.migrateRealmAtPath(realmPath, new DBMigration());
            realm = Realm.getInstance(ctx);
        }

    }

    public void storePerson(Person person)
    {
        realm.beginTransaction();

        Person realmPerson = realm.copyToRealm(person);

        realm.commitTransaction();

    }

    public RealmResults<Person> retrievePersons()
    {
        return realm.allObjects(Person.class);
    }

    public void close()
    {
        this.realm.close();
    }

}
