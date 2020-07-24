package jz791.isit242.com.android_world_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    public static final String EXTRA_QUESTION_TYPE = "questionType";
    public static final String EXTRA_SESSION_NICKNAME = LoginActivity.EXTRA_SESSION_NICKNAME;
    public static final String EXTRA_SESSION_START = "sessionStart";
    public static final String EXTRA_SESSION_POINTS = "sessionPoints";

    Button geographyButton;
    Button historyButton;
    Button literatureButton;
    Button earnedPointsButton;
    Button exitAppButton;

    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        nickname = getIntent().getStringExtra(LoginActivity.EXTRA_SESSION_NICKNAME);

        geographyButton = findViewById(R.id.historyButton);
        historyButton = findViewById(R.id.geographyButton);
        literatureButton = findViewById(R.id.literatureButton);
        earnedPointsButton = findViewById(R.id.earnedPointsButton);
        exitAppButton = findViewById(R.id.exitAppButton);

        geographyButton.setOnClickListener(onStartQuizClick);
        historyButton.setOnClickListener(onStartQuizClick);
        literatureButton.setOnClickListener(onStartQuizClick);
        earnedPointsButton.setOnClickListener(onEarnedPointsClick);
        exitAppButton.setOnClickListener(onExitAppClick);

        // set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Click listener for starting the quiz
     */
    View.OnClickListener onStartQuizClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // prepare intent for quiz
            Intent quizIntent = new Intent(MainMenuActivity.this, QuizActivity.class);
            quizIntent.putExtra(EXTRA_SESSION_NICKNAME, nickname);
            quizIntent.putExtra(EXTRA_SESSION_START, System.currentTimeMillis());
            quizIntent.putExtra(EXTRA_SESSION_POINTS, 0);

            switch(v.getId()){
                case R.id.geographyButton:
                    quizIntent.putExtra(EXTRA_QUESTION_TYPE, QuestionType.geography); break;
                case R.id.historyButton:
                    quizIntent.putExtra(EXTRA_QUESTION_TYPE, QuestionType.history); break;
                case R.id.literatureButton:
                    quizIntent.putExtra(EXTRA_QUESTION_TYPE, QuestionType.literature); break;
                default:
                    return;
            }
            startActivity(quizIntent);
        }
    };

    View.OnClickListener onEarnedPointsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainMenuActivity.this, PointsActivity.class);
            intent.putExtra(EXTRA_SESSION_NICKNAME, nickname);
            startActivity(intent);
        }
    };

    View.OnClickListener onExitAppClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    };
}
