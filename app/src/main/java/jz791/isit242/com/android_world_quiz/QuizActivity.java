package jz791.isit242.com.android_world_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_QUESTION_TYPE = MainMenuActivity.EXTRA_QUESTION_TYPE;
    public static final String EXTRA_SESSION_NICKNAME = LoginActivity.EXTRA_SESSION_NICKNAME;
    public static final String EXTRA_SESSION_START = MainMenuActivity.EXTRA_SESSION_START;
    public static final String EXTRA_SESSION_POINTS = MainMenuActivity.EXTRA_SESSION_POINTS;
    public static final String EXTRA_CORRECT_ANSWERS = "correctAnswers";

    TextView questionText;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Toolbar toolbar;
    TextView toolbarQuesCount;

    private QuestionType questionType;
    private SessionInfo sessionInfo;

    private Question[] questionSet;
    private Question[] selectedQuestions;

    private int correct = 0;                // total correct questions
    private int qCounter = -1;              // which question we are on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        button1 = findViewById(R.id.optionButton1);
        button2 = findViewById(R.id.optionButton2);
        button3 = findViewById(R.id.optionButton3);
        button4 = findViewById(R.id.optionButton4);
        toolbar = findViewById(R.id.my_toolbar);
        toolbarQuesCount = findViewById(R.id.toolbar_questionCount);

        button1.setOnClickListener(answerButtonClick);
        button2.setOnClickListener(answerButtonClick);
        button3.setOnClickListener(answerButtonClick);
        button4.setOnClickListener(answerButtonClick);

        questionType = (QuestionType) getIntent().getSerializableExtra(EXTRA_QUESTION_TYPE);
        String nickname = getIntent().getStringExtra(EXTRA_SESSION_NICKNAME);
        long date = (long) getIntent().getSerializableExtra(EXTRA_SESSION_START);
        int sessionPoints = getIntent().getIntExtra(EXTRA_SESSION_POINTS, 0);

        // create our sessionInfo object
        sessionInfo = new SessionInfo(nickname, date, sessionPoints);

        // set the toolbar title to chosen quiz-area
        // and set the questionSet
        switch (questionType) {
            case geography:
                toolbar.setTitle("Geography");
                questionSet = Questions.geography;
                break;
            case history:
                toolbar.setTitle("History");
                questionSet = Questions.history;
                break;
            case literature:
                toolbar.setTitle("Literature");
                questionSet = Questions.literature;
                break;
        }

        correct = 0;
        qCounter = -1;
        selectedQuestions = new Question[10];

        // choose 10 questions randomly
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            boolean isValid = false;
            int randomNum;

            do {
                randomNum = (rnd.nextInt() & Integer.MAX_VALUE) % questionSet.length;
                if (i == 0) break;

                for (int j = 0; j < i; j++) {
                    // if question is duplicate
                    if (selectedQuestions[j].question == questionSet[randomNum].question) {
                        break;
                    } else if (j == i - 1)
                        isValid = true;
                }
            } while (!isValid);

            selectedQuestions[i] = questionSet[randomNum];  // copy random question over to selected questions
        }

        // set toolbar
        setSupportActionBar(toolbar);

        // load the first question
        nextQuestion();
    }

    /**
     * When user has chosen an answer
     */
    private View.OnClickListener answerButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button buttonClicked = (Button) v;
            processAnswer(buttonClicked.getText().toString());
        }
    };

    /**
     * Check if answer is correct and (if possible) go to next question. If reached
     * end go to results screen.
     *
     * @param btnText the text of the button clicked
     */
    private void processAnswer(String btnText) {
        if (isCorrectAnswer(btnText))
            correct++;
        if (qCounter < 9)
            nextQuestion();
        else {
            // go to results screen
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra(EXTRA_SESSION_NICKNAME, sessionInfo.nickname);
            intent.putExtra(EXTRA_SESSION_POINTS, sessionInfo.totalPoints);
            intent.putExtra(EXTRA_SESSION_START, sessionInfo.sessionStart);
            intent.putExtra(EXTRA_QUESTION_TYPE, questionType);
            intent.putExtra(EXTRA_CORRECT_ANSWERS, correct);
            startActivity(intent);
        }
    }

    private boolean isCorrectAnswer(String btnText) {
        if (btnText == selectedQuestions[qCounter].correctAns)
            return true;
        else
            return false;
    }

    private void nextQuestion() {
        qCounter++;

        // update toolbar title
        toolbarQuesCount.setText("Question " + (qCounter+1) + " of 10");

        Random rnd = new Random(); // creating Random object
        int randomPosition = ((rnd.nextInt() & Integer.MAX_VALUE) % 4) + 1;      // random position for the correct answer
        Question currQuestion = selectedQuestions[qCounter];

        String correctAnswer = currQuestion.correctAns;
        String wrongAnswer1 = currQuestion.wrongAns1;
        String wrongAnswer2 = currQuestion.wrongAns2;
        String wrongAnswer3 = currQuestion.wrongAns3;

        // randomize the position of answers for each run
        switch (randomPosition) {
            case 1:
                button1.setText(correctAnswer);
                button2.setText(wrongAnswer1);
                button3.setText(wrongAnswer2);
                button4.setText(wrongAnswer3);
                break;
            case 2:
                button1.setText(wrongAnswer1);
                button2.setText(correctAnswer);
                button3.setText(wrongAnswer2);
                button4.setText(wrongAnswer3);
                break;
            case 3:
                button1.setText(wrongAnswer1);
                button2.setText(wrongAnswer2);
                button3.setText(correctAnswer);
                button4.setText(wrongAnswer3);
                break;
            case 4:
                button1.setText(wrongAnswer1);
                button2.setText(wrongAnswer2);
                button3.setText(wrongAnswer3);
                button4.setText(correctAnswer);
                break;
            default:
                return;
        }
        questionText.setText(currQuestion.question);
    }
}
