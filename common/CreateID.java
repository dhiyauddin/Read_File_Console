package common;

import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBAction;

public class CreateID {
    int zeroLength; //noof0
    int IDCurrentTotal; //noID1
    int IDNewTotal; //noID2
    
    String ID = null;
    String zeroCode = null; // codeof0
    String IDCurrentCode= null; //codeID1
    String IDNewCode = null; //codeID2
    
    String queryAll = "";
    String queryMax = "";
    
    ResultSet rsAll = null;
    ResultSet rsMax = null;
    
    DBAction dbAll = null;
    DBAction dbMax = null;
    
    public void CreateID() {}

    public String CreateID(String dbName, String tableName, String typeID, String typeCode)
    {
        int codeTotal = 0;
        int zeroTotal = 0;
        String zeroString = "";
        String codeSubstring = "";

        try
        {
            if (typeID.equals("user_id")) {			// create user ID
                codeTotal = 1;
                zeroTotal = 6;
                zeroString = "00000";
                codeSubstring = "SUBSTRING("+typeID+",1,1)";
            }

            dbAll = new DBAction();
            dbAll.openConnection();

            queryAll = "SELECT * FROM "+ dbName +"."+ tableName +" WHERE "+codeSubstring+" = '"+typeCode+"'";
            dbAll.setQuery(queryAll);
            rsAll = dbAll.getResultSet();
            
            if(!rsAll.next())
            {
                ID = typeCode+zeroString+"1";
            }
            else
            {
                dbMax = new DBAction();
                dbMax.openConnection();
                
                queryMax = "SELECT MAX("+typeID+") FROM "+ dbName +"."+ tableName +" WHERE "+codeSubstring+" = '"+typeCode+"'";
                dbMax.setQuery(queryMax);
                rsMax = dbMax.getResultSet();
                rsMax.next();
                
                IDCurrentCode = rsMax.getString(1);
                IDCurrentCode = IDCurrentCode.substring(codeTotal);
                IDCurrentTotal = Integer.parseInt(IDCurrentCode);
                IDNewTotal = IDCurrentTotal + 1;
                IDNewCode = Integer.toString(IDNewTotal);
                zeroLength = zeroTotal - IDNewCode.length();
                
                if ( zeroLength == 0 )
                    zeroCode = "";
                else
                    zeroCode = "0";
                
                for(int count=1; count< zeroLength; count++ ) {
                    zeroCode = zeroCode+"0";
                }

                ID = typeCode+zeroCode+IDNewCode;
                
                dbMax.myConn.close();
            }
            dbAll.myConn.close();
        }
        catch (SQLException e) {e.printStackTrace();}
        return ID;
    }
}
