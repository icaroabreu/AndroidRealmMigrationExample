package com.icaroabreu.realmmigration;

import com.icaroabreu.realmmigration.model.Person;

import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.internal.ColumnType;
import io.realm.internal.Table;

/**
 * Created by Icaro Abreu on 05/05/2015.
 */
public class DBMigration implements RealmMigration {
    @Override
    public long execute(Realm realm, long version) {

        if(version == 0)
        {
            Table personTable = realm.getTable(Person.class);
            long nameIndex = getIndexForProperty(personTable, "name");
            long firstNameIndex = personTable.addColumn(ColumnType.STRING, "first_name");
            long lastNameIndex = personTable.addColumn(ColumnType.STRING, "last_name");
            for (int i = 0; i < personTable.size(); i++) {

                personTable.setString(firstNameIndex, i, personTable.getString(nameIndex, i));
                personTable.setString(lastNameIndex, i, "Doe");

            }
            personTable.removeColumn(nameIndex);
            version++;
        }

        return version;
    }

    private long getIndexForProperty(Table table, String name) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
