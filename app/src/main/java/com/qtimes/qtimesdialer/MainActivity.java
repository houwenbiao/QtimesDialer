package com.qtimes.qtimesdialer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import im.dlg.dialer.DialpadActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvNum;
    private static final String BROADCAST_PERMISSION_DISC = "com.qtimes.permissions.DIALER_BROADCAST";
    private static final String BROADCAST_ACTION_DISC = "com.qtimes.permissions.dialer_broadcast";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNum = findViewById(R.id.tv_num);
        Intent intent = new Intent(MainActivity.this, DialpadActivity.class);
        intent.putExtra("EXTRA_REGION_CODE", "US");
        startActivityForResult(intent, 100); // any result request code is ok
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String formatted = data.getStringExtra(DialpadActivity.EXTRA_RESULT_FORMATTED);
            String raw = data.getStringExtra(DialpadActivity.EXTRA_RESULT_RAW);
            Log.i("Main", formatted + ",  " + raw);
            tvNum.setText(raw);
            if ("1234".equals(raw)) {
                Intent intent = new Intent();  //Itent就是我们要发送的内容
                intent.putExtra("host", raw);
                intent.setAction(BROADCAST_ACTION_DISC);   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
                sendBroadcast(intent, BROADCAST_PERMISSION_DISC);   //发送广播
            }
        }
    }
}
