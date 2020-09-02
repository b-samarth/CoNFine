package com.defiance.confine;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private int b = FormActivity.b, g = FormActivity.g, p = FormActivity.p, s = FormActivity.s, l = FormActivity.l, m = FormActivity.m, h = FormActivity.h, a = FormActivity.a;
    private String risk;
    private ResultData get;
    private ImageView risk_img;
    private TextView risk_text;
    private TextView risk_desc;
    private FirebaseFirestore db;
    private Map<String, String> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        risk_img = findViewById(R.id.risk_img);
        risk_desc = findViewById(R.id.risk_description);
        risk_text = findViewById(R.id.risk_text);
        get = FormActivity.data.checkRisk();

        if (user != null) {
            Log.wtf("string", FormActivity.data.getData().toString());
            map.put("combination", FormActivity.data.getData().toString());
            db.collection(user.getEmail())
                    .document("new")
                    .set(map, SetOptions.merge());
        }
        Log.wtf("string", FormActivity.data.getData().toString());
        if (get != null) {
            risk_img.setImageResource(get.getImage());
            risk_text.setText(get.getRisk_level());
            risk_desc.setText(get.getRisk_description());
        }
    }

}