package com.example.utspemrogramanmobile;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private EditText editNIK;
    private TextView editTanggalLahir;
    private TextView editUsia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNIK = findViewById(R.id.editNIK);
        editTanggalLahir = findViewById(R.id.editTanggalLahir);
        editUsia = findViewById(R.id.editUsia);
        datePicker = findViewById(R.id.datePicker);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        editTanggalLahir.setText(day + "/" + (month + 1) + "/" + year);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editTanggalLahir.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                int age = calculateAge(year, monthOfYear, dayOfMonth);
                editUsia.setText("Usia: " + age);
            }
        });

        editNIK.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validateNIKLength(editNIK.getText().toString());
            }
        });
    }

    private int calculateAge(int year, int month, int day) {
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    private void validateNIKLength(String nik) {
        if (nik.length() != 16) {
            Toast.makeText(this, "Panjang NIK harus 16 digit", Toast.LENGTH_SHORT).show();
        }
    }
}
