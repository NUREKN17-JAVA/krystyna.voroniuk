package ua.nure.kn.voroniuk.agent;

import ua.nure.kn.voroniuk.db.DatabaseException;

public class SearchException extends Exception {
	
	public SearchException(DatabaseException e) {
		// TODO Auto-generated constructor stub
		e.printStackTrace();
	}

}
