package com.mobileprogramming.leavenow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class SigninActivity extends AppCompatActivity {


    private EditText et_id;
    private EditText et_pw;
    private EditText et_name;
    private Button btn_submit;
    private TextView tv_result;
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        et_name = findViewById(R.id.et_name);
        tv_result = findViewById(R.id.tv_result);
        btn_submit = findViewById(R.id.btn_submit);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = et_id.getText().toString().trim();
                String userPw = et_pw.getText().toString().trim();
                String userName = et_name.getText().toString().trim();

                if (!userId.isEmpty() && !userPw.isEmpty() && !userName.isEmpty()) {
                    String loginQuery = "INSERT into leavenow.user (user_id,email, password,nickname) VALUES ('" + userId + "', email, '" + userPw + "', '" + userName + "'); ";
                    DB db = new DB(SigninActivity.this);

                    db.executeQuery(loginQuery, new DB.QueryResponseListener() {
                        @Override
                        public void onQuerySuccess(Object data) {
                            String result = (String) data;
                            tv_result.setText("회원가입 " + result);
                        }
                        @Override
                        public void onQueryError(String errorMessage) {
                            tv_result.setText("오류: " + errorMessage);
                        }

                        @Override
                        public void onQueryError(Exception e) {

                        }
                    });

                } else {
                    Toast.makeText(SigninActivity.this, "공백란이 존재합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}