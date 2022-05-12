package com.example.rnmediadev007.livesoccerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rnmediadev007.livesoccerapp.R;
import com.example.rnmediadev007.livesoccerapp.fragments.Commentary;

/**
 * Created by zeeshan on 3/18/2017.
 */

public class ListAdapter_commentary extends BaseAdapter {

    Commentary main;

    public ListAdapter_commentary(Commentary main) {

        this.main = main;
    }


    @Override
    public int getCount() {
        return main.live_data_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolderItem {
        TextView imp, goal, minute;


        public String imp_str;
        public String goal_str;
        public String minute_str;
        public String body;
        public TextView commentary;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment_cell, null);
            holder.goal = (TextView) convertView.findViewById(R.id.goal);
            holder.imp = (TextView) convertView.findViewById(R.id.imp);
            holder.minute = (TextView) convertView.findViewById(R.id.minute);
            holder.commentary = (TextView) convertView.findViewById(R.id.commentary);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }
        holder.imp_str = this.main.live_data_list.get(position).c_imp;
        holder.goal_str = this.main.live_data_list.get(position).c_goal;
        holder.minute_str = this.main.live_data_list.get(position).c_minute;
        holder.body = this.main.live_data_list.get(position).c_body;
        holder.goal.setText("Is Goal : "+holder.goal_str);
        holder.imp.setText("Important : "+holder.imp_str);
        holder.minute.setText("Minute : "+holder.minute_str);
        holder.commentary.setText(holder.body);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Intent opn = new Intent(main.getContext(),news_detail.class);
                opn.putExtra("img", main.live_data_list.get(position).image_url);
                opn.putExtra("heading", main.live_data_list.get(position).type);
                opn.putExtra("desc", main.live_data_list.get(position).new_desc);

                main.getContext().startActivity(opn);
                */
            }
        });


        return convertView;
    }
}
