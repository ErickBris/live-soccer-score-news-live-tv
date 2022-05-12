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
import com.example.rnmediadev007.livesoccerapp.activity.MainActivity;
import com.example.rnmediadev007.livesoccerapp.adapters.live_streaming_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class live_streaming extends Fragment {

    private String urlJsonArry = "http://dummydomain.com/soccer.json";
    private static String TAG = MainActivity.class.getSimpleName();
    private ListView mListView;
    // Progress dialog
    private ProgressDialog pDialog;
    public live_streaming_adapter listAdapter;
    private ListView listView;
    public ArrayList<Constants> live_data_list = new ArrayList<Constants>();
    Constants constants;
    View rootView;

    String status;
    private TextView NomatchFound;

    public live_streaming() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.live, container, false);

        this.mListView = (ListView)rootView.findViewById(R.id.data);
        NomatchFound = (TextView) rootView.findViewById(R.id.NomatchFound);
        listAdapter = new live_streaming_adapter(this);
        // listView.setAdapter(listAdapter);
        mListView.setAdapter(listAdapter);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        makeJsonArrayRequest();
        // listView = (ListView) rootView.findViewById(R.id.listView);

        return rootView;
    }

    private void makeJsonArrayRequest() {


        String tag_string_req1 = "string_req";
        // Defined Array values to show in ListView
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq1 = new StringRequest(Request.Method.GET,
                urlJsonArry, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray jsonaray = jsonobject.getJSONArray("streaming");
                    for(int i = 0 ; i<jsonaray.length();i++) {
                        JSONObject jsonObject1 = jsonaray.getJSONObject(i);

                       status = jsonObject1.getString("status");
                    }
                    JSONArray channels = jsonobject.getJSONArray("channels");
                    for(int j = 0 ; j<channels.length();j++) {
                        JSONObject jsonObject2 = channels.getJSONObject(j);
                        constants = new Constants();
                        if(status.equals("yes")) {
                            NomatchFound.setVisibility(View.GONE);
                            constants.name = jsonObject2.getString("Name");
                            constants.url = jsonObject2.getString("url");
                            live_data_list.add(constants);
                        }

                    }

                    listAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
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




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}