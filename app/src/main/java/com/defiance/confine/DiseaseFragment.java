package com.defiance.confine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class DiseaseFragment extends Fragment {

    private Map<String, String> map = new HashMap<String, String>()
    {
        {
            put("diabetes", "no");
            put("hypertension", "no");
            put("lungDisease", "no");
            put("heartDisease", "no");
            put("kidneyDisease", "no");
            put("liverDisease", "no");
            put("bloodDisease", "no");
            put("immunoDeficiency", "no");
        }
    };
    private Button button;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private CheckBox diabetes, hyper, lungs, heart, kidney, liver, blood, immune;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disease, container, false);

        button = view.findViewById(R.id.next_disease);
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        diabetes = view.findViewById(R.id.diabetes);
        hyper = view.findViewById(R.id.hypertension);
        lungs = view.findViewById(R.id.lungs);
        heart = view.findViewById(R.id.heart);
        kidney = view.findViewById(R.id.kidney);
        liver = view.findViewById(R.id.liver);
        blood = view.findViewById(R.id.blood);
        immune = view.findViewById(R.id.immune);

        diabetes.setOnCheckedChangeListener(myCheckBoxListener);
        hyper.setOnCheckedChangeListener(myCheckBoxListener);
        lungs.setOnCheckedChangeListener(myCheckBoxListener);
        heart.setOnCheckedChangeListener(myCheckBoxListener);
        kidney.setOnCheckedChangeListener(myCheckBoxListener);
        liver.setOnCheckedChangeListener(myCheckBoxListener);
        blood.setOnCheckedChangeListener(myCheckBoxListener);
        immune.setOnCheckedChangeListener(myCheckBoxListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.wtf("Map", String.valueOf(map));
                db.collection("Users")
                        .document(user.getUid())
                        .set(map, SetOptions.merge());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame, new TravelFragment());
                transaction.commit();
            }
        });


        return view;
    }

    private CompoundButton.OnCheckedChangeListener myCheckBoxListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.diabetes:
                    if (b) {
                        map.put("diabetes", "yes");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        map.put("diabetes", "no");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.hypertension:
                    if (b) {
                        map.put("hypertension", "yes");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        map.put("hypertension", "no");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.lungs:
                    if (b) {
                        map.put("lungDisease", "yes");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        map.put("lungDisease", "no");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.heart:
                    if (b) {
                        map.put("heartDisease", "yes");
                        FormActivity.h += 1;
                        FormActivity. data.addtoString('H');
                    }
                    else {
                        map.put("heartDisease", "no");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.kidney:
                    if (b) {
                        map.put("kidneyDisease", "yes");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        map.put("kidneyDisease", "no");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.liver:
                    if (b) {
                        map.put("liverDisease", "yes");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        map.put("liverDisease", "no");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.blood:
                    if (b) {
                        map.put("bloodDisease", "yes");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        map.put("bloodDisease", "no");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.immune:
                    if (b) {
                        map.put("immunoDeficiency", "yes");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else{
                        map.put("immunoDeficiency", "no");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                        }
                    break;
            }
        }
    };
}
