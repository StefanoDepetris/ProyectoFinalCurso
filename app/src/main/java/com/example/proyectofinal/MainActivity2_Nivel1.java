package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity2_Nivel1 extends AppCompatActivity {

    private TextView tv_nombre,tv_score;
    private ImageView iv_Auno,iv_Ados,iv_vidas;
    private EditText et_respuesta;
    private MediaPlayer mp,map_bad,mp_good;

    int score, numAleatorio_uno,numAleatorio_dos,resultado,vidas=3;
    String nombre_jugador,string_score,string_vidas;

    //Arreglo String de nombre de las imagenes, es para los imageView
    String  numero []={"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__nivel1);
        Toast.makeText(this,"Nivel 1-Sumas Basicas",Toast.LENGTH_LONG).show();

        //Relaciones partes logicas a visuales.
        tv_nombre=(TextView) findViewById(R.id.jugador);
        tv_score=(TextView) findViewById(R.id.textView_vidas);
        iv_Auno=(ImageView) findViewById(R.id.imageView_0);
        iv_Ados=(ImageView) findViewById(R.id.imageView_20);
        iv_vidas=(ImageView) findViewById(R.id.imageView_vidas);
        et_respuesta=(EditText) findViewById(R.id.resultado);

            //PARA RECUPERAR EL PARAMETRO PASADO DE LA PRIMERA VISTA
        nombre_jugador=getIntent().getStringExtra("nombre");
        tv_nombre.setText("Jugador: "+ nombre_jugador);

            //Para mostrar icono al action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        mp=MediaPlayer.create(this,R.raw.goats);
        mp.start();
        mp.setLooping(true);


        mp_good=MediaPlayer.create(this,R.raw.wonderful);
        map_bad=MediaPlayer.create(this,R.raw.bad);
    }
}