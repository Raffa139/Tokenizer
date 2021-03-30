package com.ren.tokenize;

import java.util.Objects;

public abstract class Token {
    protected TokenType type;

    protected String value;

    protected int offset;

    public Token(TokenType type, String value, int offset) {
        this.type = type;
        this.value = value;
        this.offset = offset;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return offset == token.offset && Objects.equals(type, token.type) && Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value, offset);
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", offset=" + offset +
                '}';
    }
}
