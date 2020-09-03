package com.defiance.confine;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class DosAndDontsActivity extends AppCompatActivity {

    private ViewPager dos_viewpager;
    private DosAndDontsAdapter dosAndDontsAdapter;
    private List<DosAndDontsModel> dosAndDontsModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos_and_donts);

        dosAndDontsModelList = new ArrayList<>();
        dosAndDontsModelList.add(new DosAndDontsModel(R.string.dos, R.string.dos_1_head, R.string.dos_3_head, R.string.dos_3_head, R.string.dos_4_head, R.string.dos_5_head, R.string.dos_6_head, R.string.dos_1_head, R.string.dos_1_body, R.string.dos_2_body, R.string.dos_3_body, R.string.dos_4_body, R.string.dos_5_body, R.string.dos_6_body, R.string.dos_1_body));
        dosAndDontsModelList.add(new DosAndDontsModel(R.string.donts, R.string.donts_1_head, R.string.donts_3_head, R.string.donts_3_head, R.string.donts_4_head, R.string.donts_5_head, R.string.donts_6_head, R.string.donts_7_head, R.string.donts_1_body, R.string.donts_2_body, R.string.donts_3_body, R.string.donts_4_body, R.string.donts_5_body, R.string.donts_6_body, R.string.donts_7_body));

        dosAndDontsAdapter = new DosAndDontsAdapter(dosAndDontsModelList, this);
        dos_viewpager = findViewById(R.id.dos_donts_viewpager);
        dos_viewpager.setAdapter(dosAndDontsAdapter);

    }
}