package com.phaynemaker.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerLogin extends AppCompatActivity {

    Button btnSubmit;
    EditText etHex, etZero;
    TextView tvCaution1, tvCaution2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_login);

        btnSubmit = findViewById(R.id.btnSubmit);
        etZero = findViewById(R.id.etZero);
        etHex = findViewById(R.id.etHex);

        tvCaution1 = findViewById(R.id.tvCaution1);
        tvCaution2 = findViewById(R.id.tvCaution2);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etHex.getText().toString().isEmpty() || etZero.getText().toString().isEmpty()){
                    if (etHex.getText().toString().isEmpty()){
                        tvCaution1.setText("!");
                    }
                    else{
                        tvCaution1.setText("  ");
                    }
                    if (etZero.getText().toString().isEmpty()){
                        tvCaution2.setText("!");
                    }
                    else{
                        tvCaution2.setText("  ");
                    }
                }
                else {
                    String player2 = etZero.getText().toString().trim();
                    String player1 = etHex.getText().toString().trim();

                    Intent intent = new Intent(PlayerLogin.this, com.phaynemaker.tictactoe.MainActivity.class);
                    intent.putExtra("player1", player1);
                    intent.putExtra("player2", player2);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
