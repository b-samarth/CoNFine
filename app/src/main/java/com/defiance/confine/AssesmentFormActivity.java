package com.defiance.confine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kenilt.loopingviewpager.scroller.AutoScroller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssesmentFormActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Adapter adapter;
    private List<Model> models;
    private AutoScroller autoScroller;
    Button testbtn;
    private TextView covid_head, covid_data;
    ProgressBar pb;

    JSONObject jsonObject = null;
    String  url = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST";
    Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment_form);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);

        models = new ArrayList<>();
        models.add(new Model(R.mipmap.card_1));
        models.add(new Model(R.mipmap.card_2));
        models.add(new Model(R.mipmap.card_3));
        models.add(new Model(R.mipmap.card_4));
        models.add(new Model(R.mipmap.card_5));
        models.add(new Model(R.mipmap.card_6));

        covid_data = findViewById(R.id.covid_data);
        covid_head = findViewById(R.id.covid_head);
        pb = findViewById(R.id.pb);

        adapter = new Adapter(models, this);
        testbtn = findViewById(R.id.testbtn);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);
        autoScroller = new AutoScroller(viewPager, getLifecycle(),3000);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    map.put("activeCases", response.get("activeCases").toString());
                    map.put("activeCasesNew", response.get("activeCasesNew").toString());
                    map.put("recovered", response.get("recovered").toString());
                    map.put("recoveredNew", response.get("recoveredNew").toString());
                    map.put("deaths", response.get("deaths").toString());
                    map.put("deathsNew", response.get("deathsNew").toString());
                    map.put("totalCases", response.get("totalCases").toString());
                    pb.setVisibility(View.GONE);
                    covid_head.setText("Total Active Cases");
                    covid_data.setText(response.get("activeCases").toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Json object", error.toString());

                    }
                });

        requestQueue.add(jsonObjectRequest);
//        Intent intent = new Intent(AssesmentFormActivity.this, CovidCases.class);
    }
}