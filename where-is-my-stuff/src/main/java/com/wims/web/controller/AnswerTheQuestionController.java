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

import com.wims.data.AnswerData;
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
public class AnswerTheQuestionController {
    
    @RequestMapping(value = {"/", "/post_answer**"}, method = RequestMethod.GET)
    public ModelAndView viewProfileAction(@RequestParam(value = "user_id", required = false) int userId,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "publish_date", required = false) String publishDate,
            @RequestParam(value = "question_id", required = false) int questionId) {
        
        insertAnswerDataToDb(new AnswerData(0,questionId,userId,publishDate,text));
        
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Where Is My Stuff");
        model.addObject("question_data", DataManager.$().getQuestion(questionId));
        model.addObject("current_user_id", userId);
        model.addObject("current_user_data", DataManager.$().getDataBase().getUsers().get(userId));
        model.setViewName("answer_the_question");
        return model;
    }
    
    private void insertAnswerDataToDb(AnswerData answerData){
        List<String>  values = new ArrayList<String>();
        values.add(String.valueOf(answerData.getQuestionId()));
        values.add(answerData.getAnswer());
        values.add(String.valueOf(answerData.getUserId()));
        values.add(answerData.getPublishDate());
        DataManager.$().getDataBase().addAnswer(values);
    }
    
}
