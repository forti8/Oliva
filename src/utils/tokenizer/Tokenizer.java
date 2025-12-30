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
            "new", "del", "void", "exp", "class", "print", "default", "case", "switch",
            "break"
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

            else if (character == '#') {
                break;
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

                    if (word.equals("true") || word.equals("false")) {
                        Token t = new Token();
                        t.setContent(word);
                        t.setType(Token.TokenType.BOOL);
                        list.add(t);
                        continue;
                    }

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
                StringBuilder sb = new StringBuilder();
                boolean isFloat = false;

                while (c < length && (Character.isDigit(this.separatedLine.charAt(c)) || (this.separatedLine.charAt(c) == '.' && !isFloat))) {
                    if (this.separatedLine.charAt(c) == '.') isFloat = true;
                    sb.append(this.separatedLine.charAt(c++));
                }

                String number = sb.toString();
                Token t = new Token();
                t.setContent(number);

                if (c < length) {
                    char nextChar = this.separatedLine.charAt(c);
                    if (nextChar == '+' || nextChar == '-' || nextChar == '*' || nextChar == '/') {
                        t.setContent(number + nextChar);
                        t.setType(isFloat ? Token.TokenType.OP_FLOAT : Token.TokenType.OP_INT);
                        c++; 
                    } else {
                        t.setType(isFloat ? Token.TokenType.FLOAT : Token.TokenType.INT);
                    }
                } else {
                    t.setType(isFloat ? Token.TokenType.FLOAT : Token.TokenType.INT);
                }
                list.add(t);
                continue;
            }

            else if (character == '"') {
                c++;
                StringBuilder sb = new StringBuilder();
                while (c < length && this.separatedLine.charAt(c) != '"') {
                    sb.append(this.separatedLine.charAt(c++));
                }
                c++;

                Token t = new Token();
                String content = sb.toString();

                if (c < length && this.separatedLine.charAt(c) == '+') {
                    t.setContent(content + "+");
                    t.setType(Token.TokenType.OP_STR);
                    c++; 
                } else {
                    t.setContent(content);
                    t.setType(Token.TokenType.STR);
                }
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

            else if (character == '+' || character == '-' || character == '*' || character == '/') {
                
                if (c + 1 < length && this.separatedLine.charAt(c + 1) == '+' && character == '+') {
                    Token t = new Token();
                    t.setContent("++");
                    t.setType(Token.TokenType.AUTO_INCREMENT);
                    list.add(t);
                    c += 2;
                    continue;
                }

                else if (c + 1 < length && this.separatedLine.charAt(c + 1) == '-' && character == '-') {
                    Token t = new Token();
                    t.setContent("--");
                    t.setType(Token.TokenType.AUTO_DECREMENT);
                    list.add(t);
                    c += 2;
                    continue;
                }

                Token pT = list.isEmpty() ? null : list.get(list.size() - 1);
                
                if (pT != null) {
                    boolean isInt = pT.type == Token.TokenType.INT;
                    boolean isFloat = pT.type == Token.TokenType.FLOAT;
                    boolean isStr = pT.type == Token.TokenType.STR && character == '+';
                    boolean isWord = pT.type == Token.TokenType.WORD;

                    if (isInt || isFloat || isStr || isWord) {
                        Token.TokenType newType;
                        if (isStr) newType = Token.TokenType.OP_STR;
                        else if (isFloat) newType = Token.TokenType.OP_FLOAT;
                        else if (isWord) newType = Token.TokenType.OP_WORD;
                        else newType = Token.TokenType.OP_INT;

                        pT.setContent(pT.getContent().concat(String.valueOf(character)));
                        pT.setType(newType);
                        
                        c++;
                        continue;
                    }
                }

                Token t = new Token();
                t.setContent(String.valueOf(character));
                switch (character) {
                    case '+': t.setType(Token.TokenType.PLUS); break;
                    case '-': t.setType(Token.TokenType.MINUS); break;
                    case '*': t.setType(Token.TokenType.MULTIPLY); break;
                    case '/': t.setType(Token.TokenType.DIVISION); break;
                }
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

            else if (character == '\'') {
                int start = c;
                c++; // Skip the opening single quote

                StringBuilder charContent = new StringBuilder();
                while (c < length && this.separatedLine.charAt(c) != '\'') {
                    charContent.append(this.separatedLine.charAt(c));
                    c++;
                }

                if (c < length && this.separatedLine.charAt(c) == '\'') {
                    c++;
                } 
                
                else {
                    throw new RuntimeException("Lexical error: unclosed character literal at position " + start);
                }

                if (c != (start + 3)) {
                    throw new RuntimeException("Lexical error: you are trying to assign more/less than one character in position  " + start);
                }

                Token t = new Token();
                t.setContent(charContent.toString());
                t.setType(Token.TokenType.CHAR);
                list.add(t);
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