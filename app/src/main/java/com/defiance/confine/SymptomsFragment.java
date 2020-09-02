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

public class SymptomsFragment extends Fragment {
    private Map<String, String> map = new HashMap<String, String>()
    {
        {
            put("cough", "no");
            put("muffled", "no");
            put("shortness", "no");
            put("fever", "no");
            put("nausea", "no");
            put("diarrhoea", "no");
            put("sense", "no");
        }
    };
    private Button button;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private CheckBox cough, muffled, breath, fever, nausea, diarrhoea, sense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_symptoms, container, false);

        button = view.findViewById(R.id.next_symptoms);
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        cough = view.findViewById(R.id.cough);
        muffled = view.findViewById(R.id.muffled);
        breath = view.findViewById(R.id.breath);
        fever = view.findViewById(R.id.fever);
        nausea = view.findViewById(R.id.nausea);
        diarrhoea = view.findViewById(R.id.diarrhoea);
        sense = view.findViewById(R.id.sense);


        cough.setOnCheckedChangeListener(myCheckBoxListener);
        muffled.setOnCheckedChangeListener(myCheckBoxListener);
        breath.setOnCheckedChangeListener(myCheckBoxListener);
        fever.setOnCheckedChangeListener(myCheckBoxListener);
        nausea.setOnCheckedChangeListener(myCheckBoxListener);
        diarrhoea.setOnCheckedChangeListener(myCheckBoxListener);
        sense.setOnCheckedChangeListener(myCheckBoxListener);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.wtf("Map", String.valueOf(map));
                db.collection("Users")
                        .document(user.getUid())
                    .set(map, SetOptions.merge());
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame, new DiseaseFragment());
                transaction.commit();
        }
        });
        return view;
    }

    private CompoundButton.OnCheckedChangeListener myCheckBoxListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.cough:
                    if (b) {
                        map.put("cough", "yes");
                        FormActivity.l += 1;
                        FormActivity.data.addtoString('L');
                    }
                    else {
                        map.put("cough", "no");
                        FormActivity.l -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.muffled:
                    if (b) {
                        map.put("muffled", "yes");
                        FormActivity.l += 1;
                        FormActivity.data.addtoString('L');
                    }
                    else {
                        map.put("muffled", "no");
                        FormActivity.l -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.breath:
                    if (b) {
                        map.put("shortness", "yes");
                        FormActivity.m += 1;
                        FormActivity. data.addtoString('M');
                    }
                    else {
                        map.put("shortness", "no");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.fever:
                    if (b) {
                        map.put("fever", "yes");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        map.put("fever", "no");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.nausea:
                    if (b) {
                        map.put("nausea", "yes");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        map.put("nausea", "no");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.diarrhoea:
                    if (b) {
                        map.put("diarrhoea", "yes");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        map.put("diarrhoea", "no");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.sense:
                    if (b) {
                        map.put("sense", "yes");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        map.put("sense", "no");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
            }
        }
    };
}