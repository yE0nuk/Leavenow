package com.mobileprogramming.leavenow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class HomeActivity extends AppCompatActivity {

    TextView tv_name;
    String id, name;

    Button btnCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv_name = findViewById(R.id.tv_name);
        id = MainActivity.ID;

        // MainActivity에 입력된 ID를 이용하여 user의 name을 가져오는 구문
        String loginQuery = "SELECT nickname FROM user WHERE user_id = '" + id + "'";
        DB db = new DB(HomeActivity.this);
        db.executeQuery(loginQuery, new DB.QueryResponseListener() {
            @Override
            public void onQuerySuccess(Object data) {
                JSONArray jsonArray = (JSONArray) data;
                try {
                    name = jsonArray.getJSONObject(0).getString("nickname");
                    tv_name.setText( name + "님.");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onQueryError(String errorMessage) {
                System.out.println(errorMessage);;
            }

            @Override
            public void onQueryError(Exception e) {

            }
        });

        btnCom = findViewById(R.id.btnCom);

        btnCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

    }
}