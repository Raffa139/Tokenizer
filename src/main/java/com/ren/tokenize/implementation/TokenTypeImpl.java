package com.ren.tokenize.implementation;

import com.ren.tokenize.TokenType;

public enum TokenTypeImpl implements TokenType {
    TT_NUMBER_LITERAL("NUM"),
    TT_STRING_LITERAL("STR"),
    TT_OPERATION("OP"),
    TT_PAREN_OPEN("LPAREN"),
    TT_PAREN_CLOSE("RPAREN"),
    TT_WHITESPACE("WHTSPCE"),
    TT_IGNORED("IGNRD"),
    TT_END_OF_FILE("EOF");

    private final String value;

    TokenTypeImpl(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
