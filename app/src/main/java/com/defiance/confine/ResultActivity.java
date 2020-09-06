package com.defiance.confine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class ResultActivity extends AppCompatActivity {

    private int b = FormActivity.b, g = FormActivity.g, p = FormActivity.p, s = FormActivity.s, l = FormActivity.l, m = FormActivity.m, h = FormActivity.h, a = FormActivity.a;
    private String risk;
    private ResultData get;
    private ImageView risk_img;
    private TextView risk_text;
    private TextView risk_desc;
    private FirebaseFirestore db;
    private Button diet, excersise;
    private TextView random_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        risk_img = findViewById(R.id.risk_img);
        diet = findViewById(R.id.diet_plan);
        excersise = findViewById(R.id.excersise_plan);
        risk_desc = findViewById(R.id.risk_description);
        risk_text = findViewById(R.id.risk_text);
        random_text = findViewById(R.id.random_text);
        get = FormActivity.data.checkRisk();


        if (user != null) {
            NavigationDrawerActivity.map.put("combination", FormActivity.data.getData());
                db.collection("Users")
                    .document(user.getEmail())
                    .set(NavigationDrawerActivity.map, SetOptions.merge());

        }
        if (get != null) {
            risk_img.setImageResource(get.getImage());
            risk_text.setText(get.getRisk_level());
            risk_desc.setText(get.getRisk_description());
        }
        else {
            risk_text.setVisibility(View.GONE);
            risk_desc.setVisibility(View.GONE);
            risk_img.setImageResource(R.drawable.unique);
            random_text.setText(R.string.unique_result);
        }

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, DietPlanActivity.class));
            }
        });
    }

}