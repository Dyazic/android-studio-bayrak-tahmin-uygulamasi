package com.example.bayrakquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SonEkranActivity extends AppCompatActivity {
    private TextView textViewSonuc;
    private TextView    textViewYuzdeSonuc;
    private Button buttonTekrarBasla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son_ekran);
        textViewSonuc = findViewById(R.id.textViewSonuc);
        textViewYuzdeSonuc=findViewById(R.id.textViewYuzdeSonuc);
        buttonTekrarBasla=findViewById(R.id.buttonTekrarBasla);
     buttonTekrarBasla.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(SonEkranActivity.this,QuizActivity.class));
             finish();
         }
     });
    }
}