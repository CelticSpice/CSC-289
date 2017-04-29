/**
 * Writes reservable/reservation information into a CSV file
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.gayj5385.gui.dialog.CreateReservableReportDialog;
import edu.faytechcc.student.gayj5385.gui.dialog.CreateReservationReportDialog;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
     */
    public CSVWriter(String fileName)
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
    
    /**
     * Creates a report of reservables
     * 
     * @param dialog The parent dialog
     * @param reservables The reservable to input to a report
     */
    public void writeReservableReport(CreateReservableReportDialog dialog,
            List<Reservable> reservables)
    {
        Reservable r = null;   // Info will never be pulled when this is null

        try
        {
            for (int i = 0; i < reservables.size() + 1; i++)
            {
                if (i >= 1)
                    r = reservables.get(i - 1);
                
                if (dialog.isLocationNameSelected())
                    if (i == 0)
                        printWriter.print("Location Name,");
                    else
                        printWriter.print(r.getLocation().getName() + ",");
                if (dialog.isCapacitySelected())
                    if (i == 0)
                        printWriter.print("Capacity,");
                    else
                        printWriter.print(r.getLocation().getCapacity()+ ",");
                if (dialog.isStartDateSelected())
                    if (i == 0)
                        printWriter.print("Start Date,");
                    else
                        printWriter.print(r.getStartDate() + ",");
                if (dialog.isEndDateSelected())
                    if (i == 0)
                        printWriter.print("End Date,");
                    else
                        printWriter.print(r.getEndDate() + ",");
                if (dialog.isStartTimeSelected())
                    if (i == 0)
                        printWriter.print("Start Time,");
                    else
                        printWriter.print(r.getStartTime() + ",");
                if (dialog.isEndTimeSelected())
                    if (i == 0)
                        printWriter.print("End Time,");
                    else
                        printWriter.print(r.getEndTime() + ",");
                if (dialog.isCostSelected())
                    if (i == 0)
                        printWriter.print("Cost,");
                    else
                        printWriter.print(r.getCost() + ",");
                if (dialog.isReservedSelected())
                    if (i == 0)
                        printWriter.print("Reserved?");
                    else if (r.getTimeframe().isReserved())
                        printWriter.print("Yes");
                    else
                        printWriter.print("No");
                printWriter.println();
            }
            printWriter.flush();
            printWriter.close();
            fileWriter.close();
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Error creating report",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Creates a report of reservations
     * 
     * @param dialog The parent dialog
     * @param reservations The reservations to input to a report
     */
    public void writeReservationReport(CreateReservationReportDialog dialog,
            List<Reservation> reservations)
    {
        Reservation r = null;   // Info will never pulled when this is null
        
        try
        {
            for (int i = 0; i < reservations.size() + 1; i++)
            {
                if (i >= 1)
                    r = reservations.get(i - 1);
                
                if (dialog.isLocationNameSelected())
                    if (i == 0)
                        printWriter.print("Location Name,");
                    else
                        printWriter.print(r.getLocationName() + ",");
                if (dialog.isCapacitySelected())
                    if (i == 0)
                        printWriter.print("Capacity,");
                    else
                        printWriter.print(r.getLocation().getCapacity()+ ",");
                if (dialog.isStartDateSelected())
                    if (i == 0)
                        printWriter.print("Start Date,");
                    else
                        printWriter.print(r.getStartDate() + ",");
                if (dialog.isEndDateSelected())
                    if (i == 0)
                        printWriter.print("End Date,");
                    else
                        printWriter.print(r.getEndDate() + ",");
                if (dialog.isStartTimeSelected())
                    if (i == 0)
                        printWriter.print("Start Time,");
                    else
                        printWriter.print(r.getStartTime() + ",");
                if (dialog.isEndTimeSelected())
                    if (i == 0)
                        printWriter.print("End Time,");
                    else
                        printWriter.print(r.getEndTime() + ",");
                if (dialog.isCostSelected())
                    if (i == 0)
                        printWriter.print("Cost,");
                    else
                        printWriter.print(r.getCost() + ",");
                if (dialog.isFirstNameSelected())
                    if (i == 0)
                        printWriter.print("First Name,");
                    else
                        printWriter.print(r.getReserverFirstName() + ",");
                if (dialog.isLastNameSelected())
                    if (i == 0)
                        printWriter.print("Last Name,");
                    else
                        printWriter.print(r.getReserverLastName() + ",");
                if (dialog.isEmailAddressSelected())
                    if (i == 0)
                        printWriter.print("Email Address,");
                    else
                        printWriter.print(r.getReserverEmail()+ ",");
                if (dialog.isPhoneNumberSelected())
                    if (i == 0)
                        printWriter.print("Phone Number,");
                    else
                        printWriter.print(r.getReserverPhone() + ",");
                if (dialog.isEventTypeSelected())
                    if (i == 0)
                        printWriter.print("Event Type,");
                    else
                        printWriter.print(r.getEventType() + ",");
                if (dialog.isNumberAttendingSelected())
                    if (i == 0)
                        printWriter.print("Number Attending,");
                    else
                        printWriter.print(r.getNumberAttending() + ",");
                printWriter.println();
            }
            printWriter.flush();
            printWriter.close();
            fileWriter.close();
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Error creating report",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
