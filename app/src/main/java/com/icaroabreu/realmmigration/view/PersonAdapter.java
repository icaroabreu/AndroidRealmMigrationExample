package com.icaroabreu.realmmigration.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.icaroabreu.realmmigration.R;
import com.icaroabreu.realmmigration.model.Person;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Icaro Abreu on 05/05/2015.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    private List<Person> persons;
    private Activity activity;

    public PersonAdapter(Activity activity, List<Person> objects) {
        super(activity, 0, objects);

        this.persons = objects;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        Person person = persons.get(position);

        if(convertView != null)
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        else
        {
            convertView = ((LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.list_person_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.name.setText(person.getFirst_name() + " " + person.getLast_name());

        return convertView;
    }

    static class ViewHolder {

        @InjectView(R.id.person_name)
        TextView name;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

    }
}
