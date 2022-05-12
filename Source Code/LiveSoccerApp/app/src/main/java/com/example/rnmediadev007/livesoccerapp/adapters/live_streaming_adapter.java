package com.example.rnmediadev007.livesoccerapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rnmediadev007.livesoccerapp.R;
import com.example.rnmediadev007.livesoccerapp.activity.video;
import com.example.rnmediadev007.livesoccerapp.fragments.live_streaming;

/**
 * Created by zeeshanbutt on 5/10/2017.
 */
public class live_streaming_adapter extends BaseAdapter {

    live_streaming main;

    public live_streaming_adapter(live_streaming main) {

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

    static class ViewHolderItem {
        TextView name_streaming;
        String name_streaming_str;


        public String video_url;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.live_streamming_cell, null);
            holder.name_streaming = (TextView) convertView.findViewById(R.id.hline);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolderItem) convertView.getTag();


        }

        holder.name_streaming_str = this.main.live_data_list.get(position).name;
        holder.video_url = this.main.live_data_list.get(position).url;


        holder.name_streaming.setText(holder.name_streaming_str);



        final ViewHolderItem finalHolder = holder;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opn = new Intent(main.getActivity(), video.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                opn.putExtra("live_url", finalHolder.video_url);
                opn.putExtra("key","live");
                main.startActivity(opn);

                // full_scorboard.checkmatchid(finalHolder.datapath);
            }
        });


        return convertView;
    }

}