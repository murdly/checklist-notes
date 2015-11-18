package com.example.arkadiuszkarbowy.tasklog.view.adapters.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arkadiuszkarbowy.tasklog.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arkadiuszkarbowy on 18/11/15.
 */
public class DateTimeViewHolder {
    @Bind(R.id.icon)
    ImageView mIcon;
    @Bind(R.id.dateAndTime)
    TextView mDateTime;

    SimpleDateFormat mFormat = new SimpleDateFormat("EEE, MMM d 'o' HH:mm", Locale.getDefault());

    public DateTimeViewHolder(View layout) {
        ButterKnife.bind(this, layout);
    }

    public void setIcon(int id){
        mIcon.setImageResource(id);
    }

    public void setDateTime(Date date){
        mDateTime.setText(mFormat.format(date));
    }
}