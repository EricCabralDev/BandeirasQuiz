package com.example.bandeirasquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RankingActivity extends AppCompatActivity {

    private TextView tv_nome;
    private TextView tv_pontuacao;
    private Button btn_reiniciar;
    private Button btn_inicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);


    tv_nome = findViewById(R.id.tv_nome);
    tv_pontuacao = findViewById(R.id.tv_pontuacao);
    btn_reiniciar = findViewById(R.id.btn_reiniciar);
    btn_inicio = findViewById(R.id.btn_inicio);

    Intent intent = getIntent();
    String playerName = intent.getStringExtra("NOME_USUARIO");
    int pontuacao = intent.getIntExtra("PONTUACAO", 0);

    tv_nome.setText("Usuario: " + playerName);
    tv_pontuacao.setText("Pontuação" + pontuacao);

    btn_reiniciar.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent retryIntent = new Intent(RankingActivity.this, QuizActivity.class);
            retryIntent.putExtra("USER_NAME", playerName); // Passa o nome do usuário para a QuizActivity
            startActivity(retryIntent);
            finish();
        }
    });

    btn_inicio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent mainMenuIntent = new Intent(RankingActivity.this, MainActivity.class);
            startActivity(mainMenuIntent)   ;
            finish();
        }
    });


    }
}