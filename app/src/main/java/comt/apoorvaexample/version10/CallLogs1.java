package comt.apoorvaexample.version10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

public class CallLogs1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_logs1);

        if(ContextCompat.checkSelfPermission(CallLogs1.this ,
                Manifest.permission.READ_CALL_LOG )!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(CallLogs1.this ,
                    Manifest.permission.READ_CALL_LOG)){
                ActivityCompat.requestPermissions(CallLogs1.this,
                        new String[]{Manifest.permission.READ_CALL_LOG} , 1);
            }else{
                ActivityCompat.requestPermissions(CallLogs1.this,
                        new String[]{Manifest.permission.READ_CALL_LOG} , 1);
            }

        }else{

            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(getCallDetails());

        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        switch (requestCode){
            case 1: {
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(CallLogs1.this,
                            Manifest.permission.READ_CALL_LOG)== PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(this, "Permission granted" , Toast.LENGTH_LONG).show();
                        TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setText(getCallDetails());

                    }

                }else {
                    Toast.makeText(this, "Permission denied" , Toast.LENGTH_LONG).show();
                }
                return;
            }

        }


    }
    @SuppressLint("DefaultLocale")
    private  String getCallDetails() {
        StringBuffer ab = new StringBuffer();
        Cursor managecursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managecursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managecursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managecursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managecursor.getColumnIndex(CallLog.Calls.DURATION);
        //ab.append("Call Details :\n\n");
        Integer callDuration = 0;
        Integer total = 0;
        String dir = null;
        int num =0;
        int[] arr=new int[1000];
        int i=0;





        while (managecursor.moveToNext()) {
            String phNumber = managecursor.getString(number);
            num++;
            String callType = managecursor.getString(type);
            String callDate = managecursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
            String dateString = formatter.format(callDayTime);
            callDuration = managecursor.getInt(duration);
            total = callDuration+total;

            arr[i++] = callDuration;








            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;


            }

            //ab.append("callduration:" + callDuration +"\n");
            //ab.append("\n");

        }



        int big=arr[0];

        for( i=0;i<=num;i++){
            if(arr[i] >= big){
                big=arr[i];
                System.out.print(arr[i]);

            }}



        //  ab.append(" \n Phone Number :" +phNumber + " \nCallType :" +dir + " \nCall Date : " + dateString + " \n Call Duration :" + callDuration);
        ab.append("Call Logs \n\n\n");
        ab.append("Last call duration : \t" +callDuration +"\tsec");
        ab.append("\n\n\n\n");
        ab.append("Today's overall call duration : \t " + total+ "\tsec");
        ab.append("\n\n\n\n");

        ab.append("Max time spendable: \t"  );
        ab.append("\n\n\n\n");
        ab.append("Longest call : \t"  +big);





        managecursor.close();
        return ab.toString();

    }
    }
