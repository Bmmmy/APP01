package com.example.app01;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends Activity {

    //註冊功能所需
    private EditText accountEditText, passwordEditText,checkPdEditText;
    private Button registerButton;
    //顯示消息設定"showToast"
    private void showToast(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //返回按鍵
        Button backButton = findViewById(R.id.button4);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //當"返回按鈕"被點擊時，導到home畫面
                Intent intent = new Intent (RegisterActivity.this,MainActivity .class);
                startActivity(intent);
            }
        });

        //註冊按鍵
        // 創建 DBHelper 實例
        DBHelper dbHelper = new DBHelper(this, "CameraDB", null, 1);

        // 檢查資料庫是否存在，如果不存在則創建
        dbHelper.checkDBTable();

        // 找到 UI 元素
        accountEditText = findViewById(R.id.editTextText);
        passwordEditText = findViewById(R.id.editTextText4);
        checkPdEditText = findViewById(R.id.editTextText5);


        registerButton = findViewById(R.id.button3);


        // 設定新增按鈕的點擊事件處理程式
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 讀取使用者輸入的資料
                String account = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String checkPd = checkPdEditText.getText().toString();

                // 將資料新增到資料庫
                boolean checkRegister = dbHelper.registerFunction(account, password, checkPd);

                if(checkRegister){
                    //show成功字樣
                    showToast("新增成功");
                    // 清空輸入欄位
                    accountEditText.setText("");
                    passwordEditText.setText("");
                    checkPdEditText.setText("");

                }else{
                    //show 失敗字樣
                    showToast("新增失敗，請重新輸入!");
                }

            }
        });

    }
}