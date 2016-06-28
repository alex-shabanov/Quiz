package com.byodmidtermapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private  final String TAG = this.getClass().getSimpleName() + " @" + System.identityHashCode(this);
    private String studentEmail, emailBody;

    private Map<String,String> map;
    private ArrayList<Question> mQuestions;
    private int mQuestionIndex;

    private TextView mQuestionTextView;
    private TextView answerATextView;
    private TextView answerBTextView;
    private TextView answerCTextView;
    private TextView answerDTextView;
    private TextView answerETextView;

    private Button answerAButton;
    private Button answerBButton;
    private Button answerCButton;
    private Button answerDButton;
    private Button answerEButton;
    private Button nextButton;
    private Button prevButton;
    private Button submitButton;

    public Map<String,String> getMap(){return map;}
    public void setStudentEmail(String email){studentEmail = email;}
    public void setEmailBody(String text){emailBody = text;}
    public String getStudentEmail(){return studentEmail;}
    public String getEmailBody(){return emailBody;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.questionTextView);
        answerATextView = (TextView) findViewById(R.id.answerAView);
        answerBTextView = (TextView) findViewById(R.id.answerBView);
        answerCTextView = (TextView) findViewById(R.id.answerCView);
        answerDTextView = (TextView) findViewById(R.id.answerDView);
        answerETextView = (TextView) findViewById(R.id.answerEView);

        answerAButton = (Button) findViewById(R.id.answerAButton);
        answerBButton = (Button) findViewById(R.id.answerBButton);
        answerCButton = (Button) findViewById(R.id.answerCButton);
        answerDButton = (Button) findViewById(R.id.answerDButton);
        answerEButton = (Button) findViewById(R.id.answerEButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        prevButton = (Button) findViewById(R.id.prevButton);
        submitButton = (Button) findViewById(R.id.submitButton);

        if(savedInstanceState != null){
            mQuestions = new ArrayList<>();
            mQuestionIndex = savedInstanceState.getInt(Constants.STATE_questionIndex);
            String questionStr, answer_A, answer_B, answer_C, answer_D, answer_E, questonKey;
            String studentEmail;
            int arrayLength = savedInstanceState.getInt(Constants.ARRAY_LIST_SIZE);
            for(int i = 0; i < arrayLength; i++){
                if(i == 0){
                    studentEmail = savedInstanceState.getString(Constants.STUDENT_EMAIL + " " + String.valueOf(i));
                    setStudentEmail(studentEmail);
                }
                questionStr = savedInstanceState.getString(Constants.XML_QUESTION + " " + String.valueOf(i));
                answer_A = savedInstanceState.getString(Constants.XML_ANSWER_A + " " + String.valueOf(i));
                answer_B = savedInstanceState.getString(Constants.XML_ANSWER_B + " " + String.valueOf(i));
                answer_C = savedInstanceState.getString(Constants.XML_ANSWER_C + " " + String.valueOf(i));
                answer_D = savedInstanceState.getString(Constants.XML_ANSWER_D + " " + String.valueOf(i));
                answer_E = savedInstanceState.getString(Constants.XML_ANSWER_E + " " + String.valueOf(i));
                questonKey = savedInstanceState.getString(Constants.XML_QUESTION_KEY + " " + String.valueOf(i));
                studentEmail = savedInstanceState.getString(Constants.STUDENT_EMAIL + " " + String.valueOf(i));
                Question myQuestion = new Question(questionStr, answer_A, answer_B, answer_C, answer_D, answer_E,
                        questonKey, studentEmail);
                mQuestions.add(myQuestion);
            }
            map = new HashMap<>();
            String keyValue;
            for(int i = 1; i <= Constants.NUMBER_OF_QUESTIONS; i++){
                keyValue = savedInstanceState.getString("Q" + String.valueOf(i));
                if(keyValue == null) continue;
                if(keyValue.equalsIgnoreCase(Constants.A) && keyValue != null){map.put("Q" + String.valueOf(i), "A");}
                else if(keyValue.equalsIgnoreCase(Constants.B) && keyValue != null){map.put("Q" + String.valueOf(i), "B");}
                else if(keyValue.equalsIgnoreCase(Constants.C) && keyValue != null){map.put("Q" + String.valueOf(i), "C");}
                else if(keyValue.equalsIgnoreCase(Constants.D) && keyValue != null){map.put("Q" + String.valueOf(i), "D");}
                else if(keyValue.equalsIgnoreCase(Constants.E) && keyValue != null){map.put("Q" + String.valueOf(i), "E");}
            }
            updateUI();
        }
        answerAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mQuestions.get(mQuestionIndex).getQuestionKey();
                if(getMap().size() == 0){
                    getMap().put(key, "A");
                    answerATextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(getMap().containsKey(key)){
                    String keyValue = getMap().get(key);
                    if(keyValue.equalsIgnoreCase(Constants.B)){answerBTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.C)){answerCTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.D)){answerDTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.E)){answerETextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    getMap().put(key, "A");
                    answerATextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(!getMap().containsKey(key)){
                    getMap().put(key, "A");
                    answerATextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                Toast.makeText(MainActivity.this, R.string.answerA, Toast.LENGTH_SHORT).show();
            }
        });
        answerBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mQuestions.get(mQuestionIndex).getQuestionKey();
                if(getMap().size() == 0){
                    getMap().put(key, "B");
                    answerBTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(getMap().containsKey(key)){
                    String keyValue = getMap().get(key);
                    if(keyValue.equalsIgnoreCase(Constants.A)){answerATextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.C)){answerCTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.D)){answerDTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.E)){answerETextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    getMap().put(key, "B");
                    answerBTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(!getMap().containsKey(key)){
                    getMap().put(key, "B");
                    answerBTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                Toast.makeText(MainActivity.this, R.string.answerB, Toast.LENGTH_SHORT).show();
            }
        });
        answerCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mQuestions.get(mQuestionIndex).getQuestionKey();
                if(getMap().size() == 0){
                    getMap().put(key, "C");
                    answerCTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(getMap().containsKey(key)){
                    String keyValue = getMap().get(key);
                    if(keyValue.equalsIgnoreCase(Constants.A)){answerATextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.B)){answerBTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.D)){answerDTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.E)){answerETextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    getMap().put(key, "C");
                    answerCTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(!getMap().containsKey(key)){
                    getMap().put(key, "C");
                    answerCTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                Toast.makeText(MainActivity.this, R.string.answerC, Toast.LENGTH_SHORT).show();
            }
        });
        answerDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mQuestions.get(mQuestionIndex).getQuestionKey();
                if(getMap().size() == 0){
                    getMap().put(key, "D");
                    answerDTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(getMap().containsKey(key)){
                    String keyValue = getMap().get(key);
                    if(keyValue.equalsIgnoreCase(Constants.A)){answerATextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.B)){answerBTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.C)){answerCTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.E)){answerETextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    getMap().put(key, "D");
                    answerDTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(!getMap().containsKey(key)){
                    getMap().put(key, "D");
                    answerDTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                Toast.makeText(MainActivity.this, R.string.answerD, Toast.LENGTH_SHORT).show();
            }
        });
        answerEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mQuestions.get(mQuestionIndex).getQuestionKey();
                if(getMap().size() == 0){
                    getMap().put(key, "E");
                    answerETextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(getMap().containsKey(key)){
                    String keyValue = getMap().get(key);
                    if(keyValue.equalsIgnoreCase(Constants.A)){answerATextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.B)){answerBTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.C)){answerCTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    else if(keyValue.equalsIgnoreCase(Constants.D)){answerDTextView.setTextColor(getResources().getColor(R.color.Aqua));}
                    getMap().put(key, "E");
                    answerETextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else if(!getMap().containsKey(key)){
                    getMap().put(key, "E");
                    answerETextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                Toast.makeText(MainActivity.this, R.string.answerE, Toast.LENGTH_SHORT).show();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionIndex < mQuestions.size() - 1) mQuestionIndex++;
                updateUI();
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionIndex > 0) mQuestionIndex--;
                updateUI();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyValue, emailContent = "";
                String studentAnswers = "";
                for(int i = 1; i <= Constants.NUMBER_OF_QUESTIONS; i++){
                    if(getMap().containsKey("Q" + String.valueOf(i))){
                        keyValue = getMap().get("Q" + String.valueOf(i));
                        studentAnswers += "   " + Constants.STU_ANSWERS_KEY + "Q" + String.valueOf(i) + ">" + keyValue
                                + Constants.STU_ANSWERS + "\n";
                    }
                }
                emailContent += studentAnswers;
                setEmailBody(emailContent);
                Bundle myBundle = new Bundle();
                myBundle.putString(Constants.STUDENT_EMAIL, getStudentEmail());
                myBundle.putString(Constants.STUDENT_ANSWER_XML, getEmailBody());
                Intent nameFormIntent = new Intent(getApplicationContext(), NameForm.class);
                nameFormIntent.putExtra(Constants.BUNDLE_XML, myBundle);
                startActivity(nameFormIntent);
            }
        });
        if(mQuestions == null) {
            mQuestions = new ArrayList<>();
            map = new HashMap<>();
            FileDownloadTask download = new FileDownloadTask();
            download.execute();
        }
    }

    private void updateUI(){
        if(mQuestions != null && mQuestions.size() > 0) {
            if(mQuestionIndex > mQuestions.size()) mQuestionIndex = 0;
            if(mQuestionIndex < 14){submitButton.setVisibility(View.INVISIBLE);}
            else if(mQuestionIndex == 14){submitButton.setVisibility(View.VISIBLE);}
            mQuestionTextView.setText("" + (mQuestionIndex + 1) + ") " + mQuestions.get(mQuestionIndex).toString());
            Question midtermQuestion = mQuestions.get(mQuestionIndex);
            String answerA = midtermQuestion.getAnswerA();
            String answerB = midtermQuestion.getAnswerB();
            String answerC = midtermQuestion.getAnswerC();
            String answerD = midtermQuestion.getAnswerD();
            String answerE = midtermQuestion.getAnswerE();
            answerATextView.setText(answerA);
            answerBTextView.setText(answerB);
            answerCTextView.setText(answerC);
            answerDTextView.setText(answerD);
            answerETextView.setText(answerE);
            String stEmail;
            stEmail = midtermQuestion.getStudentEmail();
            setStudentEmail(stEmail);
            String key = midtermQuestion.getQuestionKey();
            //String str = map.toString();
            //Log.i("MAP", str);
            if(map != null) {
                String keyValue = getMap().get(key);
                if(map.containsKey(key) && keyValue.equalsIgnoreCase("A")){
                    answerATextView.setTextColor(getResources().getColor(R.color.sysYellow));
                    answerBTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerCTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerDTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerETextView.setTextColor(getResources().getColor(R.color.Aqua));
                }
                else if(map.containsKey(key) && keyValue.equalsIgnoreCase("B")){
                    answerATextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerBTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                    answerCTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerDTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerETextView.setTextColor(getResources().getColor(R.color.Aqua));
                }
                else if(map.containsKey(key) && keyValue.equalsIgnoreCase("C")){
                    answerATextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerBTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerCTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                    answerDTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerETextView.setTextColor(getResources().getColor(R.color.Aqua));
                }
                else if(map.containsKey(key) && keyValue.equalsIgnoreCase("D")){
                    answerATextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerBTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerCTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerDTextView.setTextColor(getResources().getColor(R.color.sysYellow));
                    answerETextView.setTextColor(getResources().getColor(R.color.Aqua));
                }
                else if(map.containsKey(key) && keyValue.equalsIgnoreCase("E")){
                    answerATextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerBTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerCTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerDTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerETextView.setTextColor(getResources().getColor(R.color.sysYellow));
                }
                else{
                    answerATextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerBTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerCTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerDTextView.setTextColor(getResources().getColor(R.color.Aqua));
                    answerETextView.setTextColor(getResources().getColor(R.color.Aqua));
                }
            }
        }
    }

    private class FileDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                InputStream iStream = getResources().openRawResource(R.raw.midtermquestions);
                BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
                mQuestions = Midterm.pullParseFrom(reader);
                reader.close();
            }
            catch(java.io.FileNotFoundException e){Log.i(TAG, "ERROR: downloaded file not found");}
            catch(java.io.IOException e){Log.i(TAG, "ERROR: unable to close file");}
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            updateUI();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){  //called after onPause()
        super.onSaveInstanceState(savedInstanceState);
        String questionStr, answer_A, answer_B, answer_C, answer_D, answer_E, key;
        String studentEmail;
        savedInstanceState.putInt(Constants.STATE_questionIndex, mQuestionIndex);
        savedInstanceState.putInt(Constants.ARRAY_LIST_SIZE, mQuestions.size());
        Question myQuestions;
        for(int i = 0; i < mQuestions.size(); i++){
            myQuestions = mQuestions.get(i);
            questionStr = myQuestions.getQuestionString();
            answer_A = myQuestions.getAnswerA();
            answer_B = myQuestions.getAnswerB();
            answer_C = myQuestions.getAnswerC();
            answer_D = myQuestions.getAnswerD();
            answer_E = myQuestions.getAnswerE();
            key = myQuestions.getQuestionKey();
            studentEmail = myQuestions.getStudentEmail();
            savedInstanceState.putString(Constants.XML_QUESTION + " " + String.valueOf(i), questionStr);
            savedInstanceState.putString(Constants.XML_ANSWER_A + " " + String.valueOf(i), answer_A);
            savedInstanceState.putString(Constants.XML_ANSWER_B + " " + String.valueOf(i), answer_B);
            savedInstanceState.putString(Constants.XML_ANSWER_C + " " + String.valueOf(i), answer_C);
            savedInstanceState.putString(Constants.XML_ANSWER_D + " " + String.valueOf(i), answer_D);
            savedInstanceState.putString(Constants.XML_ANSWER_E + " " + String.valueOf(i), answer_E);
            savedInstanceState.putString(Constants.XML_QUESTION_KEY + " " + String.valueOf(i), key);
            savedInstanceState.putString(Constants.STUDENT_EMAIL + " " + String.valueOf(i), studentEmail);
        }
        int mapSize = map.size();
        savedInstanceState.putInt(Constants.MAP_SIZE, mapSize);
        if(map.containsKey(Constants.Q_1)){savedInstanceState.putString("Q1", map.get("Q1").toString());}
        if(map.containsKey(Constants.Q_2)){savedInstanceState.putString("Q2", map.get("Q2").toString());}
        if(map.containsKey(Constants.Q_3)){savedInstanceState.putString("Q3", map.get("Q3").toString());}
        if(map.containsKey(Constants.Q_4)){savedInstanceState.putString("Q4", map.get("Q4").toString());}
        if(map.containsKey(Constants.Q_5)){savedInstanceState.putString("Q5", map.get("Q5").toString());}
        if(map.containsKey(Constants.Q_6)){savedInstanceState.putString("Q6", map.get("Q6").toString());}
        if(map.containsKey(Constants.Q_7)){savedInstanceState.putString("Q7", map.get("Q7").toString());}
        if(map.containsKey(Constants.Q_8)){savedInstanceState.putString("Q8", map.get("Q8").toString());}
        if(map.containsKey(Constants.Q_9)){savedInstanceState.putString("Q9", map.get("Q9").toString());}
        if(map.containsKey(Constants.Q_10)){savedInstanceState.putString("Q10", map.get("Q10").toString());}
        if(map.containsKey(Constants.Q_11)){savedInstanceState.putString("Q11", map.get("Q11").toString());}
        if(map.containsKey(Constants.Q_12)){savedInstanceState.putString("Q12", map.get("Q12").toString());}
        if(map.containsKey(Constants.Q_13)){savedInstanceState.putString("Q13", map.get("Q13").toString());}
        if(map.containsKey(Constants.Q_14)){savedInstanceState.putString("Q14", map.get("Q14").toString());}
        if(map.containsKey(Constants.Q_15)){savedInstanceState.putString("Q15", map.get("Q15").toString());}
    }

    @Override
    protected void onStart(){
        super.onStart();
        //Log.i(TAG, "onStart()");
        //activity paused but visible to user, not in foreground
    }
    @Override
    protected void onResume(){
        super.onResume();
        //Log.i(TAG, "onResume()");
        //activity is visible, running and in foreground
    }
    @Override
    protected void onPause(){
        super.onPause();
        //Log.i(TAG, "onPause()");
        //activity paused, visible, not in foreground
    }
    @Override
    protected void onStop(){
        super.onStop();
        //Log.i(TAG, "onStop()");
        //activity stopped, not visible
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Log.i(TAG, "onDestroy()");
        //activity and will be destroyed
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) { return true; }
        return super.onOptionsItemSelected(item);
    }
}
