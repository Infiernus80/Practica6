package com.example.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText ecInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ecInfo = findViewById(R.id.eInfo);
        String archivos[] = fileList();

        if (archivoExiste(archivos,"guardado.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("guardado.txt"));
                BufferedReader contenido = new BufferedReader(archivo);
                String linea = contenido.readLine();
                String todo = "";

                while (linea == null){
                    todo = todo + linea +"\n";
                    linea = contenido.readLine();
                }

                contenido.close();
                archivo.close();
                ecInfo.setText(todo);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean archivoExiste(String[] archivos, String nombre) {
        for (int i = 0; i < archivos.length; i++) {
            if (nombre.equals(archivos[i])){
                return true;
            }
        }
        return false;
    }

    public void botonGuardar(View view) {
        try {
            OutputStreamWriter arch = new OutputStreamWriter(openFileOutput("guardado.txt", Activity.MODE_PRIVATE));
            arch.write(ecInfo.getText().toString());
            arch.flush();
            arch.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Se guardo correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}