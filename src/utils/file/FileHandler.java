package utils.file;

// importing necessary packages for file reading
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import utils.tokenizer.Tokenizer;
import compiler.interpreter.Interpreter;

// file handler class
public class FileHandler {

    // private attribute for file path
    private String path;

    // constructor
    public FileHandler (String path) {
        super();
        this.path = path;
    }

    // read member
    public int read () {

        // tries to initialize file reader
        try (FileReader fr = new FileReader(this.path)) {

            // tries to initialize buffered reader
            try (BufferedReader br = new BufferedReader(fr)) {
    
                String line;
    
                // while line is not null
                while ((line = br.readLine()) != null) {

                    // starts parsing
                    Tokenizer lineTokenizer = new Tokenizer(line);
                    lineTokenizer.tokenize();
                    Interpreter lineInterpreter = new Interpreter(lineTokenizer.getList());
                    lineInterpreter.activate();
                }
        
                br.close();
            }
    
            catch (IOException e) {
                System.out.println(e.toString());
                return 1;
            }
        }

        // file not found exception
        catch (FileNotFoundException e) {
            System.out.println("File not found exception: " + e.getMessage());
            return 1;
        }
        
        // general IO exception
        catch (IOException e) {
            System.out.println(e.toString());
            return 1;
        }

        // returns 0 for success, 1 for failure
        return 0;
    }

    // copy instance content to another file
    public int copyTo (String destination) {

        try (FileReader fr = new FileReader(this.path)) {

            try (BufferedReader br = new BufferedReader(fr)) {
    
                String line;

                // tries to write using buffered writer
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(destination))) {
    
                    while ((line = br.readLine()) != null) {
                        bw.write(line);
                        bw.newLine();
                    }
                } 
                
                catch (IOException e) {
                    System.out.println("Error writing file.");
                }

                br.close();
            }
    
            catch (IOException e) {
                System.out.println(e.toString());
                return 1;
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found exception");
            return 1;
        }
        
        catch (IOException e) {
            System.out.println(e.toString());
            return 1;
        }

        return 0;
    }

    // returns the first line number where the search string occurs
    public int searchFor (String search) {

        try {

            Scanner scanner = new Scanner(new File(this.path));
            int lineNumber = 0;

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                lineNumber++;

                if (line.contains(search)) {
                    scanner.close();
                    return lineNumber;
                }
            }

            scanner.close();
            return -1;
        } 
        
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return -1;
    }

    // read a specific line from the file
    public String readLine (int desiredLine) {
        
        try (FileReader fr = new FileReader(this.path)) {

            try (BufferedReader br = new BufferedReader(fr)) {

                String line;
                int currentLine = 0;
    
                while ((line = br.readLine()) != null) {
                    
                    currentLine++;
    
                    if (currentLine == desiredLine) {
                        return line;
                    }  
                }
            }

            catch (IOException e) {
                System.out.println(e.toString());
            }            
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }

        return "";
    }
}