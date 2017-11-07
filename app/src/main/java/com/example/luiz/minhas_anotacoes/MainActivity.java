package com.example.luiz.minhas_anotacoes;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private ImageView botaoSalvar;
    private EditText texto;
    private static final String NOME_ARQUIVO = "arquivo.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = (EditText) findViewById(R.id.editTextId);
        botaoSalvar = (ImageView) findViewById(R.id.botaoSalvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoDigitado = texto.getText().toString();
                gravarNoArquivo(textoDigitado);
                Toast.makeText(getApplicationContext(),"Anotações Salvas com Sucesso!",Toast.LENGTH_SHORT).show();

            }
        });
//////////////////Recuperar o que foi gravado//////////////////////
        if(lerArquivo()!=null){
            texto.setText(lerArquivo());
        }

    }

    public void gravarNoArquivo(String texto) {

        try {
            //escreve no arquivo o texto digitado
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();

        } catch (IOException e) {
            Log.v("Mainctivity", e.toString());
        }


    }

    public String lerArquivo() {
        String resultado = "";
        try {
            ///Abrir o arquivo/////
            InputStream arquivo = openFileInput(NOME_ARQUIVO);
            if (arquivo != null) {
                /////Ler Arquivo//////InputStreamReader recebe um InputStream arquivo como parametro
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                ///Buffer do arquivo lido/////
                //O bufferedReader revebe um inputStream reader como parametro para melhorar o desempenho
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //string linha para receber o que foi digitado e adicionar ao resultado
                String linha = "";
                while ((linha = bufferedReader.readLine()) != null) {


                    resultado = resultado + linha;
                }
                arquivo.close();
            }
        }catch (IOException e) {
            Log.v("MainActivity",e.toString());
        }
        return resultado;
    }



}
