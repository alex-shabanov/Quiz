package com.byodmidtermapp;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Midterm {

    private static final String TAG = Midterm.class.getSimpleName();

    public static ArrayList<Question> pullParseFrom(BufferedReader reader){
        ArrayList<Question> questions = new ArrayList<Question>();
        String answerA = "", answerB = "", answerC = "", answerD = "", answerE = "";
        String studentEmail = "";
        XmlPullParserFactory factory; // get our factory and create a PullParser
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(reader);               // set input file for parser
            int eventType = xpp.getEventType(); // get initial eventType
            String currentText = ""; //current text between XML tags being parsed
            Question currentQuestion; //current question object being parsed
            String currentQuestionText = null; //text of the question being parsed
            String currentKey = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName(); // get the current tag
                switch(eventType){  // handle the various xml tags encounted
                    case XmlPullParser.START_TAG: //XML opening tags
                        if (tagname.equalsIgnoreCase(Constants.XML_QUESTION)) {
                            currentKey = xpp.getAttributeValue(null, Constants.XML_QUESTION_KEY);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        currentText = xpp.getText().trim();  // get text between xml tags
                        break;
                    case XmlPullParser.END_TAG: //XML closing tags
                        if(tagname.equalsIgnoreCase(Constants.XML_QUESTION_TEXT)){
                            currentQuestionText = currentText;  //end of a question's text </question_text>
                        }
                        else if(tagname.equalsIgnoreCase(Constants.XML_ANSWER_A)){
                            answerA = currentText;
                        }
                        else if(tagname.equalsIgnoreCase(Constants.XML_ANSWER_B)){
                            answerB = currentText;
                        }
                        else if(tagname.equalsIgnoreCase(Constants.XML_ANSWER_C)){
                            answerC = currentText;
                        }
                        else if(tagname.equalsIgnoreCase(Constants.XML_ANSWER_D)){
                            answerD = currentText;
                        }
                        else if(tagname.equalsIgnoreCase(Constants.XML_ANSWER_E)){
                            answerE = currentText;
                        }
                        else if(tagname.equalsIgnoreCase(Constants.STUDENT_EMAIL)){
                            studentEmail = currentText;
                        }
                        else if(tagname.equalsIgnoreCase(Constants.XML_QUESTION)){
                            currentQuestion = new Question(currentQuestionText, answerA, answerB, answerC, answerD, answerE,
                                    currentKey, studentEmail);
                            questions.add(currentQuestion);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {e.printStackTrace();}
        catch (java.io.IOException e) {e.printStackTrace();}
        return questions;
    }
}
