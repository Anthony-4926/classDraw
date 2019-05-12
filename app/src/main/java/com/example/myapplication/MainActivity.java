package com.example.myapplication;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int n = 0;
    private DisplayMetrics metric = new DisplayMetrics();
    private MediaPlayer mediaPlayer;
    private int shakingTime =1;

    private SensorManager sensorManager;
    private Vibrator vibrator;

    private static final String TAG = "TestSensorActivity";
    private static final int SENSOR_SHAKE = 10;

    private long lastShaking = System.nanoTime();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;// 屏幕宽度（像素）
        LinearLayout studentList = findViewById(R.id.student_list);

        for (Student s : DataBase.students) {
            LinearLayout item = new LinearLayout(MainActivity.this);
            item.setOrientation(LinearLayout.HORIZONTAL);
            item.setId(Integer.valueOf(s.getNum()));
            item.setGravity(Gravity.CENTER_VERTICAL);

            ImageView head = new ImageView(MainActivity.this);
            head.setImageResource(R.mipmap.ic_launcher_round);
            LinearLayout.LayoutParams paramsHead = new LinearLayout.LayoutParams(width / 6, width / 6);
            head.setLayoutParams(paramsHead);
            LinearLayout.LayoutParams layoutParamsHead = (LinearLayout.LayoutParams) head.getLayoutParams();
            layoutParamsHead.setMargins(0, 0, 30, 0);

            LinearLayout info = new LinearLayout(MainActivity.this);
            info.setOrientation(LinearLayout.VERTICAL);

            TextView name = new TextView(MainActivity.this);
            name.setText(s.getName());
            name.setTextSize(20);
            TextView num = new TextView(MainActivity.this);
            num.setText(s.getNum());
            num.setTextSize(20);

            info.addView(name);
            info.addView(num);

            item.addView(head);
            item.addView(info);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(MainActivity.this, "点我干啥", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this, String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
                    mediaPlayer = MediaPlayer.create(MainActivity.this, getSound(String.valueOf(v.getId())));
                    mediaPlayer.start();
                }
            });

            if (n % 2 == 0) {
                item.setBackgroundResource(R.color.list_odd);
            }
            studentList.addView(item);
            n++;
        }


//摇一摇
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null) {// 注册监听器
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {// 取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    /**
     * 重力感应监听
     */
    private SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            Log.i(TAG, "x轴方向的重力加速度" + x + "；y轴方向的重力加速度" + y + "；z轴方向的重力加速度" + z);
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
            int medumValue = 10;// 三星 i9250怎么晃都不会超过20，没办法，只设置19了
            if ((Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) && shakingTime++ % 5 == 0) {
                vibrator.vibrate(500);
                Message msg = new Message();
                msg.what = SENSOR_SHAKE;
                handler.sendMessage(msg);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    /**
     * 动作执行
     */
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SENSOR_SHAKE:
                    startActivity(new Intent(MainActivity.this, ShakingAcitvity.class));
                    Log.i(TAG, "检测到摇晃，执行操作！");
                    break;
            }
        }

    };


    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    public static int getSound(String num) {
        int result = 0;
        for (Student s : DataBase.students) {
            if (s.getNum().equals(num)) {
                result = s.getSound();
            }
        }
        return result;
    }
}
