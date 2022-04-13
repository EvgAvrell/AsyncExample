package com.example.asyncexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class AsyncTaskExample extends AppCompatActivity {

    private TextView mInfoTextView;
    private ProgressBar mProgressBar;
    private Button mStartButton;
    private ProgressBar mHorizontalProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);

        mInfoTextView = (TextView) findViewById(R.id.text_info);
        mStartButton = (Button) findViewById(R.id.button_start);
        mHorizontalProgressBar = findViewById(R.id.progressBar);
    }


    public void onClick(View v) {
        CurierTask curierTask = new CurierTask();
        curierTask.execute();
    }

    class CurierTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            mInfoTextView.setText("Доставщик зашел в ваш дом");
            mStartButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mInfoTextView.setText("Этаж" + values[0]);
            mHorizontalProgressBar.setProgress(values[0]);
        }

        @Override
        protected Integer doInBackground(Void... voids){
            try {
                int numerofFlors = 14;
                int counter = 0;
                for (int i = 0; i<numerofFlors; i++){
                    getFloor(counter);
                    publishProgress(++counter);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
           mInfoTextView.setText("Звонок в дверь");
           mStartButton.setVisibility(View.VISIBLE);
           mHorizontalProgressBar.setProgress(0);
        }
    }

    private void getFloor(int counter) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

}
