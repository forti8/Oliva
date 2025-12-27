package utils.tokenizer;
import compiler.token.Token;
import java.util.ArrayList;
import java.util.List;

// tokenizer class
public class Tokenizer {

    // attribute that stores the line to be separated
    private String separatedLine;
    private List<Token> list = new ArrayList<>();

    // constructor
    public Tokenizer (String separatedLine) {
        super();
        this.separatedLine = separatedLine;
    }

    // checks if a word is reserved by looking through the array
    public static boolean isReservedWord (String word) {

        final String[] reservedWords = {
            "int", "str", "char", "bool", "float", "if", "else", "elif",
            "return", "continue", "for", "while", "do", "fun", "imp",
            "try", "catch", "imut", "var", "true", "false", "null",
            "new", "del", "void", "exp", "class"
        };

        for (String p : reservedWords) {
            if (word.equals(p)) {
                return true;
            }
        }

        return false;
    }

    // function to separate tokens
    public void tokenize () {

        // number of characters in the line
        int length = this.separatedLine.length();

        // current character index
        int c = 0;

        // traverse the string
        while (c < length) {

            // base variables
            char character = this.separatedLine.charAt(c);

            // skip spaces
            if (Character.isSpaceChar(character)) {
                c++;
                continue;
            }

            // if it's a letter or underscore
            else if (Character.isLetter(character) || character == '_') {

                StringBuilder sb = new StringBuilder();

                // while it's a letter, digit, or underscore
                while (c < length && (Character.isLetterOrDigit(this.separatedLine.charAt(c)) || this.separatedLine.charAt(c) == '_')) {
                    sb.append(this.separatedLine.charAt(c++));
                }

                String word = sb.toString();
                
                // check if it's a reserved word
                if (isReservedWord(word)) {
                    Token t = new Token();
                    t.setContent(word);
                    t.setType(Token.TokenType.RESERVED_WORD);
                    list.add(t);
                    continue;
                }

                Token t = new Token();
                t.setContent(word);
                t.setType(Token.TokenType.WORD);
                list.add(t);
                continue;
            }

            else if (Character.isDigit(character)) {

                boolean isFloat = false;
                StringBuilder sb = new StringBuilder();

                // while it's a digit or a first occurrence of a dot
                while (c < length && (Character.isDigit(this.separatedLine.charAt(c)) || (this.separatedLine.charAt(c) == '.' && !isFloat))) {
                     
                    if (this.separatedLine.charAt(c) == '.') isFloat = true;
                    sb.append(this.separatedLine.charAt(c++));
                }

                String number = sb.toString();

                if (isFloat) {
                    Token t = new Token();
                    t.setContent(number);
                    t.setType(Token.TokenType.FLOAT);
                    list.add(t);
                    continue;
                }

                Token t = new Token();
                t.setContent(number);
                t.setType(Token.TokenType.INT);
                list.add(t);
                continue;
            }

            // operators
            // ======================================
            if (character == '=') {
                if (c + 1 < length && this.separatedLine.charAt(c + 1) == '=') {
                    Token t = new Token();
                    t.setContent("==");
                    t.setType(Token.TokenType.EQUAL_COMPARISON);
                    list.add(t);
                    c += 2;
                    continue;
                }
                Token t = new Token();
                t.setContent("=");
                t.setType(Token.TokenType.EQUALS);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '+') {
                Token t = new Token();
                t.setContent("+");
                t.setType(Token.TokenType.PLUS);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '-') {
                Token t = new Token();
                t.setContent("-");
                t.setType(Token.TokenType.MINUS);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '/') {
                Token t = new Token();
                t.setContent("/");
                t.setType(Token.TokenType.DIVISION);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '*') {
                if (c + 1 < length && this.separatedLine.charAt(c + 1) == '*') {
                    Token t = new Token();
                    t.setContent("**");
                    t.setType(Token.TokenType.EXPONENT);
                    list.add(t);
                    c += 2;
                    continue;
                }
                Token t = new Token();
                t.setContent("*");
                t.setType(Token.TokenType.MULTIPLY);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '&') {
                Token t = new Token();
                t.setContent("&");
                t.setType(Token.TokenType.AND);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '|') {
                if (c + 1 < length && this.separatedLine.charAt(c + 1) == '|') {
                    Token t = new Token();
                    t.setContent("||");
                    t.setType(Token.TokenType.OR);
                    list.add(t);
                    c += 2;
                    continue;
                }
                Token t = new Token();
                t.setContent("|");
                t.setType(Token.TokenType.PIPE);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '>') {
                if (c + 1 < length && this.separatedLine.charAt(c + 1) == '=') {
                    Token t = new Token();
                    t.setContent(">=");
                    t.setType(Token.TokenType.GREATER_EQUAL);
                    list.add(t);
                    c += 2;
                    continue;
                }
                Token t = new Token();
                t.setContent(">");
                t.setType(Token.TokenType.GREATER_THAN);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '<') {
                if (c + 1 < length && this.separatedLine.charAt(c + 1) == '=') {
                    Token t = new Token();
                    t.setContent("<=");
                    t.setType(Token.TokenType.LESS_EQUAL);
                    list.add(t);
                    c += 2;
                    continue;
                }
                Token t = new Token();
                t.setContent("<");
                t.setType(Token.TokenType.LESS_THAN);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '^') {
                Token t = new Token();
                t.setContent("^");
                t.setType(Token.TokenType.CARET);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '"') {
                Token t = new Token();
                t.setContent("\"");
                t.setType(Token.TokenType.DOUBLE_QUOTE);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '\'') {
                Token t = new Token();
                t.setContent("'");
                t.setType(Token.TokenType.SINGLE_QUOTE);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '\\') {
                Token t = new Token();
                t.setContent("\\");
                t.setType(Token.TokenType.BACKSLASH);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '.') {
                Token t = new Token();
                t.setContent(".");
                t.setType(Token.TokenType.DOT);
                list.add(t);
                c++;
                continue;
            }

            else if (character == ',') {
                Token t = new Token();
                t.setContent(",");
                t.setType(Token.TokenType.COMMA);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '(') {
                Token t = new Token();
                t.setContent("(");
                t.setType(Token.TokenType.OPEN_PAREN);
                list.add(t);
                c++;
                continue;
            }

            else if (character == ')') {
                Token t = new Token();
                t.setContent(")");
                t.setType(Token.TokenType.CLOSE_PAREN);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '{') {
                Token t = new Token();
                t.setContent("{");
                t.setType(Token.TokenType.OPEN_BRACE);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '}') {
                Token t = new Token();
                t.setContent("}");
                t.setType(Token.TokenType.CLOSE_BRACE);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '[') {
                Token t = new Token();
                t.setContent("[");
                t.setType(Token.TokenType.OPEN_BRACKET);
                list.add(t);
                c++;
                continue;
            }

            else if (character == ']') {
                Token t = new Token();
                t.setContent("]");
                t.setType(Token.TokenType.CLOSE_BRACKET);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '?') {
                Token t = new Token();
                t.setContent("?");
                t.setType(Token.TokenType.QUESTION_MARK);
                list.add(t);
                c++;
                continue;
            }

            else if (character == ':') {
                Token t = new Token();
                t.setContent(":");
                t.setType(Token.TokenType.COLON);
                list.add(t);
                c++;
                continue;
            }

            else if (character == '!') {
                if (c + 1 < length && this.separatedLine.charAt(c + 1) == '=') {
                    Token t = new Token();
                    t.setContent("!=");
                    t.setType(Token.TokenType.DIFFERENT);
                    list.add(t);
                    c += 2;
                    continue;
                }
                Token t = new Token();
                t.setContent("!");
                t.setType(Token.TokenType.NOT);
                list.add(t);
                c++;
                continue;
            }

            // Move to next character if no condition met
            c++;
        }
    }

    // returns list size
    public int listSize () {
        return this.list.size();
    } 

    // getter
    public List<Token> getList () {
        return this.list;
    }
}