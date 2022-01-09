package com.quangcao.bai5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_kq;
    EditText edt_pid ,edt_name , edt_price , edt_description;
    Button btn_get, btn_get_string, btn_insert , btn_update , btn_delete;
    String kq = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_pid = findViewById(R.id.edt_pid);
        edt_name = findViewById(R.id.edt_name);
        edt_price = findViewById(R.id.edt_price);
        edt_description = findViewById(R.id.edt_description);
        tv_kq = findViewById(R.id.tv_kq);
        btn_get = findViewById(R.id.btn_get);
        btn_get_string = findViewById(R.id.btn_get_2);

        btn_insert = findViewById(R.id.btn_Insert);
        btn_update = findViewById(R.id.btn_Update);
        btn_delete = findViewById(R.id.btn_delete);

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FunctionVolley functionVolley = new FunctionVolley();
//                functionVolley.getStringVolley(MainActivity.this , tv_kq);
                functionVolley.getJsonArray(MainActivity.this , tv_kq);
            }
        });

        btn_get_string.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FunctionVolley functionVolley = new FunctionVolley();
                functionVolley.getStringVolley(MainActivity.this , tv_kq);
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FunctionVolley functionVolley = new FunctionVolley();
                functionVolley.insertVolley(MainActivity.this , tv_kq,
                        edt_name.getText().toString(),
                        edt_price.getText().toString(),
                        edt_description.getText().toString()
                        );
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FunctionVolley functionVolley = new FunctionVolley();
                functionVolley.updateVolley(MainActivity.this , tv_kq,
                        edt_pid.getText().toString(),
                        edt_name.getText().toString(),
                        edt_price.getText().toString(),
                        edt_description.getText().toString()
                        );
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FunctionVolley functionVolley = new FunctionVolley();
                functionVolley.deleteVolley(MainActivity.this , tv_kq , edt_pid.getText().toString() );
            }
        });
    } // end Oncreate
}