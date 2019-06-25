package chat.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/ChatSocketV2/{memberNo1}")
public class ChatSocketV2 {
	
	//allRemote裡裝著所有正在跟伺服器連線的使用者
	//主鍵是MemberNo, 值是一個list，index=0為sessionId, index=1為用來送訊息的物件
	static Map<Integer, List<Object>> allRemote = new HashMap<>();
	
	String url = "jdbc:mysql://localhost:3306/whiloud?useUnicode=yes&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Taipei";
	String user = "root";
	String password = "suh9h9d0";

	
	@OnOpen
	//開啟連線時，會接收到memberNo1參數，還會接受到屬於他的session物件
	//Session物件可以取出sessionID和用來送訊息的basic物件
	public void onOpen(@PathParam("memberNo1") Integer memberNo1, Session session) {

		System.out.println("memberNo = " + memberNo1 + " has opened a connection, sessionID = " + session.getId());
		
		try {
			RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
			
			//把連上線的使用者裝入map中
			allRemote.put(memberNo1, new ArrayList<Object>(Arrays.asList(session.getId(),basicRemote)));
			System.out.println(allRemote);
			
//			allRemote.put(memberNo1, basicRemote);
			basicRemote.sendText("Connection Established");
			
			//伺服器送出某使用者已上線online||memberNo1給所有客戶端
			for(Integer memberNo : allRemote.keySet()) {
				if(memberNo == memberNo1) {	//不用告訴自己，自己已經上線
					continue;
				}
				List<Object> remote = allRemote.get(memberNo);
				RemoteEndpoint.Basic informOtherOnline =  (RemoteEndpoint.Basic)remote.get(1);
				informOtherOnline.sendText("online||" + memberNo1);
			}
			
			//伺服器送出目前上線名單給剛連上socket的使用者: online||{memberNo}
			for(Integer memberNo : allRemote.keySet()) {
				if(memberNo == memberNo1) {	//不用告訴自己，自己已經上線
					continue;
				}
				basicRemote.sendText("online||" + memberNo);
			}
			
			System.out.println("allRemote..." + allRemote.size());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@OnMessage
	//收到訊息時
	public void onMessage(String message, Session session) {
		System.out.println("recieve message: " + message);
		
		//如果開頭是resetUnread，代表只需要更新friend talbe的unread的資料
		if(message.startsWith("resetUnread|")){
			Integer memberNo1 = Integer.parseInt(message.substring(message.indexOf("|")+1,message.lastIndexOf("|")));
			Integer memberNo2 = Integer.parseInt(message.substring(message.lastIndexOf("|")+1));
			resetUnread(memberNo1, memberNo2);
			System.out.println("unread已歸零");
			return;
		}
		
		//如果是聊天訊息
		try {
			//向資料庫新增聊天資料，轉傳給需要送達的對象
			Integer memberNo1 = Integer.parseInt(message.substring(0, message.indexOf("|")));
			Integer memberNo2 = Integer.parseInt(message.substring(message.lastIndexOf("|")+1));
			message = message.substring(message.indexOf("|")+1, message.lastIndexOf("|"));
			System.out.println("memberNo1:" + memberNo1 + ", memberNo2:" + memberNo2);
			
			insertChat(memberNo1, memberNo2, message);
			unreadPlusOne(memberNo1, memberNo2);	//更新聊天紀錄的同時，未讀也+1
			String nickname = getMemberNickName(memberNo1);
			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			String returnText = "return|" + ts + " " + nickname + " says: <br>" + message;
			
			//先把訊息回傳給傳送者
			session.getBasicRemote().sendText(returnText);
			
			//再把訊息送給對象，並判斷有沒有在線上
			if(allRemote.get(memberNo2)==null) {
				System.out.println("訊息傳送對象" + memberNo2 + "不在線上");
			}else {
				String sendText = "message|" + ts + " " + nickname + " says: <br>" + message + "|" + memberNo1;
				((RemoteEndpoint.Basic)allRemote.get(memberNo2).get(1)).sendText(sendText);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}



	
	@OnClose
	//如果有離線事件觸發時
	public void onClose(Session session) {
		
		
		Integer offLineMember = null;
		
		try {
			//判斷是誰下線
			for(Integer memberNo : allRemote.keySet()) {
				if(allRemote.get(memberNo).get(0).toString().equals(session.getId())){
					offLineMember = memberNo;
					allRemote.remove(memberNo);
					System.out.println("memberNo " + memberNo + " has ended");
					break;
				}
			}
			
			//防止用戶只是重新整理或跳轉頁面之類的，所以在500毫秒後再檢查一次這個人在不在線上
			Thread.sleep(500);
			
			//如果用戶真的下線了
			if(allRemote.get(offLineMember)==null) {
				//告訴其他全部使用者offLineMember下線了
				for(Integer memberNo : allRemote.keySet()) {
					RemoteEndpoint.Basic informOtherOffline = (RemoteEndpoint.Basic)allRemote.get(memberNo).get(1);
					informOtherOffline.sendText("offline||" + offLineMember);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------
	//     以下是資料庫操作的部分
	//------------------------------------
	public void insertChat(Integer memberNo1, Integer memberNo2, String message) {
		
		String sql = "INSERT INTO chat (memberNo1, memberNo2, message, isRead, creationDateTime)"
				+ " VALUES(?,?,?,0,now());";
		try(Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, memberNo1);
			pstmt.setInt(2, memberNo2);
			pstmt.setString(3, message);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void unreadPlusOne(Integer memberNo1, Integer memberNo2) {
		
		String sql = "UPDATE friend SET unread = (unread + 1)"
				+ " WHERE memberNo = ? AND friendNo = ?";
		try(Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, memberNo2);
			pstmt.setInt(2, memberNo1);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getMemberNickName(Integer memberNo) {
		String sql = "SELECT nickname FROM member WHERE memberNo = ?";
		String nickname = null;
		try(Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			pstmt.setInt(1, memberNo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				nickname = rs.getString("nickname");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return nickname;
	}
	
	
	private void resetUnread(Integer memberNo1, Integer memberNo2) {
		String sql = "UPDATE friend SET unread = 0"
				+ " WHERE memberNo = ? AND friendNo = ?";
		try(Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, memberNo2);
			pstmt.setInt(2, memberNo1);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}