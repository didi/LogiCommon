package com.didiglobal.logi.dsl.parse.query_string.parser;

import com.didiglobal.logi.dsl.parse.query_string.ast.QSNode;
import com.didiglobal.logi.dsl.parse.query_string.ast.QSValueNode;

public abstract class QSBaseParser {
    public QSNode parse(String queryString) throws ParseException {
        if (!queryString.contains(":") || ":".equals(queryString.trim())) {
            return new QSValueNode(queryString, null, null);
        }

        return TopLevelQuery();
    }

    public abstract QSNode TopLevelQuery() throws ParseException;


    public final String discardEscapeChar(String input) throws ParseException {
        return StringUtils.discardEscapeChar(input);
    }
}
