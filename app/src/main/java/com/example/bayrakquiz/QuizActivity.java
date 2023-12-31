package com.example.bayrakquiz;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
    private ImageView imageViewBayrak;
    private TextView textViewDogru;
    private TextView textViewYanlis;
    private TextView textViewSoru;
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonD;
    private ArrayList<Bayraklar>sorularListe;
    private ArrayList<Bayraklar>yanlisSeceneklerListe;
    private Bayraklar dogruSoru;
    private  VeriTabani vt;
    //Sayaçlar
    private int soruSayac=0;
    private int yanlisSayac=0;
    private int dogruSayac=0;
    //seçenekler karıştır
    private HashSet<Bayraklar>secenekKaristirmaList=new HashSet<>();
    private ArrayList<Bayraklar> seceneklerListe=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        imageViewBayrak=findViewById(R.id.imageViewBayrak);
        textViewDogru=findViewById(R.id.textViewDogru);
        buttonA=findViewById(R.id.buttonA);
        buttonB=findViewById(R.id.buttonB);
        buttonC=findViewById(R.id.buttonC);
        buttonD=findViewById(R.id.buttonD);
        buttonA=findViewById(R.id.buttonA);
        textViewDogru=findViewById(R.id.textViewDogru);
        textViewYanlis=findViewById(R.id.textViewYanlis);
        textViewSoru=findViewById(R.id.textViewSoru);
        vt=new VeriTabani(this);
        sorularListe=new BayraklarDao().rasgeleBesGetir(vt);
        soruYukle();

        buttonA.setOnClickListener(v -> {
          dogruKontrol( buttonA);
          sayacKontrol();
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol( buttonB);
                sayacKontrol();
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol( buttonC);
                sayacKontrol();
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol( buttonD);
                sayacKontrol();
            }
        });

    }
    public void soruYukle(){
        textViewSoru.setText((soruSayac+1)+". Soru");

        dogruSoru=sorularListe.get(soruSayac);
        yanlisSeceneklerListe=new BayraklarDao().rasgeleUcYanlisGetir(vt,dogruSoru.getBayrak_id());
        imageViewBayrak.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(),"drawable",getPackageName()));
        secenekKaristirmaList.clear();
        secenekKaristirmaList.add(dogruSoru);
        secenekKaristirmaList.add(yanlisSeceneklerListe.get(0));
        secenekKaristirmaList.add(yanlisSeceneklerListe.get(1));
        secenekKaristirmaList.add(yanlisSeceneklerListe.get(2));
        seceneklerListe.clear();
        for(Bayraklar b:secenekKaristirmaList){
            seceneklerListe.add(b);

        }
        buttonA.setText(seceneklerListe.get(0).getBayrak_ad());
        buttonB.setText(seceneklerListe.get(1).getBayrak_ad());
        buttonC.setText(seceneklerListe.get(2).getBayrak_ad());
        buttonD.setText(seceneklerListe.get(3).getBayrak_ad());

    }
    public void dogruKontrol(Button button){
        String buttonYazi=button.getText().toString();
        String dogruCevap=dogruSoru.getBayrak_ad();
        if(buttonYazi.equals(dogruCevap)){
            dogruSayac++;
        }else{
            yanlisSayac++;
        }
        textViewDogru.setText("Doğru = "+dogruSayac);
        textViewYanlis.setText("Yanlis : "+yanlisSayac);
    }
    public void sayacKontrol(){
        soruSayac++;
        if(soruSayac!=5){
            soruYukle();
        }else{
           Intent intent=new Intent(QuizActivity.this, SonEkranActivity.class) ;
           intent.putExtra("dogruSayac",dogruSayac);
           startActivity(intent);
            finish();
        }
    }
}