package com.example.projetomobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomobile.database.dao.UsuarioDAO;
import com.example.projetomobile.database.model.UsuarioModel;


public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText senha;
    private TextView entrar;
    private TextView cadastro;
    private Switch mostrarSenha;
    SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        SharedPreferences.Editor edit = preferences.edit();

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
                }else if(password.isEmpty()){
                    senha.setError("Campo Senha Vazio");
                }else{

                    UsuarioDAO dao = new UsuarioDAO(LoginActivity.this);
                    UsuarioModel userB;

                    userB = dao.SelectLogin(username, password);

                    if(userB.getId() == 0){
                        user.setError("Campo Usuario ou Senha Incorreto");
                    }else{
                        edit.putInt("KEY_ID", userB.getId());
                        edit.putString("KEY_NOME", userB.getNome());
                        edit.apply();
                        startActivity(new Intent(LoginActivity.this, viagensActivity.class));
                    }
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