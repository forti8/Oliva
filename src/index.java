import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder;
import java.lang.InterruptedException;

import utils.file.FileHandler;

// index class
public class index {

    // main method responsible for system initialization in Java
    public static void main(String[] args) {
        
        // variable to track if a command has already been used
        boolean commandUsed = false;

        // execution argument prefix
        final String executionPrefix = "-";
        
        // information argument prefix
        final String infoPrefix = "--";
        
        // valid extension for OliLang files
        final String validExtension = ".ol";
        
        // path of the executed file, defaults to index.validExtension
        String path = "index" + validExtension;

        // temporary scope/variable block
        if (true) {
            
            // tries to verify the language configuration file
            File configFile = new File("./configs/.config.ol");

            // if the configuration file exists
            if (configFile.exists()) {
    
                // instance using the custom FileHandler class
                FileHandler handler = new FileHandler("./configs/.config.ol");
    
                // line containing the content (-1 if it doesn't exist)
                int lineIndex = handler.searchFor("INDEX");
    
                // retrieves the line content
                String lineContent = handler.readLine(lineIndex);

                // remove spaces
                lineContent = lineContent.replaceAll(" ", "");

                // removes "INDEX=" to leave only the {path}
                lineContent = lineContent.replaceFirst("INDEX=", "");
                path = lineContent;
            }
        }

        // for each argument contained in args
        for (String argument : args) {

            // checks the prefix
            // info prefix: help, version ...
            if (argument.startsWith(infoPrefix)) {
                
                // removes the prefix and transforms text to lowercase
                argument = argument.replaceFirst(infoPrefix, "");
                argument.toLowerCase();

                commandUsed = true;
            }

            // execution prefix: configurations
            else if (argument.startsWith(executionPrefix)) {

                // removes the prefix
                argument = argument.replaceFirst(executionPrefix, "");
                argument.toLowerCase();

                // -config-file command
                if (argument.equals("config-file")) {

                    // new configuration file .config.ol
                    File newConfigFile = new File(".config.ol");
                    
                    // checks if the file exists
                    if (!(newConfigFile.exists())) {

                        // if it doesn't exist, try to create it
                        try {
                            newConfigFile.createNewFile();
                        }
                        
                        // catch IO exception
                        catch (IOException e) {
                            System.out.println(e.toString());
                        }
                    }

                    // tries to create the process
                    try {
                        // creates a process instance
                        Process p = new ProcessBuilder("sh", "src/sh/config.sh").start();
                        p.waitFor();
                    } 
                    
                    // catch process-related exceptions
                    catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }

                    FileHandler configTemplate = new FileHandler("./src/models/config.txt");
                    configTemplate.copyTo("./configs/.config.ol");
                }

                commandUsed = true;
            }

            // no prefix: treat as file path
            else {

                // if the file has a valid extension, set it as the path
                if (argument.endsWith(validExtension)) path = argument;
            }
        }

        // instance that stores the file to be read
        FileHandler fileToRead = new FileHandler(path);
        
        // attempts to read the file if no specific command was used
        if(!commandUsed) fileToRead.read();
    }
}