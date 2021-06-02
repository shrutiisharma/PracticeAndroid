package com.example.helloworld;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.databinding.ActivityDialogsDemoBinding;
import com.example.helloworld.databinding.DialogAddImageBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogsDemoActivity extends AppCompatActivity {

    ActivityDialogsDemoBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityDialogsDemoBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("Dialogs Demo");

        //showCustomThemeDialog();
        //showCustomViewDialog();
        //showSimpleDialog();

        //Log.e("ABC","Shruti");
    }

    private void showSimpleDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Congratulations!")
                .setMessage("You won a lottery of Rs. 1 Crore.")
                .setCancelable(false)
                .setPositiveButton("REDEEM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogsDemoActivity.this, "You redeemed!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("DISAGREE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogsDemoActivity.this, "You disagreed!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("PROVE IT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogsDemoActivity.this, "You asked for a proof!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showCustomViewDialog(){
        DialogAddImageBinding binding = DialogAddImageBinding
                .inflate(getLayoutInflater());

        new MaterialAlertDialogBuilder(DialogsDemoActivity.this)
                .setView(binding.getRoot())
                .show();

        //Use binding reference to bind data, set event handlers
    }

    private void showCustomThemeDialog(){
        new MaterialAlertDialogBuilder(DialogsDemoActivity.this, R.style.CustomDialogTheme)
                .setTitle("Congratulations!")
                .setMessage("You won a lottery of Rs. 1 Crore.")
                .show();
    }
}