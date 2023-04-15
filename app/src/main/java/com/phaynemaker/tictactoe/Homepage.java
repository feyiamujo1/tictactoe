package com.phaynemaker.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    private Dialog exitDialog;

    private Button btnFriend;

    private Button dialogBtnYes, dialogBtnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btnFriend = findViewById(R.id.btnFriend);

        exitDialog = new Dialog(this);

        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, PlayerLogin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        exitDialog.setContentView(R.layout.exit_dialog);
        exitDialog.setCancelable(true);
        exitDialog.setCanceledOnTouchOutside(false);

        dialogBtnNo = (Button) exitDialog.findViewById(R.id.dialogBtnNo);

        dialogBtnYes = (Button) exitDialog.findViewById(R.id.dialogBtnYes);

        dialogBtnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Homepage.super.onBackPressed();
                Homepage.this.finish();
                System.exit(0);
            }
        });

        dialogBtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.cancel();
            }
        });

        exitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        exitDialog.show();
    }
}
