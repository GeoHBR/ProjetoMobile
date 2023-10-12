package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetomobile.database.dao.UsuarioDAO;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class AdicionarViagem extends AppCompatActivity {


    private EditText dateEditText;
    private EditText dateEditText2;
    private TextView userNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_viagem);

        UsuarioDAO dao = new UsuarioDAO(AdicionarViagem.this);
        dao.mostra();

        dateEditText = findViewById(R.id.dateEditText);
        dateEditText2 = findViewById(R.id.dateEditText2);

        // TextWatcher para o primeiro EditText
        dateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                formatInput(s);
            }
        });

        // TextWatcher para o segundo EditText
        dateEditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                formatInput(s);
            }
        });
    }

    private void formatInput(Editable s) {
        String input = s.toString();
        if (input.length() == 2 && s.charAt(1) != '/') {
            s.insert(2, "/");
        } else if (input.length() == 5 && s.charAt(4) != '/') {
            s.insert(5, "/");
        } else if (input.length() > 10) {
            s.delete(10, s.length());
        }
    }
}