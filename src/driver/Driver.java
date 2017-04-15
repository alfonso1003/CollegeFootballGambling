package driver;
import java.sql.*;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		String DBName = "Scores2008";
		String DBurl = "jdbc:mysql://localhost:3306/" + DBName + "?useSSL=false";
		String DBusername = "root";
		String DBpassword = "Wutangclan25";
		
		// list of all FCS teams
		String[] teamsList = {"Air Force", "Akron", "Alabama", "Appalachian State", "Arizona", "Arizona State", "Arkansas", "Arkansas State", "Army", "Auburn", "Ball State", "Baylor", "Boise State", "Boston College", "Bowling Green", "Buffalo", "BYU", "California", "Fresno State", "UCLA", "UCF", "Central Michigan", "Charlotte", "Cincinnati", "Clemson", "Colorado", "Colorado State", "Connecticut", "Duke", "Eastern Michigan", "East Carolina", "Florida International", "Florida", "Florida Atlantic", "Florida State", "Georgia", "Georgia Southern", "Georgia Tech", "Hawaii", "Houston", "Idaho", "Illinois", "Indiana", "Iowa", "Iowa State", "Kansas", "Kansas State", "Kent State", "Kentucky", "LSU", "Louisiana Tech", "Louisiana-Lafayette", "Louisiana-Monroe", "Louisville", "Marshall", "Maryland", "Massachusetts", "Memphis", "Miami (Florida)", "Miami (Ohio)", "Michigan", "Michigan State", "Middle Tennessee", "Minnesota", "Mississippi", "Mississippi State", "Missouri", "Navy", "Nebraska", "Nevada", "UNLV", "New Mexico", "New Mexico State", "North Carolina", "North Carolina State", "North Texas", "Northern Illinois", "Northwestern", "Notre Dame", "Ohio", "Ohio State", "Oklahoma", "Oklahoma State", "Old Dominion", "Oregon", "Oregon State", "Penn State", "Pittsburgh", "Purdue", "Rice", "Rutgers", "San Diego State", "San Jose State", "South Alabama", "South Carolina", "South Florida", "USC", "SMU", "Southern Mississippi", "Stanford", "Syracuse", "TCU", "Temple", "Tennessee", "Texas", "Texas A&M", "Texas State", "Texas Tech", "UTEP", "UTSA", "Toledo", "Troy", "Tulane", "Tulsa", "Utah", "Utah State", "Vanderbilt", "Virginia", "Virginia Tech", "Wake Forest", "Washington", "Washington State", "West Virginia", "Western Kentucky", "Western Michigan", "Wisconsin", "Wyoming", "UAB", "Georgia State"};
		
		// list of all teams incorrectly added to DB from ESPN scores scraping
		String[] teamToEditList = {"Cal", "W Michigan", "Miami (OH)", "Appalachian St", "Ga Southern", "Washington St", "UMass", "E Michigan", "UConn", "Cent Michigan", "New Mexico St", "UL Monroe", "UL Lafayette", "Southern Miss", "Ole Miss", "Mid Tennessee", "FIU", "FAU", "Miss St", "NC State", "Miami"};
		
		// list of updated names to update in DB - make sure this list mirrors teamToEditList
		String [] teamEditList = {"California", "Western Michigan", "Miami (Ohio)", "Appalachian State", "Georgia Southern", "Washington State", "Massachusetts", "Eastern Michigan", "Connecticut", "Central Michigan", "New Mexico State", "Louisiana-Monroe", "Louisiana-Lafayette", "Southern Mississippi", "Mississippi", "Middle Tennessee", "Florida International", "Florida Atlantic", "Mississippi State", "North Carolina State", "Miami (Florida)"};
		
		try {
			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection(DBurl, DBusername, DBpassword);
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			Statement twoStmt = myConn.createStatement();
			Statement threeStmt = myConn.createStatement();
			
			// 3. Execute a SQL Query
			
			// use these two resultset statements as examples
			// ResultSet myRs = myStmt.executeQuery("select * from CollegeFootballStats.Test");
			// int yourRs = twoStmt.executeUpdate("insert into CollegeFootballStats.Test (PassYards) Values (10000)");
			
			/*  ONLY USE THIS FOR CLEARING ALL TABLES
			 
			int createTableRs;
			for (int i = 0; i < 1; i++) {
				createTableRs = threeStmt.executeUpdate(
					"TRUNCATE" + " `" + DBName + "`.`" + "Georgia Southern" + "`" 
				);
			}
			*/
			/* *** an example of UPDATE SQL query
			UPDATE `Scores2008`.`Test`
			SET
			`Opponent` = "test" 
			WHERE `Opponent` = 'cornelia'
			*/
			
			/* *** use this for all of the teams that get inputted incorrectly as Opponents
			int createTableRs;
			for (int i = 0; i < teamToEditList.length; i++) {
				createTableRs = threeStmt.executeUpdate(
					"UPDATE " + "`" + DBName + "`.`" + teamsList[i] + "` "
				    + "SET `Opponent` = '" + teamEditList[i] +"' "
					+ "WHERE `Opponent`='" + teamToEditList[i] + "'"		
				);
			}
			*/
			
			/* ***** Use updateNumOfGames.java file for creating first row of data in a new table *****
			*/
			/* DO NOT RUN THIS until necessary
			 /*  ***  This is query for creating new CollegeFootballStats schema in DB
			int createTableRs;
			for (int i = 0; i < teamsList.length; i++) {	
				createTableRs = threeStmt.executeUpdate(
						 "CREATE TABLE" + " `" + DBName + "`.`" + teamsList[i] + "` (" 
						 + "`gamesPlayed` INT(11), "
						 + "`offPointsPerGame` DOUBLE, "
						 + "`offRushAvg` DOUBLE, "
						 + "`offRushAtt` DOUBLE, "
						 + "`offPassPct` DOUBLE, "
						 + "`offPassYdsAtt` DOUBLE, "
						 + "`offAttPerGame` DOUBLE, "
						 + "`offYardsPerPlay` DOUBLE, "
						 + "`offFirstDwns` DOUBLE, "
						 + "`off20PlusYds` DOUBLE, "
						 + "`off30PlusYds` DOUBLE, "
						 + "`off40PlusYds` DOUBLE, "
						 + "`defPointsPerGame` DOUBLE, "
						 + "`defRushAvg` DOUBLE, "
						 + "`defPassPct` DOUBLE, "
						 + "`defPassYdsAtt` DOUBLE, "
						 + "`defPassAttPerGame` DOUBLE, "
						 + "`defYdsPerPlay` DOUBLE, "
						 + "`defTFL` DOUBLE, "
						 + "`def20PlusPlays` DOUBLE, "
						 + "`def30PlusPlays` DOUBLE, "
						 + "`def40PlusPlays` DOUBLE, "
						 + "`turnMargin` DOUBLE, "
						 + "`off3rdDownPct` DOUBLE, "
						 + "`offRZScorePct` DOUBLE, "
						 + "`offRZTDPct` DOUBLE, "
						 + "`def3rdDownPct` DOUBLE, "
						 + "`defRZScorePct` DOUBLE, "
						 + "`defRZTDPct` DOUBLE"
						 + ")"
				);
			}
			*/
			
			/* ***  This is for creating Scores schema ***
			int createTableRs;
			for (int i = 0; i < teamsList.length; i++) {	
				createTableRs = threeStmt.executeUpdate(
						 "CREATE TABLE" + " `" + DBName + "`.`" + teamsList[i] + "` (" 
					   + "`Opponent` VARCHAR(50) NULL, "
					   + "`TeamScore` INT NULL, "
					   + "`OpponentScore` INT NULL);"
				);
			}
			*/
			

			// 4. Process the result set
			/*
			while (myRs.next()) {
				System.out.println(myRs.getString(""));
			}
			*/
			
		} catch (Exception ex) {
			System.out.println("exception is " + ex);
		}
		
	}

}
