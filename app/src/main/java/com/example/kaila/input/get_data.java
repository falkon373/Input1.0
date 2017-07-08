package com.example.kaila.input;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import java.io.*;

public class get_data extends AppCompatActivity {

    Intent intend;
    protected int n;
    protected int o;
    protected Button add;
    protected EditText in;
    protected TextView msg;
    protected String data;
    protected static int i;
    protected static boolean append=true;


    protected boolean validate(String x)
    {
        if(x.length()>0)
            return true;
        return false;
    }

    protected void execute()
    {
        in=(EditText) findViewById(R.id.editText);
        msg=(TextView) findViewById(R.id.textView10);
        add=(Button) findViewById(R.id.button);
        add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i<n)
                {
                    boolean result=validate(in.getText().toString());
                    if(result)
                    {
                        FileWriter f;
                        try{
                            f = new FileWriter(getFilesDir().getAbsolutePath()+"/data.txt",append);
                            f.write(in.getText().toString()+Integer.toString(o)+"\n");
                            f.flush();
                            f.close();
                            i++;
                            msg.setText("Succes");
                            if(i==n)
                                finish();
                        }
                        catch(Exception e){
                            msg.setText(e.getMessage()+"\n"+getFilesDir().getAbsolutePath()+"/data.txt");
                        }
                    }
                    else
                    {
                        msg.setText("null value");
                    }
                }
                else
                    finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        intend =getIntent();
        n=(int)Integer.parseInt(intend.getStringExtra(MainActivity.EXTRA_MESSAGE));
        o=n%10;
        n/=10;
        i=0;
        /*msg=(TextView) findViewById(R.id.textView10);
        msg.setText(intend.getStringExtra(MainActivity.EXTRA_MESSAGE));*/
        execute();
    }
}
