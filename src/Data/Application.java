/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import Exception.RecordExistsException;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.mail.internet.AddressException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    public static void main(String[] args) throws SQLException, IOException,
                                                  NoSuchAlgorithmException,
                                                  ParserConfigurationException,
                                                  TransformerException,
                                                  SAXException,
                                                  XPathExpressionException,
                                                  AddressException,
                                                  RecordExistsException
    {
        SystemUtil.initResources();
        
        Location loc = new Location("Barn", 75);
        
        ZonedDateTime start = ZonedDateTime
                .of(LocalDate.of(2018, 4, 25), LocalTime.of(6, 0),
                        ZoneId.systemDefault());
        
        ZonedDateTime end = ZonedDateTime
                .of(LocalDate.of(2018, 4, 26), LocalTime.of(6, 0),
                        ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe
                (start, end, new BigDecimal(175));
        
        Admin.addReservable(new Reservable(loc, timeframe));
    }
}