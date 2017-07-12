package com.example.acer.vale_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class OtpActivity extends AppCompatActivity {
    private ImageButton ib;
    private TextView text;
    private EditText et;
    private CheckBox cb;
    private Button btnLogin;
    private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ib= (ImageButton) findViewById(R.id.ib2);
        text= (TextView) findViewById(R.id.tvOtp);
        et= (EditText) findViewById(R.id.et);
        cb= (CheckBox) findViewById(R.id.cb2);
        btnLogin= (Button) findViewById(R.id.btnLogin2);

        btnLogin.setEnabled(false);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(OtpActivity.this,Login2Activity.class);
                startActivity(i1);
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(OtpActivity.this,Login2Activity.class);
                startActivityForResult(i1,20);

            }
        });

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                count++;

                if((count%2)==0)
                {
                    btnLogin.setEnabled(false);
                }
                else
                    btnLogin.setEnabled(true);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i1=new Intent(OtpActivity.this,TabbedActivity.class);
                startActivity(i1);
            }
        });


    }
    @Override
    public void onBackPressed()
    {
        Intent i1=new Intent(OtpActivity.this,Login2Activity.class);
        startActivity(i1);
    }
}
