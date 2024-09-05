package com.example.bandeirasquiz;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciarQuiz;
    private Button btnFecharQuiz;
    private EditText etPlayerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnIniciarQuiz = findViewById(R.id.btn_iniciar);
        btnFecharQuiz = findViewById(R.id.btn_sair);
        etPlayerName = findViewById(R.id.edt_nome);

        btnIniciarQuiz.setOnClickListener(view -> {
            String playerName = etPlayerName.getText().toString();
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("NOME_USUARIO", playerName); // Passa o nome do usuário para a QuizActivity
            startActivity(intent);
            finish();
        });

        btnFecharQuiz.setOnClickListener(view -> finish());

        btnIniciarQuiz.setEnabled(false);

        etPlayerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnIniciarQuiz.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });



    }
}