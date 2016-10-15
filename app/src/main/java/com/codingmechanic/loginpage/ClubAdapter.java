package com.codingmechanic.loginpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mofi on 10/15/16.
 */

public class ClubAdapter extends ArrayAdapter<Club> {
    public ClubAdapter(Context context, ArrayList<Club> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Club event = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name_club);
        TextView type = (TextView) convertView.findViewById(R.id.type_club);
        TextView email = (TextView) convertView.findViewById(R.id.club_email);
        TextView desc = (TextView) convertView.findViewById(R.id.club_desc);

        name.setText(event.getName());
        type.setText(event.getType());
        email.setText(event.getEmail());
        desc.setText(event.getDesc());

        return convertView;
        //return super.getView(position, convertView, parent);
    }

}
