/*******************************************************************************
 *    ___                  _   ____  ____
 *   / _ \ _   _  ___  ___| |_|  _ \| __ )
 *  | | | | | | |/ _ \/ __| __| | | |  _ \
 *  | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *   \__\_\\__,_|\___||___/\__|____/|____/
 *
 * Copyright (c) 2014-2016 Appsicle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.questdb.ql.ops.col;

import com.questdb.io.sink.CharSink;
import com.questdb.ql.Record;
import com.questdb.ql.StorageFacade;
import com.questdb.ql.ops.AbstractVirtualColumn;
import com.questdb.store.ColumnType;
import com.questdb.store.SymbolTable;
import com.questdb.store.VariableColumn;

public class SymRecordSourceColumn extends AbstractVirtualColumn {
    private final int index;
    private SymbolTable symbolTable;

    public SymRecordSourceColumn(int index) {
        super(ColumnType.SYMBOL);
        this.index = index;
    }

    @Override
    public CharSequence getFlyweightStr(Record rec) {
        return rec.getSym(index);
    }

    @Override
    public CharSequence getFlyweightStrB(Record rec) {
        return rec.getSym(index);
    }

    @Override
    public int getInt(Record rec) {
        return rec.getInt(index);
    }

    @Override
    public CharSequence getStr(Record rec) {
        return rec.getSym(index);
    }

    @Override
    public void getStr(Record rec, CharSink sink) {
        sink.put(rec.getSym(index));
    }

    @Override
    public int getStrLen(Record rec) {
        CharSequence cs = rec.getSym(index);
        return cs == null ? VariableColumn.NULL_LEN : cs.length();
    }

    @Override
    public String getSym(Record rec) {
        return rec.getSym(index);
    }

    @Override
    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public void prepare(StorageFacade facade) {
        this.symbolTable = facade.getSymbolTable(index);
    }
}