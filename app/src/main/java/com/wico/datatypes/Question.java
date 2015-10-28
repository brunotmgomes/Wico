package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.wico.exceptions.WicoParseException;

import java.util.ArrayList;

@ParseClassName("Question")
public final class Question extends ParseObject{

    public static final class Builder{
        private String title;
        private String content;
        private int numberOfAnswers;

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Builder numberOfAnswers(int numberOfAnswers){
            this.numberOfAnswers = numberOfAnswers;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }

    /** The method below (public Question()) , although empty, is mandatory to allow question object to be used as a parse object.
     */
    public Question(){

    }

    private Question(Builder builder){
        put("title", builder.title);
        put("content",builder.content);
        put("answers",builder.numberOfAnswers);
    }

    public String getTitle(){
        return getString("title");
    }

    public String getContent(){
        return getString("content");
    }

    public String getNumberOfAwnsers(){
        return getString("numberOfAnswers");
    }

    public ArrayList<Question> saveArrayOfQuestions(){
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.whereExists("content");
        query.orderByDescending("createdAt");
        ArrayList<Question> questions = new ArrayList<>();
        try{
            questions.addAll(query.find());
        }catch (ParseException exception) {
            throw new WicoParseException();
        }
         return questions;
    }
}