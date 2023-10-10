package com.example.projetomobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


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

        mostrarSenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    senha.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    senha.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = user.getText().toString();
                String password = senha.getText().toString();

                if(username.isEmpty()){
                    user.setError("Campo Usuario Vazio");
                }
                if(password.isEmpty()){
                    senha.setError("Campo Senha Vazio");
                }
                if (username.equals("adm") && password.equals("adm")) {
                    startActivity(new Intent(LoginActivity.this, viagensActivity.class));
            }


        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });
    }
});
}
}