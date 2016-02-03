package com.irs1318.Scouter_Client;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.irs1318.Scouter_Client.Net.*;

public class MainInput extends Activity {
    int defaultMax = 10;
    int objectNum = 22;
    int pageId = 0;
    int i;
    String text;
    String[] objectName = new String[objectNum];
    int[] objectType = new int[objectNum];
    int[] objectValue = new int[objectNum];
    TCPClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void dataThing(TCPClient client) {
        NetworkPacket[] networkPackets = client.GetPackets();
        for (i = 0; i < networkPackets.length; i++) {
            objectName[i] = networkPackets[i].Name;
            objectType[i] = networkPackets[i].DataAsInt();
        }
        loadObjects();
    }

    public void connect() {
        EditText editText = (EditText) findViewById(R.id.editText);
        client = new TCPClient(11111, editText.getText().toString());
        client.OnDataAvailable.add(this::dataThing);
    }

    public void noConnect() {
        for (i = 1; i < objectNum; i++) {
            switch(i) {
                case 0:
                    objectName[i] = "First Page";
                    break;
                case 1:
                    objectName[1] = "First Set";
                    objectType[1] = 2;
                    break;
                case 2:case 3:case 4:
                    objectName[i] = "Multiple Choice";
                    objectType[i] = 6;
                    break;
                case 5:
                    objectName[i] = "Second Set";
                    objectType[i] = 2;
                    break;
                case 6:case 7:
                    objectName[i] = "Buttons";
                    objectType[i] = 4;
                    break;
                case 8:
                    objectName[i] = "Second Page";
                    objectType[i] = 1;
                    break;
                case 9:
                    objectName[i] = "First Set";
                    objectType[i] = 2;
                    break;
                case 10:case 11:case 12:case 13:case 14:case 15:case 16:case 17:case 18:
                    objectName[i] = "Switches";
                    objectType[i] = 3;
                    break;
                case 19:
                    objectName[i] = "Second Set";
                    objectType[i] = 2;
                    break;
                case 20:
                    objectName[i] = "Seek-Bar";
                    objectType[i] = 5;
                    break;
                case 21:
                    objectName[i] = "First Page";
                    objectType[i] = 7;
                    break;
            }
        }
        loadObjects();
    }

    public void loadObjects() {
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.startLayout);
        mainLayout.setVisibility(View.GONE);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setId(objectNum);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(linearLayout);

        GridLayout gridLayout = new GridLayout(this);
        gridLayout.addOnLayoutChangeListener(layoutChangeListener);
        gridLayout.setColumnCount(3);
        linearLayout.addView(gridLayout);

        RadioGroup radioGroup = new RadioGroup(this);
        boolean inRadio = false;
        boolean newPage = false;
        int oldPageId = 0;

        for(i = 1; i < objectNum; i++) {
            switch(objectType[i]) {
                case 1:
                    if(!newPage) {
                        gridLayout = new GridLayout(this);
                        gridLayout.setColumnCount(2);
                        linearLayout.addView(gridLayout);

                        Space space = new Space(this);
                        gridLayout.addView(space);
                    }
                    Button button = new Button(this);
                    button.setText(objectName[i]);
                    button.setOnClickListener(pageListener);
                    button.setId(i);
                    linearLayout.addView(button);

                    linearLayout = new LinearLayout(this);
                    linearLayout.setId(i + objectNum);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    linearLayout.setVisibility(View.GONE);
                    mainLayout.addView(linearLayout);

                    gridLayout = new GridLayout(this);
                    gridLayout.addOnLayoutChangeListener(layoutChangeListener);
                    gridLayout.setColumnCount(3);
                    linearLayout.addView(gridLayout);

                    inRadio = false;
                    oldPageId = pageId;
                    pageId = i;
                    newPage = false;
                    break;
                case 2:
                    TextView textView = new TextView(this);
                    textView.setText(objectName[i]);
                    textView.setTextSize(20);
                    textView.setGravity(1);
                    linearLayout.addView(textView);

                    gridLayout = new GridLayout(this);
                    gridLayout.addOnLayoutChangeListener(layoutChangeListener);
                    gridLayout.setColumnCount(3);
                    linearLayout.addView(gridLayout);

                    inRadio = false;
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
                    gridLayout.addOnLayoutChangeListener(layoutChangeListener);
                    gridLayout.setColumnCount(3);
                    linearLayout.addView(gridLayout);
                    break;
                case 6:
                    if(!inRadio) {
                        radioGroup = new RadioGroup(this);
                        gridLayout.addView(radioGroup);
                        inRadio = true;
                    }

                    RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(objectName[i]);
                    radioButton.setId(i);
                    radioGroup.addView(radioButton);
                    break;
                case 7:
                    gridLayout = new GridLayout(this);
                    gridLayout.setColumnCount(2);
                    linearLayout.addView(gridLayout);

                    button = new Button(this);
                    button.setText(objectName[i]);
                    button.setOnClickListener(pageListener);
                    button.setId(oldPageId);
                    gridLayout.addView(button);

                    newPage = true;
                    break;
                case 8:
                    textView = new TextView(this);
                    textView.setText(objectName[i]);
                    textView.setTextSize(15);
                    gridLayout.addView(textView);
                    break;
            }
        }
        pageId = objectNum;
    }
    GridLayout.OnLayoutChangeListener layoutChangeListener = new GridLayout.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
            GridLayout gridLayout = (GridLayout) view;
            //if(view.getWidth()) gridLayout.setColumnCount(gridLayout.getColumnCount() - 1);
        }
    };
    Button.OnClickListener pageListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(pageId).setVisibility(View.GONE);
            i = v.getId();
            findViewById(i + objectNum).setVisibility(View.VISIBLE);
            pageId = i + objectNum;
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
}