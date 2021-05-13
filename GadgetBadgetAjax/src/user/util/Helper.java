package user.util;
//IT19058160
//name : W.M.C.S Bandara
import java.util.ArrayList;
//here used the helper class to generate the message ID when inserting details to database ,when replying for messages.
public class Helper {//helper class
	private static final String COM = "";//declaring
	
	public static String generateStudentIDs(ArrayList<String>arrayList) {
		String msgid;
		
		int next = arrayList.size();
		next++;//increment by 1
		
		msgid = COM + next;
		if(arrayList.contains(msgid)) {//if condition
		next++;
		msgid= COM + next;
		
		}
	return msgid;//return the value
	}
}