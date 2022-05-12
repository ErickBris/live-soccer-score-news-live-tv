package com.example.rnmediadev007.livesoccerapp.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rnmediadev007.livesoccerapp.App.AppController;
import com.example.rnmediadev007.livesoccerapp.App.Constants;
import com.example.rnmediadev007.livesoccerapp.R;
import com.example.rnmediadev007.livesoccerapp.activity.Live_matches_Details;
import com.example.rnmediadev007.livesoccerapp.adapters.ListAdapter_matchInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MatchInfo extends Fragment {
    // json array response url

    private static String TAG = MatchInfo.class.getSimpleName();
    private ListView mListView;
    // Progress dialog
    private ProgressDialog pDialog;
    public ListAdapter_matchInfo listAdapter;
    private ListView listView;
    public ArrayList<Constants> live_data_list = new ArrayList<Constants>();
    RelativeLayout content_main;
    Constants constants;
    View rootView;
    Live_matches_Details live_matches_details;
    private LinearLayout venue_layout;
    TextView venue;
    private TextView nodatafound;

    public MatchInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.matchinfo, container, false);
        // listView = (ListView) rootView.findViewById(R.id.listView);
        content_main = (RelativeLayout) rootView.findViewById(R.id.content_main);
        this.mListView = (ListView) rootView.findViewById(R.id.list_tracks);
        this.venue = (TextView) rootView.findViewById(R.id.venue);
        this.nodatafound = (TextView) rootView.findViewById(R.id.NomatchFound);
        this.venue_layout= (LinearLayout) rootView.findViewById(R.id.venue_layout);
        listAdapter = new ListAdapter_matchInfo(this);
        // listView.setAdapter(listAdapter);
        mListView.setDivider(null);
        mListView.setAdapter(listAdapter);
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        makeJsonArrayRequest();

        return rootView;
    }
    private void makeJsonArrayRequest() {
        venue.setText(live_matches_details.venue_name+"("+live_matches_details.countery+")");
        String urlJsonArry = "http://www.phoneappsking.com/soccer/event.php?match="+live_matches_details.match_id;
        String tag_string_req1 = "string_req";
        // Defined Array values to show in ListView
        final ProgressDialog pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest strReq1 = new StringRequest(Request.Method.GET,
                urlJsonArry, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                venue_layout.setVisibility(View.VISIBLE);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        constants = new Constants();
                        if(!jsonArray.equals(null) && jsonArray.length()>0) {
                            constants.team_id = jsonObject1.getString("team_id");
                            if (jsonObject1.getString("team_id").matches(live_matches_details.home_id)) {
nodatafound.setVisibility(View.GONE);
mListView.setVisibility(View.VISIBLE);
                                //  Toast.makeText(getContext(), live_matches_details.home_id+"home"+live_matches_details.away_id, Toast.LENGTH_SHORT).show();
                                constants.p_minute = jsonObject1.getString("minute");
                                constants.p_extra_minute = jsonObject1.getString("extra_minute");
                                constants.p_type = jsonObject1.getString("type");
                                constants.p_id = jsonObject1.getString("player_id");
                                constants.p_name = jsonObject1.getString("player_name");
                                constants.p_assist_id = jsonObject1.getString("assist_id");
                                constants.p_assist_name = jsonObject1.getString("assist_player_name");
                                constants.p_in_id = jsonObject1.getString("player_in_id");
                                constants.p_in_name = jsonObject1.getString("player_in_name");
                                constants.p_out_id = jsonObject1.getString("player_out_id");
                                constants.p_out_name = jsonObject1.getString("player_out_name");
                                constants.p_is_new = jsonObject1.getString("is_new");
                                constants.p_is_exist = jsonObject1.getString("is_exist");
                                constants.p_logo = jsonObject1.getString("player_logo");
                                constants.p_in_logo = jsonObject1.getString("player_in_logo");
                                constants.p_out_logo = jsonObject1.getString("player_out_logo");
                            }
                            if (jsonObject1.getString("team_id").equals(live_matches_details.away_id)) {
                                //  Toast.makeText(getContext(), live_matches_details.home_id+"away"+live_matches_details.away_id, Toast.LENGTH_SHORT).show();
                                constants.a_minute = jsonObject1.getString("minute");
                                constants.a_extra_minute = jsonObject1.getString("extra_minute");
                                constants.a_type = jsonObject1.getString("type");
                                constants.a_id = jsonObject1.getString("player_id");
                                constants.a_name = jsonObject1.getString("player_name");
                                constants.a_assist_id = jsonObject1.getString("assist_id");
                                constants.a_assist_name = jsonObject1.getString("assist_player_name");
                                constants.a_in_id = jsonObject1.getString("player_in_id");
                                constants.a_in_name = jsonObject1.getString("player_in_name");
                                constants.a_out_id = jsonObject1.getString("player_out_id");
                                constants.a_out_name = jsonObject1.getString("player_out_name");
                                constants.a_is_new = jsonObject1.getString("is_new");
                                constants.a_is_exist = jsonObject1.getString("is_exist");
                                constants.a_logo = jsonObject1.getString("player_logo");
                                constants.a_in_logo = jsonObject1.getString("player_in_logo");
                                constants.a_out_logo = jsonObject1.getString("player_out_logo");
                            }
                            live_data_list.add(constants);
                        }
                    }
                    listAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                pDialog.hide();

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }


//                Toast.makeText(getContext(), message + "income", Toast.LENGTH_LONG).show();

                pDialog.hide();
            }
        }) {

        };

        // Adding request to request queue
        AppController.getInstance().

                addToRequestQueue(strReq1, tag_string_req1);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
