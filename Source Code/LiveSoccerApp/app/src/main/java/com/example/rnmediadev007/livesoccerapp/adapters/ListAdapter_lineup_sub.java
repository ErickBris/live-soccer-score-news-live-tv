package com.example.rnmediadev007.livesoccerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rnmediadev007.livesoccerapp.R;
import com.example.rnmediadev007.livesoccerapp.fragments.LineUp;

/**
 * Created by zeeshan on 3/18/2017.
 */

public class ListAdapter_lineup_sub extends BaseAdapter {

    LineUp main;

    public ListAdapter_lineup_sub(LineUp main) {

        this.main = main;
    }


    @Override
    public int getCount() {
        return main.live_data_list_sub.size();
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
        public String p_name, p_type;
        public String a_name;
        public String a_type;
        public String a_minute;
        public RelativeLayout away_layout, home_layout;
        private final int[] bgColors = new int[]{R.color.colorAccent1, R.color.offwhite};
        public String a_position_str, p_position_str;
        public TextView a_position, p_position;
        public LinearLayout layout;
        public String p_logo;
        public String a_logo;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lineup_list_cell, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.img1 = (ImageView) convertView.findViewById(R.id.img1);
            holder.player = (TextView) convertView.findViewById(R.id.player);
            holder.player1 = (TextView) convertView.findViewById(R.id.player1);
            holder.p_position = (TextView) convertView.findViewById(R.id.position);
            holder.a_position = (TextView) convertView.findViewById(R.id.position1);
            holder.home_layout = (RelativeLayout) convertView.findViewById(R.id.rl_home);
            holder.away_layout = (RelativeLayout) convertView.findViewById(R.id.rl_away);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.main_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }
        int colorPosition = position % holder.bgColors.length;
        holder.layout.setBackgroundResource(holder.bgColors[colorPosition]);
        holder.p_name = this.main.live_data_list_sub.get(position).p_name;
        holder.a_name = this.main.live_data_list_sub.get(position).a_name;
        holder.p_type = this.main.live_data_list_sub.get(position).p_type;
        holder.a_type = this.main.live_data_list_sub.get(position).a_type;
        holder.p_position_str = this.main.live_data_list_sub.get(position).p_position;
        holder.a_position_str = this.main.live_data_list_sub.get(position).a_position;
        holder.p_logo = this.main.live_data_list.get(position).p_logo;
        holder.a_logo = this.main.live_data_list.get(position).a_logo;
        if (holder.p_name != null && !holder.p_name.equals(null) && !holder.p_name.isEmpty() || (holder.p_type != null && !holder.p_type.equals(null) && !holder.p_type.isEmpty())) {


            if (holder.p_type.equals("substitution")) {
                /*if (!holder.p_logo.equals(null) && !holder.p_logo.isEmpty()) {
                    Picasso.with(main.getContext()).load(holder.p_logo).placeholder(R.drawable.football_player_small).into(holder.img);
                } else {

                    Picasso.with(main.getContext()).load(R.drawable.football_player_small).placeholder(R.drawable.football_player_small).into(holder.img);
                }*/
                holder.player.setText(holder.p_name);
                if (holder.a_position_str != null) {
                    holder.p_position.setText("position" + holder.p_position_str);
                }
                holder.home_layout.setVisibility(View.VISIBLE);

            }
        }
        if (holder.a_name != null && !holder.a_name.equals(null) && !holder.a_name.isEmpty() || (holder.a_type != null && !holder.a_type.equals(null) && !holder.a_type.isEmpty())) {

            if (holder.a_type.equals("substitution")) {
               /* if (!holder.a_logo.equals(null) && !holder.a_logo.isEmpty()) {
                    Picasso.with(main.getContext()).load(holder.a_logo).placeholder(R.drawable.football_player_small).into(holder.img);
                } else {

                    Picasso.with(main.getContext()).load(R.drawable.football_player_small).placeholder(R.drawable.football_player_small).into(holder.img);
                }*/
                holder.away_layout.setVisibility(View.VISIBLE);
                holder.player1.setText(holder.a_name);
                if (holder.a_position_str != null) {
                    holder.a_position.setText("position : " + holder.a_position_str);
                }
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
