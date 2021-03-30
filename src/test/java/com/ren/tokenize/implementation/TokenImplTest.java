package com.ren.tokenize.implementation;

import org.junit.Test;

import static com.ren.tokenize.implementation.TokenTypeImpl.TT_END_OF_FILE;
import static com.ren.tokenize.implementation.TokenTypeImpl.TT_WHITESPACE;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TokenImplTest {

    @Test
    public void forEof() {
        TokenImpl tokenImpl = TokenImpl.forEof();

        assertThat(tokenImpl, is(notNullValue()));
        assertThat(tokenImpl.getType(), is(equalTo(TT_END_OF_FILE)));
        assertThat(tokenImpl.getValue(), is(nullValue()));
        assertThat(tokenImpl.getOffset(), is(-1));
    }

    @Test
    public void testEquals() {
        TokenImpl tokenImpl = TokenImpl.forEof();

        assertThat(tokenImpl, is(equalTo(TokenImpl.forEof())));
        assertThat(tokenImpl, is(not(equalTo(new TokenImpl(TT_WHITESPACE, " ", 1)))));
    }

    @Test
    public void testHashCode() {
        TokenImpl tokenImpl = TokenImpl.forEof();

        assertThat(tokenImpl.hashCode(), is(TokenImpl.forEof().hashCode()));
        assertThat(tokenImpl.hashCode(), is(not(new TokenImpl(TT_WHITESPACE, " ", 1).hashCode())));
    }
}