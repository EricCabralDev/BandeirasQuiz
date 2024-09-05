package com.example.bandeirasquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView tv_pergunta;
    private ImageView iv_bandeira;
    private RadioButton opc1, opc2, opc3, opc4;
    private Button btn_responder;
    private RadioGroup opc_grupo;

    private int[] bandeiras = {R.drawable.usa, R.drawable.hungria, R.drawable.guatemala, R.drawable.china,
            R.drawable.cuba, R.drawable.coreia_sul, R.drawable.bulgaria, R.drawable.brasil, R.drawable.italia, R.drawable.canada};
    private String[][] opcoes = {
            {"Chile", "USA", "Síria", "India"},
            {"Nigeria", "Lituania", "Hungria", "Bolivia"},
            {"Bélgica", "Noruega", "San Marino", "Guatemala"},
            {"China", "Japão", "Coreia do Sul", "Malasia"},
            {"Cuba", "Porto Rico", "México", "Equador"},
            {"Taiwan", "Coreia do Sul", "Filipinas", "Sérvia"},
            {"Ucrânia", "Finlandia", "Bulgaria", "Russia"},
            {"Brasil", "Argentina", "Suecia", "Uruguai"},
            {"Portugal", "Italia", "Espanha", "França"},
            {"Chile", "Suiça", "Dinamarca", "Canada"}
    };
    private String[] correctAnswers = {
            "USA", "Hungria", "Guatemala",
            "China", "Cuba", "Coreia do Sul",
            "Bulgaria", "Brasil", "Italia",
            "Canada"
    };

    private int indexQuestao = 0;
    private int pontuacao = 0;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tv_pergunta = findViewById(R.id.tv_pergunta);
        iv_bandeira = findViewById(R.id.iv_bandeira);
        opc1 = findViewById(R.id.rdbtn_op1);
        opc2 = findViewById(R.id.rdbtn_op2);
        opc3 = findViewById(R.id.rdbtn_op3);
        opc4 = findViewById(R.id.rdbtn_op4);
        btn_responder = findViewById(R.id.btn_responder);
        opc_grupo = findViewById(R.id.rdgrp_opcoes);


        playerName = getIntent().getStringExtra("NOME_USUARIO");

        pularPergunta();

        btn_responder.setEnabled(false);

        opc_grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btn_responder.setEnabled(true);
            }
        });

        btn_responder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = opc_grupo.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedOption = findViewById(selectedId);
                    String resp = selectedOption.getText().toString();
                    verificarResposta(resp);
                    indexQuestao++;
                    if (indexQuestao < bandeiras.length) {
                        pularPergunta();
                    } else {
                        terminarQuiz(); // Finaliza o quiz após a última pergunta
                    }
                    // Desabilita o botão "RESPONDER" após clicar
                    btn_responder.setEnabled(false);
                } else {
                    Toast.makeText(QuizActivity.this, "Escolha uma alternativa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void pularPergunta() {
        iv_bandeira.setImageResource(bandeiras[indexQuestao]);
        opc1.setText(opcoes[indexQuestao][0]);
        opc2.setText(opcoes[indexQuestao][1]);
        opc3.setText(opcoes[indexQuestao][2]);
        opc4.setText(opcoes[indexQuestao][3]);
        opc_grupo.clearCheck();
    }

    private void verificarResposta(String answer) {
        if (answer.equals(correctAnswers[indexQuestao])) {
            pontuacao++;
        }
    }

    private void terminarQuiz() {
        Intent intent = new Intent(QuizActivity.this, RankingActivity.class);
        intent.putExtra("NOME_USUARIO", playerName);
        intent.putExtra("PONTUACAO", pontuacao);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Cria um Intent para retornar à tela principal
        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
        // Limpa a pilha de atividades para que a tela principal seja a única na pilha
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // Encerra a atividade atual
        finish();

        // Chama a implementação padrão de onBackPressed (opcional, se necessário)
        super.onBackPressed();
    }
}