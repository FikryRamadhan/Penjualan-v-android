package com.example.testapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder
{
    ImageView itemImage;
    TextView programeTitle;
    EditText ProgramText;
    TextView programDescription;

    EditText Editjm;
    ImageButton btnKernjang;

    ImageButton btnPlus;

    ImageButton btnMin;

    ImageButton btnProfile;

    Intent intent;

    EditText textCount;

    ProgramViewHolder(View v)
    {
        itemImage = v.findViewById(R.id.imageView);
        programeTitle = v.findViewById(R.id.textView1);
        programDescription = v.findViewById(R.id.textView2);
        btnKernjang = v.findViewById(R.id.btnkeranjang);
        btnPlus = v.findViewById(R.id.btnplus);
        btnMin = v.findViewById(R.id.btnmin);
        btnProfile = v.findViewById(R.id.btnProfile);
        textCount = v.findViewById(R.id.count);
        ProgramText = v.findViewById(R.id.count);
//        Editjm = v.findViewById(R.id.count);
//        Editjm.setCompoundDrawablesWithIntrinsicBounds(com.google.android.material.R.drawable.mtrl_ic_arrow_drop_down, 0, com.google.android.material.R.drawable.ic_search_black_24, 0);
    }
}
