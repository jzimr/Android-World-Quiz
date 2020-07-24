package jz791.isit242.com.android_world_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "usersession.db";
    public static final String TABLE_NAME = "sessions";
    public static final String COL_1 = "NICKNAME";
    public static final String COL_2 = "START_TIME";
    public static final String COL_3 = "POINTS";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(NICKNAME TEXT, START_TIME INTEGER, POINTS INTEGER, " +
                "PRIMARY KEY (NICKNAME, START_TIME));");
    }
    // called when the database needs to be upgraded to the new schema version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    /**
     * add a new record into database
     * @param nickname nickname of user
     * @param dateInMillis time in milliseconds
     * @param totalPoints total amount of points in session
     * @return
     */
    public boolean insertData(String nickname, long dateInMillis, int totalPoints) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nickname);
        contentValues.put(COL_2, dateInMillis);
        contentValues.put(COL_3, totalPoints);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    /**
     * Get all records of database
     * @return
     */
    public Cursor viewAllRecords() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    /**
     * Get all records of a nickname
     * @param nickname nickname of whose records to retrieve
     * @return cursor to database of records
     */
    public Cursor viewRecord(String nickname) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE NICKNAME='" +nickname + "'", null);
        return res;
    }

    /**
     * Get the record of a nickname bound to a specific datetimme
     * @param nickname nickname of user
     * @param dateInMillis time in milliseconds
     * @return
     */
    public Cursor viewRecord(String nickname, long dateInMillis) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE NICKNAME='" + nickname + "' AND " + "START_TIME=" + dateInMillis + "", null);
        return res;
    }

    /**
     * updating the record matching the id
     * @param nickname nickname of user
     * @param dateInMillis time in milliseconds
     * @param totalPoints total amount of points in session
     * @return
     */
    public boolean updateRecord(String nickname, long dateInMillis, int totalPoints){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // update points
        String strSQL = "UPDATE " + TABLE_NAME + " SET " + COL_3 + "=" + totalPoints +
                " WHERE " + COL_1 + "='" + nickname + "' AND " + COL_2 + "=" + dateInMillis + ";";

        try{
            sqLiteDatabase.execSQL(strSQL);
        } catch(SQLException e){
            System.out.print(e.getMessage());
            return false;
        }
        return true;
    }
}
