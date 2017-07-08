package com.example.kaila.input;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Calendar;
import android.content.Intent;
import java.io.*;




public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private EditText income;
    private TextView start_date;
    private TextView end_date;
    private TextView error;
    private TextView disp;
    private TextView result;
    private DatePickerDialog.OnDateSetListener s_d;
    private DatePickerDialog.OnDateSetListener e_d;
    static String P1;
    static String P2;
    private String sd;
    private String ed;
    private String er;
    private String option;
    private String s_r;
    private RadioGroup rGroup;
    private Button reset;
    private Button submit;


    //protected int countLeapYears(int d);

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, get_data.class);
        disp =(TextView) findViewById(R.id.textView6);
        set_p1_p2();
        disp.setText("switching...");
        intent.putExtra(EXTRA_MESSAGE,P1+P2);
        startActivity(intent);
        disp.setText("Done");
    }

    protected void set_p1_p2()
    {
        int ans;
        int d1,d2,m1,m2,y1,y2;
        int monthDays[] ={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        m1=(int)(Integer.parseInt(sd.substring(0,2)));
        m2=(int)(Integer.parseInt(ed.substring(0,2)));
        d1=(int)(Integer.parseInt(sd.substring(3,5)));
        d2=(int)(Integer.parseInt(ed.substring(3,5)));
        y1=(int)(Integer.parseInt(sd.substring(6)));
        y2=(int)(Integer.parseInt(ed.substring(6)));
        int n1=y1*365+d1;
        for(int i=0;i<m2-1;i++)
        {
            n1+=monthDays[i];
        }
        int n2=y2*365+d2;
        for(int i=0;i<m2-1;i++)
        {
            n2+=monthDays[i];
        }
        ans=n2-n1;
        if(option.equals("weekly"))
        {
            P2="1";
            ans/=7;
        }
        else
        {
            P2="2";
            ans/=30;
        }
        P1=Integer.toString(ans);
    }

    protected void reset(){
        end_date=(TextView) findViewById(R.id.textView4);
        end_date.setText(null);
        start_date=(TextView) findViewById(R.id.textView2);;
        start_date.setText(null);
        rGroup = (RadioGroup) findViewById(R.id.rGroup);
        rGroup.clearCheck();
        disp =(TextView) findViewById(R.id.textView6);
        disp.setText("msg:");
        sd="";
        ed="";
    }

    boolean validate(String start,String end,String option)
    {
        er="";
        int d1,d2,m1,m2,y1,y2;
        float in;
        if(option.length()<1)
            er+="invaild frequency;";
        if(start.length()<1)
            er+="invalid start date;";
        if(end.length()<1)
            er+="invalid end date;";
        /*try{
            in=(float)(Float.parseFloat(income));
        }
        catch(Exception e){
            er+="invalid income;";
        }*/
        if(er.length()<1)
        {
            boolean flag=false;
            m1=(int)(Integer.parseInt(start.substring(0,2)));
            m2=(int)(Integer.parseInt(end.substring(0,2)));
            d1=(int)(Integer.parseInt(start.substring(3,5)));
            d2=(int)(Integer.parseInt(end.substring(3,5)));
            y1=(int)(Integer.parseInt(start.substring(6)));
            y2=(int)(Integer.parseInt(end.substring(6)));
            if(y2>y1)
                flag=true;
            else if(y2<y1)
                flag=false;
            else
            {
                if(m2>m1)
                    flag=true;
                else if(m2<m1)
                        flag=false;
                else
                {
                    if(d2>=d1)
                        flag=true;
                    else
                        flag=false;
                }
            }
            if(!flag)
            {
                er+="invalid date(s<e);";
                return false;
            }
        }
        else
            return false;
        return true;
    }



    protected void execute() {

        sd="";
        ed="";
        option="";
        //start of start date input and display

        start_date=(TextView) findViewById(R.id.textView2);
        start_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Calendar cal= Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int date = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, s_d,year,month,date);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        s_d = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                month++;
                if(month<10&&date<10)
                    sd="0"+month+"/"+"0"+date+"/"+year;
                else if(month<10)
                    sd="0"+month+"/"+date+"/"+year;
                else if(date<10)
                    sd=month+"/"+"0"+date+"/"+year;
                else
                    sd=month+"/"+date+"/"+year;
                start_date.setText(sd);
            }
        };

        //end of start date input and display
        //start of end date input and display

        end_date=(TextView) findViewById(R.id.textView4);
        end_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Calendar cal= Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int date = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, e_d,year,month,date);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        e_d = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                month++;
                if(month<10&&date<10)
                    ed="0"+month+"/"+"0"+date+"/"+year;
                else if(month<10)
                    ed="0"+month+"/"+date+"/"+year;
                else if(date<10)
                    ed=month+"/"+"0"+date+"/"+year;
                else
                    ed=month+"/"+date+"/"+year;
                end_date.setText(ed);
            }
        };

        //end of end date input and display
        //start of Radio selection settings

        rGroup = (RadioGroup) findViewById(R.id.rGroup);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                disp =(TextView) findViewById(R.id.textView6);
                int checked;
                checked = rGroup.indexOfChild(findViewById(i));
                switch (checked){
                    case 0:
                        option="weekly";
                        break;
                    case 1:
                        option="monthly";
                        break;
                    default:
                        option="";
                        break;
                }
                //disp.setText("frequency: "+option);
            }
        });

        //end of Radio selection settings
        //start of income textbox

        /*income=(EditText) findViewById(R.id.editText3);
        income.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                in=income.getText().toString();
            }
        });
        */

        //end of income textbox
        //start of reset button

        reset=(Button) findViewById(R.id.button2);
        reset.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();                                                                            //calls function void protected reset()
            }
        });

        //end of reset button
        //start of submit button
/*
        submit=(Button) findViewById(R.id.button3);
        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = validate(sd,ed,option);
                if(result)
                {
                    call_get_data(view);

                }
                else
                {
                    error =(TextView) findViewById(R.id.textView6);
                    error.setText(er);
                }
            }
        });
*/
        //end of submit button
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        execute();                                                                                  //calls function void protected execute()
    }
}
