package com.didiglobal.logi.dsl.parse.dsl.parser.root;

import com.didiglobal.logi.dsl.parse.dsl.ast.common.KeyWord;
import com.didiglobal.logi.dsl.parse.dsl.ast.common.value.ValueNode;
import com.didiglobal.logi.dsl.parse.dsl.ast.root.Profile;
import com.didiglobal.logi.dsl.parse.dsl.parser.DslParser;
import com.didiglobal.logi.dsl.parse.dsl.parser.ParserType;

public class ProfileParser extends DslParser {
    public ProfileParser(ParserType type) {
        super(type);
    }

    @Override
    public KeyWord parse(String name, Object obj) {
        Profile node = new Profile(name);
        node.n = ValueNode.getValueNode(obj);
        return node;
    }
}
