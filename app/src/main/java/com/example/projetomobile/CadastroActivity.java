package com.example.projetomobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomobile.database.dao.GasolinaDAO;
import com.example.projetomobile.database.dao.UsuarioDAO;
import com.example.projetomobile.database.model.GasolinaModel;
import com.example.projetomobile.database.model.UsuarioModel;

public class CadastroActivity extends AppCompatActivity {

    private EditText edit_nome;
    private EditText edit_email;
    private EditText edit_senha;
    private TextView btnEntrar;
    private TextView btnCadastro;

    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        preferences = PreferenceManager.getDefaultSharedPreferences(CadastroActivity.this);
        SharedPreferences.Editor edit = preferences.edit();

        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if(nome.isEmpty()){
                    edit_nome.setError("Campo Vazio");
                }else if(email.isEmpty()){
                    edit_email.setError("Campo Vazio");
                }else if(senha.isEmpty()){
                    edit_senha.setError("Campo Vazio");
                }else{

                    UsuarioDAO dao = new UsuarioDAO(CadastroActivity.this);
                    UsuarioModel model = new UsuarioModel();

                    model.setNome(nome);
                    model.setEmail(email);
                    model.setSenha(senha);

                    int id = dao.Insert(model);
                    if(id > 0){
                        Intent activity = new Intent(CadastroActivity.this, viagensActivity.class);

                        edit.putInt("KEY_ID", id);
                        edit.putString("KEY_NOME", edit_nome.getText().toString());
                        edit.apply();

                        startActivity(activity);
                    }else{
                        edit_email.setError("Email ja cadastrado");
                    }

                }
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
            }
        });

    }
}
