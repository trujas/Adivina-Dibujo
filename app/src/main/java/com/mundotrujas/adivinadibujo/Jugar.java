package com.mundotrujas.adivinadibujo;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Jugar extends ActionBarActivity {
    private String[] nombre_simpsons={"bart","gorgory","homero","lisa","maguie","marge","ralph"};
    private String[] sombra_simpsons={"s_bart","s_gorgory","s_homero","s_lisa","s_maguie","s_marge","s_ralph"};
    private int nroIntentos=3, nroAleatorio=0;
    private Button btnAdivinar;
    private ImageView imgDibujo;
    private EditText txtAdivinar;
    private TextView lblIntentos,lblCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        btnAdivinar=(Button)findViewById(R.id.btnAdivina);
        imgDibujo =(ImageView) findViewById(R.id.imgFigura);
        txtAdivinar=(EditText)findViewById(R.id.txtAdivinar);
        lblIntentos=(TextView) findViewById(R.id.lblIntentos);
        lblCuenta=(TextView)findViewById(R.id.lblCuenta);

        nroAleatorio=aleatorio();
        buscaDibujoSombra(nroAleatorio);
        lblIntentos.setText("Tiene: "+ String.valueOf(nroIntentos) +" intentos");
        btnAdivinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sTexto=txtAdivinar.getText().toString().toLowerCase();
                if(sTexto.equals(nombre_simpsons[nroAleatorio]))
                {
                    buscaDibujo(nroAleatorio);
                    esperar();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Nombre Simpsons Incorrecto",Toast.LENGTH_SHORT).show();
                    nroIntentos=nroIntentos-1;
                    lblIntentos.setText("Tiene: "+ String.valueOf(nroIntentos) +" intentos");
                }
                if(nroIntentos==0)
                {
                    finish();
                }

            }
        });
    }

    public int  aleatorio()
    {
        return (int) (Math.random()*nombre_simpsons.length);
    }

    public void buscaDibujoSombra(int nId)
    {
        int recurso= imgDibujo.getResources().getIdentifier(sombra_simpsons[nId],"drawable",getPackageName());
        imgDibujo.setImageResource(recurso);
    }
    public void buscaDibujo(int nId)
    {
        int recurso=imgDibujo.getResources().getIdentifier(nombre_simpsons[nId],"drawable",getPackageName());
        imgDibujo.setImageResource(recurso);
    }
    public void esperar()
    {
        new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                lblCuenta.setText("Generando en: "+ millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                nroAleatorio=aleatorio();
                buscaDibujoSombra(nroAleatorio);
                txtAdivinar.setText("");
                lblCuenta.setText("");
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
