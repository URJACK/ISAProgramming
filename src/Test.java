import com.DAO.FriendDAO;
import com.DAO.UserDAO;
import com.json.Friend_Json;
import com.json.Info_Status;
import com.json.Info_Status_Object;
import com.model.FriendRequest;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        Session session = null;
        Info_Status_Object iso = new Info_Status_Object();
        try {
            session = SessionOpenner.getInstance().getSession();
            String account = "Fanshuai";
            User user = UserDAO.getUser(account, session);
            List<FriendRequest> friendShipRequest = FriendDAO.getFriendShipRequest(session, user.getAccount());
            List<Friend_Json> friend_jsons = getFriendJsonList(friendShipRequest);
            iso.setInfos("查询申请列表成功");
            iso.setObj(friend_jsons);
            iso.setStatus(true);
        } catch (Exception e) {
            iso.setInfos("查询失败");
            iso.setStatus(false);
        } finally {
            if (session != null)
                session.close();
        }
    }
    private static List<Friend_Json> getFriendJsonList(List<FriendRequest> friendShipRequest) {
        List<Friend_Json> friend_jsons = new ArrayList<>();
        for (int i = 0; i < friendShipRequest.size(); i++) {
            Friend_Json fj = new Friend_Json();
            FriendRequest fr = friendShipRequest.get(i);
            User sender = fr.getUserA();
            fj.setAccount(sender.getAccount());
            fj.setEmail(sender.getEmail());
            friend_jsons.add(fj);
        }
        return friend_jsons;
    }
}