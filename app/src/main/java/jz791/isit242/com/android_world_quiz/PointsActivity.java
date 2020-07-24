package jz791.isit242.com.android_world_quiz;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PointsActivity extends AppCompatActivity {
    public static final String EXTRA_SESSION_NICKNAME = LoginActivity.EXTRA_SESSION_NICKNAME;

    private ArrayList<SessionInfo> sessions;
    private PointsListAdapter mAdapter;
    private TextView resultsText;
    private ListView pointsListView;

    Database sessionDB;
    String nickname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        sessionDB = new Database(this);
        sessions = new ArrayList<>();
        resultsText = findViewById(R.id.resultsText);
        pointsListView = findViewById(R.id.pointsListView);
        nickname = getIntent().getStringExtra(EXTRA_SESSION_NICKNAME);

        int totalTotalPoints = 0;   // count user's points from all sessions
        // get data from database and fill into arraylist
        Cursor res = sessionDB.viewRecord(nickname);
        res.moveToFirst();
        for(int i = 0; i < res.getCount(); i++){
            String nickname = res.getString(0);
            long dateInMillis = res.getLong(1);
            int totalPoints = res.getInt(2);
            sessions.add(new SessionInfo(nickname, dateInMillis, totalPoints));
            totalTotalPoints += totalPoints;

            res.moveToNext();
        }
        this.mAdapter = new PointsListAdapter(this, sessions);
        pointsListView.setAdapter(mAdapter);
        //setListAdapter(mAdapter);


        // set the top text
        if(sessions.size() == 0) {
            resultsText.setText("Nothing to show here yet :(\nStart playing!");
        } else {
            resultsText.setText("Hello " + nickname + ", you have earned " +  totalTotalPoints + " points in total for these sessions");
        }


        // set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * Called when user selects an item on the toolbar (back button in this case)
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
