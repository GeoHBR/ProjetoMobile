package com.example.projetomobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText senha;
    private TextView entrar;
    private TextView cadastro;
    private Switch mostrarSenha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.edit_usuario);
        senha = findViewById(R.id.edit_senha_login);
        entrar = findViewById(R.id.btn_entrar_login);
        cadastro = findViewById(R.id.btn_cadastro_login);
        mostrarSenha = findViewById(R.id.switchMostrarSenha);

        mostrarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mostrarSenha.isChecked()){
                    senha.setInputType(InputType.TYPE_CLASS_TEXT);
                }else{
                    senha.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getText().toString().isEmpty()){
                    user.setError("Campo Obrigatório Vazio");
                }
                if(senha.getText().toString().isEmpty()){
                    senha.setError("Campo Obrigatório Vazio");
                }
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });
    }
}
