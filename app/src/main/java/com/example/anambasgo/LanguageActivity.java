package com.example.anambasgo;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class LanguageActivity extends AppCompatActivity {
    Spinner mLanguage;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        Spinner mLanguage = (Spinner) findViewById(R.id.spLanguage);
        final TextView mTextView = (TextView) findViewById(R.id.textView);
        ArrayAdapter mAdapter = new ArrayAdapter<String>(LanguageActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.language_option));
        mLanguage.setAdapter(mAdapter);
        if (LocaleHelper.getLanguage(LanguageActivity.this).equalsIgnoreCase("en")) {
            mLanguage.setSelection(mAdapter.getPosition("English"));
        } else if (LocaleHelper.getLanguage(LanguageActivity.this).equalsIgnoreCase("in")) {
            mLanguage.setSelection(mAdapter.getPosition("Indonesian"));
        }

        mLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Context context;
                Resources resources;
                switch (i) {
                    case 0:
                        context = LocaleHelper.setLocale(LanguageActivity.this, "en");
                        resources = context.getResources();
                        mTextView.setText(resources.getString(R.string.about));
                        break;
                    case 1:
                        context = LocaleHelper.setLocale(LanguageActivity.this, "in");
                        resources = context.getResources();
                        mTextView.setText(resources.getString(R.string.about));

                        break;
                    case 2:
                        context = LocaleHelper.setLocale(LanguageActivity.this, "es");
                        resources = context.getResources();
                        mTextView.setText(resources.getString(R.string.about));
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    public void back (View view) {
        onBackPressed();
    }
}
