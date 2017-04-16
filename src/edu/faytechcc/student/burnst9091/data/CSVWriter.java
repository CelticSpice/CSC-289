/**
 * Writes reservable information into a CSV file
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class CSVWriter
{
    // Field(s)
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    
    /**
     * Constructs a new CSVWriter
     * 
     * @param fileName The name of the CSV file to create
     * @throws IOException Error creating report
     */
    public CSVWriter(String fileName) throws IOException
    {
        try
        {
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Error creating report",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
