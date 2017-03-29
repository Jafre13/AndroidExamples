package deothan.dk.permissionexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private boolean canRead = false;
    private boolean canWrite= false;

    public static final int MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private String fileName = "test.txt";
    private String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testWritterBtnClick(View view) {
        permissionCheck();
        testAccess();
    }

    private void permissionCheck() {
        //Checks if the app can use the sensor if it is anything but PERMISSION_GRANTED then it requests access.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Can be used to show a dialog explaining why the app needs the permission, it will return true if the user has denied it earlier.
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //You now have access to the sensor, so here you can call the method to set it up.
                    testAccess();
                } else {
                    //Here you can show a Dialog box telling them the impact of denying access.
                }
            }
            default:
                System.out.println("The requestCode was unhandled.");
        }
    }

    public void testAccess() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("Writting.");
            File file = new File(path, fileName);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                fileOutputStream.write("I am writting stuff".getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done.");
        }
        else {
            System.out.println("No access.");
            permissionCheck();
        }
    }
}
