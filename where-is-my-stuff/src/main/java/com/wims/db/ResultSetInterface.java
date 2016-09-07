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

/**
 *
 * @author lt-gbaghdas
 */
public interface ResultSetInterface {
    void close() throws SqliteException;
    boolean next() throws SqliteException;
    int getInt(String columnName) throws SqliteException;
    long getLong(String columnName) throws SqliteException;
    float getFloat(String columnName) throws SqliteException;
    String getString(String columnName) throws SqliteException;
    boolean isNull(String columnName) throws SqliteException;
}
