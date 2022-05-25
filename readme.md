# Tokenizer
A small library for parsing and converting text into tokens, which can be used for a variety of purposes,
for example a custom programming language.

## Usage example
A bunch of patterns, each specifying a regular expression and return token type, can be plugged into the tokenizer.
They are evaluated based on their priority.
```java
Tokenizer tokenizer = new Tokenizer(
  new TokenPatternImpl(TT_NUMBER_LITERAL, "\\d+", 0),
  new TokenPatternImpl(TT_WHITESPACE, "\\s+", 0),
  new TokenPatternImpl(TT_STRING_LITERAL, "\\w+", 0)
);

ResultStream<TokenImpl> stream = (ResultStream<TokenImpl>) tokenizer.tokenize("say 123");

for (Token token : stream.getTokens().asList()) {
  System.out.println(token);
}
```
The output looks like this:
```
Token{ type=TT_STRING_LITERAL, value='say', offset=0 }
Token{ type=TT_WHITESPACE, value=' ', offset=3 }
Token{ type=TT_NUMBER_LITERAL, value='123', offset=4 }
```