package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity2_Nivel1 extends AppCompatActivity {

    private TextView tv_nombre,tv_score;
    private ImageView iv_Auno,iv_Ados,iv_vidas;
    private EditText et_respuesta;
    private MediaPlayer mp,map_bad,mp_good;

    int score, numAleatorio_uno,numAleatorio_dos,resultado,vidas=3;
    String nombre_jugador,string_score,string_vidas;//Los string son necesario porque son lo que pasamos al otro antivity

    //Arreglo String de nombre de las imagenes, es para los imageView
    String  numero []={"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__nivel1);
        Toast.makeText(this,"Nivel 1-Sumas Basicas",Toast.LENGTH_LONG).show();

        //Relaciones partes logicas a visuales.
        tv_nombre=(TextView) findViewById(R.id.jugador);
        tv_score=(TextView) findViewById(R.id.textView_score);
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


        Numaleatorio();//Cuando se inicia la app se ejecuta el num aleatorioo


    }

    public void Comparar(View view)
    {
        //Para obtener  la respuesta del jugador.
        String respuesta= et_respuesta.getText().toString();

        if(!respuesta.equals(" "))
        {
            int respuesta_int= Integer.parseInt(respuesta);
            if(resultado==respuesta_int)//SI ESTA BIEN
            {
                //Si esta bien metemos audio de rta correcta
                mp_good.start();
                score++;
                tv_score.setText("Score: " + score);//Para que se muestre la pantalla.
                et_respuesta.setText("");//Limpio campo si esta bien.
            }
            else
            {
                map_bad.start();
                vidas--;

                //Crear un menu segun la cantidad de vidas que le quedan al usuario
                switch(vidas)
                {
                    case 3:
                        iv_vidas.setImageResource(R.drawable.tresvidas);
                        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                         break;
                    case 2:
                        iv_vidas.setImageResource(R.drawable.dosvidas);
                        Toast.makeText(this, "Te queda 2 manzanas", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        iv_vidas.setImageResource(R.drawable.unavida);
                        Toast.makeText(this, "Te queda una manzana", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Intent intent= new Intent(this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        mp.stop();
                        mp.release();
                        break;
                }
                et_respuesta.setText("");//Limpio campo si esta bien.

            }
            Numaleatorio();//Independientemente si el resultado es bueno o malo el aleatorio se debe llamar.Para actualizar los numeros
        }
        else
        {
            Toast.makeText(this, "Escribe respuesta", Toast.LENGTH_SHORT).show();
        }
    }
    //Metodo para crear nunero aleatorio imagenes.
    public void Numaleatorio()
    {
        if(score<=9)
        {
            numAleatorio_uno=(int) (Math.random() * 10);//Para hacer un random de 0 a 10
            numAleatorio_dos=(int) (Math.random() * 10);//Para hacer un random de 0 a 10
            resultado=numAleatorio_uno+numAleatorio_dos;
            if(resultado<=10)
            {
                for(int i=0;i<numero.length;i++)//Numero es el arreglo de nombres de las imagenes.
                {
                    int id=getResources().getIdentifier(numero[i],"drawable",getPackageName());

                    if(numAleatorio_uno==i)
                    {
                        iv_Auno.setImageResource(id);//Seteo los imageView
                    }
                    if(numAleatorio_dos==i)
                    {
                        iv_Ados.setImageResource(id);
                    }
                }
            }

            else {
                Numaleatorio();//Si el resultado es mayor a 10.
            }
        }


        else
        {
            Intent intent=new Intent(this,MainActivity2_Nivel2.class);
            string_score=String.valueOf(score);
            string_vidas=String.valueOf(vidas);
            intent.putExtra("jugador",nombre_jugador);//SE ENVIAN SOLO STRING ENTRE INTEND
            intent.putExtra("score",string_score);
            intent.putExtra("vidas",string_vidas);

            startActivity(intent);
            finish();
            mp.stop();
            mp.release();//Libera  espacio de madiaplayer
        }


    }

}