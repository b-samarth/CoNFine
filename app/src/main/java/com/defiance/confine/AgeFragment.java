package com.defiance.confine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class AgeFragment extends Fragment {

    private RadioGroup age_group;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private Button button;
    private Map<String,String> map = new HashMap<>();
    private int counter = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age, container, false);

        age_group = view.findViewById(R.id.age_group);
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        button = view.findViewById(R.id.next_age);

        age_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.more10:
                        FormActivity.a = 1;
                        counter = 1;
                        FormActivity.data.addtoString('L');
                        map.put("ageGroup", "10-39");
                        break;
                    case R.id.more40:
                        FormActivity.a = 2;
                        counter = 1;
                        FormActivity.data.addtoString('M');
                        map.put("ageGroup", "40-79");
                        break;
                    case R.id.more80:
                        FormActivity.a = 3;
                        counter = 1;
                        FormActivity.data.addtoString('H');
                        map.put("ageGroup", ">80");
                        break;
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter != 0) {
                    db.collection("Users")
                            .document(user.getUid())
                            .set(map, SetOptions.merge());
                    if (FormActivity.b == 1) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.main_frame, new SmokeFragment());
                        transaction.commit();
                    } else if (FormActivity.g == 1) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.main_frame, new PregnantFragment());
                        transaction.commit();
                    }
                }
                else
                    Toast.makeText(getActivity(), "Select at least one option", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}