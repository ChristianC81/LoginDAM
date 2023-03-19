package com.christiancrespo.logindam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class Menu extends AppCompatActivity {
    ImageView imgView;
    String rutaImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent= getIntent();

        String email= intent.getStringExtra("usr");
        String password= intent.getStringExtra("psw");

    }
    public void tomarFoto(View view) {
        Intent intentcamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentcamara.resolveActivity(getPackageManager()) != null) {

            //metodos para guardar
            File imagenArchivo = null;
            try {
                imagenArchivo = guardarImagen();
            }catch(IOException ex){
                Log.e("Error", ex.toString());
            }
            //validacion
            if(imagenArchivo != null){
                Uri fotoUri = FileProvider.getUriForFile(this, "com.christiancrespo.logindam.fileprovider",imagenArchivo);
                intentcamara.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                startActivityForResult(intentcamara, 1);
            }

        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Bundle bundle = data.getExtras();
            //imagen = (Bitmap) bundle.get("data");
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImg);
            imgView = (ImageView) findViewById(R.id.imageView);
            imgView.setImageBitmap(imgBitmap);
        }
    }
    private File guardarImagen() throws IOException {
        String imagentomada = "img_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File img = File.createTempFile(imagentomada, ".jpg",directorio);
        rutaImg=img.getAbsolutePath();
        return img;
    }
}