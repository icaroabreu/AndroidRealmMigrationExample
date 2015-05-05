package com.icaroabreu.realmmigration;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.icaroabreu.realmmigration.model.Person;
import com.icaroabreu.realmmigration.view.PersonAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {

    @InjectView(R.id.first_name)
    EditText firstNameInput;

    @InjectView(R.id.last_name)
    EditText lastNameInput;

    @InjectView(R.id.list_person)
    ListView list;

    private DatabaseManager manager;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        manager = new DatabaseManager(this);

        adapter = new PersonAdapter(this, manager.retrievePersons());

        list.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.close();
    }

    @OnClick(R.id.submit)
    public void newPerson()
    {
        Person newPerson = new Person();
        newPerson.setFirst_name(firstNameInput.getText().toString());
        newPerson.setLast_name(lastNameInput.getText().toString());
        manager.storePerson(newPerson);
        adapter.notifyDataSetChanged();
        firstNameInput.setText("");
        lastNameInput.setText("");
    }
}
