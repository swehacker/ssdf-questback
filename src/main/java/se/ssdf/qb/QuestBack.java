package se.ssdf.qb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestBack extends HttpServlet {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q1 = req.getParameter("q1");
        String q2 = req.getParameter("q2");
        String q3 = req.getParameter("q3");
        String q4 = req.getParameter("q4");
        String q5 = req.getParameter("q5");

        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("insert into questions (q1, q2, q3, q4, q5) values (?, ?, ?, ?, ?)");
            pstmt.setString(1, q1);
            pstmt.setString(2, q2);
            pstmt.setString(3, q3);
            pstmt.setString(4, q4);
            pstmt.setString(5, q5);

            pstmt.execute();

            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        resp.sendRedirect("tack.html");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/qb", "root", "");
    }
}
