package ua.nure.kn.voroniuk.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.nure.kn.voroniuk.usermanagement.User;
import ua.nure.kn.voroniuk.usermanagement.util.Messages;

public class UserTableModel extends AbstractTableModel {
	
	private static final String[] COLUMN_NAMES = {Messages.getString("UserTableModel.0"), Messages.getString("UserTableModel.firstName"), Messages.getString("UserTableModel.lastName")};


	private static final Class[] COLUMN_CLASSESS = {Long.class, String.class, String.class};	
	private List users = null;
	
	public UserTableModel(Collection users) {
		this.users = new ArrayList(users);
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return users.size();
	}
	
	public Class getColumnClass(int columnIndex) {
		return COLUMN_CLASSESS[columnIndex];
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2: 
			return user.getLastName();
		}
		return null;
	}

}
