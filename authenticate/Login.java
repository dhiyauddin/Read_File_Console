package authenticate;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Config;
import connection.DBAction;

public class Login extends Config{
    
    int userHits = 0;
    String userName = "";
    String userPassword = "";
    String userLevel = "";
    String userID = "";
    String userFName = "";
    String userStatus = "";
    String userICNo = "";
    
    public void validateLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, Exception
    {
        db = new DBAction();
        db.openConnection();
        
        db1 = new DBAction();
        db1.openConnection();
        
        db2 = new DBAction();
        db2.openConnection();
        
        HttpSession session = req.getSession(true);

        userName = req.getParameter("txtUsername");
        userPassword = req.getParameter("txtPassword");

        sql = "SELECT user_level, user_id, user_status, user_hits FROM user_login " +
                "WHERE " +
                "username = '" + userName + "' AND " +
                "password = '"+ userPassword +"'";

        db.setQuery(sql);
        rs = db.getResultSet();

        if(!rs.next())
        {
            session.setAttribute("userStatus", "Inactive");
        }else{
            userID = rs.getString("user_id");
            userLevel = rs.getString("user_level");
            userHits = rs.getInt("user_hits");
            userStatus = rs.getString("user_status");

            // to get user first name
            sql1 = "SELECT firstname, icnumber  from user " +
                    "WHERE user_id = '" + userID + "'";
            
            db1.setQuery(sql1);
            rs1 = db1.getResultSet();
            
            if(!rs1.next())
            {
                session.setAttribute("userStatus", "Inactive");
            }else{
                userFName = rs1.getString("firstname");
                userICNo = rs1.getString("icnumber");
            }

            // to increase userHits
            userHits = userHits + 1;
            sql2 = "UPDATE user_login " +
                    "SET user_hits = " + userHits +
                    " WHERE user_id = '" + userID + "'";

            db2.setQuery(sql2);
            db2.updateRecord();

            // set session
            session.setAttribute("userFName", userFName);
            session.setAttribute("userLevel", userLevel);
            session.setAttribute("userID", userID);
            session.setAttribute("userStatus", userStatus);
            session.setAttribute("userICNo", userICNo);
            session.setAttribute("userHits", Integer.toString(userHits));
            
        }

        db.myConn.close();
        db1.myConn.close();
        db2.myConn.close();
    }
}

