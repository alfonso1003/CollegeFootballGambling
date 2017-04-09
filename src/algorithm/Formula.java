package algorithm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class Formula {
	// DB Info
	String DBName = "Scores2008";
	String DBurl = "jdbc:mysql://localhost:3306/" + DBName + "?useSSL=false";
	String DBusername = "root";
	String DBpassword = "Wutangclan25";
	
	public String teamOneName;
	// array of opponents faced by team one
	public static ArrayList<String> teamOneOpponentsArrayList = new ArrayList<String>();
	// array for points scored by team one on offense
	public static ArrayList<Integer> teamOneScoresArrayList = new ArrayList<Integer>();
	// array for points given up by team one on defense
	public static ArrayList<Integer> teamOneOpponentsScoresArrayList = new ArrayList<Integer>();

	public String teamTwoName;
	// array of opponents faced by team one
	public static ArrayList<String> teamTwoOpponentsArrayList = new ArrayList<String>();
	// array for points scored by team one on offense
	public static ArrayList<Integer> teamTwoScoresArrayList = new ArrayList<Integer>();
	// array for points given up by team one on defense
	public static ArrayList<Integer> teamTwoOpponentsScoresArrayList = new ArrayList<Integer>();


	public static void main(String[] args) {

	}
	
	// Team One
	// name of Team One
	public void receiveTeamOneName(String team) {
		teamOneName = team;
	}
	
	// an array of all the teams the focused team played
	public void receiveTeamOneOpponentsArray(ArrayList<String> opponentsArray) {
		for (String opp : opponentsArray) {
			teamOneOpponentsArrayList.add(opp);
		}
	}
	
	// array for score of team --> use this to measure offense of focused team
	public void receiveTeamOneScoresArray(ArrayList<Integer> scoresArray) {
		for (Integer score : scoresArray) {
			teamOneScoresArrayList.add(score);
		}
	}
	
	// array for the score of the opponents --> use this to measure defense of focused team
	public void receiveTeamOneOpponentScoresArray(ArrayList<Integer> oppScoreArray) {
		for (Integer score : oppScoreArray) {
			teamOneOpponentsScoresArrayList.add(score);
		}
	}
	
	// Team Two
	// name of Team Two
	public void receiveTeamTwoName(String team) {
		teamTwoName = team;
	}
	
	// an array of all the teams the focused team played
	public void receiveTeamTwoOpponentsArray(ArrayList<String> opponentsArray) {
		for (String opp : opponentsArray) {
			teamTwoOpponentsArrayList.add(opp);
		}
	}
	
	// array for score of team --> use this to measure offense of focused team
	public void receiveTeamTwoScoresArray(ArrayList<Integer> scoresArray) {
		for (Integer score : scoresArray) {
			teamTwoScoresArrayList.add(score);
		}
	}
	
	// array for the score of the opponents --> use this to measure defense of focused team
	public void receiveTeamTwoOpponentScoresArray(ArrayList<Integer> oppScoreArray) {
		for (Integer score : oppScoreArray) {
			teamTwoOpponentsScoresArrayList.add(score);
		}
	}
	
	public void teamOneUpdateDB() {
		// teamOneName, teamOneOpponentsArrayList, teamOneScoresArrayList, teamOneOpponentsScoresArrayList
		for (int i = 0; i < teamOneOpponentsArrayList.size(); i++) {
			String opponent = teamOneOpponentsArrayList.get(i);
			int teamScore = teamOneScoresArrayList.get(i);
			int opponentScore = teamOneOpponentsScoresArrayList.get(i);
			if (opponent.contains("'")) {
				opponent = "Hawai'i";
				Hawaii(teamScore, opponentScore);
				continue;
			}
			try {
				// 1. Get a connection to database
				Connection myConn = DriverManager.getConnection(DBurl, DBusername, DBpassword);
				
				// 2. Create a statement
				Statement myStmt = myConn.createStatement();
				
				// 3. Execute a SQL Query
				int yourRs = myStmt.executeUpdate(
						"INSERT INTO `" + DBName + "`.`" + teamOneName + "`" 
						+ " (Opponent, TeamScore, OpponentScore) VALUES(" + "'" + opponent + "'" 
						+ ", " + teamScore
						+ ", " + opponentScore + ");"
						);

			} catch (Exception ex) {
				System.out.println("exception is " + ex);
				System.out.println("Opponent is " + opponent);
			}
		}
	}
	
	public void teamTwoUpdateDB() {
		// teamTwoName, teamTwoOpponentsArrayList, teamTwoScoresArrayList, teamTwoOpponentsScoresArrayList
		for (int i = 0; i < teamTwoOpponentsArrayList.size(); i++) {
			String opponent = teamTwoOpponentsArrayList.get(i);
			int teamScore = teamTwoScoresArrayList.get(i);
			int opponentScore = teamTwoOpponentsScoresArrayList.get(i);
			if (opponent.contains("'")) {
				Hawaii(teamScore, opponentScore);
				continue;
			}
			try {
				// 1. Get a connection to database
				Connection myConn = DriverManager.getConnection(DBurl, DBusername, DBpassword);
				
				// 2. Create a statement
				Statement myStmt = myConn.createStatement();
				
				// 3. Execute a SQL Query
				int yourRs = myStmt.executeUpdate(
						"INSERT INTO `" + DBName + "`.`" + teamTwoName + "`" 
						+ " (Opponent, TeamScore, OpponentScore) VALUES(" + "'" + opponent + "'" 
						+ ", " + teamScore
						+ ", " + opponentScore + ");"
						);

			} catch (Exception ex) {
				System.out.println("exception is " + ex);
			}
		}
	}
	
	public void Hawaii(int teamScore, int opponentScore) {
		try {
			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection(DBurl, DBusername, DBpassword);
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			// 3. Execute a SQL Query
			int yourRs = myStmt.executeUpdate(
					"INSERT INTO `" + DBName + "`.`" + teamOneName + "`" 
					+ " (Opponent, TeamScore, OpponentScore) VALUES('Hawaii', " + teamScore + ", " + opponentScore + ");"
					);

		} catch (Exception ex) {
			System.out.println("exception is " + ex);
		}
}

}
