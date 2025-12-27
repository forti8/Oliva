package compiler.token;

// defines the token class to be used in the tokenizer/parser
public class Token {

    // types
    public static enum TokenType {
        CHAR,
        INT, 
        VOID,
        STR,
        BOOL, 
        FLOAT,
        WORD,
        RESERVED_WORD,
        OPEN_PAREN,
        CLOSE_PAREN,
        OPEN_BRACE,
        CLOSE_BRACE,
        OPEN_BRACKET,
        CLOSE_BRACKET,
        DOT,
        COMMA,
        QUESTION_MARK,
        COLON,
        EQUALS,
        PLUS,
        MINUS, 
        DIVISION,
        MULTIPLY,
        AND,
        PIPE,
        OR,
        NOT,
        GREATER_THAN,
        GREATER_EQUAL,
        LESS_THAN,
        LESS_EQUAL,
        DIFFERENT, 
        EQUAL_COMPARISON,
        CARET,
        EXPONENT,
        DOUBLE_QUOTE,
        SINGLE_QUOTE,
        BACKSLASH
    }

    // token attributes
    public String content;
    public TokenType type;

    // setters
    public void setContent (String c) {
        this.content = c;
    }
    public void setType (TokenType t) {
        this.type = t;
    }

    // getters
    public String getContent () {
        return this.content;
    }
    public TokenType getType () {
        return this.type;
    }
}