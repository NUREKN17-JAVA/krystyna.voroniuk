package ua.nure.kn.voroniuk.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.voroniuk.db.DaoFactory;
import ua.nure.kn.voroniuk.db.DatabaseException;
import ua.nure.kn.voroniuk.usermanagement.User;

public class AddServlet extends EditServlet {

	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

	@Override
	protected void processUser(User user) throws DatabaseException {
		// TODO Auto-generated method stub
		try {
			DaoFactory.getInstance().getUserDao().create(user);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
}
