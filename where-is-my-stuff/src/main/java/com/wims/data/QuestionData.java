/*
 * Copyright 2016 lt-gbaghdas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wims.data;

import com.wims.db.DataManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lt-gbaghdas
 */
public class QuestionData {
    
    private int id;
    private int userId;
    private int categoryId;
    private String question;
    private String title;
    private String publishDate;
    
    public QuestionData(int id, int categoryId,int userId,String title,String publishDate, String question){
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
        this.title = title;
        this.question = question;
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate.substring(0,10);
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
  
    
    public List<AnswerData> getAnswers(){
        HashMap <Integer , AnswerData> answers = DataManager.$().getDataBase().getAnswers();
        List <AnswerData> result = new ArrayList<AnswerData>();
        for (Map.Entry pair : answers.entrySet()) {
            if(((AnswerData)pair.getValue()).getQuestionId() == id)
                result.add((AnswerData) pair.getValue());
        }
        return result;
    }
    
    public CategoryData getCategory(){
        return DataManager.$().getDataBase().getCategories().get(categoryId);
    }
    
    public UserData getUser(){
        return DataManager.$().getDataBase().getUsers().get(userId);
    }
}
