package comt.apoorvaexample.version10;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class StepCounter1 extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    TextView tv_steps;

    boolean running = false;
    long lStartTime;
    long output;
    long lEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter1);

        lStartTime = System.nanoTime();


        TextView stepsLeft = (TextView) findViewById(R.id.stepsLeft);


        int stepsleft = Integer.parseInt(String.valueOf(stepsLeft.getText()));


        TextView target = (TextView) findViewById(R.id.target);



        int totaltarget = Integer.parseInt(String.valueOf(target.getText()) );
        TextView tv_info = (TextView) findViewById(R.id.tv_info);



        int tvsteps= Integer.parseInt(String.valueOf(tv_info.getText()));

        TextView calories = (TextView) findViewById(R.id.calories);

        int caloriesBurnt = Integer.parseInt(String.valueOf(calories.getText()));

        stepsleft = totaltarget - tvsteps;

        String apoo= Integer.toString(stepsleft);
        stepsLeft.setText(apoo);

        caloriesBurnt =  tvsteps*(10000/500);


        TextView tv_steps = (TextView) findViewById(R.id.tv_steps);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        long lEndTime = System.nanoTime();

        long output = lEndTime - lStartTime;

        //Log.i("test" , String.valueOf(output));

        TextView time = (TextView) findViewById(R.id.time);
        int timer = Integer.parseInt(String.valueOf(time.getText()));

        timer = (int) output;



    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!= null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);

        }else{
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        boolean running = false;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(running){
            tv_steps.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}

