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

/**
 *
 * @author lt-gbaghdas
 */
public class AnswerData {
    
    private int id;
    private int questionId;
    private int userId;
    private String answer;
    private String publishDate;
    
    public AnswerData(int id, int questionId, int userId, String publishDate,String answer){
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.answer = answer;
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    
    public UserData getUser(){
        return DataManager.$().getDataBase().getUsers().get(userId);
    }
   
}
