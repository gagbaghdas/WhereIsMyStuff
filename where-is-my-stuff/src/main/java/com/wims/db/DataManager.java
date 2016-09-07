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

import com.wims.data.QuestionData;
import java.util.List;

/**
 *
 * @author lt-gbaghdas
 */
public class DataManager {
    private final DataBase dataBase;
    private static DataManager instance;
    
    private DataManager(){
        dataBase = new DataBase(new JdbcDatabaseAdapter());
        dataBase.initDatabase(true);
    }
    
    public static DataManager $(){
        return instance == null ? instance = new DataManager() : instance;
    }
    
    public DataBase getDataBase(){
        return dataBase;
    }
    
    public QuestionData getQuestion(int questionId){
        List<QuestionData> questions = dataBase.getQuestions();
        for(QuestionData question : questions){
            if(questionId==question.getId())
                return question;
        }
            return null;
    }
}
