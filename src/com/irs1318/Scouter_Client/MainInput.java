package com.irs1318.Scouter_Client;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainInput extends Activity {
    int defaultMax = 10;
    int objectNum = 23;
    int columns = 0;
    int pageId = 0;
    String text;
    int i;

    String[] objectName = new String[objectNum];
    int[] objectType = new int[objectNum];
    int[] objectValue = new int[objectNum];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        for (i = 0; i < objectNum; i++) {
            switch(i) {
                case 0:
                    objectName[0] = "First Page";
                    objectType[0] = 1;
                    break;
                case 1:
                    objectName[1] = "First Set";
                    objectType[1] = 2;
                    break;
                case 2:case 3:case 4:case 5:
                    objectName[i] = "Multiple Choice";
                    objectType[i] = 6;
                    break;
                case 6:
                    objectName[i] = "Second Set ";
                    objectType[i] = 2;
                    break;
                case 7:case 8:
                    objectName[i] = "Buttons";
                    objectType[i] = 4;
                    break;
                case 9:
                    objectName[i] = "Second Page";
                    objectType[i] = 1;
                    break;
                case 10:
                    objectName[i] = "First Set";
                    objectType[i] = 2;
                    break;
                case 11:case 12:case 13:case 14:case 15:case 16:case 17:case 18:case 19:
                    objectName[i] = "Switches";
                    objectType[i] = 3;
                    break;
                case 20:
                    objectName[i] = "Second Set";
                    objectType[i] = 2;
                    break;
                case 21:
                    objectName[i] = "Seek-Bar";
                    objectType[i] = 5;
                    break;
            }
        }
        loadObjects();
    }

    public void loadObjects() {
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        LinearLayout tabLayout = (LinearLayout) findViewById(R.id.tabLayout);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(linearLayout);

        GridLayout gridLayout = new GridLayout(this);
        linearLayout.addView(gridLayout);

        for (i = 0; i < objectNum; i++) {
            switch(objectType[i]) {
                case 1:
                    Button button = new Button(this);
                    button.setText(objectName[i]);
                    button.setOnClickListener(pageListener);
                    button.setId(i);
                    tabLayout.addView(button);

                    linearLayout = new LinearLayout(this);
                    linearLayout.setId(i + objectNum);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    linearLayout.setVisibility(View.GONE);
                    mainLayout.addView(linearLayout);

                    gridLayout = new GridLayout(this);
                    linearLayout.addView(gridLayout);
                    columns = 0;
                    break;
                case 2:
                    TextView textView = new TextView(this);
                    textView.setText(objectName[i]);
                    textView.setTextSize(20);
                    textView.setGravity(1);
                    linearLayout.addView(textView);

                    gridLayout = new GridLayout(this);
                    linearLayout.addView(gridLayout);
                    columns = 0;
                    break;
                case 3:
                    CheckBox checkBox = new CheckBox(this);
                    checkBox.setText(objectName[i]);
                    checkBox.setId(i);
                    gridLayout.addView(checkBox);
                    break;
                case 4:
                    button = new Button(this);
                    text = objectName[i] + ": 0";
                    button.setText(text);
                    button.setId(i);
                    button.setOnClickListener(clickListener);
                    gridLayout.addView(button);
                    break;
                case 5:
                    textView = new TextView(this);
                    text = objectName[i] + ": 0";
                    textView.setId(i + objectNum);
                    textView.setText(text);
                    linearLayout.addView(textView);

                    SeekBar seekBar = new SeekBar(this);
                    seekBar.setId(i);
                    seekBar.setMax(defaultMax);
                    seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
                    linearLayout.addView(seekBar);

                    gridLayout = new GridLayout(this);
                    linearLayout.addView(gridLayout);
                    columns = 0;
                    break;
                case 6:
                    RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(objectName[i]);
                    radioButton.setId(i);
                    radioButton.setOnCheckedChangeListener(checkedChangeListener);
                    gridLayout.addView(radioButton);
                    break;
            }
            gridLayout.setColumnCount(3);
            columns++;
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            if(gridLayout.getY() > size.y) gridLayout.setColumnCount(columns);
        }
    }
    Button.OnClickListener pageListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(pageId != 0) findViewById(pageId).setVisibility(View.GONE);
            findViewById(v.getId() + objectNum).setVisibility(View.VISIBLE);
            pageId = v.getId() + objectNum;
        }
    };
    Button.OnClickListener clickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            i = button.getId();
            objectValue[i]++;
            if (objectValue[i] > defaultMax) objectValue[i] = 0;
            text = objectName[i] + ": " + objectValue[i];
            button.setText(text);
        }
    };
    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            i = seekBar.getId();
            TextView textView = (TextView) findViewById(i + objectNum);
            text = objectName[i] + ": " + progress;
            if(fromUser) textView.setText(text);
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    RadioButton.OnCheckedChangeListener checkedChangeListener = new RadioButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            for(i = buttonView.getId() - 1; objectType[i] != 2 && objectType[i] != 1; i--) if(objectValue[i] == 6) findViewById(i).setActivated(false);
            for(i = buttonView.getId() + 1; objectType[i] != 2 && objectType[i] != 1; i++) if(objectValue[i] == 6) findViewById(i).setActivated(false);
        }
    };
}