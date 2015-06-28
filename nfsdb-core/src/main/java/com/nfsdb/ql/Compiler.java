/*******************************************************************************
 *   _  _ ___ ___     _ _
 *  | \| | __/ __| __| | |__
 *  | .` | _|\__ \/ _` | '_ \
 *  |_|\_|_| |___/\__,_|_.__/
 *
 *  Copyright (c) 2014-2015. The NFSdb project and its contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/

package com.nfsdb.ql;

import com.nfsdb.exceptions.JournalException;
import com.nfsdb.factory.JournalReaderFactory;
import com.nfsdb.ql.parser.Optimiser;
import com.nfsdb.ql.parser.ParserException;
import com.nfsdb.ql.parser.QueryParser;

public class Compiler {

    private final QueryParser parser = new QueryParser();
    private final Optimiser optimiser = new Optimiser();

    public RecordSource<? extends Record> compile(CharSequence query, JournalReaderFactory factory) throws ParserException, JournalException {
        parser.setContent(query);
        return optimiser.compile(parser.parse().getQueryModel(), factory);
    }
}