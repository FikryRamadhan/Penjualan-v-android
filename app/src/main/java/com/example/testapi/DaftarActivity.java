package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DaftarActivity extends AppCompatActivity {

    private TextView textViewnama;
    private TextView textViewAlamat;
    private TextView textViewUsername;
    private TextView textViewPassword;
    private TextView textViewConfirm;


    private Button btnDaftar;

    private Button btnSudah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        btnDaftar = findViewById(R.id.buttonDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAPI();
            }
        });

        btnSudah = findViewById(R.id.buttonSudah);
        btnSudah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });

    }

    public void callAPI(){
        Users users = new Users();
        // Password
        textViewPassword = findViewById(R.id.editTextPassword);
        users.password = textViewPassword.getText().toString();
        // username
        textViewConfirm = findViewById(R.id.editTextConfirmPassword);
        String confirm = textViewConfirm.getText().toString();

        if(!confirm.equals(users.password.toString())){
            Toast.makeText(DaftarActivity.this, "Silahkan Konfirmasi Password Kembali", Toast.LENGTH_SHORT).show();
        }

        String url1= "http://192.168.1.102:8080/api/User";
        try {

            // Menyelipkan data ke url
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ID","0");
            jsonObject.put("type_user","Customer");
            jsonObject.put("nama",textViewnama.getText().toString());
            jsonObject.put("alamat",textViewAlamat.getText().toString());
            jsonObject.put("username",textViewUsername.getText().toString());
            jsonObject.put("password",textViewPassword.getText().toString());


            RequestQueue requestQueue = Volley.newRequestQueue(DaftarActivity.this);
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url1, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(DaftarActivity.this,"Daftar Berhasil", Toast.LENGTH_LONG).show();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DaftarActivity.this,"Daftar Gagal", Toast.LENGTH_LONG);

                }
            });
            requestQueue.add(objectRequest);
        }
        catch (Exception ec)
        {

        }
    }

}