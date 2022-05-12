package com.example.rnmediadev007.livesoccerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rnmediadev007.livesoccerapp.R;
import com.example.rnmediadev007.livesoccerapp.fragments.MatchInfo;
import com.squareup.picasso.Picasso;

/**
 * Created by zeeshan on 3/18/2017.
 */

public class ListAdapter_matchInfo extends BaseAdapter {

    MatchInfo main;

    public ListAdapter_matchInfo(MatchInfo main) {

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
        ImageView img, img1;
        public String date;
        public TextView player, player1, minute, minute1;
        public String p_name, p_minute, p_type;
        public String a_name;
        public String a_type;
        public String a_minute;
        public RelativeLayout away_layout, home_layout;
        public String a_out_name, a_in_name, p_out_name, p_in_name;
        public TextView player1_out, player_out;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.matchinfo_list_cell, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.img1 = (ImageView) convertView.findViewById(R.id.img1);
            holder.player = (TextView) convertView.findViewById(R.id.player);
            holder.player_out = (TextView) convertView.findViewById(R.id.player_out);
            holder.player1 = (TextView) convertView.findViewById(R.id.player1);
            holder.player1_out = (TextView) convertView.findViewById(R.id.player1_out);
            holder.minute = (TextView) convertView.findViewById(R.id.minute);
            holder.minute1 = (TextView) convertView.findViewById(R.id.minute1);
            holder.home_layout = (RelativeLayout) convertView.findViewById(R.id.rl_home);
            holder.away_layout = (RelativeLayout) convertView.findViewById(R.id.rl_away);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }
        holder.p_name = this.main.live_data_list.get(position).p_name;
        holder.p_in_name = this.main.live_data_list.get(position).p_in_name;
        holder.p_out_name = this.main.live_data_list.get(position).p_out_name;
        holder.a_name = this.main.live_data_list.get(position).a_name;
        holder.a_in_name = this.main.live_data_list.get(position).a_in_name;
        holder.a_out_name = this.main.live_data_list.get(position).a_out_name;
        holder.p_type = this.main.live_data_list.get(position).p_type;
        holder.a_type = this.main.live_data_list.get(position).a_type;
        holder.p_minute = this.main.live_data_list.get(position).p_minute;
        holder.a_minute = this.main.live_data_list.get(position).a_minute;
        holder.date = this.main.live_data_list.get(position).date;
        if (holder.p_name != null && !holder.p_name.equals(null) && !holder.p_name.isEmpty() || (holder.p_type != null && !holder.p_type.equals(null) && !holder.p_type.isEmpty())) {
            if (holder.p_type.equals("goal")) {
                Picasso.with(main.getContext()).load(R.drawable.goal_icon).into(holder.img);

                holder.player.setText(holder.p_name);
                holder.minute.setText(holder.p_minute + "'");
                holder.home_layout.setVisibility(View.VISIBLE);
            }
            if (holder.p_type.equals("yellowcard")) {
                Picasso.with(main.getContext()).load(R.drawable.yellowcard_icon).into(holder.img);
                holder.player.setText(holder.p_name);
                holder.minute.setText(holder.p_minute + "'");
                holder.home_layout.setVisibility(View.VISIBLE);

            }
            if (holder.p_type.equals("redwcard")) {
                Picasso.with(main.getContext()).load(R.drawable.redcard_icon).into(holder.img);
                holder.player.setText(holder.p_name);
                holder.minute.setText(holder.p_minute + "'");
                holder.home_layout.setVisibility(View.VISIBLE);

            }
            if (holder.p_type.equals("substitution")) {
                Picasso.with(main.getContext()).load(R.drawable.inout_icon).into(holder.img);
                if (!holder.p_out_name.equals(null) && !holder.p_out_name.isEmpty()) {
                    holder.player_out.setVisibility(View.VISIBLE);
                    holder.player_out.setText(holder.a_out_name);
                }
                holder.player.setText(holder.p_in_name);
                holder.minute.setText(holder.p_minute + "'");
                holder.home_layout.setVisibility(View.VISIBLE);
            }


        }
        if (holder.a_name != null && !holder.a_name.equals(null) && !holder.a_name.isEmpty() || (holder.a_type != null && !holder.a_type.equals(null) && !holder.a_type.isEmpty())) {

            if (holder.a_type.equals("goal")) {
                Picasso.with(main.getContext()).load(R.drawable.goal_icon).into(holder.img1);
                holder.away_layout.setVisibility(View.VISIBLE);
                holder.player1.setText(holder.a_name);
                holder.minute1.setText(holder.a_minute + "'");
            }
            if (holder.a_type.equals("yellowcard")) {
                Picasso.with(main.getContext()).load(R.drawable.yellowcard_icon).into(holder.img1);
                holder.away_layout.setVisibility(View.VISIBLE);
                holder.player1.setText(holder.a_name);
                holder.minute1.setText(holder.a_minute + "'");
            }
            if (holder.a_type.equals("redcard")) {
                Picasso.with(main.getContext()).load(R.drawable.redcard_icon).into(holder.img1);
                holder.away_layout.setVisibility(View.VISIBLE);
                holder.player1.setText(holder.a_name);
                holder.minute1.setText(holder.a_minute + "'");
            }
            if (holder.a_type.equals("substitution")) {
                Picasso.with(main.getContext()).load(R.drawable.inout_icon).into(holder.img1);

                holder.away_layout.setVisibility(View.VISIBLE);
                if (!holder.a_out_name.equals(null) && !holder.a_out_name.isEmpty()) {
                    holder.player1_out.setVisibility(View.VISIBLE);
                    holder.player1_out.setText(holder.a_out_name);
                }

                holder.player1.setText(holder.a_in_name);
                holder.minute1.setText(holder.a_minute + "'");
            }


        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Intent opn = new Intent(main.getContext(), news_detail.class);
                opn.putExtra("img", main.live_data_list.get(position).image_url);
                opn.putExtra("heading", main.live_data_list.get(position).type);
                opn.putExtra("desc", main.live_data_list.get(position).new_desc);

                main.getContext().startActivity(opn);*/

            }
        });


        return convertView;
    }
}
