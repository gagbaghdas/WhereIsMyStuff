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

/**
 *
 * @author lt-gbaghdas
 */
package com.wims.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDatabaseAdapter extends DatabaseAdapter {

    private SingletonConnectionPool connectionPool;

    @Override
    public void init() {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        int index = path.lastIndexOf("target");
        String scriptPath = path.substring(0, index);
        connectionPool = new SingletonConnectionPool("jdbc:sqlite:"+scriptPath+"WhereISMyStuff.s3db");
    }

    @Override
    public void executeUpdate(String query)  throws SqliteException {
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new SqliteException(e);
            
        }
    }

    @Override
    public ResultSetInterface executeQuery(String query) throws SqliteException {
        ResultSetInterface rsInterface = null;
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rsInterface = new JdbcResultSet(rs);
            
        } catch (SQLException e) {
            throw new SqliteException(e);
        }

        return rsInterface;
    }

    @Override
    public void close() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = connectionPool.getConnection();
        } catch (SQLException e) {
            
        }
        return conn;
    }
}
