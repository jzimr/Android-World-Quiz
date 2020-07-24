package jz791.isit242.com.android_world_quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultsActivity extends AppCompatActivity {
    public static final String EXTRA_QUESTION_TYPE = MainMenuActivity.EXTRA_QUESTION_TYPE;
    public static final String EXTRA_SESSION_NICKNAME = LoginActivity.EXTRA_SESSION_NICKNAME;
    public static final String EXTRA_SESSION_START = MainMenuActivity.EXTRA_SESSION_START;
    public static final String EXTRA_SESSION_POINTS = MainMenuActivity.EXTRA_SESSION_POINTS;
    public static final String EXTRA_CORRECT_ANSWERS = QuizActivity.EXTRA_CORRECT_ANSWERS;

    Database sessionDB;

    TextView resultsText;
    Button restartButton;
    Button areaButton1;
    Button areaButton2;
    Button finishButton;

    QuestionType questionType;
    QuestionType questionTypeArea1;
    QuestionType questionTypeArea2;

    int correct = 0;
    int currentPoints = 0;

    SessionInfo sessionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        sessionDB = new Database(this);

        resultsText = findViewById(R.id.resultsText);
        restartButton = findViewById(R.id.restartButton);
        areaButton1 = findViewById(R.id.areaButton1);
        areaButton2 = findViewById(R.id.areaButton2);
        finishButton = findViewById(R.id.finishButton);

        restartButton.setOnClickListener(onNewAttemptClick);
        areaButton1.setOnClickListener(onNewAttemptClick);
        areaButton2.setOnClickListener(onNewAttemptClick);
        finishButton.setOnClickListener(onFinishSessionClick);

        questionType = (QuestionType) getIntent().getSerializableExtra(EXTRA_QUESTION_TYPE);
        correct = getIntent().getIntExtra(EXTRA_CORRECT_ANSWERS, 0);
        String nickname = getIntent().getStringExtra(EXTRA_SESSION_NICKNAME);
        long date = (long) getIntent().getSerializableExtra(EXTRA_SESSION_START);
        int sessionPoints = getIntent().getIntExtra(EXTRA_SESSION_POINTS, 0);

        // create our sessionInfo object
        sessionInfo = new SessionInfo(nickname, date, sessionPoints);

        // calculate total points
        currentPoints = (correct * 5) - ((10 - correct) * 2);
        sessionInfo.totalPoints += currentPoints;

        resultsText.setText("Well done " + sessionInfo.nickname + ", you have " + correct + " correct and " + (10 - correct) + " incorrect answers.\n" +
                "You earned " + currentPoints + " points for this attempt.\n" +
                "Overall in this session, you have " + sessionInfo.totalPoints + " points");

        // change name of the newArea buttons to display other areas the user can choose from
        switch (questionType) {
            case geography:
                areaButton1.setText("History");
                questionTypeArea1 = QuestionType.history;
                areaButton2.setText("Literature");
                questionTypeArea2 = QuestionType.literature;
                break;
            case history:
                areaButton1.setText("Geography");
                questionTypeArea1 = QuestionType.geography;
                areaButton2.setText("Literature");
                questionTypeArea2 = QuestionType.literature;
                break;
            case literature:
                areaButton1.setText("Geography");
                questionTypeArea1 = QuestionType.geography;
                areaButton2.setText("History");
                questionTypeArea2 = QuestionType.history;
                break;
        }

        // set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // we want to save/update data every time the user finishes an attempt
        saveSessionToDatabase();
    }

    private void saveSessionToDatabase(){
        // check if session already exists in database
        //SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        //String date = sdf.format(sessionInfo.sessionStart);     // date as string in format: YYYY-MM-DD HH:MM:SS
        long date = sessionInfo.sessionStart;
        Cursor res = sessionDB.viewRecord(sessionInfo.nickname, date);

        // this session does not exist in database yet
        if(res.getCount() == 0){
            boolean isInserted = sessionDB.insertData(sessionInfo.nickname, date, sessionInfo.totalPoints);

            if (isInserted)
                System.out.println("SESSION ADDED!");
            else
                System.out.println("SESSION COULD NOT BE ADDED :(");
        }
        else if(res.getCount() > 0){
            boolean isUpdated = sessionDB.updateRecord(sessionInfo.nickname, date, sessionInfo.totalPoints);

            if (isUpdated)
                System.out.println("SESSION UPDATED!");
            else
                System.out.println("SESSION COULD NOT BE UPDATED :(");
        }
    }

    View.OnClickListener onNewAttemptClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ResultsActivity.this, QuizActivity.class);
            intent.putExtra(EXTRA_SESSION_NICKNAME, sessionInfo.nickname);
            intent.putExtra(EXTRA_SESSION_START, sessionInfo.sessionStart);
            intent.putExtra(EXTRA_SESSION_POINTS, sessionInfo.totalPoints);

            switch(v.getId()){
                case R.id.restartButton:
                    intent.putExtra(EXTRA_QUESTION_TYPE, questionType);
                    break;
                case R.id.areaButton1:
                    intent.putExtra(EXTRA_QUESTION_TYPE, questionTypeArea1);
                    break;
                case R.id.areaButton2:
                    intent.putExtra(EXTRA_QUESTION_TYPE, questionTypeArea2);
                    break;
                default:
                    return;
            }

            startActivity(intent);
        }
    };

    View.OnClickListener onFinishSessionClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(ResultsActivity.this, MainMenuActivity.class);
            intent.putExtra(EXTRA_SESSION_NICKNAME, sessionInfo.nickname);

            AlertDialog alertDialog = new AlertDialog.Builder(ResultsActivity.this).create();
            alertDialog.setTitle("End of Session");
            alertDialog.setMessage("Well done " + sessionInfo.nickname + ", in this session you had " + sessionInfo.totalPoints + " points");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Main Menu",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    };
}
