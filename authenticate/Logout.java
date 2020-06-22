package authenticate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout {
	
	public void removeSession(HttpServletRequest req)
	{
		HttpSession session = req.getSession(true);
		
		session.setAttribute("userID", "");
		session.setAttribute("userLevelID", "");
		session.setAttribute("userNickName", "");
		session.setAttribute("error", "");
		session.invalidate(); 
	}
}
