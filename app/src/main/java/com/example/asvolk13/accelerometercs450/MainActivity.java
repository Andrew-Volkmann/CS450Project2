package com.example.asvolk13.accelerometercs450;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    TextView z_accel_view = null;
    TextView x_accel_view = null;
    TextView y_accel_view = null;
    AccelerometerHandler ah = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.z_accel_view = (TextView) findViewById(R.id.z_accel_view);
        this.x_accel_view = (TextView) findViewById(R.id.x_accel_view);
        this.y_accel_view = (TextView) findViewById(R.id.y_accel_view);


    }

    @Override
    protected void onStart() {
        super.onStart();
        this.ah = new AccelerometerHandler(this);
        this.ah.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {

        float [] xyz = (float []) o;
        this.z_accel_view.setText(Float.toString(xyz[2]));
        this.x_accel_view.setText(Float.toString(xyz[0]));
        this.y_accel_view.setText(Float.toString(xyz[1]));
    }
}


