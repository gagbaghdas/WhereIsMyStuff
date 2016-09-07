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
import com.wims.db.DataBase;
import com.wims.db.DataManager;
import java.util.HashMap;
import javafx.util.Pair;
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
public class ProfileController {

    @RequestMapping(value = {"/", "/save_user_data**"}, method = RequestMethod.GET)
    public ModelAndView saveUserAction(@RequestParam(value = "user_id", required = false) int userId,
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "pic_url", required = false) String picUrl) {

        updateUserDataInDb(new UserData(userId, userName, email, password, picUrl));

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Where Is My Stuff");
        model.addObject("user_data", DataManager.$().getDataBase().getUsers().get(userId));
        model.addObject("current_user_id", userId);
        model.setViewName("profile");
        return model;
    }

    private void updateUserDataInDb(UserData userData) {
        Pair<String, String> condition = new Pair<String, String>(DataBase.COLUMN_USERS_ID, String.valueOf(userData.getUserId()));
        HashMap<String, String> values = new HashMap<String, String>();
        values.put(DataBase.COLUMN_USERS_USER_NAME, userData.getUserName());
        if (!userData.getPassword().equals("")) {
            values.put(DataBase.COLUMN_USERS_PASSWORD, userData.getPassword());
        }
        values.put(DataBase.COLUMN_USERS_EMAIL, userData.getEmail());
        values.put(DataBase.COLUMN_USERS_PIC_URL, userData.getPicUrl());

        DataManager.$().getDataBase().updateUserData(condition, values);
    }
}
