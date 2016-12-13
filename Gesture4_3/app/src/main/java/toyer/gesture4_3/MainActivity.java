package toyer.gesture4_3;

import java.util.ArrayList;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Button;
import java.lang.String;


public class MainActivity extends Activity implements OnGesturePerformedListener, OnClickListener{
    GestureLibrary gLib;
    GestureOverlayView gestures;
    TextView first;
    TextView operand;
    TextView second;
    TextView result;
    Button refresh;
    Button calculate;
    private int COUNT=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first=(TextView)findViewById(R.id.first);
        operand=(TextView)findViewById(R.id.operand);
        second=(TextView)findViewById(R.id.second);
        result=(TextView)findViewById(R.id.result);
        refresh=(Button)findViewById(R.id.refresh);
        calculate=(Button)findViewById(R.id.calculate);

        refresh.setOnClickListener(this);
        calculate.setOnClickListener(this);

        //Загрузка жестов (gestures) из res/raw/gestures
        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            finish();
        }
        gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);
    }

    public void onClick(View v) {

        if (refresh.equals(v)) {
            first.setText("");
            second.setText("");
            operand.setText("");
            result.setText("");
            COUNT=0;
        }
        else if (calculate.equals(v)) {
              if(first.getText().toString()!=null && second.getText().toString()!=null && operand.getText().toString()!=null)
               {
                   calculateFunc(first.getText().toString(), second.getText().toString(), operand.getText().toString());
               }
        }
    }

    public void calculateFunc(String a, String b, String op)
    {
        int one = Integer.parseInt(a);
        int two = Integer.parseInt(b);
        int res=0;

        if (op.equals("+")) {
            res = one + two;
            result.setText(""+res);
        }
        else if (op.equals("-")) {
            res = one - two;
            result.setText(""+res);
        }
        else if (op.equals("/")) {
            if(two!=0) {
                res = one / two;
                result.setText(""+res);
            }
            else result.setText("division by zero!");
        }
        else if (op.equals("*")) {
            res = one * two;
            result.setText(""+res);
        }
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
//Создаёт ArrayList c загруженными из gestures жестами
        ArrayList<Prediction> predictions = gLib.recognize(gesture);
        if (predictions.size() > 0) {
//если загружен хотя бы один жест из gestures
            Prediction prediction = predictions.get(0);
            if(COUNT==0) {
                if (prediction.score > 1.0) {
                    String t = first.getText().toString();
                    if (prediction.name.equals("1"))
                        first.setText(t+"1");
                    else if (prediction.name.equals("2"))
                        first.setText(t+"2");
                    else if (prediction.name.equals("3"))
                        first.setText(t+"3");
                    else if (prediction.name.equals("4"))
                        first.setText(t+"4");
                    else if (prediction.name.equals("5"))
                        first.setText(t+"5");
                    else if (prediction.name.equals("6"))
                        first.setText(t+"6");
                    else if (prediction.name.equals("7"))
                        first.setText(t+"7");
                    else if (prediction.name.equals("8"))
                        first.setText(t+"8");
                    else if (prediction.name.equals("9"))
                        first.setText(t+"9");
                    else if (prediction.name.equals("0"))
                        first.setText(t+"0");
                    else if(first.getText()!=null){
                        COUNT=1;
                        if(prediction.name.equals("+"))
                            operand.setText("+");
                        else if(prediction.name.equals("-"))
                            operand.setText("-");
                        else if(prediction.name.equals("/"))
                            operand.setText("/");
                        else if(prediction.name.equals("*"))
                            operand.setText("*");
                        else { COUNT=0;}
                    }
                } else {
                    COUNT=0;
                }
            }
            else if(COUNT==1)
            {
                    String t = second.getText().toString();
                    if (prediction.name.equals("1"))
                        second.setText(t + "1");
                    else if (prediction.name.equals("2"))
                        second.setText(t + "2");
                    else if (prediction.name.equals("3"))
                        second.setText(t + "3");
                    else if (prediction.name.equals("4"))
                        second.setText(t + "4");
                    else if (prediction.name.equals("5"))
                        second.setText(t + "5");
                    else if (prediction.name.equals("6"))
                        second.setText(t + "6");
                    else if (prediction.name.equals("7"))
                        second.setText(t + "7");
                    else if (prediction.name.equals("8"))
                        second.setText(t + "8");
                    else if (prediction.name.equals("9"))
                        second.setText(t + "9");
                    else if (prediction.name.equals("0"))
                        second.setText(t + "0");

            }
        }
    }
}