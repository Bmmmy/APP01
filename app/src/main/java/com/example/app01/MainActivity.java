package com.example.app01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.button);
        Button registerButton = findViewById(R.id.button2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //當"登入"按鈕被點擊時，被導到有"open Camera"button的按鈕(CameraActivity)
                Intent intent_camera = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent_camera);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //當"註冊"被點擊時，導到RegisterActivity畫面
                Intent intent_register = new Intent (MainActivity.this, RegisterActivity.class);
                startActivity(intent_register);
            }
        });
    }
}