package com.byodmidtermapp;


/**
 * Created by admin on 2016-01-29.
 */
public class Question {

    private static final String TAG = Question.class.getSimpleName();
    private String mQuestionString;  // id of string resource representing the question
    private String questionKey; // question identifier
    String answerA, answerB, answerC, answerD, answerE;
    String studentEmail;

    public Question(String aQuestion, String a, String b, String c, String d, String e,
                    String key, String email){
        mQuestionString = aQuestion;
        answerA = a;
        answerB = b;
        answerC = c;
        answerD = d;
        answerE = e;
        studentEmail = email;
        questionKey = key;
        if(questionKey != null && !questionKey.isEmpty()) { questionKey = key; }
        else { questionKey = "none"; }
    }

    public String getQuestionString(){return this.mQuestionString;}
    public String getAnswerA(){return this.answerA;}
    public String getAnswerB(){return this.answerB;}
    public String getAnswerC(){return this.answerC;}
    public String getAnswerD(){return this.answerD;}
    public String getAnswerE(){return this.answerE;}
    public String getQuestionKey(){return questionKey;}
    public String getStudentEmail(){return studentEmail;}

    public String toString(){
        String toReturn = "";
        toReturn += mQuestionString;
        return toReturn;
    }
}
