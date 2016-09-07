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
package com.wims.db;

import com.wims.data.AnswerData;
import com.wims.data.CategoryData;
import com.wims.data.QuestionData;
import com.wims.data.UserData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author lt-gbaghdas
 */
public class DataBase {

    public static final String TABLE_USERS = "users";
    public static final String TABLE_QUESTIONS = "questions";
    public static final String TABLE_ANSWERS = "answers";
    public static final String TABLE_CATEGORIES = "categories";

    public static final String COLUMN_USERS_ID = "id";
    public static final String COLUMN_USERS_USER_NAME = "user_name";
    public static final String COLUMN_USERS_EMAIL = "email";
    public static final String COLUMN_USERS_PASSWORD = "password";
    public static final String COLUMN_USERS_PIC_URL = "user_pic_url";

    public static final String COLUMN_CATEGORIES_ID = "id";
    public static final String COLUMN_CATEGORIES_NAME = "name";

    public static final String COLUMN_QUESTIONS_ID = "question_id";
    public static final String COLUMN_QUESTIONS_QUESTION = "question";
    public static final String COLUMN_QUESTIONS_USER_ID = "user_id";
    public static final String COLUMN_QUESTIONS_CATEGORY_ID = "categoryId";
    public static final String COLUMN_QUESTIONS_TITLE = "title";
    public static final String COLUMN_QUESTIONS_PUBLISH_DATE = "publish_date";

    public static final String COLUMN_ANSWERS_ID = "answer_id";
    public static final String COLUMN_ANSWERS_QUESTION_ID = "question_id";
    public static final String COLUMN_ANSWERS_USER_ID = "user_id";
    public static final String COLUMN_ANSWERS_ANSWER = "answer";
    public static final String COLUMN_ANSWERS_PUBLISH_DATE = "publish_date";

    private final HashMap<String, List> tableColumnNames = new HashMap<String, List>();
    private DatabaseAdapter databaseAdapter;

    public DataBase(DatabaseAdapter databaseAdapter) {
        this.databaseAdapter = databaseAdapter;
        initTableColumnNames();
    }

    private void initTableColumnNames() {
        List<String> usersTableColumns = new ArrayList<String>();
        usersTableColumns.add(COLUMN_USERS_USER_NAME);
        usersTableColumns.add(COLUMN_USERS_EMAIL);
        usersTableColumns.add(COLUMN_USERS_PASSWORD);
        usersTableColumns.add(COLUMN_USERS_PIC_URL);
        tableColumnNames.put(TABLE_USERS, usersTableColumns);
        
        List<String> categoriesTableColumns = new ArrayList<String>();
        categoriesTableColumns.add(COLUMN_CATEGORIES_ID);
        categoriesTableColumns.add(COLUMN_CATEGORIES_NAME);
        tableColumnNames.put(TABLE_CATEGORIES, categoriesTableColumns);

        List<String> questionsTableColumns = new ArrayList<String>();
        questionsTableColumns.add(COLUMN_QUESTIONS_QUESTION);
        questionsTableColumns.add(COLUMN_QUESTIONS_USER_ID);
        questionsTableColumns.add(COLUMN_QUESTIONS_CATEGORY_ID);
        questionsTableColumns.add(COLUMN_QUESTIONS_TITLE);
        questionsTableColumns.add(COLUMN_QUESTIONS_PUBLISH_DATE);
        tableColumnNames.put(TABLE_QUESTIONS, questionsTableColumns);

        List<String> answersTableColumns = new ArrayList<String>();
        answersTableColumns.add(COLUMN_ANSWERS_QUESTION_ID);
        answersTableColumns.add(COLUMN_ANSWERS_ANSWER);
        answersTableColumns.add(COLUMN_ANSWERS_USER_ID);
        answersTableColumns.add(COLUMN_ANSWERS_PUBLISH_DATE);
        tableColumnNames.put(TABLE_ANSWERS, answersTableColumns);
    }

    public void initDatabase(boolean local) {
        databaseAdapter.initDatabase(local);
    }

    public HashMap<Integer, UserData> getUsers() {
        HashMap<Integer, UserData> users = new HashMap<Integer, UserData>();
        ResultSetInterface rs = null;
        try {
            rs = databaseAdapter.selectQuery(TABLE_USERS);
            while (rs.next()) {
                int userId = rs.getInt(COLUMN_USERS_ID);
                String userName = rs.getString(COLUMN_USERS_USER_NAME);
                String email = rs.getString(COLUMN_USERS_EMAIL);
                String password = rs.getString(COLUMN_USERS_PASSWORD);
                String picUrl = rs.getString(COLUMN_USERS_PIC_URL);
                users.put(userId, new UserData(userId, userName, email, password, picUrl));
            }

        } catch (SqliteException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SqliteException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    public List<QuestionData> getQuestions() {
        return getQuestions(null);
    }

    public List<QuestionData> getQuestions(String condition) {
        List<QuestionData> questions = new ArrayList<QuestionData>();
        ResultSetInterface rs = null;
        try {
            rs = databaseAdapter.selectQuery(TABLE_QUESTIONS, condition,COLUMN_QUESTIONS_PUBLISH_DATE, "desc");
            while (rs.next()) {
                int userId = rs.getInt(COLUMN_QUESTIONS_USER_ID);
                String question = rs.getString(COLUMN_QUESTIONS_QUESTION);
                String title = rs.getString(COLUMN_QUESTIONS_TITLE);
                String publishDate = rs.getString(COLUMN_QUESTIONS_PUBLISH_DATE);
                int questionId = rs.getInt(COLUMN_QUESTIONS_ID);
                int categoryId = rs.getInt(COLUMN_QUESTIONS_CATEGORY_ID);
                questions.add(new QuestionData(questionId, categoryId, userId, title, publishDate, question));
            }

        } catch (SqliteException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SqliteException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }

    public HashMap<Integer, CategoryData> getCategories() {
        HashMap<Integer, CategoryData> categories = new HashMap<Integer, CategoryData>();
        ResultSetInterface rs = null;
        try {
            rs = databaseAdapter.selectQuery(TABLE_CATEGORIES);
            while (rs.next()) {
                int categoryId = rs.getInt(COLUMN_CATEGORIES_ID);
                String name = rs.getString(COLUMN_CATEGORIES_NAME);

                categories.put(categoryId, new CategoryData(categoryId, name));
            }

        } catch (SqliteException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SqliteException e) {
                e.printStackTrace();
            }
        }

        return categories;
    }

    public HashMap<Integer, AnswerData> getAnswers() {
        HashMap<Integer, AnswerData> answers = new HashMap<Integer, AnswerData>();
        ResultSetInterface rs = null;
        try {
            rs = databaseAdapter.selectQuery(TABLE_ANSWERS);
            while (rs.next()) {
                int answerId = rs.getInt(COLUMN_ANSWERS_ID);
                int questionId = rs.getInt(COLUMN_ANSWERS_QUESTION_ID);
                int userId = rs.getInt(COLUMN_ANSWERS_USER_ID);
                String answer = rs.getString(COLUMN_ANSWERS_ANSWER);
                String publishDate = rs.getString(COLUMN_ANSWERS_PUBLISH_DATE);
                answers.put(answerId, new AnswerData(answerId, questionId, userId, publishDate, answer));
            }

        } catch (SqliteException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SqliteException e) {
                e.printStackTrace();
            }
        }

        return answers;
    }

    public void addQuestion(List<String> values) {
        try {
             databaseAdapter.insertRowQuery(TABLE_QUESTIONS, tableColumnNames.get(TABLE_QUESTIONS), values);
        } catch (SqliteException e) {
            e.printStackTrace();
        } 
    }


    
    public void addUser(List<String> values) {
        try {
             databaseAdapter.insertRowQuery(TABLE_USERS, tableColumnNames.get(TABLE_USERS), values);
        } catch (SqliteException e) {
            e.printStackTrace();
        } 
    }

    public void addAnswer(List<String> values) {
        try {
             databaseAdapter.insertRowQuery(TABLE_ANSWERS, tableColumnNames.get(TABLE_ANSWERS), values);
        } catch (SqliteException e) {
            e.printStackTrace();
        } 
    }
    
    public void updateUserData(Pair<String,String> condition,HashMap<String,String> values) {
        try {
             databaseAdapter.updateQuery(TABLE_USERS, condition, values);
        } catch (SqliteException e) {
            e.printStackTrace();
        } 
    }
}
