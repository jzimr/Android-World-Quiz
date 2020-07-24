package jz791.isit242.com.android_world_quiz;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PointsListAdapter extends ArrayAdapter<SessionInfo> {
    private ArrayList<SessionInfo> mDataSet;

    private LayoutInflater mInflater;

    public PointsListAdapter(Context context, ArrayList<SessionInfo> dataSet) {
        super(context, R.layout.points_row_layout);
        this.mDataSet = new ArrayList<SessionInfo>(dataSet);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public SessionInfo getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getPosition(SessionInfo item) {
        return mDataSet.indexOf(item);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.points_row_layout, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.textView_name);
            holder.description = (TextView) convertView.findViewById(R.id.textView_description);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SessionInfo currSes = mDataSet.get(position);

        // get time
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currSes.sessionStart);
        String mYear = formatNumber(calendar.get(Calendar.YEAR));
        String mMonth = formatNumber(calendar.get(Calendar.MONTH));
        String mDay = formatNumber(calendar.get(Calendar.DAY_OF_MONTH));
        String mHour = formatNumber(calendar.get(Calendar.HOUR));
        String mMinute = formatNumber(calendar.get(Calendar.MINUTE));

        holder.name.setText(currSes.totalPoints + " points earned");
        holder.description.setText("on session " + mDay + "-" + mMonth + "-" + mYear + " " + mHour + ":" + mMinute);
        holder.pos = position;
        return convertView;
    }

    /**
     * Converts numbers 0, 1, 2, ..., 9 to two digits 00, 01, 02, ..., 09
     * @param number integer to convert
     * @return formatted string
     */
    String formatNumber(int number) {
        String formatted = "";

        if(number < 10)
            formatted = "0" + number;
        else
            formatted = String.valueOf(number);

        return formatted;
    }


    //---------------static views for each row-----------//
    static class ViewHolder {
        TextView name;
        TextView description;
        int pos; //to store the position of the item within the list
    }
}
