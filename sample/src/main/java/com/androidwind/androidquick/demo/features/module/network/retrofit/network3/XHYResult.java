package com.androidwind.androidquick.demo.features.module.network.retrofit.network3;

import java.io.Serializable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class XHYResult implements Serializable {
    private String question;
    private String answer;
    private String zimu;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getZimu() {
        return zimu;
    }

    public void setZimu(String zimu) {
        this.zimu = zimu;
    }
}
