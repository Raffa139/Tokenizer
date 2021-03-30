package com.ren.tokenize.implementation;

import org.junit.Test;

import static com.ren.tokenize.implementation.TokenTypeImpl.TT_IGNORED;
import static com.ren.tokenize.implementation.TokenTypeImpl.TT_WHITESPACE;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TokenImplPatternTest {

    @Test
    public void tokenize() {
        String src = "a = 12;";
        TokenPatternImpl tp = new TokenPatternImpl(TT_WHITESPACE, "\\s+", 0);

        TokenImpl tokenImpl;
        tokenImpl = (TokenImpl) tp.tokenize(src, 0);
        assertTokenNull(tokenImpl);

        tokenImpl = (TokenImpl) tp.tokenize(src, 1);
        assertTokenNotNull(tokenImpl, 1);

        tokenImpl = (TokenImpl) tp.tokenize(src, 2);
        assertTokenNull(tokenImpl);

        tokenImpl = (TokenImpl) tp.tokenize(src, 3);
        assertTokenNotNull(tokenImpl, 3);
    }

    @Test
    public void testEquals() {
        TokenPatternImpl tp = new TokenPatternImpl(TT_WHITESPACE, "\\s+", 0);

        assertThat(tp, is(equalTo(new TokenPatternImpl(TT_WHITESPACE, "\\s+", 0))));
        assertThat(tp, is(not(equalTo(new TokenPatternImpl(TT_IGNORED, ".", 0)))));
    }

    @Test
    public void testHashCode() {
        TokenPatternImpl tp = new TokenPatternImpl(TT_WHITESPACE, "\\s+", 0);

        assertThat(tp.hashCode(), is(equalTo(new TokenPatternImpl(TT_WHITESPACE, "\\s+", 0).hashCode())));
        assertThat(tp.hashCode(), is(not(equalTo(new TokenPatternImpl(TT_IGNORED, ".", 0).hashCode()))));
    }

    private void assertTokenNull(TokenImpl tokenImpl) {
        assertThat(tokenImpl, is(nullValue()));
    }

    private void assertTokenNotNull(TokenImpl tokenImpl, int expectedOffset) {
        assertThat(tokenImpl, is(notNullValue()));
        assertThat(tokenImpl.getType(), is(equalTo(TT_WHITESPACE)));
        assertThat(tokenImpl.getValue(), is(equalTo(" ")));
        assertThat(tokenImpl.getOffset(), is(expectedOffset));
    }
}