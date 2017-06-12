/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
//        Session session = SessionOpenner.getInstance().getSession();
//        FriendDAO.addFriendShipRequest(session,"Admin","Debug");
//        try {
//            FriendDAO.deleteFriendShipRequest(session,"Fufangzhou","Debu");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("删除失败");
//        }
//        List<FriendRequest> debug = FriendDAO.getFriendShipRequest(session, "Debug");
//        System.out.println(debug);
        Exception e = new Exception("NIhao");
        System.out.println(e.getMessage());
    }
}