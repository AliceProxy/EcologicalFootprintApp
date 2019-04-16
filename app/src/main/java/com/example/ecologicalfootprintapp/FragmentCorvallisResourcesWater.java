package com.example.ecologicalfootprintapp;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import com.example.ecologicalfootprintapp.TextFlowHelper;

public class FragmentCorvallisResourcesWater extends Fragment {
    private static final String TAG = "CorvallisWater";
    View myview;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        FragmentQuestionRes.getInstance().updateQ15();
        final String waterContentText1, waterContentText2;
        waterContentText1 = getString(R.string.corvallisWater1);
        waterContentText2 = getString(R.string.corvallisWater2);

        View view = inflater.inflate(R.layout.fragment_corvallis_water, container, false);
        Log.d(TAG, "onCreateView: started.");

        final ImageView waterPic1 = (ImageView) (view).findViewById(R.id.waterPic1);
        final ImageView waterPic2 = (ImageView) (view).findViewById(R.id.waterPic2);
        final TextView waterContent1 = (TextView) (view).findViewById(R.id.waterText1);
        final TextView waterContent2 = (TextView) (view).findViewById(R.id.waterText2);

        final ViewTreeObserver vto = waterPic1.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                waterPic1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                makeSpan(waterContentText1, waterContent1, waterPic1);
            }
        });

        final ViewTreeObserver vto2 = waterPic1.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                waterPic1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                makeSpan(waterContentText2, waterContent2, waterPic2);
            }
        });

        waterPic1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openBrowser(waterPic1);

            }
        });

        waterPic2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openBrowser(waterPic2);

            }
        });

        return view;
    }
    private void makeSpan(String text,TextView txtView, ImageView imgView) {
        /**
         * Get the text
         */
        int finalHeight = imgView.getMeasuredHeight();
        int finalWidth = imgView.getMeasuredWidth();

        Spanned htmlText = Html.fromHtml(text);
        SpannableString mSpannableString= new SpannableString(htmlText);

        int allTextStart = 0;
        int allTextEnd = htmlText.length() - 1;

        /**
         * Calculate the lines number = image height.
         * You can improve it... it is just an example
         */
        int lines;
        Rect bounds = new Rect();
        txtView.getPaint().getTextBounds(text.substring(0,10), 0, 1, bounds);

        //float textLineHeight = mTextView.getPaint().getTextSize();
        float fontSpacing=txtView.getPaint().getFontSpacing();
        lines = (int) (finalHeight/(fontSpacing));

        /**
         * Build the layout with LeadingMarginSpan2
         */
        LeadingMarginSpanHelper span = new LeadingMarginSpanHelper(lines, finalWidth +10 );
        mSpannableString.setSpan(span, allTextStart, allTextEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        txtView.setText(mSpannableString);

    }

    public void openBrowser(View view) {

        //Get url from tag
        String url = (String) view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse(url));

        startActivity(intent);

    }
}
