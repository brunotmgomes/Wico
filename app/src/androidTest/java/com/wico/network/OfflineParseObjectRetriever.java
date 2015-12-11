package com.wico.network;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.datatypes.WicoPage;
import com.wico.exceptions.WicoParseException;
import com.wico.network.interfaces.ParseObjectRetriever;

import java.util.ArrayList;

public class OfflineParseObjectRetriever implements ParseObjectRetriever {


    @Override
    public WicoPage getPage(String id) {
        ParseQuery<WicoPage> query = createPageQuery(id);
        WicoPage page;
        try {
            page = query.getFirst();
        } catch (ParseException e){
            throw new WicoParseException();
        }
        return page;
    }

    @Override
    public Question getQuestion(String questionId) {
        return null;
    }

    @Override
    public ArrayList<Question> getQuestions(String parentPageId) {
        ParseQuery<Question> query = createQuestionsQuery(parentPageId);
        ArrayList<Question> questions = new ArrayList<>();
        try {
            questions.addAll(query.find());
        } catch (ParseException e) {
            throw new WicoParseException();
        }
        return questions;
    }

    @Override
    public ArrayList<Answer> getAnswersForQuestion(String questionId) {
        return null;
    }

    @Override
    public ArrayList<WicoPage> getChildrenPages(String parentPageId) {
        ParseQuery<WicoPage> query = createChildrenPagesQuery(parentPageId);
        ArrayList<WicoPage> wicoPages = new ArrayList<>();
        try {
            wicoPages.addAll(query.find());
        } catch (ParseException e) {
            throw new WicoParseException();
        }
        return wicoPages;
    }

    private ParseQuery<WicoPage> createPageQuery(String pageId){
        ParseQuery<WicoPage> query = ParseQuery.getQuery(WicoPage.class);
        query.fromLocalDatastore();
        query.whereEqualTo("objectId", pageId);
        return query;
    }

    private ParseQuery<Question> createQuestionsQuery(String pageId) {
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.fromLocalDatastore();
        query.whereEqualTo("parentId", pageId);
        return query;
    }

    private ParseQuery<WicoPage> createChildrenPagesQuery(String wicoPageId) {
        ParseQuery<WicoPage> query = ParseQuery.getQuery(WicoPage.class);
        query.fromLocalDatastore();
        query.whereEqualTo("parentId",wicoPageId);
        return query;
    }

    private ParseQuery<Question> createQuestionQuery(String questionId){
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.fromLocalDatastore();
        query.whereEqualTo("objectId", questionId);

        return query;
    }




}
