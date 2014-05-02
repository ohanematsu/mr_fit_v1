package serverCom;

import java.io.Serializable;
import java.util.List;

public class AuthenMessage implements Serializable {
	public int type;
	public String userId;
	public String password;
	public boolean result1;
	List<Friend> friends;
}
