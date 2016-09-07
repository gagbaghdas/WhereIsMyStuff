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

import com.wims.data.UserData;
import com.wims.db.DataManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class SignInController {

    @RequestMapping(value = {"/", "/login_**"}, method = RequestMethod.GET)
    public ModelAndView loginAction(@RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "password", required = false) String password) {

        ModelAndView model = new ModelAndView();
        if (userName == null || userName.equals("")) {
            model.addObject("title", "Where Is My Stuff");
            model.addObject("error", "Please insert user name");
            model.setViewName("login");
            return model;
        }

        if (password == null || password.equals("")) {
            model.addObject("title", "Where Is My Stuff");
            model.addObject("error", "Please insert password");
            model.setViewName("login");
            return model;
        }

        if (isRegisteredUser(userName, password)) {
            model.addObject("title", "Where Is My Stuff");
            model.addObject("questions", DataManager.$().getDataBase().getQuestions());
            model.addObject("categories", DataManager.$().getDataBase().getCategories());
            model.addObject("user_name", userName);
            model.addObject("user_id", getUserId(userName, password));
            model.setViewName("home");
            return model;
        }
        model.addObject("title", "Where Is My Stuff");
        model.addObject("error", "User name or/and password is/are incorrect");
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = {"/", "/signup_**"}, method = RequestMethod.GET)
    public ModelAndView viewProfileAction(@RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "email", required = false) String email) {

        ModelAndView model = new ModelAndView();
        if (userName == null || userName.equals("")) {
            model.addObject("title", "Where Is My Stuff");
            model.addObject("response", "Username must be non empty.");
            model.setViewName("login");
            return model;
        }

        if (password == null || password.equals("")) {
            model.addObject("title", "Where Is My Stuff");
            model.addObject("response", "Password must be non empty.");
            model.setViewName("login");
            return model;
        }
        if (email == null || email.equals("")) {
            model.addObject("title", "Where Is My Stuff");
            model.addObject("response", "Email must be non empty.");
            model.setViewName("login");
            return model;
        }
        if (isRegisteredUser(userName, password)) {
            model.addObject("title", "Where Is My Stuff");
            model.addObject("response", "User with this name and password already registered");
            model.setViewName("login");
            return model;
        } else if (isRegisteredEmail(email)) {
            model.addObject("title", "Where Is My Stuff");
            model.addObject("response", "User with this email already registered");
            model.setViewName("login");
            return model;
        } else if (!isValidEmailAddress(email)) {
            model.addObject("title", "Where Is My Stuff");
            model.addObject("response", "Email not valid");
            model.setViewName("login");
            return model;
        }

        insertUserDataToDb(new UserData(0, userName, email, password, "resources/images/profile.png"));
        model.addObject("title", "Where Is My Stuff");
        model.addObject("response", "You have successfully registered");
        model.setViewName("login");
        return model;
    }

    private boolean isRegisteredUser(String userName, String password) {
        for (Map.Entry pair : DataManager.$().getDataBase().getUsers().entrySet()) {
            if (((UserData) pair.getValue()).getUserName().equals(userName) && ((UserData) pair.getValue()).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRegisteredEmail(String email) {
        for (Map.Entry pair : DataManager.$().getDataBase().getUsers().entrySet()) {
            if (((UserData) pair.getValue()).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private int getUserId(String userName, String password) {
        for (Map.Entry pair : DataManager.$().getDataBase().getUsers().entrySet()) {
            if (((UserData) pair.getValue()).getUserName().equals(userName) && ((UserData) pair.getValue()).getPassword().equals(password)) {
                return ((UserData) pair.getValue()).getUserId();
            }
        }
        return 0;
    }

    private void insertUserDataToDb(UserData userData) {
        List<String> values = new ArrayList<String>();
        values.add(userData.getUserName());
        values.add(userData.getEmail());
        values.add(userData.getPassword());
        values.add(userData.getPicUrl());
        DataManager.$().getDataBase().addUser(values);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
