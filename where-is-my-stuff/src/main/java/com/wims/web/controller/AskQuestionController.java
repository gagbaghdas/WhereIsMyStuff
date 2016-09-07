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
package com.wims.web.controller;

import com.wims.data.QuestionData;
import com.wims.db.DataManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lt-gbaghdas
 */
@Controller
public class AskQuestionController {

    @RequestMapping(value = {"/", "/post_question**"}, method = RequestMethod.GET)
    public ModelAndView viewProfileAction(@RequestParam(value = "user_id", required = false) int userId,
            @RequestParam(value = "current_user_id", required = false) int currentUserId,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "publish_date", required = false) String publishDate,
            @RequestParam(value = "category_id", required = false) int categoryId) {
        
        insertQuestionDataToDb(new QuestionData(0,categoryId,userId,title, publishDate,text));
        
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Where Is My Stuff");
        model.addObject("questions", DataManager.$().getDataBase().getQuestions());
        model.addObject("categories", DataManager.$().getDataBase().getCategories());
        model.addObject("user_name", currentUserId == 0 ? "sign in" : DataManager.$().getDataBase().getUsers().get(currentUserId).getUserName());
        model.addObject("user_id", currentUserId);
        model.setViewName("home");
        return model;
    }
    
    private void insertQuestionDataToDb(QuestionData questionData){
        List<String>  values = new ArrayList<String>();
        values.add(questionData.getQuestion());
        values.add(String.valueOf(questionData.getUserId()));
        values.add(String.valueOf(questionData.getCategoryId()));
        values.add(questionData.getTitle());
        values.add(questionData.getPublishDate());
        DataManager.$().getDataBase().addQuestion(values);
    }
    
}
