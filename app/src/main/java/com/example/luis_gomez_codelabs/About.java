package com.example.luis_gomez_codelabs;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

    // Para poder usar los Intent implicitos, no olvidar poner en la class, implements View.OnClickListener,

    // quedando asi: public class about extends AppCompatActivity implements View.OnClickListener {


public class About extends AppCompatActivity implements View.OnClickListener {


    // Declaro los botones e imagenes que van a estar en el layout about
    Button boton; // Boton para que al pinchar en él, vaya a la pagina principal
    ImageView btntlf1; // Imagen del tlf que al pulsar ira a la app para llamar
    ImageView btntemail;
    ImageView btngithub;
    ImageView btntwitter;
    ImageView btnlinkedin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        // Aqui le digo el id al boton para volver a la pagina principal

        boton = findViewById(R.id.boton);

        // y aqui le digo al boton que vaya de este activity al principal
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(About.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Ahora le digo que id tienen los botones o imagenes que al pulsar ira a la web, buscador, app del tlf etc

        btntlf1 = findViewById(R.id.tlf1);
        btntlf1.setOnClickListener(this);

        btntemail = findViewById(R.id.email1);
        btntemail.setOnClickListener(this);

        btngithub = findViewById(R.id.github);
        btngithub.setOnClickListener(this);

        btntwitter = findViewById(R.id.twitter);
        btntwitter.setOnClickListener(this);

        btnlinkedin = findViewById(R.id.linkedin);
        btnlinkedin.setOnClickListener(this);


    }

    // Ahora creamos un metodo onclick, y dependiendo del boton pulsado va a hacer una accion

    // Para poder usarlo, no olvidar ponerle al metodo principal al final esto, implements View.OnClickListener,

   // quedando asi: public class about extends AppCompatActivity implements View.OnClickListener.

   @Override
   public void onClick(View view) {
       Intent intent = new Intent();

       switch (view.getId()){

           // Si pulsamos el icono o imagen del tlf, se abre la app del tlf hacia ese numero para llamar
           case R.id.tlf1:
               intent.setAction(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("tel:669739931"));
               break;

           // Si pulsamos la imagen de email1, va a abrir la app por defecto qe tenga el telefono para enviar email
           case R.id.email1:

               intent.setAction(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("mailto:luisgomezlinares@yahoo.es"));
               break;

           // Si pulsamos la imagen de github, se abre la web en el navegador
           case R.id.github:

               intent.setAction(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("https://github.com/luisgomezestech?tab=repositories"));
               break;

           case R.id.twitter: // Aqui va a abrir una web
               intent.setAction(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("https://twitter.com/Luisgomezprieto"));
               break;

           case R.id.linkedin: // Aqui va a abrir una web
               intent.setAction(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("https://www.linkedin.com/in/luis-javier-gomez-prieto-ba47b7176"));
               break;
       }

       startActivity(intent);

   }


}
