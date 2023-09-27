package com.example.grafikawprowadzenie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button_dalej;
    private Button button_wstecz;
    private Button button_pokaz;
    private ImageView imageView;
    private EditText edittext;
    private int aktualny = 0;
    private boolean czyUkryty = false;
    ArrayList<Integer> obrazki = new ArrayList<>();

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("LICZNIK",aktualny);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Stan","uruchomiono metodo oncreate");
        obrazki.add(R.drawable.obraz1);
        obrazki.add(R.drawable.obraz2);
        obrazki.add(R.drawable.obraz3);
        obrazki.add(R.drawable.obraz4);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        button_wstecz =(Button) findViewById(R.id.button4);
        button_dalej =findViewById(R.id.button5);
        button_pokaz = findViewById(R.id.button6);
        edittext = findViewById(R.id.editTextNumber);
        if(savedInstanceState != null){
            aktualny = savedInstanceState.getInt("LICZNIK");
            pokazobraz(aktualny);
        }
        button_pokaz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aktualny =  Integer.valueOf(edittext.getText().toString());
                        if (aktualny<0 || aktualny>obrazki.size()-1){
                            Toast.makeText(MainActivity.this, "Nie ma takiego obrazu", Toast.LENGTH_SHORT).show();
                            aktualny = 0;
                        }
                        pokazobraz(aktualny);
                    }
                }
        );


        button_dalej.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aktualny++;
                        if(aktualny == obrazki.size()){
                            aktualny = 0;
                            pokazobraz(aktualny);
                        }
                    }
                }
        );
        button_wstecz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aktualny--;
                        if(aktualny < 0){
                            aktualny = obrazki.size()-1;
                        }
                        pokazobraz(aktualny);
                    }
                }
        );

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(czyUkryty){
                            imageView.setVisibility(View.VISIBLE);
                            button.setText(R.string.ukry_obrazj);
                            czyUkryty = false;
                        }
                        else {
                            imageView.setVisibility(View.INVISIBLE);
                            button.setText(R.string.poka_obraz);
                            czyUkryty = true;
                        }
                    }
                }
        );
    }
    private void pokazobraz (int i){
        imageView.setImageResource(obrazki.get(i));
        imageView.setVisibility(View.VISIBLE);
    }
}