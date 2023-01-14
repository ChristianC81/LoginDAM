package com.christiancrespo.logindam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private EditText editTextcorreo;
    private EditText editTextcontra;
    FirebaseAuth mAuth;
    String email;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextcorreo  = (EditText) findViewById(R.id.txtCorreo);
        editTextcontra =  findViewById(R.id.pswClave);
    }


    public void clickBoton(View view) {

        Intent intent = new Intent(this,Menu.class);

        mAuth = FirebaseAuth.getInstance();

        email = editTextcorreo.getText().toString().trim();
        pass = editTextcontra.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast toastR = Toast.makeText(getApplicationContext(), "Inicio de Sesión Correcto :D", Toast.LENGTH_SHORT);
                    toastR.show();
                    String usuario = editTextcorreo.getText().toString();
                    String password = editTextcontra.getText().toString();

                    intent.putExtra("usr", usuario);
                    intent.putExtra("psw", password);

                    startActivity(intent);
                    Toast toast = Toast.makeText(getApplicationContext(), "EMAIL: " + editTextcorreo.getText().toString() + " CONTRASEÑA : " + editTextcontra.getText().toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    onStart();

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Inicio de Sesión Fallido \nEmail o contraseña Incorrectos :c", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        }




    /*
    public void iniciarSesion(View view){

    }
*/
   public void Registrarse(View viw){
        Intent intentR = new Intent(this,RegisterActivity.class);
        startActivity(intentR);
    }
    }
