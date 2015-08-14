package com.mundotrujas.adivinadibujo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Principal extends ActionBarActivity implements Button.OnClickListener{
    public Button btnJugar,btnCreditos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnJugar = (Button) findViewById(R.id.btnJugar);
        btnCreditos = (Button) findViewById(R.id.btnCreditos);
        btnJugar.setOnClickListener(this);
        btnCreditos.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnJugar:
                Intent frmJugar = new Intent(getApplicationContext(),Jugar.class);
                startActivity(frmJugar);
            break;
            case R.id.btnCreditos:
                Intent frmCreditos = new Intent(Principal.this, Creditos.class);
                startActivity(frmCreditos);
            break;

            default:
                break;

        }

    }
}
