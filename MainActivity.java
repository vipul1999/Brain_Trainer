package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView Results;
    TextView PointsTextView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView sumTextView;
    TextView timerText;
    Button playAgainButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;


    public void generateQuestions(){

            Random rand = new Random();
            int a = rand.nextInt(21);
            int b = rand.nextInt(21);
            sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
            int incorrectanswer;
            locationOfCorrectAnswer = rand.nextInt(4);
            answers.clear();

            for (int i = 0; i < 4; i++)
            {
                if (i == locationOfCorrectAnswer)
                {
                    answers.add(a + b);
                }
            else
                {
                incorrectanswer = rand.nextInt(41);
                while (incorrectanswer == a + b)
                    {
                        incorrectanswer = rand.nextInt(41);
                    }
                answers.add(incorrectanswer);

                }
            }
            button1.setText(Integer.toString(answers.get(0)));
            button2.setText(Integer.toString(answers.get(1)));
            button3.setText(Integer.toString(answers.get(2)));
            button4.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(View view){

            numberOfQuestions=0;
            score=0;

            timerText.setText("10s");
            PointsTextView.setText("0/0");
            Results.setText("See Here");

            playAgainButton.setVisibility(View.INVISIBLE);

            timerText.setVisibility(View.VISIBLE);
            sumTextView.setVisibility(View.VISIBLE);
            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.VISIBLE);
            PointsTextView.setVisibility(View.VISIBLE);
            generateQuestions();

            new CountDownTimer(10100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(millisUntilFinished/1000)+"s" );
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerText.setText("0s");
                Results.setText("Your Score is: "+ Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions*4));

                timerText.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                PointsTextView.setVisibility(View.INVISIBLE);


            }
            }.start();

    }


    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
              Results.setText("Correct");
              score=score+4;


        }
        else{
            score=score-2;
            Results.setText("Wrong");
        }
        numberOfQuestions++;
        PointsTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions*4));
        generateQuestions();
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Results=(TextView)findViewById(R.id.correct);
        startButton=(Button)findViewById(R.id.startButton);
        sumTextView=  (TextView)findViewById(R.id.sumText);
        PointsTextView=(TextView)findViewById(R.id.scoreText);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        timerText=(TextView)findViewById(R.id.timerText);
        playAgainButton=(Button) findViewById(R.id.playAgain);

        playAgain(findViewById(R.id.playAgain));



    }
}
