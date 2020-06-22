package common;

public class SpecialCharacter{

    public String ReplaceCharacter(String str, String pattern, String replace) 
	{
    	int s = 0, e = 0;
		
     	if (replace == null) 
      		{ replace = ""; }
    	
		StringBuffer result = new StringBuffer((int) str.length()*2);
    	while ((e = str.indexOf(pattern, s)) >= 0) {
      		result.append(str.substring(s, e));
      		result.append(replace);
      		s = e + pattern.length();
    	}
    	result.append(str.substring(s));
    	return result.toString();
  	}
	
	public String ReplaceString(String target, String from, String to) 
	{   
	  	int start = target.indexOf (from);
	  	if (start==-1) return target;
	  	int lf = from.length();
	  	char [] targetChars = target.toCharArray();
	  	StringBuffer buffer = new StringBuffer();
	  	int copyFrom = 0;
	  
	  	while (start != -1) {
			buffer.append (targetChars, copyFrom, start-copyFrom);
			buffer.append (to);
			copyFrom=start+lf;
			start = target.indexOf (from, copyFrom);
	 	}
		
	  	buffer.append (targetChars, copyFrom, targetChars.length-copyFrom);
	  	return buffer.toString();
	}

	public String ReplaceMultipleString(String target, String[][] from, String[][] to, int id) 
	{   
		String buf = "";
		
		for (int i = 1; i < id; i++)
		{
			int start = target.indexOf (from[i][0]);
			if (start==-1) return target;
			int lf = from[i][0].length();
			char [] targetChars = target.toCharArray();
			StringBuffer buffer = new StringBuffer();
			int copyFrom = 0;
		  
			while (start != -1) {
				buffer.append (targetChars, copyFrom, start-copyFrom);
				buffer.append (to[i][1]);
				copyFrom=start+lf;
				start = target.indexOf (from[i][0], copyFrom);
			}
			
			buffer.append (targetChars, copyFrom, targetChars.length-copyFrom);
			buf = buffer.toString();
			
			target = buf;
		}
		return buf;
	}


    public String HTMLCharacter(String value)
	{
    	if ( value == null ) return "";
		
		value = ReplaceCharacter(value, "&", "&amp;");
   		value = ReplaceCharacter(value, "<", "&lt;");
   		value = ReplaceCharacter(value, ">", "&gt;");
   		value = ReplaceCharacter(value, "\"", "&" + "quot;");
    	return value;
  	}
 
 
    public String printCapitalized(String str) {
        // Print a copy of str to standard output, with the
        // first letter of each word in upper case.
     char ch;       // One of the characters in str.
     char prevCh;   // The character that comes before ch in the string.
     char newCh;
     String newStr;
     int i;         // A position in str, from 0 to str.length()-1.
     prevCh = '.';  // Prime the loop with any non-letter character.
     newStr = "";
     for ( i = 0;  i < str.length();  i++ ) {
        ch = str.charAt(i);
        if ( Character.isLetter(ch)  &&  ! Character.isLetter(prevCh) )
           newCh = Character.toUpperCase(ch);
        else
           newCh = ch;
        prevCh = ch;  // prevCh for next iteration is ch.
        newStr = newStr + newCh;        
     }
     return newStr;
     
  }
  

}
