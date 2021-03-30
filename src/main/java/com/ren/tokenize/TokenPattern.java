package com.ren.tokenize;

import java.util.Objects;

public abstract class TokenPattern implements Comparable<TokenPattern> {
    protected TokenType returnType;

    protected String pattern;

    protected int priority;

    public TokenPattern(TokenType returnType, String pattern, int priority) {
        this.returnType = returnType;
        this.pattern = pattern;
        this.priority = priority;
    }

    public abstract Token tokenize(String source, int current);

    public TokenType getReturnType() {
        return returnType;
    }

    public String getPattern() {
        return pattern;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenPattern that = (TokenPattern) o;
        return priority == that.priority && Objects.equals(returnType, that.returnType) && Objects.equals(pattern, that.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(returnType, pattern, priority);
    }

    @Override
    public int compareTo(TokenPattern o) {
        if (this.equals(o)) {
            return 0;
        }

        int comp = Integer.compare(this.priority, o.priority);
        return comp == 0 ? 1 : -comp;
    }

    @Override
    public String toString() {
        return "TokenPattern{" +
                "returnType=" + returnType +
                ", pattern='" + pattern + '\'' +
                ", priority=" + priority +
                '}';
    }
}
