package com.example.czero.zmsz.ZMSZView;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.czero.zmsz.R;
import com.example.czero.zmsz.ZMSZData.MineListAdapter;

/**
 * Created by zake on 5/20/16.
 */
public class HappyActivity extends LinearLayout {
    private Button godelight;

    public HappyActivity(Context context) {
        super(context);
    }

    public HappyActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HappyActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        godelight= (Button) findViewById(R.id.godelight);
        godelight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent godelight = new Intent(getContext(),DelightActivity.class);
                sta0
            }
        });


    }


    }

