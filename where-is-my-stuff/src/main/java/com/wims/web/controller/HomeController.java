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
import com.wims.db.DataBase;
import com.wims.db.DataManager;
import java.io.FileReader;
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
public class HomeController {

    @RequestMapping(value = {"/", "/home**"}, method = RequestMethod.GET)
    public ModelAndView homePage(@RequestParam(value = "user_id", required = false) int userId) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Where Is My Stuff");
        model.addObject("questions", DataManager.$().getDataBase().getQuestions());
        model.addObject("categories", DataManager.$().getDataBase().getCategories());
        model.addObject("user_name", userId == 0 ? "sign in" : DataManager.$().getDataBase().getUsers().get(userId).getUserName());
        model.addObject("user_id", userId);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = {"/", "/filter**"}, method = RequestMethod.GET)
    public ModelAndView filterAction(@RequestParam(value = "category_id", required = false) int categoryId,
            @RequestParam(value = "user_id", required = false) int userId) {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Where Is My Stuff");
        model.addObject("questions", categoryId == 0 ? DataManager.$().getDataBase().getQuestions() : DataManager.$().getDataBase().getQuestions(DataBase.COLUMN_QUESTIONS_CATEGORY_ID + "=" + categoryId));
        model.addObject("categories", DataManager.$().getDataBase().getCategories());
        model.addObject("user_name", userId == 0 ? "sign in" : DataManager.$().getDataBase().getUsers().get(userId).getUserName());
        model.addObject("user_id", userId);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = {"/", "/ask_question**"}, method = RequestMethod.GET)
    public ModelAndView askQuestionAction(@RequestParam(value = "user_id", required = false) int userId) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Where Is My Stuff");
        model.addObject("user_data", DataManager.$().getDataBase().getUsers().get(userId));
        model.addObject("categories", DataManager.$().getDataBase().getCategories());
        model.setViewName("ask_question");
        return model;
    }

    @RequestMapping(value = {"/", "/view_profile**"}, method = RequestMethod.GET)
    public ModelAndView viewProfileAction(@RequestParam(value = "user_id", required = false) int userId,
            @RequestParam(value = "current_user_id", required = false) int currentUserId) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Where Is My Stuff");
        model.addObject("user_data", DataManager.$().getDataBase().getUsers().get(userId));
        model.addObject("user_profile_pic" ,getProfilePicPath() + DataManager.$().getDataBase().getUsers().get(userId).getPicUrl());
        model.addObject("current_user_id", currentUserId);
        model.addObject("current_user_data", currentUserId == 0 ? "" : DataManager.$().getDataBase().getUsers().get(currentUserId));
        model.setViewName("profile");
        return model;
    }

    @RequestMapping(value = {"/", "/view_question**"}, method = RequestMethod.GET)
    public ModelAndView viewQuestionAction(@RequestParam(value = "question_id", required = false) int questionId,
            @RequestParam(value = "current_user_id", required = false) int currentUserId) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Where Is My Stuff");
        model.addObject("question_data", DataManager.$().getQuestion(questionId));
        model.addObject("current_user_id", currentUserId);
        model.addObject("current_user_data", currentUserId == 0 ? "" : DataManager.$().getDataBase().getUsers().get(currentUserId));
        model.setViewName("answer_the_question");
        return model;
    }
    
    private QuestionData getQuestion(int questionId){
        List<QuestionData> questions = DataManager.$().getDataBase().getQuestions();
        for(QuestionData question : questions){
            if(questionId==question.getId())
                return question;
        }
            return null;
    }
    
    private String getProfilePicPath(){
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        int index = path.lastIndexOf("classes");
        return path.substring(1, index);
    }

}
