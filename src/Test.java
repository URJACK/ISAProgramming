import com.model.Friend;
import com.model.FriendChat;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
        SessionOpenner so = SessionOpenner.getInstance();
        Session session = so.getSession();
        Transaction tr = session.beginTransaction();
        Friend friend = (Friend) session.createQuery("FROM Friend WHERE userAid='2' AND userBid='4' OR userAid='4' AND userBid='2'").list().get(0);
        System.out.println(friend.getChats().toArray().length);
        System.out.println(friend.getUserA());
        System.out.println(friend.getUserB());
        Set<FriendChat> chats = friend.getChats();
        FriendChat[] chatsarray = new FriendChat[chats.size()];
        chats.toArray(chatsarray);
        System.out.println(Arrays.toString(chatsarray));
    }
}
