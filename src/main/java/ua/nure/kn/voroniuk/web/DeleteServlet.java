package ua.nure.kn.voroniuk.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.voroniuk.db.DaoFactory;
import ua.nure.kn.voroniuk.db.DatabaseException;
import ua.nure.kn.voroniuk.usermanagement.User;

public class DeleteServlet extends EditServlet {

	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/delete.jsp").forward(req, resp);
	}

	@Override
	protected void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doCancel(req, resp);
	}

	@Override
	protected void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		try {
            try {
				DaoFactory.getInstance().getUserDao().delete((User) req.getSession().getAttribute("user"));
			} catch (ReflectiveOperationException e) {
				e.printStackTrace();
			}
        } catch (DatabaseException e) {
            req.setAttribute("error", "Error in the database: " + e.getMessage());
            try {
				req.getRequestDispatcher("/delete.jsp").forward(req, resp);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        try {
			req.getRequestDispatcher("/browse").forward(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
