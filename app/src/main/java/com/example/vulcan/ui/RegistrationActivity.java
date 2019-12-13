package com.example.vulcan.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vulcan.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText etLogin;
    private TextInputEditText etEmail;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        initViews();

        btnRegister.setOnClickListener(registerListener);

    }

    private void initViews() {
        etLogin = findViewById(R.id.et_login);
        etEmail = findViewById(R.id.et_email);
        btnRegister = findViewById(R.id.registration_button);
    }

    View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboardFrom(RegistrationActivity.this, v);
            final Snackbar snackbarErr = Snackbar.make(v,setMassage(), Snackbar.LENGTH_INDEFINITE);
            snackbarErr.setAction("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbarErr.dismiss();
                }
            });
            snackbarErr.show();
        }
    };

    private String setMassage() {
        String msg;
        if (Objects.requireNonNull(etLogin.getText()).length() < 3 || Objects.requireNonNull(etEmail.getText()).length() < 3) {
            msg = "Некорректно указан login или email";
        } else {
            msg = "Ошибка сервера, попробуйте позже.";
        }
        return msg;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
