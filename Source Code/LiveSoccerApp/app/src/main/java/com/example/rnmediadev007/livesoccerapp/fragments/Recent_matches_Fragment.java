package com.example.rnmediadev007.livesoccerapp.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import com.example.rnmediadev007.livesoccerapp.adapters.ListAdapter_Recent_matches;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Recent_matches_Fragment extends Fragment {
    public static  String count_str="0";
    String urlJsonArry = "http://www.phoneappsking.com/soccer/live.php";
    TextView NomatchFound;
    Constants constants;
    ListView listView;
    ListAdapter_Recent_matches listAdapter_recent_matches;
    public ArrayList<Constants> recent_data_list = new ArrayList<Constants>();
    View rootView;


    public Recent_matches_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.live, container, false);
        NomatchFound = (TextView) rootView.findViewById(R.id.NomatchFound);
        listView = (ListView) rootView.findViewById(R.id.data);
        listAdapter_recent_matches = new ListAdapter_Recent_matches(this);
        listView.setAdapter(listAdapter_recent_matches);
        listView.setDivider(null);
        makeJsonArrayRequest();

        return rootView;
    }
    private void makeJsonArrayRequest() {
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
                    JSONArray stories = jsonObject.getJSONArray("data");
                    for (int i = 0; i < stories.length(); i++) {
                        constants = new Constants();
                        JSONObject jsonObject1 = stories.getJSONObject(i);

                        String data_ht_score = jsonObject1.getString("ht_score");
                        String data_ft_score = jsonObject1.getString("ft_score");
                        String data_home_team_id = jsonObject1.getString("home_team_id");
                        String data_away_team_id = jsonObject1.getString("away_team_id");
                        String data_away_team_logo = jsonObject1.getString("away_team_logo");

                        String data_home_score_penalities = jsonObject1.getString("home_score_penalties");
                        String data_away_score_penalties = jsonObject1.getString("away_score_penalties");
                        String data_spectators = jsonObject1.getString("spectators");

                        String data_starting_time = jsonObject1.getString("starting_time");
                        String data_status = jsonObject1.getString("status");
                        String data_minute = jsonObject1.getString("minute");
                        String data_extra_minute = jsonObject1.getString("extra_minute");
                        String data_compitition_id = jsonObject1.getString("competition_id");
                        String data_competition_name = jsonObject1.getString("competition_name");
                        String data_venue_id = jsonObject1.getString("venue_id");
                        String data_venue_name = jsonObject1.getString("venue_name");
                        String data_venue_city = jsonObject1.getString("venue_city");
                        String data_season_id = jsonObject1.getString("season_id");
                        String data_season_name = jsonObject1.getString("season_name");
                        String data_round_id = jsonObject1.getString("stage_id");
                        String data_aggregate = jsonObject1.getString("aggregate");
                        String data_placeholder = jsonObject1.getString("placeholder");
                        String data_country_id = jsonObject1.getString("country_id");
                        if ((jsonObject1.getString("status")).equals("LIVE")) {

                        }else{
                            constants.data_id = jsonObject1.getString("id");
                            constants.data_venue_name = jsonObject1.getString("venue_name");
                            constants.data_venue_city = jsonObject1.getString("venue_city");
                            constants.data_home_team_id = jsonObject1.getString("home_team_id");
                            constants.data_away_team_id = jsonObject1.getString("away_team_id");
                            constants.data_minute = jsonObject1.getString("minute");
                            constants.data_home_team_name = jsonObject1.getString("home_team_name");
                            constants.data_country_name = jsonObject1.getString("country_name");
                            constants.data_country_flag = jsonObject1.getString("country_flag");
                            constants.data_away_team_name = jsonObject1.getString("away_team_name");
                            constants.data_home_score = jsonObject1.getString("home_score");
                            constants.data_away_score = jsonObject1.getString("away_score");
                            constants.data_starting_date = jsonObject1.getString("starting_date");
                            constants.data_home_team_logo = jsonObject1.getString("home_team_logo");
                            constants.data_away_team_logo = jsonObject1.getString("away_team_logo");

                            NomatchFound.setVisibility(View.GONE);
                            recent_data_list.add(constants);

                        }
                    }
                    int count = listView.getAdapter().getCount();
                    count_str=String.valueOf(count);
                    listAdapter_recent_matches.notifyDataSetChanged();
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


                //    Toast.makeText(getContext(), message + "income", Toast.LENGTH_LONG).show();

                pDialog.hide();
            }
        }) {

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq1, tag_string_req1);
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
