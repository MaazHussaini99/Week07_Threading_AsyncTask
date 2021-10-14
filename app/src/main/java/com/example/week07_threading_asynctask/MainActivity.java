package com.example.week07_threading_asynctask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.AsynchronousChannelGroup;

public class MainActivity extends AppCompatActivity {


    private ProgressBar mProgressBar;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);
        mTv = (TextView)findViewById(R.id.smrTv);

        DownloadTask dt = new DownloadTask();
        /*dt.execute("https://google.com",
                "https://wikipidia.com",
                "http://www.farmingdale.edu");*/

        mTv.setOnClickListener(this::enableAlert);
    }

    private void enableAlert(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Maaz Hussaini");
        alert.setPositiveButton("Ok", (v, a)->{
            Toast.makeText(MainActivity.this, "You clicked Ok on the alert",
                    Toast.LENGTH_LONG).show();
        });
        alert.create().show();

    }


    private boolean downloadURL(String URL){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    class DownloadTask extends AsyncTask<String, Integer, Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setProgress(0);
        }

        @Override
        protected Integer doInBackground(String... url) {

            int downloadSuccess = 0;
            for (int i = 0; i<url.length; i++){
                if (downloadURL(url[i])){
                    downloadSuccess++;
                }

                publishProgress((i+1)*100/url.length);
            }
            return downloadSuccess;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            mTv.setText("Done");

            if (integer > 2){
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }

        }
    }
}