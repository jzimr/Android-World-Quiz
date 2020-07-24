package jz791.isit242.com.android_world_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_SESSION_NICKNAME = "sessionNickname";

    EditText nicknameET;
    Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nicknameET = findViewById(R.id.nicknameEditText);
        enterButton = findViewById(R.id.enterButton);

        nicknameET.addTextChangedListener(onTextListener);  // listen to text change
        enterButton.setOnClickListener(enterButtonClick);

        enterButton.setEnabled(false);  // disable button by default

        // set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }

    private View.OnClickListener enterButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
            intent.putExtra(EXTRA_SESSION_NICKNAME, nicknameET.getText().toString());
            startActivity(intent);
        }
    };

    /**
     check that the user enters a text before being able to enter main menu
     */
    private TextWatcher onTextListener = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(count > 0)
                enterButton.setEnabled(true);
            else
                enterButton.setEnabled(false);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void afterTextChanged(Editable s) { }
    };




}
