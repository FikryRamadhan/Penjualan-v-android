package com.example.testapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn ;

    private Button btnDaftar ;

    private TextView textViewUsername;

    private ProgressDialog progressDialog;

    private TextView textViewPassword;
    Users users = new Users();

    String username;
    String password;

    String nama;
    String alamat;

//    private ProgressDialog progressDialog;
    private List<Users> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.buttonLogin);
        textViewUsername = findViewById(R.id.editTextUsername);
        textViewPassword = findViewById(R.id.editTextPassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPIV4();
            }
        });


        btnDaftar = findViewById(R.id.buttonDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DaftarActivity.class);

                startActivity(intent);
            }
        });


    }

    /*
    *
    * Get Api For Get User
    *
    * */

    public void callAPIV4() {
        String url1= "http://192.168.1.102:8080/api/User/username?username="+ textViewUsername.getText() +"&password="+ textViewPassword.getText() +"\n";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
//                    progressDialog.setTitle("Dagoan");
//                    progressDialog.show();

                    users.ID = response.getInt("id");
                    users.type_user = response.getString("type_user");
                    users.name = response.getString("nama");
                    users.alamat = response.getString("alamat");
                    users.username = response.getString("username");
                    users.password = response.getString("password");

                    username = response.getString("username");
                    password = response.getString("password");
                    nama = response.getString("nama");
                    alamat = response.getString("alamat");

                    String sUsername = String.valueOf(textViewUsername.getText());
                    String sPassword = String.valueOf(textViewPassword.getText());


                    if(sUsername.equals(username)){
                        Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);

//                        Menyelipkan Variabel
                        intent.putExtra("username", username);
                        intent.putExtra("nama",nama);
                        intent.putExtra("alamat", alamat);

                        startActivity(intent);

//                        progressDialog.dismiss();
                    } else if (sPassword != password) {
                        Toast.makeText(MainActivity.this, "Password Incorect / Silahkan Cek Password Kembali", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Masukan Kembali Data Anda", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception ex) {}


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}