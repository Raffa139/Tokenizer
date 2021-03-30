package com.ren.tokenize.implementation;

import com.ren.tokenize.*;

import java.util.*;

public class Tokenizer {
    protected Set<TokenPattern> patterns;

    public Tokenizer() {
        this.patterns = new TreeSet<>();
    }

    public Tokenizer(TokenPattern... patterns) {
        this.patterns = new TreeSet<>(Arrays.asList(patterns));
    }

    public Tokenizer(Set<TokenPattern> patterns) {
        this.patterns = patterns;
    }

    public void addTokenPattern(TokenPattern pattern) {
        this.patterns.add(pattern);
    }

    public void removeTokenPattern(TokenPattern pattern) {
        this.patterns.remove(pattern);
    }

    public TokenStream tokenize(String source) throws UnexpectedCharacterException {
        List<Token> result = new ArrayList<>();

        int pos = 0;
        while (pos < source.length()) {
            char c = source.charAt(pos);
            Token token = null;

            for (TokenPattern tp : patterns) {
                token = tp.tokenize(source, pos);
                if (token != null) {
                    break;
                }
            }

            if (token != null) {
                result.add(token);
                String val = token.getValue();
                pos += val.length();
            } else {
                throw new UnexpectedCharacterException("Unexpected char " + c);
            }
        }

        return new ResultStream(result);
    }
}
