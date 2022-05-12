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
import com.example.rnmediadev007.livesoccerapp.adapters.ListAdapter_commentary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Commentary extends Fragment {
    // json array response url
    private static String TAG = Commentary.class.getSimpleName();
    private ListView mListView;
    // Progress dialog
    private ProgressDialog pDialog;
    public ListAdapter_commentary listAdapter;
    private ListView listView;
    public ArrayList<Constants> live_data_list = new ArrayList<Constants>();
    RelativeLayout content_main;
    Constants constants;
    Live_matches_Details live_matches_details;
    View rootView;
    private TextView datanofound;


    public Commentary() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.comment, container, false);
        // listView = (ListView) rootView.findViewById(R.id.listView);
        content_main = (RelativeLayout) rootView.findViewById(R.id.content_main);
        this.mListView = (ListView) rootView.findViewById(R.id.list_tracks);
        this.datanofound = (TextView) rootView.findViewById(R.id.NomatchFound);
        listAdapter = new ListAdapter_commentary(this);
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
        String urlJsonArry = "http://www.phoneappsking.com/soccer/comment.php?match="+live_matches_details.match_id;

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
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        if(!jsonArray.equals(null)&&jsonArray.length()>0) {
                            datanofound.setVisibility(View.GONE);
                            constants = new Constants();
                            constants.c_id = jsonObject1.getString("id");
                            constants.c_goal = jsonObject1.getString("is_goal");
                            constants.c_body = jsonObject1.getString("body");
                            constants.c_imp = jsonObject1.getString("important");
                            constants.c_minute = jsonObject1.getString("minute");
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
