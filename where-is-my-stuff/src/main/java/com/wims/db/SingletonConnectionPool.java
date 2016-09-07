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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lt-gbaghdas
 */
public class SingletonConnectionPool {
    private final String jdbcUrl;
    private Connection connection;

    public SingletonConnectionPool(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(jdbcUrl);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SingletonConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
}

