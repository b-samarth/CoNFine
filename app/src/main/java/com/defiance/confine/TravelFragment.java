package com.defiance.confine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class TravelFragment extends Fragment {

    private ImageButton yes_travel_btn, no_travel_btn;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private Map<String,String> map = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel, container, false);

        no_travel_btn = view.findViewById(R.id.no_travel_btn);
        yes_travel_btn = view.findViewById(R.id.yes_travel_btn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        no_travel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.put("travel", "no");
                db.collection("Users")
                        .document(user.getUid())
                        .set(map, SetOptions.merge());
                changeFragment();
            }
        });
        yes_travel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.h += 1;
                map.put("travel", "yes");
                FormActivity.data.addtoString('H');
                db.collection("Users")
                        .document(user.getUid())
                        .set(map, SetOptions.merge());
                changeFragment();
            }
        });
        return view;
    }

    private void changeFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new ContactPeopleFragment());
        transaction.commit();
    }
}