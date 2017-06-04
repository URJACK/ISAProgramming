import com.model.user.Friend;
import com.model.user.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
        SessionOpenner so = SessionOpenner.getInstance();
        Session session = so.getSession();
        Transaction tr = session.beginTransaction();

//        User user = new User();
//        user.setAccount("Co_Admin");
//        user.setPassword("123456");
//        user.setEmail("316585693@qq.com");
//        user.setClazz(3);
//        user.setDate(Date.valueOf(SessionOpenner.getNowDate()));
//        session.save(user);

//        Friend friend = new Friend();
//        friend.setUserAid(1);
//        friend.setUserBid(2);
//        friend.setId(1);
//        session.save(friend);
//        FriendChat fc = new FriendChat();
//        fc.setId(1);
//        fc.setContent("你好");
//        fc.setRid(1);
//        session.save(fc);

//        Friend friend = (Friend) session.createQuery("FROM Friend WHERE id = 1").list().get(0);
//        Object[] objects = friend.getChats().toArray();
//        for (int i = 0; i < objects.length; i++) {
//            System.out.println(objects[i].toString());
//        }
        User a = (User) session.createQuery("FROM User WHERE id=2").list().get(0);
        User b = (User) session.createQuery("FROM User WHERE id=3").list().get(0);
//        Friend friend = new Friend();
//        friend.setUserAid(a.getId());
//        friend.setUserBid(b.getId());
//        session.save(friend);
//
//        tr.commit();
        System.out.println(a);
        System.out.println(b);
        session.close();
    }
}
