package com.msi.getwebpage;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

// used for interacting with user interface
// used for passing data
// used for connectivity


public class GetWebPage extends Activity {

    Handler h;

    private TextView tView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final EditText eText = (EditText) findViewById(R.id.address);
        tView = (TextView) findViewById(R.id.pagetext);

        this.h = new Handler();

               /*{
            @Override
            public void handleMessage(Message msg) {
                // process incoming messages here
                switch (msg.what) {
                    case 0:
                        tView.append((String) msg.obj);
                        break;
                }
                super.handleMessage(msg);
            }
        };*/


        if (Build.VERSION.SDK_INT >= 23)
            if (! ckeckPermissions())
                requestPermissions();


        final Button button = (Button) findViewById(R.id.ButtonGo);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try	{
                    tView.setText("");
                    final Thread tr = new Thread(){
                        public void run() {
                            try{
                                // Perform action on click
                                URL url = new URL(eText.getText().toString());
                                URLConnection conn = url.openConnection();
                                conn.connect();
                                // Get the response
                                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                String line = "";
                                while ((line = rd.readLine()) != null) {

                                    /*Message lmsg;
                                    lmsg = new Message();
                                    lmsg.obj = line;
                                    lmsg.what = 0;
                                    GetWebPage.this.h.sendMessage(lmsg);*/

                                    GetWebPage.this.h.post(new updateUIThread(line));
                                }
                            }catch (MalformedURLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                Log.i("GetWebPage", "downloading text: IOException");// Log and trace
                                e.printStackTrace();
                            }
                        }
                    };
                    tr.start();
                }
                catch (Exception e)	{
                }
            }
        });
    }


    class updateUIThread implements Runnable {
        private String msg;

        public updateUIThread(String str) {
            this.msg = str;
        }

        @Override
        public void run() {
            tView.append(msg);
        }

    }

    private boolean ckeckPermissions() {
             if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_NETWORK_STATE) ==
                    PackageManager.PERMISSION_GRANTED  && ActivityCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.INTERNET) ==
                    PackageManager.PERMISSION_GRANTED)
                return true;
            else
                return false;
      }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(GetWebPage.this,
                new String[]{Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET},
                0);
    }
}
