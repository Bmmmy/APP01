package com.example.app01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button backButton = findViewById(R.id.button4);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //當"返回按鈕"被點擊時，導到home畫面
                Intent intent = new Intent (RegisterActivity.this,MainActivity .class);
                startActivity(intent);
            }
        });
    }
}