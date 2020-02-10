package com.qtimes.qtimesdialer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import im.dlg.dialer.DialpadActivity;

import android.app.Activity;
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
                Intent intent = new Intent("com.qtimes.dialer");
                intent.putExtra("num", raw);
                sendBroadcast(intent);
            }
        }
    }
}
