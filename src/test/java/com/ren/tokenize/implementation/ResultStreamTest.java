package com.ren.tokenize.implementation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.ren.tokenize.implementation.TokenTypeImpl.TT_IGNORED;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResultStreamTest {
    private ResultStream stream;

    @Before
    public void setUp() throws Exception {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.addTokenPattern(new TokenPatternImpl(TT_IGNORED, ".", 0));
        stream = (ResultStream) tokenizer.tokenize("print();");
    }

    @Test
    public void testConstructor() {
        ResultStream stream = new ResultStream(new ArrayList<>());

        assertThat(stream, is(notNullValue()));
        assertThat(stream.getTokens(), is(notNullValue()));
        assertThat(stream.getPos(), is(-1));
    }

    @Test
    public void getCurrent() {
        stream.reset();

        TokenImpl tokenImpl = (TokenImpl) stream.getCurrent();

        assertThat(tokenImpl, is(nullValue()));

        stream.getNext();

        tokenImpl = (TokenImpl) stream.getCurrent();
        assertThat(tokenImpl, is(notNullValue()));
        assertThat(tokenImpl.getType(), is(equalTo(TT_IGNORED)));
        assertThat(tokenImpl.getValue(), is(equalTo("p")));
        assertThat(tokenImpl.getOffset(), is(equalTo(0)));
    }

    @Test
    public void getCurrentValue() {
        stream.reset();

        String value = stream.getCurrentValue();

        assertThat(value, is(nullValue()));

        stream.getNext();

        value = stream.getCurrentValue();
        assertThat(value, is(notNullValue()));
        assertThat(value, is(equalTo("p")));
    }

    @Test
    public void getCurrentType() {
        stream.reset();

        TokenTypeImpl type = (TokenTypeImpl) stream.getCurrentType();

        assertThat(type, is(nullValue()));

        stream.getNext();

        type = (TokenTypeImpl) stream.getCurrentType();
        assertThat(type, is(equalTo(TT_IGNORED)));
    }

    @Test
    public void getNext() {
        stream.reset();

        TokenImpl tokenImpl = (TokenImpl) stream.getNext();
        assertThat(stream.getPos(), is(0));

        assertThat(tokenImpl, is(notNullValue()));
        assertThat(tokenImpl.getType(), is(equalTo(TT_IGNORED)));
        assertThat(tokenImpl.getValue(), is(equalTo("p")));
        assertThat(tokenImpl.getOffset(), is(equalTo(0)));
    }

    @Test
    public void getNextValue() {
        stream.reset();

        String value = stream.getNextValue();

        assertThat(value, is(notNullValue()));
        assertThat(value, is(equalTo("p")));
    }

    @Test
    public void getNextType() {
        stream.reset();

        TokenTypeImpl type = (TokenTypeImpl) stream.getNextType();

        assertThat(type, is(equalTo(TT_IGNORED)));
    }

    @Test
    public void peekNext() {
        stream.reset();

        TokenImpl tokenImpl = (TokenImpl) stream.peekNext();
        assertThat(stream.getPos(), is(-1));

        assertThat(tokenImpl, is(notNullValue()));
        assertThat(tokenImpl.getType(), is(equalTo(TT_IGNORED)));
        assertThat(tokenImpl.getValue(), is(equalTo("p")));
        assertThat(tokenImpl.getOffset(), is(equalTo(0)));
    }

    @Test
    public void peekNextValue() {
        stream.reset();

        String value = stream.peekNextValue();

        assertThat(value, is(notNullValue()));
        assertThat(value, is(equalTo("p")));
    }

    @Test
    public void peekNextType() {
        stream.reset();

        TokenTypeImpl type = (TokenTypeImpl) stream.peekNextType();

        assertThat(type, is(equalTo(TT_IGNORED)));
    }

    @Test
    public void skipCurrent() {
        stream.reset();

        stream.skipCurrent();

        TokenImpl tokenImpl = (TokenImpl) stream.getCurrent();

        assertThat(tokenImpl, is(notNullValue()));
        assertThat(tokenImpl.getType(), is(equalTo(TT_IGNORED)));
        assertThat(tokenImpl.getValue(), is(equalTo("p")));
        assertThat(tokenImpl.getOffset(), is(equalTo(0)));
    }

    @Test
    public void skipIfTrue() {
        stream.reset();

        boolean result = stream.skipIf(true);
        assertThat(result, is(true));
        assertThat(stream.getPos(), is(0));

        TokenImpl tokenImpl = (TokenImpl) stream.getCurrent();

        assertThat(tokenImpl, is(notNullValue()));
        assertThat(tokenImpl.getType(), is(equalTo(TT_IGNORED)));
        assertThat(tokenImpl.getValue(), is(equalTo("p")));
        assertThat(tokenImpl.getOffset(), is(equalTo(0)));
    }

    @Test
    public void skipIfFalse() {
        stream.reset();

        boolean result = stream.skipIf(false);
        assertThat(result, is(false));
        assertThat(stream.getPos(), is(-1));

        TokenImpl tokenImpl = (TokenImpl) stream.getCurrent();

        assertThat(tokenImpl, is(is(nullValue())));
    }

    @Test
    public void reset() {
        stream.getNext();
        stream.getNext();
        stream.getNext();

        assertThat(stream.getPos(), is(2));

        stream.reset();

        assertThat(stream.getPos(), is(-1));
    }
}