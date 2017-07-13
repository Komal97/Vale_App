package com.example.acer.vale_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login2Activity extends AppCompatActivity {
    private ImageButton ib;
    private TextView text;
    private EditText et;
    private CheckBox cb;
    private Button btnLogin;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ib= (ImageButton) findViewById(R.id.ib);
        text= (TextView) findViewById(R.id.tvNo);
        et= (EditText) findViewById(R.id.et);
        cb= (CheckBox) findViewById(R.id.cb);
        btnLogin= (Button) findViewById(R.id.btnLogin);

        et.setSelection(et.getText().length());
        cb.setChecked(false);
        btnLogin.setEnabled(false);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Login2Activity.this,LoginActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i1);
                finish();
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Login2Activity.this,LoginActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i1);
                finish();
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
                Intent i2=new Intent(Login2Activity.this,OtpActivity.class);
                i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i2);
                finish();
            }
        });




    }

    @Override
    public void onBackPressed()
    {
        Intent i1=new Intent(Login2Activity.this,LoginActivity.class);
        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i1);
        finish();
    }
}
