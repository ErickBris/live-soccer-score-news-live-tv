package com.example.rnmediadev007.livesoccerapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rnmediadev007.livesoccerapp.R;
import com.example.rnmediadev007.livesoccerapp.activity.Live_matches_Details;
import com.example.rnmediadev007.livesoccerapp.fragments.Live_matches_Fragment;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zeeshan on 3/18/2017.
 */

public class ListAdapter_live_matches extends BaseAdapter {

    Live_matches_Fragment main;

    public ListAdapter_live_matches(Live_matches_Fragment main) {

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
        ImageView img_datapath;
        public TextView home_team_name, teams_score, away_team_name, counteryName;
        public String counteryFlag, home_team_score, away_team_score;
        private final int[] bgColors = new int[]{R.drawable.live_top_bar_blue, R.drawable.live_top_bar_green};
        public RelativeLayout bg_top_bar;
        public String date;
        public String date_str;
        public TextView date_txt;
        public ImageView img_datapath_away, img_datapath_home;
        public String home_logo, away_logo;
        public TextView minutes;
        public String minutes_str;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.live_matches_list_cell, null);
            holder.img_datapath = (ImageView) convertView.findViewById(R.id.img);
            holder.img_datapath_away = (ImageView) convertView.findViewById(R.id.away_team_img);
            holder.img_datapath_home = (ImageView) convertView.findViewById(R.id.home_team_img);
            holder.home_team_name = (TextView) convertView.findViewById(R.id.home_team_name);
            holder.counteryName = (TextView) convertView.findViewById(R.id.countryname);
            holder.away_team_name = (TextView) convertView.findViewById(R.id.away_team_name);
            holder.teams_score = (TextView) convertView.findViewById(R.id.score);
            holder.minutes = (TextView) convertView.findViewById(R.id.minutes);
            holder.bg_top_bar = (RelativeLayout) convertView.findViewById(R.id.top_bar);
            holder.date_txt = (TextView) convertView.findViewById(R.id.date);
            Typeface face = Typeface.createFromAsset(main.getActivity().getAssets(), "fonts/big_noodle_titling.ttf");
            holder.teams_score.setTypeface(face);
            holder.minutes.setTypeface(face);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }
        holder.home_team_score = this.main.live_data_list.get(position).data_home_score;
        holder.away_team_score = this.main.live_data_list.get(position).data_away_score;
        holder.minutes_str = this.main.live_data_list.get(position).data_minute;
        if (holder.home_team_score != null || holder.away_team_score != null) {
            holder.teams_score.setText(holder.home_team_score + " - " + holder.away_team_score);
            int colorPosition = position % holder.bgColors.length;
            holder.bg_top_bar.setBackgroundResource(holder.bgColors[colorPosition]);
        }
        if (holder.minutes_str != null && !holder.minutes_str.isEmpty()) {

            holder.minutes.setText(holder.minutes_str + "'");
        }
        holder.home_team_name.setText(this.main.live_data_list.get(position).data_home_team_name);
        holder.away_team_name.setText(this.main.live_data_list.get(position).data_away_team_name);
        holder.counteryName.setText(this.main.live_data_list.get(position).data_country_name);
        holder.counteryFlag = this.main.live_data_list.get(position).data_country_flag;
        holder.home_logo = this.main.live_data_list.get(position).data_home_team_logo;
        holder.away_logo = this.main.live_data_list.get(position).data_away_team_logo;
        holder.date_str = this.main.live_data_list.get(position).data_starting_date;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        String inputDateStr = holder.date_str;
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
            String outputDateStr = outputFormat.format(date);
            holder.date_txt.setText(outputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!holder.counteryFlag.isEmpty()) {
            Picasso.with(main.getActivity()).load(holder.counteryFlag).placeholder(R.drawable.flag_icon).into(holder.img_datapath);
        }
        if (!holder.home_logo.isEmpty() && !holder.home_logo.equals(null)) {
            Picasso.with(main.getActivity()).load(holder.home_logo).placeholder(R.drawable.flag_icon).into(holder.img_datapath_home);
        } else {
            Picasso.with(main.getActivity()).load(R.drawable.flag_icon).into(holder.img_datapath_home);
        }
        if (!holder.away_logo.isEmpty() && !holder.away_logo.equals(null)) {
            Picasso.with(main.getActivity()).load(holder.away_logo).placeholder(R.drawable.flag_icon).into(holder.img_datapath_away);
        } else {
            Picasso.with(main.getActivity()).load(R.drawable.flag_icon).into(holder.img_datapath_away);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent opn = new Intent(main.getContext(), Live_matches_Details.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                opn.putExtra("id", main.live_data_list.get(position).data_id);
                opn.putExtra("home_team_id", main.live_data_list.get(position).data_home_team_id);
                opn.putExtra("away_team_id", main.live_data_list.get(position).data_away_team_id);
                opn.putExtra("name", main.live_data_list.get(position).data_venue_name);
                opn.putExtra("countery", main.live_data_list.get(position).data_country_name);
                main.getContext().startActivity(opn);

            }
        });


        return convertView;
    }
}
