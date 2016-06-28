package com.byodmidtermapp;

/**
 * Created by admin on 2016-02-01.
 */
public class Constants {

    // XML tags used to defined for midterm of multiple choice questions.
    static final String XML_QUESTION = "question";
    static final String XML_QUESTION_TEXT = "question_text";
    static final String XML_ANSWER_A = "answerA";
    static final String XML_ANSWER_B = "answerB";
    static final String XML_ANSWER_C = "answerC";
    static final String XML_ANSWER_D = "answerD";
    static final String XML_ANSWER_E = "answerE";
    static final String XML_QUESTION_KEY = "questionkey";
    static final String STUDENT_NAME = "student_name";
    static final String STUDENT_NUMBER = "student_number";
    static final String STUDENT_EMAIL = "student_email";
    static final String STUDENT_ANSWER_XML = "student_answers";
    static final String BUNDLE_XML = "student_bundle";

    static final String STU_MID_ANSW_OPEN = "<student_midterm_answers>";
    static final String STU_MID_ANSW_CLOSE = "</student_midterm_answers>";
    static final String STU_NAME_OPEN = "<student_name>";
    static final String STU_NAME_CLOSE = "</student_name>";
    static final String STU_NUM_OPEN = "<student_number>";
    static final String STU_NUM_CLOSE = "</student_number>";
    static final String STU_ANSWERS = "</student_answer>";
    static final String STU_ANSWERS_KEY = "<student_answer questionkey=";
    static final String BLANK_ANSWERS = "<student_answer>none</student_answer>";

    static final String EMAIL_TO_STUDENT = "Email Student Answers";
    static final String STATE_questionIndex = "STATE_questionIndex";
    static final String ARRAY_LIST_SIZE = "arrayListSize", MAP_SIZE = "mapSize";
    static final String EMAIL_SUBJECT = "Student Midterm Answers";
    static final String Q_1 = "Q1", Q_2 = "Q2", Q_3 = "Q3", Q_4 = "Q4", Q_5 = "Q5", Q_6 = "Q6", Q_7 = "Q7", Q_8 = "Q8";
    static final String Q_9 = "Q9", Q_10 = "Q10", Q_11 = "Q11", Q_12 = "Q12", Q_13 = "Q13", Q_14 = "Q14", Q_15 = "Q15";
    static final String A = "A", B = "B", C = "C", D = "D", E = "E";
    static final int NUMBER_OF_QUESTIONS = 15;
}
