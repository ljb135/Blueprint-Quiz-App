package com.example.bp_fall_2021_quizapp;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class QuizQuestionActivity extends AppCompatActivity {

    // UI components here
    private TextView Question;
    private RadioGroup Group;
    private RadioButton Option_1;
    private RadioButton Option_2;
    private RadioButton Option_3;
    private RadioButton Option_4;
    private ProgressBar Progress_Bar;
    // other variables here
    private ArrayList<QuestionModel> question_list;
    private String name;
    private int question_num;
    private int score;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        // create arraylist of questions
        question_list = new ArrayList<QuestionModel>(4);

        // get username intent from main activity screen
        name = getIntent().getExtras().getString("Username");

        // initialize views using findViewByID
        Progress_Bar = findViewById(R.id.progress_bar);
        Group = findViewById(R.id.radio_group);
        Question = findViewById(R.id.question_text);
        Option_1 = findViewById(R.id.answer_1);
        Option_2 = findViewById(R.id.answer_2);
        Option_3 = findViewById(R.id.answer_3);
        Option_4 = findViewById(R.id.answer_4);

        // use helper method to add question content to arraylist
        addQuestions();
        // get total number of questions
        size = question_list.size();
        // set progress bar
        Progress_Bar.setProgress(0);
        // use helper method to proceed to next question
        question_num = 0;
        score = 0;

        showNextQuestion();
    }

    /**
     * Method that adds questions to our questions arraylist, using the Question Model constructor
     */
    private void addQuestions(){
        // question 1
        question_list.add(new QuestionModel("Which NBA player has won the most championships and how many did he win?","Bill Russell - 11", "Michael Jordan - 11", "Larry Bird - 13", "LeBron James - 12", 1));
        // question 2
        question_list.add(new QuestionModel("Which NBA player has scored the most points in a single game and how many points did he score?", "Jerry West - 223", "Wilt Chamberlain - 118", "Lebron James - 93", "Magic Johnson - 45", 2));
        // question 3
        question_list.add(new QuestionModel("Which NBA player has the lowest career free throw percentage?","Hakeem Olajuwon ", "Shaquille O’Neal", "Ben Wallace", "Brian Scalabrine", 3));
        // question 4
        question_list.add(new QuestionModel("How tall was the shortest NBA player to win a dunk contest?","5’6’’", "5’7’’", "5’9’’", "5’10’’", 1));
        // question 5
        question_list.add(new QuestionModel("Which NBA player ran the most in the past season?","Stephen Curry", "Fred Van Vleet", "Zach Lavine", "R.J. Barret", 2));
    }

    /**
     * Check the answer when user clicks submit and move on to next question
     */
    public void submitQuestion(View view){
        // if no options have been selected, prompt user to select an answer
        if(Group.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }
        // use helper methods to check the answer and show the next question
        checkAnswer();
        showNextQuestion();
    }

    /**
     * Display next question. If finished, move onto results screen
     */
    private void showNextQuestion(){

        // clear previous button selections
        Group.clearCheck();

        // if you haven't gone through all the questions yet
            // set the question & text to the next question
            // increase question number
            // set progress bar

        if(question_num < size) {
            Progress_Bar.setProgress(100 / size * question_num);
            question_num++;

            Question.setText(question_list.get(question_num - 1).getQuestion());
            Option_1.setText(question_list.get(question_num - 1).getOpt1());
            Option_2.setText(question_list.get(question_num - 1).getOpt2());
            Option_3.setText(question_list.get(question_num - 1).getOpt3());
            Option_4.setText(question_list.get(question_num - 1).getOpt4());
        }
        // if finished with quiz, start Results activity
        else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("Username", name);
            intent.putExtra("Score", score);
            startActivity(intent);
            finish(); // close current activity
        }

    }

    /**
     * Checks the answer and increases score if correct
     */
    private void checkAnswer(){
        // see which answer they picked
        int correct_choice = 0;
        switch(question_list.get(question_num - 1).getCorrectAnsNum()){
            case 0:
                correct_choice = Option_1.getId();
                break;
            case 1:
                correct_choice = Option_2.getId();
                break;
            case 2:
                correct_choice = Option_3.getId();
                break;
            case 3:
                correct_choice = Option_4.getId();
                break;
        }
        if(Group.getCheckedRadioButtonId() == correct_choice) {
            // increase score if correct
            score++;
        }
    }
}