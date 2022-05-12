package com.example.rnmediadev007.livesoccerapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rnmediadev007.livesoccerapp.activity.news_detail;
import com.example.rnmediadev007.livesoccerapp.fragments.News;
import com.example.rnmediadev007.livesoccerapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by zeeshan on 3/18/2017.
 */

public class ListAdapter_lastestnews extends BaseAdapter {

    News main;

    public ListAdapter_lastestnews(News main) {

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
        TextView type, hline, title,time;
        ImageView img;
        public String img_datapath_url;
        public String type_str_news;
        public String title_str_news;
        public String date;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.news_list_cell, null);
            holder.img = (ImageView) convertView.findViewById(R.id.news_img);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }
        holder.img_datapath_url = this.main.live_data_list.get(position).image_url;
        holder.type_str_news = this.main.live_data_list.get(position).type;
        holder.title_str_news = this.main.live_data_list.get(position).title;
        holder.date = this.main.live_data_list.get(position).date;
if(!holder.img_datapath_url.isEmpty()){
    Picasso.with(main.getContext()).load(holder.img_datapath_url).into(holder.img);
}else{

}



        holder.time.setText(holder.date);
        holder.type.setText(holder.type_str_news);
        holder.title.setText(holder.title_str_news);




        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent opn = new Intent(main.getContext(),news_detail.class);
                opn.putExtra("img", main.live_data_list.get(position).image_url);
                opn.putExtra("heading", main.live_data_list.get(position).type);
                opn.putExtra("desc", main.live_data_list.get(position).new_desc);

                main.getContext().startActivity(opn);

            }
        });


        return convertView;
    }
}
