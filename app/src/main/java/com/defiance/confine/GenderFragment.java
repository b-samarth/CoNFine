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

public class GenderFragment extends Fragment {

    private ImageButton malebtn, femalebtn;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private Map<String,String> map = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender, container, false);

        malebtn = view.findViewById(R.id.male_img_btn);
        femalebtn = view.findViewById(R.id.female_img_btn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        malebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.b = 1;
                FormActivity.data.addtoString('B');
                map.put("gender", "male");
                map.put("pregnant", "NA");
                db.collection("Users")
                        .document(user.getUid())
                        .set(map, SetOptions.merge());
                changeFragment();
            }
        });
        femalebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.g = 1;
                FormActivity.data.addtoString('G');
                map.put("gender", "female");
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
        transaction.replace(R.id.main_frame, new AgeFragment());
        transaction.commit();
    }
}