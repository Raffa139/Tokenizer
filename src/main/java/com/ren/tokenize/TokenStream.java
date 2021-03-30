package com.ren.tokenize;

public interface TokenStream {
    Token getCurrent();

    Token getNext();

    Token peekNext();

    void skipCurrent();
}
