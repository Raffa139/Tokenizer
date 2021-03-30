package com.ren.example;

import com.ren.tokenize.Token;
import com.ren.tokenize.UnexpectedCharacterException;
import com.ren.tokenize.implementation.ResultStream;
import com.ren.tokenize.implementation.TokenImpl;
import com.ren.tokenize.implementation.TokenPatternImpl;
import com.ren.tokenize.implementation.Tokenizer;

import static com.ren.tokenize.implementation.TokenTypeImpl.*;

public class Example {
    public static void main(String[] args) throws UnexpectedCharacterException {
        Tokenizer tokenizer = new Tokenizer(
                new TokenPatternImpl(TT_NUMBER_LITERAL, "\\d+", 0),
                new TokenPatternImpl(TT_WHITESPACE, "\\s+", 0),
                new TokenPatternImpl(TT_STRING_LITERAL, "\\w+", 0)
        );

        ResultStream<TokenImpl> stream = (ResultStream<TokenImpl>) tokenizer.tokenize("say 123");

        for (Token token : stream.getTokens().asList()) {
            System.out.println(token);
        }
    }
}
