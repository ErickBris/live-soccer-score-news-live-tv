package com.example.rnmediadev007.livesoccerapp.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.rnmediadev007.livesoccerapp.adapters.ListAdapter_lineup;
import com.example.rnmediadev007.livesoccerapp.adapters.ListAdapter_lineup_sub;
import com.example.rnmediadev007.livesoccerapp.model.NonScrollableList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LineUp extends Fragment {
    // json array response url
    private static String TAG = LineUp.class.getSimpleName();
    private NonScrollableList mListView, sub_list;
    // Progress dialog
    private ProgressDialog pDialog;
    public ListAdapter_lineup listAdapter;
    public ListAdapter_lineup_sub listAdapter1;
    private ListView listView;
    public ArrayList<Constants> live_data_list = new ArrayList<Constants>();
    public ArrayList<Constants> live_data_list_sub = new ArrayList<Constants>();
    RelativeLayout content_main;
    Constants constants;
    TextView squard, substitue, datanofound;
    View rootView;
    Live_matches_Details live_matches_details;

    public LineUp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.lineup, container, false);
        // listView = (ListView) rootView.findViewById(R.id.listView);
        content_main = (RelativeLayout) rootView.findViewById(R.id.content_main);
        this.mListView = (NonScrollableList) rootView.findViewById(R.id.list_tracks);
        this.sub_list = (NonScrollableList) rootView.findViewById(R.id.list_sub);
        this.squard = (TextView) rootView.findViewById(R.id.Squad);
        this.substitue = (TextView) rootView.findViewById(R.id.substiute);
        this.datanofound = (TextView) rootView.findViewById(R.id.NomatchFound);

        listAdapter = new ListAdapter_lineup(this);
        listAdapter1 = new ListAdapter_lineup_sub(this);
        // listView.setAdapter(listAdapter);
        mListView.setDivider(null);
        sub_list.setDivider(null);
        mListView.setAdapter(listAdapter);
        sub_list.setAdapter(listAdapter1);
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        makeJsonArrayRequest();

        return rootView;
    }

    private void makeJsonArrayRequest() {
        String urlJsonArry = "http://www.phoneappsking.com/soccer/lineup.php?match=" + live_matches_details.match_id;
        String tag_string_req1 = "string_req";
        // Defined Array values to show in ListView
        final ProgressDialog pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest strReq1 = new StringRequest(Request.Method.GET,
                urlJsonArry, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        if (!jsonArray.equals(null) && jsonArray.length() > 0) {
                            squard.setVisibility(View.VISIBLE);
                            substitue.setVisibility(View.VISIBLE);
                            datanofound.setVisibility(View.GONE);

                            constants = new Constants();
                            constants.team_id = jsonObject1.getString("team_id");
                            if (jsonObject1.getString("team_id").matches(live_matches_details.home_id)) {
                                constants.p_id = jsonObject1.getString("player_id");
                                constants.p_name = jsonObject1.getString("player_name");
                                constants.p_assist_id = jsonObject1.getString("assists");
                                constants.p_logo = jsonObject1.getString("player_logo");
                                constants.p_position = jsonObject1.getString("position");
                                constants.p_shirt = jsonObject1.getString("shirt_number");
                                constants.p_fouls_commited = jsonObject1.getString("fouls_commited");
                                constants.p_fouls_drawn = jsonObject1.getString("fouls_drawn");
                                constants.p_goals = jsonObject1.getString("goals");
                                constants.p_fouls_drawn = jsonObject1.getString("offsides");
                                constants.p_m_penalties = jsonObject1.getString("missed_penalties");
                                constants.p_s_penalties = jsonObject1.getString("scored_penalties");
                                constants.p_posx = jsonObject1.getString("posx");
                                constants.p_posy = jsonObject1.getString("posy");
                                constants.p_redcards = jsonObject1.getString("redcards");
                                constants.p_saves = jsonObject1.getString("saves");
                                constants.p_s_goals = jsonObject1.getString("shots_on_goal");
                                constants.p_s_totals = jsonObject1.getString("shots_total");
                                constants.p_yellowcards = jsonObject1.getString("yellowcards");
                                constants.p_type = jsonObject1.getString("type");
                            }
                            if (jsonObject1.getString("team_id").equals(live_matches_details.away_id)) {
                                //  Toast.makeText(getContext(), live_matches_details.home_id+"away"+live_matches_details.away_id, Toast.LENGTH_SHORT).show();
                                constants.a_id = jsonObject1.getString("player_id");
                                constants.a_name = jsonObject1.getString("player_name");
                                constants.a_assist_id = jsonObject1.getString("assists");
                                constants.a_logo = jsonObject1.getString("player_logo");
                                constants.a_type = jsonObject1.getString("type");
                                constants.a_position = jsonObject1.getString("position");
                                constants.a_shirt = jsonObject1.getString("shirt_number");
                                constants.a_fouls_commited = jsonObject1.getString("fouls_commited");
                                constants.a_fouls_drawn = jsonObject1.getString("fouls_drawn");
                                constants.a_goals = jsonObject1.getString("goals");
                                constants.a_fouls_drawn = jsonObject1.getString("offsides");
                                constants.a_m_penalties = jsonObject1.getString("missed_penalties");
                                constants.a_s_penalties = jsonObject1.getString("scored_penalties");
                                constants.a_posx = jsonObject1.getString("posx");
                                constants.a_posy = jsonObject1.getString("posy");
                                constants.a_redcards = jsonObject1.getString("redcards");
                                constants.a_saves = jsonObject1.getString("saves");
                                constants.a_s_goals = jsonObject1.getString("shots_on_goal");
                                constants.a_s_totals = jsonObject1.getString("shots_total");
                                constants.a_yellowcards = jsonObject1.getString("yellowcards");
                            }
                            live_data_list.add(constants);
                            live_data_list_sub.add(constants);
                        }

                    }
                    listAdapter.notifyDataSetChanged();
                    listAdapter1.notifyDataSetChanged();
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
