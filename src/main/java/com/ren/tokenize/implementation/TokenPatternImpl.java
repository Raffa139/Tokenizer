package com.ren.tokenize.implementation;

import com.ren.tokenize.TokenPattern;
import com.ren.tokenize.Token;
import com.ren.tokenize.TokenType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenPatternImpl extends TokenPattern {
    public TokenPatternImpl(TokenType returnType, String pattern, int priority) {
        super(returnType, pattern, priority);
    }

    @Override
    public Token tokenize(String source, int current) {
        Matcher matcher = Pattern.compile(pattern).matcher(source);

        String value;

        if (matcher.find(current)) {
            if (matcher.start() != current) {
                return null;
            }
            value = matcher.group();
        } else {
            return null;
        }

        return new TokenImpl(returnType, value, matcher.start());
    }
}
