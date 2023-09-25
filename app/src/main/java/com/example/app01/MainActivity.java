package com.example.app01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private void showToast(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 創建 DBHelper 實例
        DBHelper dbHelper = new DBHelper(this, "SampleList.db", null, 1);

        // 找到 UI 元素
        Button loginButton = findViewById(R.id.button);
        Button registerButton = findViewById(R.id.button2);
        EditText accountEditText = findViewById(R.id.editTextText2);
        EditText passwordEditText = findViewById(R.id.editTextText3);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 讀取使用者輸入的資料
                String account = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                //檢查否在資料庫中有該帳密
                boolean CheckUserExists = dbHelper.CheckIfUserExists(account,password);
                if (CheckUserExists) {
                    showToast("登入成功!");
                    //當"登入"按鈕被點擊時，被導到有"open Camera"button的按鈕(CameraActivity)
                    Intent intent_camera = new Intent(MainActivity.this, CameraActivity.class);
                    startActivity(intent_camera);
                } else {
                    //若帳號已存在，顯示訊息then清空輸入欄位
                    showToast("登入失敗! \n 請重新登入!");
                }
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