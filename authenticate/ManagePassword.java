package authenticate;

import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.*;

import common.Config;
import connection.DBAction;
import java.util.Properties;

public class ManagePassword extends Config{
	
	public String userName = "";
	public String password = "";
	public String userLevel = "";
	public String userStatus = "";
	public String user_id = "";
	
	public void insertLogin()
	{
		try {
			
			db = new DBAction();
			db.openConnection();
			
			sql = "INSERT INTO user_login (username, password, user_id, user_level, user_status) " +
				  "VALUES ('"+userName+"', '"+password+"', '"+user_id+"', '"+userLevel+"', 'active')";
			
			db.setQuery(sql);
			db.insertRecord();
			
			db.myConn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void viewLogin(String userID)
	{
		try{
			
			db = new DBAction();
			db.openConnection();
			
			sql = "SELECT * FROM user_login WHERE user_id = '" + userID + "'";
			db.setQuery(sql);
			rs = db.getResultSet();
			
			if(rs.next())
			{
				userName = rs.getString("username");
				password = rs.getString("password");
				userLevel = rs.getString("user_level");
				userStatus = rs.getString("user_status");
			}
			
			db.myConn.close();
		}catch(SQLException e){e.printStackTrace();}
	}
	
	public void viewUserLogin(String username)
	{
		try{
			
			db = new DBAction();
			db.openConnection();
			
			sql = "SELECT * FROM user_login WHERE username = '" + username + "'";
			db.setQuery(sql);
			rs = db.getResultSet();
			
			if(rs.next())
			{
				userName = rs.getString("username");
				password = rs.getString("password");
				userLevel = rs.getString("user_level");
				userStatus = rs.getString("user_status");
				user_id = rs.getString("user_id");
			}
			
			db.myConn.close();
			
		}catch(SQLException e){e.printStackTrace();}
	}
	
	public void viewAdmin()
	{
		try
		{
			db = new DBAction();
			db.openConnection();
			
			sql = "SELECT user_id FROM user_login WHERE user_level = 'admin'";
			db.setQuery(sql);
			rs = db.getResultSet();
			
			if(rs.next())
			{
				user_id = rs.getString("user_id");
			}
			
			db.myConn.close();
		}catch(SQLException e){e.printStackTrace();}
	}
	
	public void updateLogin()
	{
		try {
			
			db = new DBAction();
			db.openConnection();
			
			sql = "UPDATE user_login set " +
				  "username = '"+userName+"', " +
				  "password = '"+password+"', " + 
				  "user_level = '"+userLevel+"' " +
				  "WHERE user_id = '"+user_id+"'";
			
			db.setQuery(sql);
			db.updateRecord();
			
			db.myConn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void updateUserStatus()
	{
		try{			
			db = new DBAction();
			db.openConnection();
			
			sql = "UPDATE user_login SET " + 
				  "user_status = '" + userStatus + "' " +
				  "WHERE user_id = '" + user_id + "'";
			
			db.setQuery(sql);
			db.updateRecord();
			
			db.myConn.close();
			
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

    public void sendMail()
  	{
        try{
            boolean debug = false;

            //Set the host smtp address
            Properties props = new Properties();

            //props.put("mail.smtp.user", "'zaila.sabri@gmail.com'");
            props.put("mail.smtp.host", "ssl://smtp.gmail.com");
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");

            Authenticator auth = new SMTPAuthenticator();

            //Session session = Session.getDefaultInstance(props, auth);
            Session session = Session.getInstance(props, auth);
            session.setDebug(debug);

            // create a message
            Message msg = new MimeMessage(session);

            // set the from and to address
            InternetAddress addressFrom = new InternetAddress("admin@efarm.com");
            msg.setFrom(addressFrom);

            userName = "'" + userName + "'";
            userName.trim();
            String[] recipients = userName.split("\\,");

            InternetAddress[] addressTo = new InternetAddress[recipients.length];

            for (int i = 0; i < recipients.length; i++)
            {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);

            // Setting the Subject and Content Type
            msg.setSubject("Forget Password");

            String message = "Dear Sir/Mr./Mdm/Miss,  \n\n" +
                             "Your account information :  \n" +
                             "Username  : " + userName + "\n" +
                             "Password    : " + password + "\n";

            msg.setContent(message, "text/plain");
            Transport.send(msg);

        }catch(Exception e){e.printStackTrace();}
  	}

    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
	
	// SET DATA ----------------------------------------------------------------------------------------------------------
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setUserLevel(String userLevel)
	{
		this.userLevel = userLevel;
	}
	
	public void setUserID(String user_id)
	{
		this.user_id = user_id;
	}
	
	public void setUserStatus(String userStatus)
	{
		this.userStatus = userStatus;
	}
	
	// GET DATA ----------------------------------------------------------------------------------------------------------
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getUserLevel()
	{
		return userLevel;
	}
	
	public String getUserStatus()
	{
		return userStatus;
	}
	
	public String getUserID()
	{
		return user_id;
	}
}
