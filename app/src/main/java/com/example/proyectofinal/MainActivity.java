package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     private EditText nombre;
     private ImageView imagen;
     private TextView score;

     private MediaPlayer mp;

     int num_aleatorio=(int)(Math.random()*10);//Para cambiar de personaje
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nombre=findViewById(R.id.et_nombre);
        imagen=findViewById(R.id.imageView2);
        score= findViewById(R.id.textView);


        //Esto para poner el icono en el actionBar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Parte de programa que permite cambiar la imagen.
        int id;
        if(num_aleatorio==0 ||num_aleatorio==10)
        {
            id=getResources().getIdentifier("mango","drawable",getPackageName());
            imagen.setImageResource(id);
        }

       else if(num_aleatorio==1 ||num_aleatorio==9)
        {
            id=getResources().getIdentifier("fresa","drawable",getPackageName());
            imagen.setImageResource(id);
        }
         else if(num_aleatorio==2 ||num_aleatorio==8)
        {
            id=getResources().getIdentifier("manzana","drawable",getPackageName());
            imagen.setImageResource(id);
        }
        else if(num_aleatorio==3||num_aleatorio==7)
        {
            id=getResources().getIdentifier("sandia","drawable",getPackageName());
            imagen.setImageResource(id);
        }
        else if(num_aleatorio==4||num_aleatorio==5||num_aleatorio==6)
        {
            id=getResources().getIdentifier("sandia","drawable",getPackageName());
            imagen.setImageResource(id);
        }

        //CONECCION BASE DE DATO.
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD= admin.getWritableDatabase();//Para poder abrir en modo lectura nuestra base de datos.

        //CONSULTA DE BASE DE DATOS
        Cursor consulta=BD.rawQuery("select * from puntaje where score = (select max(score) from puntaje) ",null
        );//Consulta a base de datos.Selecciona todo de la tabla puntaje donde el score sea igual maximo

        if(consulta.moveToFirst())
        {
            String temp_nombre= consulta.getString(0);//0 PORQUE EN LA TABLA PRIMERO ESTA EL NOMBRE
            String temp_score=consulta.getString(1);
            score.setText("Record: "+temp_score+ " De: "+temp_nombre);
            BD.close();
        }
        else
        {
            BD.close();//Si no hay ningun puntaje cierrra la app.

        }

        mp=MediaPlayer.create(this,R.raw.alphabet_song);//Para guardar la pista en el objeto mp
        mp.start();
        mp.setLooping(true);
    }
    public void Jugar(View view)
    {
        String Nombre=nombre.getText().toString();
        if(!nombre.equals("")&& nombre.length()>4)
        {
            mp.stop();
            mp.release();//Para liberar recursos muy importante.

            Intent intent= new Intent(this,MainActivity2_Nivel1.class);//Para pasar a otro activity.
            //Para pasar el nombre al otro activiti
            intent.putExtra("nombre", Nombre);//NOMBRE YA TIEN EL getText().toString();
            startActivity(intent);
            finish();//Para finalizar el activity de ahora.
        }
        else
        {
            Toast.makeText(this,"Escribe un nombre xxxx",Toast.LENGTH_SHORT).show();

            //Para escribir el teclado cuando intento jugar sin nombre
            nombre.requestFocus();
            InputMethodManager imm=(InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(nombre,InputMethodManager.SHOW_IMPLICIT);
        }
    }
        @Override //FUNCIONES CUANDO APRETO EL PARA ATRAS EN LA APP, QUE NO VA HACER NADA POR ESO EL MEOTOD VACIO
        public void onBackPressed()
        {

        }


}