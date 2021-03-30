package com.ren.tokenize.implementation;

import com.ren.tokenize.Token;
import com.ren.tokenize.UnexpectedCharacterException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.ren.tokenize.implementation.TokenTypeImpl.TT_IGNORED;
import static com.ren.tokenize.implementation.TokenTypeImpl.TT_WHITESPACE;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TokenizerTest {
    private static final String SRC = "a = 12;print();";

    private Tokenizer tokenizer;

    private TokenPatternImpl tp1;
    private TokenPatternImpl tp2;

    @Before
    public void setUp() {
        tp1 = new TokenPatternImpl(TT_WHITESPACE, "\\s+", 0);
        tp2 = new TokenPatternImpl(TT_IGNORED, "[^\\s]", 0);

        tokenizer = new Tokenizer();
        tokenizer.addTokenPattern(tp1);
        tokenizer.addTokenPattern(tp2);
    }

    @Test
    public void tokenize() throws UnexpectedCharacterException {
        ResultStream stream = (ResultStream) tokenizer.tokenize(SRC);

        assertThat(stream, is(notNullValue()));

        List<Token> tokens = stream.getTokens().asList();
        assertThat(tokens.size(), is(15));
        assertThat(tokens, hasItems(new TokenImpl(TT_WHITESPACE, " ", 1)));
        assertThat(tokens, hasItems(new TokenImpl(TT_IGNORED, "p", 7)));
        assertThat(tokens.get(tokens.size()-1), is(equalTo(new TokenImpl(TT_IGNORED, ";", 14))));
    }

    @Test(expected = UnexpectedCharacterException.class)
    public void tokenizeException() throws UnexpectedCharacterException {
        tokenizer.removeTokenPattern(tp2);
        ResultStream stream = (ResultStream) tokenizer.tokenize(SRC);
    }
}