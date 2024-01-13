import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
    
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String HOURLY_RATE_KEY = "hourlyRate";
    private static final String DAILY_BONUS_KEY = "dailyBonus";
    private static final String TOTAL_HOURS_KEY = "totalHours";

    private EditText hoursWorkedEditText;
    private TextView monthlySalaryTextView;
    private double hourlyRate;
    private double dailyBonus;
    private double totalHoursWorked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoursWorkedEditText = findViewById(R.id.hoursWorkedEditText);
        monthlySalaryTextView = findViewById(R.id.monthlySalaryTextView);
        Button calculateButton = findViewById(R.id.calculateButton);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        hourlyRate = prefs.getFloat(HOURLY_RATE_KEY, 0.0f);
        dailyBonus = prefs.getFloat(DAILY_BONUS_KEY, 0.0f);
        totalHoursWorked = prefs.getFloat(TOTAL_HOURS_KEY, 0.0f);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double hoursWorked = Double.parseDouble(hoursWorkedEditText.getText().toString());
                totalHoursWorked += hoursWorked;

                double dailyEarnings = (hoursWorked * hourlyRate) + dailyBonus;
                double monthlySalary = totalHoursWorked * hourlyRate + (dailyBonus * 30); // Assuming a month has 30 days

                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                String formattedSalary = currencyFormat.format(monthlySalary);

                monthlySalaryTextView.setText(getString(R.string.monthly_salary, formattedSalary));

                // Save data for future sessions
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                editor.putFloat(HOURLY_RATE_KEY, (float) hourlyRate);
                editor.putFloat(DAILY_BONUS_KEY, (float) dailyBonus);
                editor.putFloat(TOTAL_HOURS_KEY, (float) totalHoursWorked);
                editor.apply();
            }
        });
    }
}
