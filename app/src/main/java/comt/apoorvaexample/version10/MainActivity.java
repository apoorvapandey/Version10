package comt.apoorvaexample.version10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public  void callLogs(View view){
        Intent intent = new Intent(getApplicationContext(), CallLogs1.class);
        startActivity(intent);
    }
    public  void dataUsage(View view){
        Intent intent = new Intent(getApplicationContext(), DataUsage1.class);
        startActivity(intent);
    }
    public  void stepCount(View view){
        Intent intent = new Intent(getApplicationContext(), StepCounter1.class);
        startActivity(intent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
