package init;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import chat.model.ChatBean;
import clip.model.ClipBean;
import clip.model.ScriptBean;
import comment.model.CommentBean;
import friend.model.FriendBean;
import friend.model.FriendRequestBean;
import jdk.javadoc.internal.doclets.formats.html.markup.Comment;
import member.model.Old_FriendListBean;
import post.model.PostBean;
import post.model.RecordBean;
import register.model.MemberBean;

public class Create {

	public static void main(String[] args) throws FileNotFoundException, IOException, SerialException, SQLException {
		// TODO Auto-generated method stub
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		//member資料
		MemberBean mb0 =new MemberBean(null, "nullString", "12345","nullString", "nullString","nullString", ts, "/data/memberPicture/default.png",null, 0, false, false,null,null);
		MemberBean mb1 =new MemberBean(null, "whiloud02@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","WhiWhi", "M",null, ts, "/data/memberPicture/2.jpg",null, 30, false, false,null,null);
		MemberBean mb2 =new MemberBean(null, "whiloud03@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Linda", "F",null, ts, "/data/memberPicture/3.jpg",null, 10, true, false,null,null);
		MemberBean mb3 =new MemberBean(null, "whiloud04@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Mark", "M",null, ts, "/data/memberPicture/4.jpg",null, 5, false, false,null,null);
		MemberBean mb4 =new MemberBean(null, "whiloud05@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Neow", "O",null, ts, "/data/memberPicture/5.jpg",null, 7, false, false,null,null);
		MemberBean mb5 =new MemberBean(null, "whiloud06@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Mary", "F",null, ts, "/data/memberPicture/6.jpg",null, 0, false, false,null,null);
		MemberBean mb6 =new MemberBean(null, "whiloud07@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","John", "M",null, ts, "/data/memberPicture/7.jpg",null, 0, false, false,null,null);
		MemberBean mb7 =new MemberBean(null, "whiloud08@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","AloHa", "O",null, ts, "/data/memberPicture/8.jpg",null, 0, false, false,null,null);
		MemberBean mb8 =new MemberBean(null, "whiloud09@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Shadow", "O",null, ts, "/data/memberPicture/9.jpg",null, 0, false, false,null,null);
		MemberBean mb9 =new MemberBean(null, "whiloud10@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Adela", "F",null, ts, "/data/memberPicture/10.jpg",null, 0, false, false,null,null);
		MemberBean mb10 =new MemberBean(null, "whiloud11@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","SleepCat", "O",null, ts, "/data/memberPicture/11.jpg",null, 0, false, false,null,null);
		MemberBean mb11 =new MemberBean(null, "whiloud12@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Jack", "M",null, ts, "/data/memberPicture/12.jpg",null, 0, false, false,null,null);
		MemberBean mb12 =new MemberBean(null, "whiloud13@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Yuri", "F",null, ts, "/data/memberPicture/13.jpg",null, 0, false, false,null,null);
		MemberBean mb13 =new MemberBean(null, "whiloud14@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Hiro", "M",null, ts, "/data/memberPicture/14.jpg",null, 0, false, false,null,null);
		MemberBean mb14 =new MemberBean(null, "whiloud15@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Zoro", "O",null, ts, "/data/memberPicture/15.jpg",null, 0, false, false,null,null);
		MemberBean mb15 =new MemberBean(null, "whiloud16@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Luffy", "O",null, ts, "/data/memberPicture/16.jpg",null, 0, false, false,null,null);
		MemberBean mb16 =new MemberBean(null, "whiloud17@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Chopper", "O",null, ts, "/data/memberPicture/17.jpg",null, 0, false, false,null,null);
		MemberBean mb17 =new MemberBean(null, "whiloud18@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Nami", "O",null, ts, "/data/memberPicture/18.jpg",null, 0, false, false,null,null);
		MemberBean mb18 =new MemberBean(null, "whiloud19@gmail.com", "6f7e5a841f0792d8867e6b5a1cf37993","Brook", "O",null, ts, "/data/memberPicture/19.jpg",null, 0, false, false,null,null);
		
		Set<MemberBean> mb = new LinkedHashSet<>();
		mb.add(mb0);mb.add(mb1);mb.add(mb2);mb.add(mb3);mb.add(mb4);mb.add(mb5);
		mb.add(mb6);mb.add(mb7);mb.add(mb8);mb.add(mb9);mb.add(mb10);
		mb.add(mb11);mb.add(mb12);mb.add(mb13);mb.add(mb14);mb.add(mb15);
		mb.add(mb16);mb.add(mb17);mb.add(mb18);
		
		//clip資料
		ClipBean cb1 =new ClipBean(null,"/data/clip/1.mp4","FAST & FURIOUS: Hobbs & Shaw","/data/clip/a.jpg",ts,"HOBBS","male","SHAW","male","This is the first clip.",null);
		ClipBean cb2 =new ClipBean(null,"/data/clip/2.mp4","40不惑-1","/data/clip/b.jpg",ts,"Pete","male","Barry","male","如果老婆遇到什麼大事",null);
		ClipBean cb3 =new ClipBean(null,"/data/clip/3.mp4","40不惑-2","/data/clip/c.jpg",ts,"Debbie","female","Pete","male","為什麼不跟我吵架.",null);
		ClipBean cb4 =new ClipBean(null,"/data/clip/4.mp4","自殺突擊隊-醫師vs小丑","/data/clip/d.jpg",ts,"Narrator|Dr. Quinzel","female","Joker","male","女醫生愛上犯人",null);
		ClipBean cb5 =new ClipBean(null,"/data/clip/5.mp4","星際異攻隊-浣熊和Groot","/data/clip/e.jpg",ts,"Rocket Raccoon","male","Groot","both", "I'm Groot",null);
		ClipBean cb6 =new ClipBean(null,"/data/clip/1.mp4","Hobbs & Shaw","/data/clip/f.jpg",ts,"HOBBS","male","SHAW","male","This is the first clip.",null);
		ClipBean cb7 =new ClipBean(null,"/data/clip/2.mp4","This is 40-1","/data/clip/g.jpg",ts,"Pete","male","Barry","male","如果老婆遇到什麼大事",null);
		ClipBean cb8 =new ClipBean(null,"/data/clip/3.mp4","This is 40-2","/data/clip/h.jpg",ts,"Debbie","female","Pete","male","為什麼不跟我吵架.",null);
		ClipBean cb9 =new ClipBean(null,"/data/clip/4.mp4","The Clown","/data/clip/i.jpg",ts,"Narrator|Dr. Quinzel","female","Joker","male","女醫生愛上犯人",null);
		Set<ClipBean> cb = new LinkedHashSet<>();
		cb.add(cb1);cb.add(cb2);cb.add(cb3);cb.add(cb4);cb.add(cb5);
		cb.add(cb6);cb.add(cb7);cb.add(cb8);cb.add(cb9);
		
		//post資料
		PostBean pb1 = new PostBean(null, "Whiloud好好玩", ts, "感謝大大幫忙，好友++", false, true, 0, false);
		PostBean pb2 = new PostBean(null, "獨立完成", ts, "一個人配音好無聊啊", false, true, 0, false);
		PostBean pb3 = new PostBean(null, "一起加好友", ts, "有沒有女高音願意幫忙~", false, false, 1, false);
		
		PostBean pb4 = new PostBean(null, "Amazing!", ts, "What a success!!", false, false, 2, false);
		PostBean pb5 = new PostBean(null, "I hate Clown", ts, "Clown is disgusting.", false, true, 0, false);
		PostBean pb6 = new PostBean(null, "小試身手", ts, "真的是一塊蛋糕~", false, true, 0, false);
		PostBean pb7 = new PostBean(null, "我想要飆車阿~", ts, "衝了啦~~~~~", false, false, 2, false);
		PostBean pb8 = new PostBean(null, "I am Groot", ts, "Groot is the best!!", false, false, 1, false);
		
		pb1.setMb1(mb1);pb1.setMb2(mb2);pb1.setCb(cb1);
		pb2.setMb1(mb4);pb2.setMb2(mb4);pb2.setCb(cb2);
		pb3.setMb1(mb3);pb3.setMb2(mb0);pb3.setCb(cb3);
		
		pb4.setMb1(mb5);pb4.setMb2(mb0);pb4.setCb(cb8);
		pb5.setMb1(mb2);pb5.setMb2(mb1);pb5.setCb(cb4);
		pb6.setMb1(mb6);pb6.setMb2(mb0);pb6.setCb(cb7);
		pb7.setMb1(mb7);pb7.setMb2(mb0);pb7.setCb(cb6);
		pb8.setMb1(mb8);pb8.setMb2(mb0);pb8.setCb(cb5);
		
		Set<PostBean> pb = new LinkedHashSet<>();
		pb.add(pb1);pb.add(pb2);pb.add(pb3);pb.add(pb4);
		pb.add(pb5);pb.add(pb6);pb.add(pb7);pb.add(pb8);
		
		//script資料
		ScriptBean sb1 = new ScriptBean(null,1, "HOBBS", 6, 1, "1.40", "2.54", "pick a door", "選一道門");
		sb1.setCb(cb1);
		ScriptBean sb2 = new ScriptBean(null,2,"SHAW",6,2,"2.54","3.58","I\'ll right there","我選那道門");
		sb2.setCb(cb1);
		ScriptBean sb3 = new ScriptBean(null,1,"HOBBS",6,3,"3.58","5.20","No, that\'s my door. Pick another door","不，那是我的門，請選別的門");
		sb3.setCb(cb1);
		ScriptBean sb4 = new ScriptBean(null, 2,"SHAW",6,4,"5.20","6.24","What\'s wrong with you?","你有什麼問題嗎?");
		sb4.setCb(cb1);
		ScriptBean sb5 = new ScriptBean(null, 2,"SHAW",6,5,"8.52","9.98","You know what? You were right. This is your door?","你知道嗎?你是對的，這道是你的門");
		sb5.setCb(cb1);
		ScriptBean sb6 = new ScriptBean(null, 1,"HOBBS",6,6,"9.98","12.00","what\'s the matter. You got a lot of bad guys behind that door?","怎麼了? 你的門後面有很多壞蛋?");
		sb6.setCb(cb1);
		ScriptBean sb7 = new ScriptBean(null,1,"Pete",9,1,"0.50","3.19","This sounds horrible. But do you ever wondered what it would be like...","這聽起來很可怕，但你有沒有想過...");
		sb7.setCb(cb2);
		ScriptBean sb8 = new ScriptBean(null, 1,"Pete",9,2,"3.19","6.12","if you and your wife were separated by something bigger.","要是你跟你老婆因為某件嚴重的事而分開");
		sb8.setCb(cb2);
		ScriptBean sb9 = new ScriptBean(null, 1,"Pete",9,3,"6.63","8.55","like death, like her death.","像是死亡 譬如說她死了");
		sb9.setCb(cb2);
		ScriptBean sb10 = new ScriptBean(null, 2,"Barry",9,4,"8.55","10.7","I have given it a fair amount of thought.","我還真有認真想過");
		sb10.setCb(cb2);
		ScriptBean sb11 = new ScriptBean(null, 1,"Pete",9,5,"10.70","14.50","Not in a painful way, but just like a gentle floating off.","不是想像她痛苦的死了，只是像輕輕的飄走了那樣");
		sb11.setCb(cb2);
		ScriptBean sb12 = new ScriptBean(null, 2,"Barry",9,6,"14.50","17.61","It’s gotta be peaceful, I mean this is the mother of your children.","很安詳的那樣，她可是小孩的媽阿");
		sb12.setCb(cb2);
		ScriptBean sb13 = new ScriptBean(null, 1,"Pete",9,7,"17.61","19.23","And then the new wife would be great!","然後新的老婆很棒");
		sb13.setCb(cb2);
		ScriptBean sb14 = new ScriptBean(null, 2,"Barry",9,8,"19.23","21.53","God, I can’t wait to meet my second wife!","天啊！我等不及要跟我第二任老婆相遇了");
		sb14.setCb(cb2);
		ScriptBean sb15 = new ScriptBean(null, 2,"Barry",9,9,"21.55","24.63","I hope she likes me better than this one.","我希望她比我現任老婆還喜歡我");
		sb15.setCb(cb2);
		ScriptBean sb16 = new ScriptBean(null, 1,"Debbie",7,1,"0.83","1.97","Why didn’t you fight?","你為何不跟我吵?");
		sb16.setCb(cb3);
		ScriptBean sb17 = new ScriptBean(null, 2,"Pete",7,2,"1.91","4.00","I don’t know. You get so mad at me.","我不知道，你氣得要死");
		sb17.setCb(cb3);
		ScriptBean sb18 = new ScriptBean(null, 2,"Pete",7,3,"4.79","6.24","Sometimes I feel like you want to kill me.","有時候我覺得你想殺了我");
		sb18.setCb(cb3);
		ScriptBean sb19 = new ScriptBean(null, 1,"Debbie",7,4,"6.24","7.83","I do wanna kill you.","我是真的想殺了你");
		sb19.setCb(cb3);
		ScriptBean sb20 = new ScriptBean(null, 2,"Pete",7,5,"7.83","8.44","How would you do it?","你要怎麼殺?");
		sb20.setCb(cb3);
		ScriptBean sb21 = new ScriptBean(null, 1,"Debbie",7,6,"8.44","12.00","I would poison cupcakes that you pretend not to eat everyday","我會下毒在你每天假裝沒吃的杯子蛋糕裡");
		sb21.setCb(cb3);
		ScriptBean sb22 = new ScriptBean(null, 1,"Debbie",7,7,"12.00","16.25","I would enjoy our last few months together that while killing you","在殺你的同時我會好好的享受最後相處的幾個月");
		sb22.setCb(cb3);
		ScriptBean sb23 = new ScriptBean(null, 1,"Narrator",11,1,"0.14","3.21","She was assigned to The Clown himself.","她被派給了小丑");
		sb23.setCb(cb4);
		ScriptBean sb24 = new ScriptBean(null, 2,"Joker",11,2,"3.21","8.08","Dr. Quinzel, I lived for these moments with you.","奎澤爾醫生，我就盼著跟你共處的時光");
		sb24.setCb(cb4);
		ScriptBean sb25 = new ScriptBean(null, 2,"Joker",11,3,"8.87","10.41","What do you get?","你拿了什麼");
		sb25.setCb(cb4);
		ScriptBean sb26 = new ScriptBean(null, 1,"Dr. Quinzel",11,4,"10.41","12.25","I got you a kitty.","給你拿了隻小貓");
		sb26.setCb(cb4);
		ScriptBean sb27 = new ScriptBean(null, 2,"Joker",11,5,"12.25","13.84","So thoughtful","真體貼");
		sb27.setCb(cb4);
		ScriptBean sb28 = new ScriptBean(null, 1,"Narrator",11,6,"13.84","17.68","She thought she was curing him. But, she was falling in love","她以為自己在治療他，但其實她愛上了他");
		sb28.setCb(cb4);
		ScriptBean sb29 = new ScriptBean(null, 2,"Joker",11,7,"19.25","22.16","There is something you could do for me, doctor.","你可以幫我個忙，醫生");
		sb29.setCb(cb4);
		ScriptBean sb30 = new ScriptBean(null, 1,"Dr. Quinzel",11,8,"22.16","24.05","Anything. I mean, yeah.","你隨便說，當然");
		sb30.setCb(cb4);
		ScriptBean sb31 = new ScriptBean(null, 2,"Joker ",11,9,"24.05","26.08","I need a machine gun.","我需要一把機槍");
		sb31.setCb(cb4);
		ScriptBean sb32 = new ScriptBean(null, 1,"Dr. Quinzel",11,10,"28.36","30.02","A machine gun?","機槍?");
		sb32.setCb(cb4);
		ScriptBean sb33 = new ScriptBean(null, 1,"Narrator",11,11,"32.04","34.60","Talk about a workplace romance gone wrong.","這才叫辦公室戀情出了岔子");
		sb33.setCb(cb4);
		ScriptBean sb34= new ScriptBean(null,1,"Rocket Raccoon",10,1,"0","5.0","Then you push this button, which will give you five minutes to get out of there.","然後你再按這個按鈕，然後你有五分鐘的時間逃跑");
		sb34.setCb(cb5);
		ScriptBean sb35 = new ScriptBean(null, 1,"Rocket Raccoon",10,2,"5.0","9.76","Now, whatever you do. Don’t push this button","聽好，不管怎樣，千萬別按這個");
		sb35.setCb(cb5);
		ScriptBean sb36 = new ScriptBean(null,1,"Rocket Raccoon",10,3,"9.76","13.37","Because that would set off the bomb immediately and we’ll all be dead.","按下它炸彈會立刻爆炸，然後我們就死定了");
		sb36.setCb(cb5);
		ScriptBean sb37 = new ScriptBean(null, 1,"Rocket Raccoon",10,4,"13.37","15.71","Now, repeat back what I just said.","好了，重複一遍我剛說的話");
		sb37.setCb(cb5);
		ScriptBean sb38 = new ScriptBean(null, 2,"Groot",10,5,"15.71","19.41","I am Groot. I am Groot.","我叫格魯特x2");
		sb38.setCb(cb5);
		ScriptBean sb39 = new ScriptBean(null, 1,"Rocket Raccoon",10,6,"19.41","20.24","That’s right.","沒錯");
		sb39.setCb(cb5);
		ScriptBean sb40 = new ScriptBean(null, 2,"Groot",10,7,"20.24","21.09","I am Groot.","我叫格魯特");
		sb40.setCb(cb5);
		ScriptBean sb41 = new ScriptBean(null, 1,"Rocket Raccoon",10,8,"21.09","25.81","No! that’s the button that will kill everyone! Try again","不！這個按鈕會把我們都炸死的！再來一遍");
		sb41.setCb(cb5);
		ScriptBean sb42 = new ScriptBean(null, 2,"Groot",10,9,"28.11","33.68","I am Groot. I am Groot. I am Groot.","我叫格魯特x3");
		sb42.setCb(cb5);
		ScriptBean sb43 = new ScriptBean(null, 1,"Rocket Raccoon",10,10,"33.68","37.29","No! That’s exactly what you just said. How is that even possible?","不！這跟你剛說的一模一樣好嗎? 這怎麼可能呢?");
		sb43.setCb(cb5);
		
		ScriptBean sb44 = new ScriptBean(null,1, "HOBBS", 6, 1, "1.40", "2.54", "pick a door", "選一道門");
		sb44.setCb(cb6);
		ScriptBean sb45 = new ScriptBean(null,2,"SHAW",6,2,"2.54","3.58","I\'ll right there","我選那道門");
		sb45.setCb(cb6);
		ScriptBean sb46 = new ScriptBean(null,1,"HOBBS",6,3,"3.58","5.20","No, that\'s my door. Pick another door","不，那是我的門，請選別的門");
		sb46.setCb(cb6);
		ScriptBean sb47 = new ScriptBean(null, 2,"SHAW",6,4,"5.20","6.24","What\'s wrong with you?","你有什麼問題嗎?");
		sb47.setCb(cb6);
		ScriptBean sb48 = new ScriptBean(null, 2,"SHAW",6,5,"8.52","9.98","You know what? You were right. This is your door?","你知道嗎?你是對的，這道是你的門");
		sb48.setCb(cb6);
		ScriptBean sb49 = new ScriptBean(null, 1,"HOBBS",6,6,"9.98","12.00","what\'s the matter. You got a lot of bad guys behind that door?","怎麼了? 你的門後面有很多壞蛋?");
		sb49.setCb(cb6);
		
		ScriptBean sb50 = new ScriptBean(null,1,"Pete",9,1,"0.50","3.19","This sounds horrible. But do you ever wondered what it would be like...","這聽起來很可怕，但你有沒有想過...");
		sb50.setCb(cb7);
		ScriptBean sb51 = new ScriptBean(null, 1,"Pete",9,2,"3.19","6.12","if you and your wife were separated by something bigger.","要是你跟你老婆因為某件嚴重的事而分開");
		sb51.setCb(cb7);
		ScriptBean sb52 = new ScriptBean(null, 1,"Pete",9,3,"6.63","8.55","like death, like her death.","像是死亡 譬如說她死了");
		sb52.setCb(cb7);
		ScriptBean sb53 = new ScriptBean(null, 2,"Barry",9,4,"8.55","10.7","I have given it a fair amount of thought.","我還真有認真想過");
		sb53.setCb(cb7);
		ScriptBean sb54 = new ScriptBean(null, 1,"Pete",9,5,"10.70","14.50","Not in a painful way, but just like a gentle floating off.","不是想像她痛苦的死了，只是像輕輕的飄走了那樣");
		sb54.setCb(cb7);
		ScriptBean sb55 = new ScriptBean(null, 2,"Barry",9,6,"14.50","17.61","It’s gotta be peaceful, I mean this is the mother of your children.","很安詳的那樣，她可是小孩的媽阿");
		sb55.setCb(cb7);
		ScriptBean sb56 = new ScriptBean(null, 1,"Pete",9,7,"17.61","19.23","And then the new wife would be great!","然後新的老婆很棒");
		sb56.setCb(cb7);
		ScriptBean sb57 = new ScriptBean(null, 2,"Barry",9,8,"19.23","21.53","God, I can’t wait to meet my second wife!","天啊！我等不及要跟我第二任老婆相遇了");
		sb57.setCb(cb7);
		ScriptBean sb58 = new ScriptBean(null, 2,"Barry",9,9,"21.55","24.63","I hope she likes me better than this one.","我希望她比我現任老婆還喜歡我");
		sb58.setCb(cb7);
		
		ScriptBean sb59 = new ScriptBean(null, 1,"Debbie",7,1,"0.83","1.97","Why didn’t you fight?","你為何不跟我吵?");
		sb59.setCb(cb8);
		ScriptBean sb60 = new ScriptBean(null, 2,"Pete",7,2,"1.91","4.00","I don’t know. You get so mad at me.","我不知道，你氣得要死");
		sb60.setCb(cb8);
		ScriptBean sb61 = new ScriptBean(null, 2,"Pete",7,3,"4.79","6.24","Sometimes I feel like you want to kill me.","有時候我覺得你想殺了我");
		sb61.setCb(cb8);
		ScriptBean sb62 = new ScriptBean(null, 1,"Debbie",7,4,"6.24","7.83","I do wanna kill you.","我是真的想殺了你");
		sb62.setCb(cb8);
		ScriptBean sb63 = new ScriptBean(null, 2,"Pete",7,5,"7.83","8.44","How would you do it?","你要怎麼殺?");
		sb63.setCb(cb8);
		ScriptBean sb64 = new ScriptBean(null, 1,"Debbie",7,6,"8.44","12.00","I would poison cupcakes that you pretend not to eat everyday","我會下毒在你每天假裝沒吃的杯子蛋糕裡");
		sb64.setCb(cb8);
		ScriptBean sb65 = new ScriptBean(null, 1,"Debbie",7,7,"12.00","16.25","I would enjoy our last few months together that while killing you","在殺你的同時我會好好的享受最後相處的幾個月");
		sb65.setCb(cb8);
		
		ScriptBean sb66 = new ScriptBean(null, 1,"Narrator",11,1,"0.14","3.21","She was assigned to The Clown himself.","她被派給了小丑");
		sb66.setCb(cb9);
		ScriptBean sb67 = new ScriptBean(null, 2,"Joker",11,2,"3.21","8.08","Dr. Quinzel, I lived for these moments with you.","奎澤爾醫生，我就盼著跟你共處的時光");
		sb67.setCb(cb9);
		ScriptBean sb68 = new ScriptBean(null, 2,"Joker",11,3,"8.87","10.41","What do you get?","你拿了什麼");
		sb68.setCb(cb9);
		ScriptBean sb69 = new ScriptBean(null, 1,"Dr. Quinzel",11,4,"10.41","12.25","I got you a kitty.","給你拿了隻小貓");
		sb69.setCb(cb9);
		ScriptBean sb70 = new ScriptBean(null, 2,"Joker",11,5,"12.25","13.84","So thoughtful","真體貼");
		sb70.setCb(cb9);
		ScriptBean sb71 = new ScriptBean(null, 1,"Narrator",11,6,"13.84","17.68","She thought she was curing him. But, she was falling in love","她以為自己在治療他，但其實她愛上了他");
		sb71.setCb(cb9);
		ScriptBean sb72 = new ScriptBean(null, 2,"Joker",11,7,"19.25","22.16","There is something you could do for me, doctor.","你可以幫我個忙，醫生");
		sb72.setCb(cb9);
		ScriptBean sb73 = new ScriptBean(null, 1,"Dr. Quinzel",11,8,"22.16","24.05","Anything. I mean, yeah.","你隨便說，當然");
		sb73.setCb(cb9);
		ScriptBean sb74 = new ScriptBean(null, 2,"Joker ",11,9,"24.05","26.08","I need a machine gun.","我需要一把機槍");
		sb74.setCb(cb9);
		ScriptBean sb75 = new ScriptBean(null, 1,"Dr. Quinzel",11,10,"28.36","30.02","A machine gun?","機槍?");
		sb75.setCb(cb9);
		ScriptBean sb76 = new ScriptBean(null, 1,"Narrator",11,11,"32.04","34.60","Talk about a workplace romance gone wrong.","這才叫辦公室戀情出了岔子");
		sb76.setCb(cb9);
		
		
		
		Set<ScriptBean> sb = new LinkedHashSet<>();
		sb.add(sb1); sb.add(sb2); sb.add(sb3); sb.add(sb4); sb.add(sb5); sb.add(sb6); sb.add(sb7); sb.add(sb8); sb.add(sb9); sb.add(sb10);
		sb.add(sb11); sb.add(sb12); sb.add(sb13); sb.add(sb14); sb.add(sb15); sb.add(sb16); sb.add(sb17); sb.add(sb18); sb.add(sb19); sb.add(sb20);
		sb.add(sb21); sb.add(sb22); sb.add(sb23); sb.add(sb24); sb.add(sb25); sb.add(sb26); sb.add(sb27); sb.add(sb28); sb.add(sb29); sb.add(sb30);
		sb.add(sb31); sb.add(sb32); sb.add(sb33); sb.add(sb34); sb.add(sb35); sb.add(sb36); sb.add(sb37); sb.add(sb38); sb.add(sb39); sb.add(sb40);
		sb.add(sb41); sb.add(sb42); sb.add(sb43); sb.add(sb44); sb.add(sb45); sb.add(sb46); sb.add(sb47); sb.add(sb48); sb.add(sb49); sb.add(sb50);
		sb.add(sb51); sb.add(sb52); sb.add(sb53); sb.add(sb54); sb.add(sb55); sb.add(sb56); sb.add(sb57); sb.add(sb58); sb.add(sb59); sb.add(sb60);
		sb.add(sb61); sb.add(sb62); sb.add(sb63); sb.add(sb64); sb.add(sb65); sb.add(sb66); sb.add(sb67); sb.add(sb68); sb.add(sb69); sb.add(sb71);
		sb.add(sb71); sb.add(sb72); sb.add(sb73); sb.add(sb74); sb.add(sb75); sb.add(sb76);
		
		//chat表格
		ChatBean chb1 = new ChatBean(null, ts, "你好~", true);
		chb1.setMb1(mb1);chb1.setMb2(mb3);
		ChatBean chb2 = new ChatBean(null, ts, "Hello! Nice to meet u.", true);
		chb2.setMb1(mb3);chb2.setMb2(mb1);
		ChatBean chb3 = new ChatBean(null, ts, "哇！你英文好好喔，你學多久了啊", true);
		chb3.setMb1(mb1);chb3.setMb2(mb3);
		ChatBean chb4 = new ChatBean(null, ts, "Almost 3 years, but I\'m still working on it.", true);
		chb4.setMb1(mb3);chb4.setMb2(mb1);
		ChatBean chb5 = new ChatBean(null, ts, "我都看不懂你寫的欸，可以教教我嗎?", true);
		chb5.setMb1(mb1);chb5.setMb2(mb3);
		ChatBean chb6 = new ChatBean(null, ts, "Use this app more and sure you will improve very fast", true);
		chb6.setMb1(mb3);chb6.setMb2(mb1);
		Set<ChatBean>chb  = new LinkedHashSet<>();
		chb.add(chb1);chb.add(chb2);chb.add(chb3);chb.add(chb4);chb.add(chb5);chb.add(chb6);
		//record 資料
		RecordBean rb1= new RecordBean(null, "/data/record/1.wav", ts);
		rb1.setPb(pb1);rb1.setMb(mb1);rb1.setSb(sb1);
		
		RecordBean rb2= new RecordBean(null, "/data/record/2.wav", ts);
		rb2.setPb(pb1);rb2.setMb(mb2);rb2.setSb(sb2);
		
		RecordBean rb3= new RecordBean(null, "/data/record/3.wav", ts);
		rb3.setPb(pb1);rb3.setMb(mb1);rb3.setSb(sb3);
		
		RecordBean rb4= new RecordBean(null, "/data/record/4.wav", ts);
		rb4.setPb(pb1);rb4.setMb(mb2);rb4.setSb(sb4);
		
		RecordBean rb5= new RecordBean(null, "/data/record/5.wav", ts);
		rb5.setPb(pb1);rb5.setMb(mb2);rb5.setSb(sb5);
		
		RecordBean rb6= new RecordBean(null, "/data/record/6.wav", ts);
		rb6.setPb(pb1);rb6.setMb(mb1);rb6.setSb(sb6);
		
		RecordBean rb7= new RecordBean(null, "/data/record/7.m4a", ts);
		rb7.setPb(pb2);rb7.setMb(mb4);rb7.setSb(sb7);
		
		RecordBean rb8= new RecordBean(null, "/data/record/8.m4a", ts);
		rb8.setPb(pb2);rb8.setMb(mb4);rb8.setSb(sb8);
		
		RecordBean rb9= new RecordBean(null, "/data/record/9.m4a", ts);
		rb9.setPb(pb2);rb9.setMb(mb4);rb9.setSb(sb9);
		
		RecordBean rb10= new RecordBean(null, "/data/record/10.m4a", ts);
		rb10.setPb(pb2);rb10.setMb(mb4);rb10.setSb(sb10);

		RecordBean rb11= new RecordBean(null, "/data/record/11.m4a", ts);
		rb11.setPb(pb2);rb11.setMb(mb4);rb11.setSb(sb11);
		
		RecordBean rb12= new RecordBean(null, "/data/record/12.m4a", ts);
		rb12.setPb(pb2);rb12.setMb(mb4);rb12.setSb(sb12);
		
		RecordBean rb13= new RecordBean(null, "/data/record/13.m4a", ts);
		rb13.setPb(pb2);rb13.setMb(mb4);rb13.setSb(sb13);
		
		RecordBean rb14= new RecordBean(null, "/data/record/14.m4a", ts);
		rb14.setPb(pb2);rb14.setMb(mb4);rb14.setSb(sb14);
		
		RecordBean rb15= new RecordBean(null, "/data/record/15.m4a", ts);
		rb15.setPb(pb2);rb15.setMb(mb4);rb15.setSb(sb15);
		
//		RecordBean rb16= new RecordBean(null, null, ts);
//		rb16.setPb(pb3);rb16.setMb(null);rb16.setSb(sb16);
		
		RecordBean rb17= new RecordBean(null, "/data/record/17.m4a", ts);
		rb17.setPb(pb3);rb17.setMb(mb3);rb17.setSb(sb17);
		
		RecordBean rb18= new RecordBean(null, "/data/record/18.m4a", ts);
		rb18.setPb(pb3);rb18.setMb(mb3);rb18.setSb(sb18);
		
//		RecordBean rb19= new RecordBean(null, null, ts);
//		rb19.setPb(pb3);rb19.setMb(null);rb19.setSb(sb19);
		
		RecordBean rb20= new RecordBean(null, "/data/record/20.m4a", ts);
		rb20.setPb(pb3);rb20.setMb(mb3);rb20.setSb(sb20);
		
//		RecordBean rb21= new RecordBean(null,null, ts);
//		rb21.setPb(pb3);rb21.setMb(null);rb21.setSb(sb21);
//		
//		RecordBean rb22= new RecordBean(null, null, ts);
//		rb22.setPb(pb3);rb22.setMb(null);rb22.setSb(sb22);
		
		RecordBean rb23= new RecordBean(null, "/data/record/4_59.wav", ts);
		rb23.setPb(pb4);rb23.setMb(mb5);rb23.setSb(sb59);
//		RecordBean rb24= new RecordBean(null, null, ts);
//		rb24.setPb(pb4);rb24.setMb(null);rb24.setSb(sb60);
//		RecordBean rb25= new RecordBean(null, null, ts);
//		rb25.setPb(pb4);rb25.setMb(null);rb25.setSb(sb61);
		RecordBean rb26= new RecordBean(null, "/data/record/4_62.wav", ts);
		rb26.setPb(pb4);rb26.setMb(mb5);rb26.setSb(sb62);
//		RecordBean rb27= new RecordBean(null, null, ts);
//		rb27.setPb(pb4);rb27.setMb(null);rb27.setSb(sb63);
		RecordBean rb28= new RecordBean(null, "/data/record/4_64.wav", ts);
		rb28.setPb(pb4);rb28.setMb(mb5);rb28.setSb(sb64);
		RecordBean rb29= new RecordBean(null, "/data/record/4_65.wav", ts);
		rb29.setPb(pb4);rb29.setMb(mb5);rb29.setSb(sb65);
		
		
		RecordBean rb30= new RecordBean(null, "/data/record/5_23.wav", ts);
		rb30.setPb(pb5);rb30.setMb(mb2);rb30.setSb(sb23);
		RecordBean rb31= new RecordBean(null, "/data/record/5_24.wav", ts);
		rb31.setPb(pb5);rb31.setMb(mb1);rb31.setSb(sb24);
		RecordBean rb32= new RecordBean(null, "/data/record/5_25.wav", ts);
		rb32.setPb(pb5);rb32.setMb(mb1);rb32.setSb(sb25);
		RecordBean rb33= new RecordBean(null, "/data/record/5_26.wav", ts);
		rb33.setPb(pb5);rb33.setMb(mb2);rb33.setSb(sb26);
		RecordBean rb34= new RecordBean(null, "/data/record/5_27.wav", ts);
		rb34.setPb(pb5);rb34.setMb(mb1);rb34.setSb(sb27);
		RecordBean rb35= new RecordBean(null, "/data/record/5_28.wav", ts);
		rb35.setPb(pb5);rb35.setMb(mb2);rb35.setSb(sb28);
		RecordBean rb36= new RecordBean(null, "/data/record/5_29.wav", ts);
		rb36.setPb(pb5);rb36.setMb(mb1);rb36.setSb(sb29);
		RecordBean rb37= new RecordBean(null, "/data/record/5_30.wav", ts);
		rb37.setPb(pb5);rb37.setMb(mb2);rb37.setSb(sb30);
		RecordBean rb38= new RecordBean(null, "/data/record/5_31.wav", ts);
		rb38.setPb(pb5);rb38.setMb(mb1);rb38.setSb(sb31);
		RecordBean rb39= new RecordBean(null, "/data/record/5_32.wav", ts);
		rb39.setPb(pb5);rb39.setMb(mb2);rb39.setSb(sb32);
		RecordBean rb40= new RecordBean(null, "/data/record/5_33.wav", ts);
		rb40.setPb(pb5);rb40.setMb(mb2);rb40.setSb(sb33);
		
		RecordBean rb41= new RecordBean(null, "/data/record/6_50.wav", ts);
		rb41.setPb(pb6);rb41.setMb(mb6);rb41.setSb(sb50);
		RecordBean rb42= new RecordBean(null, "/data/record/6_51.wav", ts);
		rb42.setPb(pb6);rb42.setMb(mb6);rb42.setSb(sb51);
		RecordBean rb43= new RecordBean(null, "/data/record/6_52.wav", ts);
		rb43.setPb(pb6);rb43.setMb(mb6);rb43.setSb(sb52);
		RecordBean rb44= new RecordBean(null, "/data/record/6_53.wav", ts);
		rb44.setPb(pb6);rb44.setMb(mb6);rb44.setSb(sb53);
		RecordBean rb45= new RecordBean(null, "/data/record/6_54.wav", ts);
		rb45.setPb(pb6);rb45.setMb(mb6);rb45.setSb(sb54);
		RecordBean rb46= new RecordBean(null, "/data/record/6_55.wav", ts);
		rb46.setPb(pb6);rb46.setMb(mb6);rb46.setSb(sb55);
		RecordBean rb47= new RecordBean(null, "/data/record/6_56.wav", ts);
		rb47.setPb(pb6);rb47.setMb(mb6);rb47.setSb(sb56);
		RecordBean rb48= new RecordBean(null, "/data/record/6_57.wav", ts);
		rb48.setPb(pb6);rb48.setMb(mb6);rb48.setSb(sb57);
		RecordBean rb49= new RecordBean(null, "/data/record/6_58.wav", ts);
		rb49.setPb(pb6);rb49.setMb(mb6);rb49.setSb(sb58);
		
		RecordBean rb50= new RecordBean(null, "/data/record/7_44.wav", ts);
		rb50.setPb(pb7);rb50.setMb(mb7);rb50.setSb(sb44);
//		RecordBean rb51= new RecordBean(null, null, ts);
//		rb51.setPb(pb7);rb51.setMb(null);rb51.setSb(sb45);
		RecordBean rb52= new RecordBean(null, "/data/record/7_46.wav", ts);
		rb52.setPb(pb7);rb52.setMb(mb7);rb52.setSb(sb46);
//		RecordBean rb53= new RecordBean(null, null, ts);
//		rb53.setPb(pb7);rb53.setMb(null);rb53.setSb(sb47);
//		RecordBean rb54= new RecordBean(null, null, ts);
//		rb54.setPb(pb7);rb54.setMb(null);rb54.setSb(sb48);
		RecordBean rb55= new RecordBean(null, "/data/record/7_49.wav", ts);
		rb55.setPb(pb7);rb55.setMb(mb7);rb55.setSb(sb49);
		
//		RecordBean rb56= new RecordBean(null, null, ts);
//		rb56.setPb(pb8);rb56.setMb(null);rb56.setSb(sb34);
//		RecordBean rb57= new RecordBean(null, null, ts);
//		rb57.setPb(pb8);rb57.setMb(null);rb57.setSb(sb35);
//		RecordBean rb58= new RecordBean(null, null, ts);
//		rb58.setPb(pb8);rb58.setMb(null);rb58.setSb(sb36);
//		RecordBean rb59= new RecordBean(null, null, ts);
//		rb59.setPb(pb8);rb59.setMb(null);rb59.setSb(sb37);
		RecordBean rb60= new RecordBean(null, "/data/record/8_38.wav", ts);
		rb60.setPb(pb8);rb60.setMb(mb8);rb60.setSb(sb38);
//		RecordBean rb61= new RecordBean(null, null, ts);
//		rb61.setPb(pb8);rb61.setMb(null);rb61.setSb(sb39);
		RecordBean rb62= new RecordBean(null, "/data/record/8_40.wav", ts);
		rb62.setPb(pb8);rb62.setMb(mb8);rb62.setSb(sb40);
//		RecordBean rb63= new RecordBean(null, null, ts);
//		rb63.setPb(pb8);rb63.setMb(null);rb63.setSb(sb41);
		RecordBean rb64= new RecordBean(null, "/data/record/8_42.wav", ts);
		rb64.setPb(pb8);rb64.setMb(mb8);rb64.setSb(sb42);
//		RecordBean rb65= new RecordBean(null, null, ts);
//		rb65.setPb(pb8);rb65.setMb(null);rb65.setSb(sb43);

		
		Set<RecordBean> rb  =new LinkedHashSet<>();
		rb.add(rb1);rb.add(rb2);rb.add(rb3);rb.add(rb4);rb.add(rb5);rb.add(rb6);rb.add(rb7);rb.add(rb8);rb.add(rb9);rb.add(rb10);
		rb.add(rb11);rb.add(rb12);rb.add(rb13);rb.add(rb14);rb.add(rb15);rb.add(rb17);rb.add(rb18);rb.add(rb20);
		rb.add(rb23);rb.add(rb26);rb.add(rb28);rb.add(rb29);rb.add(rb30);
		rb.add(rb31);rb.add(rb32);rb.add(rb33);rb.add(rb34);rb.add(rb35);rb.add(rb36);rb.add(rb37);rb.add(rb38);rb.add(rb39);rb.add(rb40);
		rb.add(rb41);rb.add(rb42);rb.add(rb43);rb.add(rb44);rb.add(rb45);rb.add(rb46);rb.add(rb47);rb.add(rb48);rb.add(rb49);rb.add(rb50);
		rb.add(rb52);rb.add(rb55);rb.add(rb60);
		rb.add(rb62);rb.add(rb64);

		//friend表格(memberNo2跟3~6互相是朋友)
		FriendBean fb1 = new FriendBean(null,mb1,mb2,ts,0);
		FriendBean fb2 = new FriendBean(null,mb2,mb1,ts,0);
		FriendBean fb3 = new FriendBean(null,mb1,mb3,ts,0);
		FriendBean fb4 = new FriendBean(null,mb3,mb1,ts,0);
		FriendBean fb5 = new FriendBean(null,mb1,mb4,ts,0);
		FriendBean fb6 = new FriendBean(null,mb4,mb1,ts,0);
		FriendBean fb7 = new FriendBean(null,mb1,mb5,ts,0);
		FriendBean fb8 = new FriendBean(null,mb5,mb1,ts,0);
		Set<FriendBean> fb  = new LinkedHashSet<>(
				Arrays.asList(fb1,fb2,fb3,fb4,fb5,fb6,fb7,fb8));

		//friendRequest表格(memberNo2發送邀請給7~9, memberNo2收到10的邀請)
		FriendRequestBean frb1 = new FriendRequestBean(null,mb1,mb6,ts);
		FriendRequestBean frb2 = new FriendRequestBean(null,mb1,mb7,ts);
		FriendRequestBean frb3 = new FriendRequestBean(null,mb1,mb8,ts);
		FriendRequestBean frb4 = new FriendRequestBean(null,mb10,mb1,ts);

		Set<FriendRequestBean> frb  = new LinkedHashSet<>(
				Arrays.asList(frb1,frb2,frb3,frb4));
		
		CommentBean cmb1 = new CommentBean(null,mb3,pb1,"Good Job!", ts, "223.245.0.1");
		CommentBean cmb2 = new CommentBean(null,mb4,pb1,"我覺得不行", ts, "115.135.0.2");
		CommentBean cmb3 = new CommentBean(null,mb5,pb1,"可以教我英文嗎?", ts, "212.134.22.3");
		
		CommentBean cmb4 = new CommentBean(null,mb3,pb2,"這樣行嗎?", ts, "125.255.0.1");
		CommentBean cmb5 = new CommentBean(null,mb4,pb2,"悄悄話", ts, "125.235.30.2");
		CommentBean cmb6 = new CommentBean(null,mb5,pb2,"Nice~", ts, "111.215.0.3");
		
		CommentBean cmb7 = new CommentBean(null,mb3,pb3,"Cool!", ts, "125.255.0.1");
		CommentBean cmb8 = new CommentBean(null,mb4,pb3,"我覺得還行", ts, "125.235.30.2");
		CommentBean cmb9 = new CommentBean(null,mb5,pb3,"不賴喔~", ts, "111.215.0.3");
		
		CommentBean cmb10 = new CommentBean(null,mb1,pb3,"hello", ts, "111.215.0.3");
		
		CommentBean cmb11 = new CommentBean(null,mb1,pb5,"大家快來看，這是我姊姊配音的喔~", ts, "125.255.0.1");
		CommentBean cmb12 = new CommentBean(null,mb2,pb5,"小丑是我弟的聲音就是了", ts, "125.235.30.2");
		CommentBean cmb13 = new CommentBean(null,mb4,pb5,"喵喵喵喵喵~~~~~", ts, "111.215.0.3");
		
		Set<CommentBean> cmbl  = new LinkedHashSet<>(
				Arrays.asList(cmb1,cmb2,cmb3,cmb4,cmb5,cmb6,cmb7,cmb8,cmb9,cmb10,cmb11,cmb12,cmb13));
		
		String path = 
				"src/main/webapp/WEB-INF/applicationContext.xml";
		ApplicationContext ctx = new FileSystemXmlApplicationContext(path);
		SessionFactory factory = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = factory.openSession();
		Transaction tx= null;
		try {
			tx = session.beginTransaction();
			for(MemberBean memberBean :mb) {
				session.save(memberBean);
				}
			for(ClipBean clipBean :cb) {
				session.save(clipBean);
			}
			for(PostBean postDetailBean :pb) {
				session.save(postDetailBean);
			}
			for(ScriptBean scroptBean :sb) {
				session.save(scroptBean);
			}
			for(ChatBean chatBean:chb) {
				session.save(chatBean);
			}
			for(RecordBean recordBean:rb) {
				session.save(recordBean);
			}
			for(FriendBean firendBean:fb) {
				session.save(firendBean);
			}
			for(FriendRequestBean firendRequestBean:frb) {
				session.save(firendRequestBean);
			}
			for(CommentBean commentBean:cmbl) {
				session.save(commentBean);
			}
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}
}
