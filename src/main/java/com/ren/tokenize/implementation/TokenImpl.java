package com.ren.tokenize.implementation;

import com.ren.tokenize.Token;
import com.ren.tokenize.TokenType;

import static com.ren.tokenize.implementation.TokenTypeImpl.TT_END_OF_FILE;

public class TokenImpl extends Token {
    public TokenImpl(TokenType type, String value, int offset) {
        super(type, value, offset);
    }

    public static TokenImpl forEof() {
        return new TokenImpl(TT_END_OF_FILE, null, -1);
    }
}
