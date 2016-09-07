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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

public abstract class DatabaseAdapter {

    protected static final String DB_NAME = "where_is_my_stuff";
    protected static final int DB_VERSION = 1;
    protected static final String DB_FILE = "resources/db.sql";
    protected boolean isInitialized = false;

    final public void initDatabase(boolean local) {
        init();
        if (local) {
            try {
                initFromSql();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void initFromSql() throws IOException {
        
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        int index = path.lastIndexOf("classes");
        String scriptPath = path.substring(0, index);
        FileReader fr = new FileReader(scriptPath + DB_FILE);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        try {
            while (line != null) {
                line = br.readLine();
                if (line != null && !line.equals("")) {
                    executeUpdate(line);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } catch (SqliteException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            br.close();
            fr.close();
        }
    }

    public boolean initialized() {
        return isInitialized;
    }

    protected abstract void init();

    public abstract void close();

    protected abstract void executeUpdate(String query) throws SqliteException;

    protected abstract ResultSetInterface executeQuery(String query) throws SqliteException;

    public ResultSetInterface selectQuery(String tableName) throws SqliteException {
        return selectQuery(tableName, null);
    }

    public ResultSetInterface selectQuery(String tableName, String condition) throws SqliteException {
        return executeQuery("select * from " + tableName + (condition != null ? " where " + condition : ""));
    }

    public ResultSetInterface selectQuery(String tableName, String condition, String orderBy, String orderType) throws SqliteException {
        return executeQuery("select * from " + tableName + (condition != null ? " where " + condition : "") + " order by " + orderBy + " " + orderType);
    }

    public void insertRowQuery(String tableName, List<String> tableColumnNames, List<String> values) throws SqliteException {
        String columnNames = "";
        String columnValues = "";

        for (String column : tableColumnNames) {
            columnNames += column + ", ";
        }

        for (String columnValue : values) {
            columnValues += "'" + columnValue + "'" + ", ";
        }

        int lastIndex = columnNames.lastIndexOf(",");
        if (lastIndex > 0) {
            columnNames = columnNames.substring(0, lastIndex);
        }

        lastIndex = columnValues.lastIndexOf(",");
        if (lastIndex > 0) {
            columnValues = columnValues.substring(0, lastIndex);
        }
        String query = "insert into " + tableName + (columnNames.equals("") ? "" : "(" + columnNames + ")") + " values(" + columnValues + ");";
        executeUpdate(query);

    }

    public void insertRowQuery(String tableName, List<String> values) throws SqliteException {
        insertRowQuery(tableName, new ArrayList<String>(), values);
    }

    public void dropQuery(String tableName) throws SqliteException {
        executeUpdate("drop table if exists " + tableName);
    }

    public void updateQuery(String tableName, Pair<String, String> condition, HashMap<String, String> values) throws SqliteException {
        String query = "update " + tableName + " set ";
        for (Map.Entry pair : values.entrySet()) {
            query += (pair.getKey() + " = " + "'" + pair.getValue() + "',");
        }
        int lastIndex = query.lastIndexOf(",");
        if (lastIndex > 0) {
            query = query.substring(0, lastIndex);
        }

        query += (" where " + condition.getKey() + " = " + "'" + condition.getValue() + "'");
        executeUpdate(query);
    }

    public void createQuery(String tableName, String... columnDescriptions) throws SqliteException {
        String columns = "";
        for (String columnDescription : columnDescriptions) {
            columns += columnDescription + ", ";
        }

        int lastIndex = columns.lastIndexOf(",");
        if (lastIndex > 0) {
            columns = columns.substring(0, lastIndex);
        }
        executeUpdate("create table " + tableName + " (" + columns + ")");
    }

}
