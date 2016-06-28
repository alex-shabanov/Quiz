package com.byodmidtermapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NameForm extends AppCompatActivity {

    Intent mIntent;
    Bundle studentAnswers;
    Button submitAnswersBtn;
    EditText studentNameTextEdit, studentNumberTextEdit;
    private String studentName, studentNumber, studentEmail, emailBody;

    public void setStudentName(String name){studentName = name;}
    public void setStudentNumber(String num){studentNumber = num;}
    public void setStudentEmail(String email){studentEmail = email;}
    public void setEmailBody(String text){emailBody = text;}
    public String getStudentName(){return studentName;}
    public String getStudentNumber(){return studentNumber;}
    public String getStudentEmail(){return studentEmail;}
    public String getEmailBody(){return emailBody;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        submitAnswersBtn = (Button) findViewById(R.id.submitAnswersButton);
        studentNameTextEdit = (EditText) findViewById(R.id.studentNameEditText);
        studentNumberTextEdit = (EditText) findViewById(R.id.studentNumberEditText);

        if(savedInstanceState == null) {
            mIntent = getIntent();
            if(mIntent != null) {
                studentAnswers = mIntent.getBundleExtra(Constants.BUNDLE_XML);
                String email = studentAnswers.getString(Constants.STUDENT_EMAIL);
                String stAnswers = studentAnswers.getString(Constants.STUDENT_ANSWER_XML);
                setStudentEmail(email);
                setEmailBody(stAnswers);
            }
        } else {
            String stName = savedInstanceState.getString(Constants.STUDENT_NAME);
            String stNumber = savedInstanceState.getString(Constants.STUDENT_NUMBER);
            String stEmail = savedInstanceState.getString(Constants.STUDENT_EMAIL);
            String emailContent = savedInstanceState.getString(Constants.STUDENT_ANSWER_XML);
            studentNameTextEdit.setText(stName);
            studentNumberTextEdit.setText(stNumber);
            setStudentName(stName);
            setStudentNumber(stNumber);
            setStudentEmail(stEmail);
            setEmailBody(emailContent);
        }

        submitAnswersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stName = studentNameTextEdit.getText().toString();
                String stNumber = studentNumberTextEdit.getText().toString();
                setStudentName(stName);
                setStudentNumber(stNumber);
                if (getStudentName().equals("")) {
                    hideKeyboard();
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.blankStudentName, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else if (getStudentNumber().equals("")) {
                    hideKeyboard();
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.blankStudentNumber, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                String emailContent = "";
                emailContent += Constants.STU_MID_ANSW_OPEN + "\n";
                emailContent += "   " + Constants.STU_NAME_OPEN + getStudentName() + Constants.STU_NAME_CLOSE + "\n";
                emailContent += "   " + Constants.STU_NUM_OPEN + getStudentNumber() + Constants.STU_NUM_CLOSE + "\n";
                if(getEmailBody().equalsIgnoreCase(""))  emailContent += "   " + Constants.BLANK_ANSWERS + "\n";
                else emailContent += getEmailBody();
                emailContent += Constants.STU_MID_ANSW_CLOSE;

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + getStudentEmail()));
                if (getStudentEmail().length() != 0) {
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, Constants.EMAIL_SUBJECT);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, emailContent);
                    startActivity(Intent.createChooser(emailIntent, Constants.EMAIL_TO_STUDENT));
                }
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) NameForm.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(NameForm.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {  //called after onPause()
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.STUDENT_NAME, getStudentName());
        savedInstanceState.putString(Constants.STUDENT_NUMBER, getStudentNumber());
        savedInstanceState.putString(Constants.STUDENT_EMAIL, getStudentEmail());
        savedInstanceState.putString(Constants.STUDENT_ANSWER_XML, getEmailBody());
    }
}
