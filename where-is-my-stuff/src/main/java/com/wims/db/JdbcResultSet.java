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

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lt-gbaghdas
 */
public class JdbcResultSet implements ResultSetInterface {
    private final ResultSet rs;

    public JdbcResultSet(ResultSet rs) {
        this.rs = rs;
    }

    @Override
    public void close() throws SqliteException {
        try {
            rs.close();
        } catch (SQLException e) {
            throw new SqliteException(e);
        }
    }

    @Override
    public boolean next() throws SqliteException {
        boolean hasNext;
        try {
            hasNext = rs.next();
        } catch (SQLException e) {
            throw new SqliteException(e);
        }
        return hasNext;
    }

    @Override
    public int getInt(String columnName) throws SqliteException {
        int nextInt = 0;
        try {
            nextInt = rs.getInt(columnName);
        } catch (SQLException e) {
            throw new SqliteException(e);
        }
        return nextInt;
    }

    @Override
    public long getLong(String columnName) throws SqliteException {
        long nextLong = 0;
        try {
            nextLong = rs.getLong(columnName);
        } catch (SQLException e) {
            throw new SqliteException(e);
        }
        return nextLong;
    }

    @Override
    public float getFloat(String columnName) throws SqliteException {
        float nextFloat = 0f;
        try {
            nextFloat = rs.getFloat(columnName);
        } catch (SQLException e) {
            throw new SqliteException(e);
        }
        return nextFloat;
    }

    @Override
    public String getString(String columnName) throws SqliteException {
        String nextString = null;
        try {
            nextString = rs.getString(columnName);
        } catch (SQLException e) {
            throw new SqliteException(e);
        }
        return nextString;
    }

    @Override
    public boolean isNull(String columnName) throws SqliteException {
        Object nextObject = null;
        try {
            nextObject = rs.getObject(columnName);
        } catch (SQLException e) {
            throw new SqliteException(e);
        }
        return nextObject == null;
    }
}
