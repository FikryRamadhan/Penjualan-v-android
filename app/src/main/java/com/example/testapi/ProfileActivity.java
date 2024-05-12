package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    TextView textUsername;

    TextView textNama;
    TextView textAlamat;

    ImageButton btnDashboard;

    Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent  = getIntent();
        String sUser = intent.getStringExtra("username");
        String sNama = intent.getStringExtra("nama");
        String sAlamat = intent.getStringExtra("alamat");

        textNama = findViewById(R.id.Nama);
        textNama.setText(sNama);

        textUsername = findViewById(R.id.Username);
        textUsername.setText(sUser);

        textAlamat = findViewById(R.id.Alamat);
        textAlamat.setText(sAlamat);

        btnDashboard =findViewById(R.id.btnDashboard);
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);

                intent.putExtra("username", sUser);
                intent.putExtra("nama",sNama);
                intent.putExtra("alamat", sAlamat);

                startActivity(intent);
            }
        });

        btnLogout = findViewById(R.id.buttonLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                Toast.makeText(ProfileActivity.this, "Log Out Berhasil", Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });


    }
}