package com.adgvit.appathon.model;

public class faqModel {

    String ques;
    String ans;
    public faqModel(){

    }

    public faqModel(String question, String answer) {
        this.ques = question;
        this.ans = answer;
    }

    public String getQuestion() {
        return ques;
    }

    public void setQuestion(String question) {
        this.ques = question;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
