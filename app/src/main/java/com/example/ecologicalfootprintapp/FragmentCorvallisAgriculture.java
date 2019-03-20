package com.example.ecologicalfootprintapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;

public class FragmentCorvallisAgriculture extends Fragment {
    private static final String TAG = "CorvallisAgriculture";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_corvallis_agriculture, container, false);
        Log.d(TAG, "onCreateView: started.");

        TextView contentBox1 = view.findViewById(R.id.AgriContent1);
        TextView contentBox2 = view.findViewById(R.id.AgriContent2);

        String content1 = "Corvallis has many farmers markets throughout the seasons. They can be a good opportunity for members of the community to purchase fresh local produce while supporting sustainable local farmers. You can visit Locally Grown to view dates and locations";
        String content2 = "There is an organization called Oregon Tilth that has a library of agricultural resources for Oregon residents. This page contains information such as farm business management, water conservation, and much more information about sustainable farming.";

        SpannableString ss1 = new SpannableString(content1);
        SpannableString ss2 = new SpannableString(content2);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget)
            {
                Uri uri = Uri.parse("https://locallygrown.org/home/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget)
            {
                Uri uri = Uri.parse("https://www.tilth.org/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };

        ss1.setSpan(clickableSpan1, content1.indexOf("Locally"), content1.indexOf("Locally") + 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(clickableSpan2, content2.indexOf("Oregon"), content2.indexOf("Oregon") + 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        contentBox1.setText(ss1);
        contentBox1.setMovementMethod(LinkMovementMethod.getInstance());

        contentBox2.setText(ss2);
        contentBox2.setMovementMethod(LinkMovementMethod.getInstance());


        return view;
    }
}
