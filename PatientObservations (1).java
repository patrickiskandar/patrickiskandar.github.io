import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.*;
public class PatientObservations {

	public static void main(String[] args) throws SQLException {
		PatientObservations.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://comp421.cs.mcgill.ca:50000/cs421", "piskan1", "zYC8qp4U");
		Statement stmt = conn.createStatement();
		
		try {
		
		ResultSet rs;
		String patientid;
		String freetext;
		String query;
		String choice;
		Scanner prompt = new Scanner(System.in);
		PreparedStatement preparedStmt;
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		while (true) {
			query = "SELECT hid FROM healthcare_professionals WHERE hid = ?";
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt 		(1, Integer.parseInt(args[0]));
			rs = preparedStmt.executeQuery();
			if (!rs.isBeforeFirst() ) {    
				System.out.println("Error! Please provide valid hospital id number: ");
				args[0] = prompt.nextLine();
				continue;
			} 
			else {
				break;
			}
		}
		while (true) {
			System.out.println("Press 1 to enter a patient's observation, press 2 to quit the program.");
			choice = prompt.nextLine();
			if (choice == "1") {
				System.out.println("Enter patient id number: ");
				patientid = prompt.nextLine();
				break;
			}
			else if (choice == "2") {
				System.out.println("Thank you! Closing program now.");
				rs.close();
				conn.close();
				return;
			}
			else {
				System.out.println("Error! This input is not valid.");
				continue;
			}
		}

		while (true) {
			query = "SELECT patientid FROM patients WHERE patientid = ?";
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt 		(1, Integer.parseInt(patientid));
			rs = preparedStmt.executeQuery();
			if (!rs.isBeforeFirst() ) {    
				System.out.println("Error! Please input valid patient id: ");
				patientid = prompt.nextLine();
				continue;
			} 
			else {
				System.out.println("Enter observation text: ");
				freetext = prompt.nextLine();
				break;
			}
		}
		
		query = "INSERT INTO observations (freetext, date_time, hid, patientid)" + " VALUES (?, ?, ?, ?)";
	    //Create prepared statements
		preparedStmt = conn.prepareStatement(query);
	    preparedStmt.setString 		(1, freetext);
	    preparedStmt.setTimestamp 	(2, currentTimestamp);
	    preparedStmt.setInt    		(3, Integer.parseInt(args[0]));
	    preparedStmt.setInt    		(4, Integer.parseInt(patientid));
		    
	    //execute query
	    preparedStmt.executeQuery();
	
		}	catch (Exception e) {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		    }	finally {
				stmt.close();
			    conn.close();
			}
	}	
}


