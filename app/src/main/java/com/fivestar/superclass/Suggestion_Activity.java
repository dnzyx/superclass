package com.fivestar.superclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by dnzyx on 2017/11/16.
 */

public class Suggestion_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_suggestion_activity);
        setTitle("问题建议");

    }
}
