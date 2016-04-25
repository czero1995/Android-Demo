package com.example.zake.smarttime;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zake on 3/14/16.
 */
public class AlarmView extends LinearLayout {
    private Button addalarm;
    private ListView listview;
    private ArrayAdapter<AlarmData> adapter;
    private static final String KEY_ALARM_LIST="alarmlist";
    private AlarmManager alarmManger;

    public AlarmView(Context context) {
        super(context);
        init();
    }

    public AlarmView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AlarmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        alarmManger = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addalarm = (Button) findViewById(R.id.addalarm);
        listview = (ListView) findViewById(R.id.listview);
        adapter=new ArrayAdapter<AlarmData>(getContext(),android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);
//         adapter.add(new AlarmData(System.currentTimeMillis()));
        readsavealarmlist();
        addalarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarm();
            }

        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getContext()).setTitle("操作选项").setItems(new CharSequence[]{"删除","编辑"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                deletealarm(position);
                                break;
                            case 1:
                                editor(position);
                                deletealarm(position);
                        }
                    }
                }).setNegativeButton("取消",null).show();


            }

        });
    }
    private void deletealarm(int position){
        adapter.remove(adapter.getItem(position));
        savealarmlist();

    }
    private void editor(int position){
        addAlarm();
    }



    private void addAlarm() {
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
                Calendar currentTime = Calendar.getInstance();
                if (calendar.getTimeInMillis() <= currentTime.getTimeInMillis()) {
                    calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 60 * 60 * 1000);
                }
                adapter.add(new AlarmData(calendar.getTimeInMillis()));
                alarmManger.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),5*60*1000, PendingIntent.getBroadcast(getContext(),0,new Intent(getContext(),AlarmReceiver.class),0));
                savealarmlist();

            }

        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }

    private void readsavealarmlist() {
        SharedPreferences sp = getContext().getSharedPreferences(AlarmView.class.getName(),Context.MODE_PRIVATE);
        String content = sp.getString(KEY_ALARM_LIST,null);
        if(content!=null) {
            String[] timeStrings = content.split(",");
            for (String string : timeStrings) {
                adapter.add(new AlarmData(Long.parseLong(string)));
            }
        }
//        }等同于
//            for (int i = 0; i < str.length; i++) {
//                String s = str[i];
    }

    private void savealarmlist() {
        SharedPreferences.Editor editor = getContext().getSharedPreferences(AlarmView.class.getName(), Context.MODE_PRIVATE).edit();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < adapter.getCount(); i++) {
            sb.append(adapter.getItem(i).getTime()).append(",");

        }
        if(sb.length()>1){
        String content = sb.toString().substring(0, sb.length() - 1);
        editor.putString(KEY_ALARM_LIST, content);
        System.out.println(content);
    }else{
            editor.putString(KEY_ALARM_LIST, null);
        }

        editor.commit();
    }

    private static class AlarmData {
        private final long time;
        private String timeLabel;
        private Calendar date;

        public AlarmData(long time) {
            this.time = time;

            date=Calendar.getInstance();
            date.setTimeInMillis(time);
            timeLabel = String.format("%d年%d月%d日%d:%d",
                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH)+1,
                    date.get(Calendar.DAY_OF_MONTH),
                    date.get(Calendar.HOUR_OF_DAY),
                    date.get(Calendar.MINUTE));
        }

        public long getTime() {
            return time;
        }

        public String getTimeLabel() {
            return timeLabel;
        }

        public String toString() {
            return getTimeLabel();
        }
    }
}
























