package com.example.czero.jannote;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;
import android.os.Vibrator;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by zake on 4/26/16.
 */
public class TODO extends Activity {
    private ListView todolv;
    private Button todo_btn;
    private ArrayAdapter<AlarmData> adapter;
    private AlarmManager alarmManger;
    private static final String KEY_ALARM_LIST = "alarmlist";
    private String Text;
    private EditText editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo);
        alarmManger = (AlarmManager) TODO.this.getSystemService(Context.ALARM_SERVICE);
        todolv = (ListView) findViewById(R.id.todolv);
        todo_btn = (Button) findViewById(R.id.addtodo);
        adapter = new ArrayAdapter<AlarmData>(TODO.this, android.R.layout.simple_list_item_1);
        todolv.setAdapter(adapter);

        todo_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
//                addAlarm();
                editor = new EditText(TODO.this);
                editor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                final AlertDialog.Builder builder = new AlertDialog.Builder(TODO.this);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle("简记");
                builder.setIcon(R.drawable.jannote);
                builder.setView(editor);
                builder.setMessage("请输入待办事件");
                builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        获取要分享的文本
                        Text = editor.getText().toString();
                        if (Text == null || Text.length() == 0) {
                            return;
                        }
                        addAlarm();
                    }
                });
                builder.setNegativeButton("取消", null);
                final AlertDialog alert = builder.create();
                alert.show();
            }
        });

        todolv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(TODO.this).setTitle("操作选项").setItems(new CharSequence[]{"删除", "编辑"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                deleteAlarm(position);
                                break;
                            case 1:
                                editor(position);
                                deleteAlarm(position);
                                break;
                        }
                    }
                }).setNegativeButton("取消", null).show();
            }

            private void deleteAlarm(int position) {
                adapter.remove(adapter.getItem(position));
                saveAlarm();

            }
        });
        readSaveAlarm();
    }

    private void vibrator() {
        Vibrator vibrator = (Vibrator) TODO.this.getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{1000, 3000, 1000, 3000, 1000, 3000, 1000, 3000, 1000, 3000, 1000, 3000, 1000, 3000, 1000, 3000, 1000, 3000, 1000, 3000}, -1);

    }

    private void init() {
        alarmManger = (AlarmManager) TODO.this.getSystemService(Context.ALARM_SERVICE);
    }
    public String editorgettext(){
       return editor.getText().toString();
    }


    private void addAlarm() {
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(TODO.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                Calendar currentTime = Calendar.getInstance();
                if (calendar.getTimeInMillis() <= currentTime.getTimeInMillis()) {
                    calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 60 * 60 * 1000);
                }
                adapter.add(new AlarmData(calendar.getTimeInMillis()));
                alarmManger.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5 * 60 * 1000,
                        PendingIntent.getBroadcast(TODO.this, 0, new Intent(TODO.this, AlarmReceiver.class), 0));
                saveAlarm();
            }

        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();

    }

    private void editor(int position) {
        addAlarm();
    }

    private void saveAlarm() {
        SharedPreferences.Editor editor = TODO.this.getSharedPreferences(TODO.class.getName(), Context.MODE_PRIVATE).edit();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < adapter.getCount(); i++) {
            sb.append(adapter.getItem(i).getTime()).append(",");
            if (sb.length() > 1) {
                String content = sb.toString().substring(0, sb.length() - 1);
                editor.putString(KEY_ALARM_LIST, content);
            } else {
                editor.putString(KEY_ALARM_LIST, null);
            }
            editor.commit();
        }
    }

    private void readSaveAlarm() {
        SharedPreferences sp = TODO.this.getSharedPreferences(TODO.class.getName(), Context.MODE_PRIVATE);
        String content = sp.getString(KEY_ALARM_LIST, null);
        if (content != null) {
            String[] timeStrings = content.split(",");
            for (String string : timeStrings) {
                adapter.add(new AlarmData(Long.parseLong(string)));
            }
        }
    }

    class AlarmData {
        private final long time;
        private String timeLabel;
        private Calendar date;

        public AlarmData(long time) {
            this.time = time;
            date = Calendar.getInstance();
            date.setTimeInMillis(time);
            timeLabel = String.format("%d年%d月%d日:%d:%d", date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.HOUR_OF_DAY),
                    date.get(Calendar.MINUTE));
            editor = new EditText(TODO.this);
            Text = editor.getText().toString();
            if (Text == null || Text.length() == 0) {
                return;
            }

        }

        public long getTime() {
            return time;
        }

        public String getTimeLaybel() {
            return timeLabel;

        }

        public String toString() {
            return getTimeLaybel();

        }

    }

}