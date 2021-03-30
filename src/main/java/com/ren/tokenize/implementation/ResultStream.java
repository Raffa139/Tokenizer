package com.ren.tokenize.implementation;

import com.ren.tokenize.Token;
import com.ren.tokenize.TokenStream;
import com.ren.tokenize.TokenType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ResultStream<V extends Token> implements TokenStream {
    protected List<V> tokens;

    protected int pos;

    public ResultStream(List<V> tokens) {
        this.tokens = tokens;
        reset();
    }

    @Override
    public Token getCurrent() {
        if (pos > tokens.size() || pos < 0) {
            return null;
        }

        return tokens.get(pos) != null ? tokens.get(pos) : null;
    }

    public String getCurrentValue() {
        return getCurrent() != null ? getCurrent().getValue() : null;
    }

    public TokenType getCurrentType() {
        return getCurrent() != null ? getCurrent().getType() : null;
    }

    @Override
    public Token getNext() {
        return nextToken();
    }

    public String getNextValue() {
        return peekNext() != null ? getNext().getValue() : null;
    }

    public TokenType getNextType() {
        return peekNext() != null ? getNext().getType() : null;
    }

    @Override
    public Token peekNext() {
        Token token = nextToken();
        pos = token != null ? pos-1 : pos;
        return token;
    }

    public String peekNextValue() {
        return peekNext() != null ? peekNext().getValue() : null;
    }

    public TokenType peekNextType() {
        return peekNext() != null ? peekNext().getType() : null;
    }

    @Override
    public void skipCurrent() {
        Token token = peekNext();
        if (token != null) {
            pos++;
        }
    }

    public boolean skipIf(boolean b) {
        if (b) {
            skipCurrent();
        }
        return b;
    }

    public void reset() {
        this.pos = -1;
    }

    public StreamUtility getTokens() {
        return new StreamUtility();
    }

    public int getPos() {
        return pos;
    }

    private Token nextToken() {
        if (tokens.size() > pos+1 && tokens.get(pos+1) != null) {
            pos++;
            return getCurrent();
        } else {
            return null;
        }
    }

    public class StreamUtility {
        public List<V> asList() {
            return tokens;
        }

        public <T extends Token> List<T> convertedList(Class<T> tokenImpl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            List<T> result = new ArrayList<>();

            Constructor<T> constructor = tokenImpl.getConstructor(TokenType.class, String.class, int.class);

            for (Token t : tokens) {
                T obj = constructor.newInstance(t.getType(), t.getValue(), t.getOffset());
                result.add(obj);
            }

            return result;
        }
    }
}
