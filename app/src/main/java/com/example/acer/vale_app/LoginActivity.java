package com.example.acer.vale_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private ImageView iv;
    private TextView tv1,tv2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iv= (ImageView) findViewById(R.id.iv);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        btn= (Button) findViewById(R.id.btnLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a1=new Intent(LoginActivity.this,Login2Activity.class);
                startActivity(a1);
            }
        });



    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(LoginActivity.this);
        dialog.setMessage("Do you want to exit ?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Intent i1=new Intent(LoginActivity.this,Splash.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i1.putExtra("EXIT", true);
                startActivity(i1);



            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();

    }


}
