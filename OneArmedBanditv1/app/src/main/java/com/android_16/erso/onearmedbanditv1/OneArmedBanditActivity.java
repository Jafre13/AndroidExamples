package com.android_16.erso.onearmedbanditv1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class OneArmedBanditActivity extends Activity {

    private Button start;
    private AnimationDrawable animation1, animation2, animation3;
    private ImageView imageView1, imageView2, imageView3;
    private Button stop1, stop2, stop3;

    private Button betButton;

    private TextView betTextView;
    private BanditApplication application;

    private Handler stopHandler;
    private Runnable stopRunner;
    private Random randomGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_armed_bandit);

        betTextView = (TextView) findViewById(R.id.betTextView);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        animation1 = (AnimationDrawable) imageView1.getBackground();

        imageView2 = (ImageView) findViewById(R.id.imageView2);
        animation2 = (AnimationDrawable) imageView2.getBackground();

        imageView3 = (ImageView) findViewById(R.id.imageView3);
        animation3 = (AnimationDrawable) imageView3.getBackground();


        start = (Button) findViewById(R.id.buttonStart);

        stop1 = (Button) findViewById(R.id.button1);
        stop2 = (Button) findViewById(R.id.button2);
        stop3 = (Button) findViewById(R.id.button3);

        betButton = (Button)findViewById(R.id.betButton);
        application = (BanditApplication) getApplication();
        application.setBet(1);

        betTextView.setText("C Indsats: " + (application == null ? "null" : application.getBet()));
        //betTextView.setText("C Indsats: " + (application == null));
        stopHandler = new Handler(){
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        if(stop1.isEnabled()) {
                            System.out.println("Stop 1");
                            stop1.performClick();
                        }
                        break;
                    case 2:
                        if(stop2.isEnabled()) {
                            System.out.println("Stop 2");
                            stop2.performClick();
                        }
                        break;
                    case 3:
                        if(stop3.isEnabled()) {
                            System.out.println("Stop 3");
                            stop3.performClick();
                        }
                        break;
                }
            }
       };
        randomGenerator = new Random();
        stopRunner = new Runnable() {
            @Override
            public void run() {
                try {
                    int random = randomGenerator.nextInt(4000) + 8000; // 8 - 12 sek
                    System.out.println("Sleep 1");
                    Thread.sleep(random);
                    stopHandler.sendEmptyMessage(1);

                    random = randomGenerator.nextInt(1000) + 2000; // 2 - 3 sek
                    System.out.println("Sleep 2");
                    Thread.sleep(random);
                    stopHandler.sendEmptyMessage(2);

                    random = randomGenerator.nextInt(1000)+ 2000; // 2 - 3 sek
                    System.out.println("Sleep 3");
                    Thread.sleep(random);
                    stopHandler.sendEmptyMessage(3);

                }catch (InterruptedException ie){
                    Toast.makeText(null, "This cannot happen!",Toast.LENGTH_LONG);
                }
            }
        };
    }

    @Override
    protected void onResume(){
        super.onResume();
        betTextView.setText("\tIndsats: " + (application == null ? "null" : application.getBet() + " €"));


    }

    public void onStartButtonClick(View view){
        betButton.setEnabled(false);

        animation1.start();
        animation2.start();
        animation3.start();

        view.setEnabled(false);
        stop1.setEnabled(true);
        stop2.setEnabled(true);
        stop3.setEnabled(true);

        new Thread(stopRunner).start();

    }

    public void onStopButtonClick(View view){
        if(view == stop1){
            animation1.stop();
        } else if(view == stop2){
            animation2.stop();
        } else {
            animation3.stop();
        }
        view.setEnabled(false);

        if(!stop1.isEnabled() && !stop2.isEnabled() && !stop3.isEnabled()){
            // Game Over!!!
            start.setEnabled(true);
            betButton.setEnabled(true);
            calculateWinning();
        }

    }

    private void calculateWinning() {
        String result = "";
        Bitmap b1 = ((BitmapDrawable)animation1.getCurrent()).getBitmap();
        Bitmap b2 = ((BitmapDrawable)animation2.getCurrent()).getBitmap();
        Bitmap b3 = ((BitmapDrawable)animation3.getCurrent()).getBitmap();

        Resources resource = getResources();
        int bet = ((BanditApplication)getApplication()).getBet();

        if(b1.equals(b2) && b2.equals(b3)) {
            result = String.format(resource.getString(R.string.threeEqual), 50 * bet);
        }else if (b1.equals(b2)) {
            result = String.format(resource.getString(R.string.twoEquals), 1, 2, 5 * bet);// "1 and 2 equal";
        } else if (b1.equals(b3)) {
            result = String.format(resource.getString(R.string.twoEquals), 1, 3, 5 * bet);// "1 and 3 equal";
        } else if (b2.equals(b3)) {
            result = String.format(resource.getString(R.string.twoEquals), 2, 3, 5 * bet);// "2 and 3 equal";
        } else {
            result = getResources().getString(R.string.nonEqual);//
        }
        Toast.makeText(getApplication(), result, Toast.LENGTH_LONG).show();

    }

    public void onBetButClick(View view){
        Intent betIntent = new Intent(this, BetActivity.class);
        startActivity(betIntent);
    }


}
