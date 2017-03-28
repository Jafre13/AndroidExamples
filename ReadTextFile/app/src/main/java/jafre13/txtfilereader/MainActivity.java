package jafre13.txtfilereader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView tw;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tw = (TextView) findViewById(R.id.textview);

        loadText();

        tw.setText(text);
    }



    private void loadText(){
        InputStream in = this.getApplicationContext().getResources().openRawResource(R.raw.lorem);
        
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        String line;
        StringBuilder tmp = new StringBuilder();

        try {
            while ((line = br.readLine())!=null){
                tmp.append(line);
                tmp.append("\n");
            }
            text = tmp.toString();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            text = null;
        }
    }
}
