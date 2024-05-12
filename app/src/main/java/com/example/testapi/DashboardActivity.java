package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.testapi.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity {
    EditText edSearch;
    ListView lvProgram;
    ImageButton btnKeranjang;

    ImageButton btnPlus;

    ImageButton btnMin;

    ImageButton btnProfile;

    TextView textSearch;

    EditText textCount;

    Button btnBayar;

    String programName[] = null;
    String programDescription[] = null;
    String programImage[] = null;
    String programCount[] = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        edSearch = findViewById(R.id.editTextSearch);
        edSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, com.google.android.material.R.drawable.ic_search_black_24, 0);
        lvProgram = findViewById(R.id.lvProgram);



        btnProfile = findViewById(R.id.btnProfile);

        Intent intent  = getIntent();
        String sUser = intent.getStringExtra("username");
        String sNama = intent.getStringExtra("nama");
        String sAlamat = intent.getStringExtra("alamat");


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);

                intent.putExtra("username", sUser);
                intent.putExtra("nama",sNama);
                intent.putExtra("alamat", sAlamat);

                startActivity(intent);
            }
        });

        btnBayar = findViewById(R.id.buttonBayar);
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymenyActivity.class);

                startActivity(intent);
            }
        });
    }

    public class Information extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {

            try {
                String url = "http://192.168.1.102:8080/api/Barang";
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            programName = new String[response.length()];
                            programDescription = new String[response.length()];
                            programImage = new String[response.length()];
                            programCount = new String[response.length()];
                            for (int i = 0; i < response.length(); i++)
                            {
                                JSONObject person = (JSONObject) response.get(i);

                                programName[i] = person.getString("nama_barang").toString();
                                programDescription[i] = person.getString("harga_satuan").toString();
                                programImage[i] = person.getString("image2").toString();
                                programCount[i] = "1";
                            }

                            ProgramAdapter programAdapter = new ProgramAdapter(getApplicationContext(), programName, programImage,programDescription, btnKeranjang,  btnPlus, btnMin, textCount,programCount);
                            lvProgram.setAdapter(programAdapter);

                        } catch (Exception ex){

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                RequestQueue  requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonArrayRequest);

            } catch (Exception ex){

            }
            return null;
        }
    }


    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Information information = new Information();
        information.execute("");
    }




}