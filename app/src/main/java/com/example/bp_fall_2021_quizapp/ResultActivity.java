package com.example.bp_fall_2021_quizapp;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    // UI component variables
    private TextView Result_Text;
    private TextView Score_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // initialize UI components
        Result_Text = findViewById(R.id.result_text);
        Score_Text = findViewById(R.id.score_text);

        // set username and score
        String name = getIntent().getExtras().getString("Username");
        int score = getIntent().getExtras().getInt("Score");

        Result_Text.setText("Congratulations " + name + "!");
        Score_Text.setText("Your final score was " + score + "/5.\nThink you can do better? Click the button below to try again.");
    }

    /**
     * Restarts the quiz so you can play another round
     * @param view
     */
    public void restart(View view) {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
    }
}