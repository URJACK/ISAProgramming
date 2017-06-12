import com.model.Friend;
import com.model.FriendRequest;
import com.tool.SessionOpenner;
import org.hibernate.Session;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
        Session session = SessionOpenner.getInstance().getSession();
        FriendRequest friendRequest = (FriendRequest) session.get(FriendRequest.class,1);
        System.out.println(friendRequest.getUserA());
        System.out.println(friendRequest.getUserB());
        Friend friend = (Friend) session.get(Friend.class,1);
        System.out.println(friend.getUserA());
        System.out.println(friend.getUserB());
    }
}