package com.phaynemaker.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] btn = new Button[3][3];
    private Button btnNewGame;
    private Dialog winnerDialog;
    private TextView tvWinner;
    private String player1;
    private String player2;
    private ImageView ivHome;


    private TextView tvPlayer1, tvPlayer2, tvRounds;

    private ImageView ivReset;
    private int playCount;
    private int rounds = 1;
    private String winner;

    private Boolean player1Turn = true;

    private int player1Points = 0;
    private int player2Points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = getIntent().getStringExtra("player1");
        player2 = getIntent().getStringExtra("player2");

        winnerDialog = new Dialog(this);

        for (int i =0; i<3; i++){
            for (int j =0; j<3; j++){
                String buttonID = "btn"+i+j;

                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                btn[i][j]=findViewById(resID);
                btn[i][j].setOnClickListener(this);
            }
        }

        tvPlayer1 =findViewById(R.id.tvPlayer1);
        tvPlayer2 = findViewById(R.id.tvPlayer2);
        tvRounds =findViewById(R.id.tvRounds);
        ivHome = findViewById(R.id.ivHome);

        ivReset =findViewById(R.id.ivReset);

        tvPlayer1.setText(player1 + ": " + player1Points);
        tvPlayer2.setText(player2 + ": " + player2Points);

        ivReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
                tvPlayer1.setText(player1 + ": 0");
                tvPlayer2.setText(player2 + ": 0");
                rounds=1;
                tvRounds.setText("ROUND: "+ rounds);
                player2Points=0;
                player1Points = 0;
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Homepage.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            //Toast.makeText(this, "Button "+((Button)v).toString(), Toast.LENGTH_SHORT).show();
            ((Button)v).setTextColor(getResources().getColor(R.color.colorAccent));
            ((Button)v).setText("X");
        }
        else
            {
                //Toast.makeText(this, "Button "+ ((Button)v).toString(), Toast.LENGTH_SHORT).show();
                ((Button)v).setTextColor(getResources().getColor(R.color.colorp2));
                ((Button)v).setText("O");
            }
        playCount++;

        if (checkForWin()) {
            if (player1Turn) {
                rounds++;
                player1wins();
            } else {
                player2wins();
                rounds++;
            }
        }else if (playCount==9){
            rounds ++;
            drawGame();
        }else
            {
                player1Turn = !player1Turn;
            }
    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for (int i =0; i<3;i++){
               for (int j =0; j<3; j++){
                field[i][j] = btn[i][j].getText().toString();
            }
        }

        for (int i =0; i<3;i++){
            if (!(field[i][0].equals("")) && field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])){
                return true;
            }
        }

        for (int i =0; i<3; i++){
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void player1wins(){
        //Toast.makeText(this, "Player 1 Wins!!!!!", Toast.LENGTH_SHORT).show();
        player1Points ++;
        tvPlayer1.setText(player1 + ": " + player1Points);
        winner = "p1";
        showWinnerpopup();
        //resetBoard();

    }

    private void player2wins(){
        //Toast.makeText(this, "Player 2 Wins!!!!!", Toast.LENGTH_SHORT).show();
        player2Points++;
        tvPlayer2.setText(player2 + ": " + player2Points);
        winner = "p2";
        showWinnerpopup();
        //resetBoard();
    }

    private void drawGame(){
        //Toast.makeText(this, "Draw!!!!!!", Toast.LENGTH_SHORT).show();
        winner = "draw";
        showWinnerpopup();

    }

    private void resetBoard(){
        for (int i =0; i<3;i++){
            for (int j =0; j<3; j++){
                btn[i][j].setText("");
            }
        }
        tvRounds.setText("ROUND: "+ rounds);
        player1Turn = true;
        playCount =0;
    }

    public void showWinnerpopup(){
        winnerDialog.setContentView(R.layout.winner_dialogtwo);
        winnerDialog.setCanceledOnTouchOutside(false);
        winnerDialog.setCancelable(false);

        btnNewGame = (Button) winnerDialog.findViewById(R.id.btnNewGame);
        tvWinner = (TextView) winnerDialog.findViewById(R.id.tvWinner);
        switch (winner){
            case "p1":
                tvWinner.setText(player1+ " Wins!");
                break;
            case "p2":
                tvWinner.setText(player2 +" Wins!");
                break;
            case "draw":
                tvWinner.setText("Draw!!");
                break;
        }

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winnerDialog.dismiss();

            }
        });
        winnerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        winnerDialog.show();
        resetBoard();
    }

    @Override
    public void onBackPressed() {

    }
}
