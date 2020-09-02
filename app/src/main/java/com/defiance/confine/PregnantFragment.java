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


public class PregnantFragment extends Fragment {

    private ImageButton not_pregnant_btn, pregnant_btn;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private Map<String,String> map = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pregnant, container, false);

        not_pregnant_btn = view.findViewById(R.id.no_pregnant_btn);
        pregnant_btn = view.findViewById(R.id.pregnant_btn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        not_pregnant_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.p = 0;
                map.put("pregnant", "no");
                db.collection("Users")
                        .document(user.getUid())
                        .set(map, SetOptions.merge());
                changeFragment();
            }
        });
        pregnant_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.p = 1;
                FormActivity.data.addtoString('P');
                map.put("pregnant", "yes");
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
        transaction.replace(R.id.main_frame, new SmokeFragment());
        transaction.commit();
    }
}