package com.luniun.luniun.time_taf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.luniun.luniun.time_taf.R;
import com.luniun.luniun.time_taf.WorkEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<WorkEntry> workEntries = new ArrayList<>();
    private double hourlyRate = 11.52; // Remplacez cela par votre taux horaire

    private EditText editTextHoursWorked;
    private TextView textViewTotalHours;
    private TextView textViewMonthlyEarnings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHoursWorked = findViewById(R.id.editTextHoursWorked);
        textViewTotalHours = findViewById(R.id.textViewTotalHours);
        textViewMonthlyEarnings = findViewById(R.id.textViewMonthlyEarnings);

        Button buttonAddWork = findViewById(R.id.buttonAddWork);
        buttonAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWorkEntry();
            }
        });
    }

    private void addWorkEntry() {
        String hoursWorkedStr = editTextHoursWorked.getText().toString();
        if (!hoursWorkedStr.isEmpty()) {
            int hoursWorked = Integer.parseInt(hoursWorkedStr);
            WorkEntry workEntry = new WorkEntry(hoursWorked);
            workEntries.add(workEntry);

            updateUI();
        }
    }

    private void updateUI() {
        // Calculer le total d'heures travaillées
        int totalHours = 0;
        for (WorkEntry entry : workEntries) {
            totalHours += entry.getHoursWorked();
        }
        textViewTotalHours.setText(getString(R.string.total_hours, totalHours));

        // Calculer le salaire mensuel
        double monthlyEarnings = totalHours * hourlyRate;
        textViewMonthlyEarnings.setText(getString(R.string.monthly_earnings, monthlyEarnings));
    }
}
