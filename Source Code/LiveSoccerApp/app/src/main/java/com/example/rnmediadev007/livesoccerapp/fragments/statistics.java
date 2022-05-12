package com.example.rnmediadev007.livesoccerapp.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.example.rnmediadev007.livesoccerapp.R;
import com.example.rnmediadev007.livesoccerapp.activity.Live_matches_Details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class statistics extends Fragment {
    private static String TAG = statistics.class.getSimpleName();
    Live_matches_Details live_matches_details;
    View rootView;

    String home_shots_on_goal,
            home_shots_total,
            home_fouls_total,
            home_corners_total,
            home_offsides_total,
            home_possesion,
            home_yellowcards,
            home_redcards,
            home_saves,
            away_team_id,
            away_shots_on_goal,
            away_shots_total,
            away_fouls_total,
            away_corners_total,
            away_offsides_total,
            away_possesion,
            away_yellowcards,
            away_redcards,
            away_saves;

    TextView
            home_shots_on_goal_t,
            home_shots_total_t,
            home_fouls_total_t,
            home_corners_total_t,
            home_offsides_total_t,
            home_possesion_t,
            home_yellowcards_t,
            home_redcards_t,
            home_saves_t,
            away_team_id_t,
            away_shots_on_goal_t,
            away_shots_total_t,
            away_fouls_total_t,
            away_corners_total_t,
            away_offsides_total_t,
            away_possesion_t,
            away_yellowcards_t,
            away_redcards_t,
            away_saves_t;
    private LinearLayout hidden_layout;
    private TextView nodatafound;


    public statistics() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.statstics, container, false);


        home_possesion_t = (TextView) rootView.findViewById(R.id.possision_home);
        away_possesion_t = (TextView) rootView.findViewById(R.id.possision_away);


        home_shots_on_goal_t = (TextView) rootView.findViewById(R.id.shots_on_goals_home);
        home_shots_total_t = (TextView) rootView.findViewById(R.id.shots_total_home);
        home_fouls_total_t = (TextView) rootView.findViewById(R.id.fouls_home);
        home_corners_total_t = (TextView) rootView.findViewById(R.id.corners_home);
        home_offsides_total_t = (TextView) rootView.findViewById(R.id.offsides_home);
        home_yellowcards_t = (TextView) rootView.findViewById(R.id.yellow_cards_home);
        home_redcards_t = (TextView) rootView.findViewById(R.id.red_cards_home);
        home_saves_t = (TextView) rootView.findViewById(R.id.saves_home);
        away_shots_total_t = (TextView) rootView.findViewById(R.id.shots_total_away);
        away_shots_on_goal_t = (TextView) rootView.findViewById(R.id.shots_on_goals_away);
        away_fouls_total_t = (TextView) rootView.findViewById(R.id.fousls_away);
        away_corners_total_t = (TextView) rootView.findViewById(R.id.corners_away);
        away_offsides_total_t = (TextView) rootView.findViewById(R.id.offsides_away);
        away_yellowcards_t = (TextView) rootView.findViewById(R.id.yellow_cards_away);
        away_redcards_t = (TextView) rootView.findViewById(R.id.red_cards_away);
        away_saves_t = (TextView) rootView.findViewById(R.id.saves_away);
        nodatafound = (TextView) rootView.findViewById(R.id.nodatafound);
        hidden_layout = (LinearLayout) rootView.findViewById(R.id.hidden_layout);


        makeJsonArrayRequest();

        //Any color you want to set


        return rootView;
    }


    private void makeJsonArrayRequest() {
        String urlJsonArry = "http://www.phoneappsking.com/soccer/statistics.php?match=" + live_matches_details.match_id;
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
                        if (!jsonObject.getJSONArray("data").equals(null)) {
                            hidden_layout.setVisibility(View.VISIBLE);
                            nodatafound.setVisibility(View.GONE);
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            home_shots_on_goal = jsonObject1.getString("home_shots_on_goal");
                            home_shots_total = jsonObject1.getString("home_shots_total");
                            home_fouls_total = jsonObject1.getString("home_fouls_total");
                            home_corners_total = jsonObject1.getString("home_corners_total");
                            home_offsides_total = jsonObject1.getString("home_offsides_total");
                            home_possesion = jsonObject1.getString("home_possesion");
                            home_yellowcards = jsonObject1.getString("home_yellowcards");
                            home_redcards = jsonObject1.getString("home_redcards");
                            home_saves = jsonObject1.getString("home_saves");
                            away_team_id = jsonObject1.getString("away_team_id");
                            away_shots_on_goal = jsonObject1.getString("away_shots_on_goal");
                            away_shots_total = jsonObject1.getString("away_shots_total");
                            away_fouls_total = jsonObject1.getString("away_fouls_total");
                            away_corners_total = jsonObject1.getString("away_corners_total");
                            away_offsides_total = jsonObject1.getString("away_offsides_total");
                            away_possesion = jsonObject1.getString("away_possesion");
                            away_yellowcards = jsonObject1.getString("away_yellowcards");
                            away_redcards = jsonObject1.getString("away_redcards");
                            away_saves = jsonObject1.getString("away_saves");

                            View home = rootView.findViewById(R.id.home_pos_progress);
                            ViewGroup.LayoutParams layoutParams = home.getLayoutParams();
                            layoutParams.width = (int) (dpToPixelleft(150) * Integer.valueOf(home_possesion) / 100.0f);//If you want to set 25%
                            home.setLayoutParams(layoutParams);

                            View home1 = rootView.findViewById(R.id.progress_shots_on_goals_home);
                            ViewGroup.LayoutParams layoutParams_h1 = home1.getLayoutParams();
                            layoutParams_h1.width = (int) (dpToPixelleft(150) * Integer.valueOf(home_shots_on_goal) / 100.0f);//If you want to set 25%
                            home1.setLayoutParams(layoutParams_h1);

                            View home2 = rootView.findViewById(R.id.progress_shots_total_home);
                            ViewGroup.LayoutParams layoutParams_h2 = home2.getLayoutParams();
                            layoutParams_h2.width = (int) (dpToPixelleft(150) * Integer.valueOf(home_shots_total) / 100.0f);//If you want to set 25%
                            home2.setLayoutParams(layoutParams_h2);

                            View home3 = rootView.findViewById(R.id.fouls_progress);
                            ViewGroup.LayoutParams layoutParams_h3 = home3.getLayoutParams();
                            layoutParams_h3.width = (int) (dpToPixelleft(150) * Integer.valueOf(home_fouls_total) / 100.0f);//If you want to set 25%
                            home3.setLayoutParams(layoutParams_h3);

                            View home4 = rootView.findViewById(R.id.progress_corners_home);
                            ViewGroup.LayoutParams layoutParams_h4 = home4.getLayoutParams();
                            layoutParams_h4.width = (int) (dpToPixelleft(150) * Integer.valueOf(home_corners_total) / 100.0f);//If you want to set 25%
                            home4.setLayoutParams(layoutParams_h4);

                            View home5 = rootView.findViewById(R.id.progress_offsides_home);
                            ViewGroup.LayoutParams layoutParams_h5 = home5.getLayoutParams();
                            layoutParams_h5.width = (int) (dpToPixelleft(150) * Integer.valueOf(home_offsides_total) / 100.0f);//If you want to set 25%
                            home5.setLayoutParams(layoutParams_h5);

                            View home6 = rootView.findViewById(R.id.yellow_cards_progress_home);
                            ViewGroup.LayoutParams layoutParams_h6 = home6.getLayoutParams();
                            layoutParams_h6.width = (int) (dpToPixelleft(150) * Integer.valueOf(home_yellowcards) / 100.0f);//If you want to set 25%
                            home6.setLayoutParams(layoutParams_h6);

                            View home7 = rootView.findViewById(R.id.progress_red_cards_home);
                            ViewGroup.LayoutParams layoutParams_h7 = home7.getLayoutParams();
                            layoutParams_h7.width = (int) (dpToPixelleft(150) * Integer.valueOf(home_redcards) / 100.0f);//If you want to set 25%
                            home7.setLayoutParams(layoutParams_h7);

                            View home8 = rootView.findViewById(R.id.progress_saves_home);
                            ViewGroup.LayoutParams layoutParams_h8 = home8.getLayoutParams();
                            layoutParams_h8.width = (int) (dpToPixelleft(150) * Integer.valueOf(home_saves) / 100.0f);//If you want to set 25%
                            home8.setLayoutParams(layoutParams_h8);


                            View away = rootView.findViewById(R.id.away_pos_progress);
                            ViewGroup.LayoutParams layoutParams1 = away.getLayoutParams();
                            layoutParams1.width = (int) (dpToPixelright(150) * Integer.valueOf(away_possesion) / 100.0f);//If you want to set 25%
                            away.setLayoutParams(layoutParams1);

                            View away1 = rootView.findViewById(R.id.progress_shots_on_goals_away);
                            ViewGroup.LayoutParams layoutParams_a1 = away1.getLayoutParams();
                            layoutParams_a1.width = (int) (dpToPixelright(150) * Integer.valueOf(away_shots_on_goal) / 100.0f);//If you want to set 25%
                            away1.setLayoutParams(layoutParams_a1);

                            View away2 = rootView.findViewById(R.id.progress_shots_total_away);
                            ViewGroup.LayoutParams layoutParams_a2 = away2.getLayoutParams();
                            layoutParams_a2.width = (int) (dpToPixelright(150) * Integer.valueOf(away_shots_total) / 100.0f);//If you want to set 25%
                            away2.setLayoutParams(layoutParams_a2);

                            View away3 = rootView.findViewById(R.id.away_fouls_progress);
                            ViewGroup.LayoutParams layoutParams_a3 = away3.getLayoutParams();
                            layoutParams_a3.width = (int) (dpToPixelright(150) * Integer.valueOf(away_fouls_total) / 100.0f);//If you want to set 25%
                            away3.setLayoutParams(layoutParams_a3);
                            View away4 = rootView.findViewById(R.id.progress_corners_away);
                            ViewGroup.LayoutParams layoutParams_a4 = away4.getLayoutParams();
                            layoutParams_a4.width = (int) (dpToPixelright(150) * Integer.valueOf(away_corners_total) / 100.0f);//If you want to set 25%
                            away4.setLayoutParams(layoutParams_a4);

                            View away5 = rootView.findViewById(R.id.progress_offsides_away);
                            ViewGroup.LayoutParams layoutParams_a5 = away5.getLayoutParams();
                            layoutParams_a5.width = (int) (dpToPixelright(150) * Integer.valueOf(away_offsides_total) / 100.0f);//If you want to set 25%
                            away5.setLayoutParams(layoutParams_a5);

                            View away6 = rootView.findViewById(R.id.away_yellow_cards_progress);
                            ViewGroup.LayoutParams layoutParams_a6 = away6.getLayoutParams();
                            layoutParams_a6.width = (int) (dpToPixelright(150) * Integer.valueOf(away_yellowcards) / 100.0f);//If you want to set 25%
                            away6.setLayoutParams(layoutParams_a6);

                            View away7 = rootView.findViewById(R.id.progress_red_cards_away);
                            ViewGroup.LayoutParams layoutParams_a7 = away7.getLayoutParams();
                            layoutParams_a7.width = (int) (dpToPixelright(150) * Integer.valueOf(away_redcards) / 100.0f);//If you want to set 25%
                            away7.setLayoutParams(layoutParams_a7);

                            View away8 = rootView.findViewById(R.id.progress_saves_away);
                            ViewGroup.LayoutParams layoutParams_a8 = away8.getLayoutParams();
                            layoutParams_a8.width = (int) (dpToPixelright(150) * Integer.valueOf(away_saves) / 100.0f);//If you want to set 25%
                            away8.setLayoutParams(layoutParams_a8);


                            home_possesion_t.setText(home_possesion);
                            home_shots_on_goal_t.setText(home_shots_on_goal);
                            home_shots_total_t.setText(home_shots_total);
                            home_fouls_total_t.setText(home_fouls_total);
                            home_corners_total_t.setText(home_corners_total);
                            home_offsides_total_t.setText(home_offsides_total);
                            home_yellowcards_t.setText(home_yellowcards);
                            home_redcards_t.setText(home_redcards);
                            home_saves_t.setText(home_saves);
                            away_possesion_t.setText(away_possesion);
                            away_shots_on_goal_t.setText(away_shots_on_goal);
                            away_shots_total_t.setText(away_shots_total);
                            away_fouls_total_t.setText(away_fouls_total);
                            away_corners_total_t.setText(away_corners_total);
                            away_offsides_total_t.setText(away_offsides_total);
                            away_redcards_t.setText(away_redcards);
                            away_yellowcards_t.setText(away_yellowcards);
                            away_saves_t.setText(away_saves);
                        }
                    }


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


    public int dpToPixelleft(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public int dpToPixelright(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
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
