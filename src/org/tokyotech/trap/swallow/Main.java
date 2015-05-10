package org.tokyotech.trap.swallow;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) throws Exception {
		SwallowSecurity sec = new SwallowSecurity();
		sec.login("test", "test");
		
		/*
		 * セッション情報を出力
		 */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(sec);
		byte[] session = baos.toByteArray();
		// ↑ コレを保存しておけばいい
		
		/*
		 * セッション情報を復元
		 */
		ByteArrayInputStream bais = new ByteArrayInputStream(session);
		ObjectInputStream ois = new ObjectInputStream(bais);
		sec = (SwallowSecurity) ois.readObject();
		
		/*
		 * Swallowインスタンスを取得
		 */
		Swallow swallow = sec.getSwallow();
		
		/*
		 * ユーザ情報編集と一覧取得
		 */
		swallow.modifyUser("watashi", null, null, null, null, null, null, null, null, null, null);
		for(Swallow.User u : swallow.findUser(0, 50, null, null, null, null, null, null)){
			System.out.println(u.getUserName());
		}
		
		System.out.println("\n-----\n");
		
		/*
		 * 投稿と一覧取得
		 */
		swallow.createMessage("てすと", null, null, null, null, null, null, null);
		for(Swallow.Message m : swallow.findMessage(0, 50, null, null, null, null, null, null, null, null, null, null, false)){
			System.out.println(m.getMessage());
		}
		
		System.out.println("\n-----\n");
		
		/*
		 * ファイル投稿と一覧取得
		 */
		swallow.createFile("test.txt", "text/plain", null, null, null, new ByteArrayInputStream("abcde".getBytes()));
		for(Swallow.File f : swallow.findFile(0, 50, null, null, null, null, null, null)){
			System.out.println(f.getFileName());
		}
		
		System.out.println("\n-----\n");
		
		/*
		 * ファイル取得・サムネイル取得
		 */
		System.out.println(swallow.getFile(1));
		System.out.println(swallow.getThumbnail(2, 32, 32));
		
		System.out.println("\n-----\n");
		
		/*
		 * タグ追加と一覧取得
		 */
		swallow.createTag("myTag");
		for(Swallow.Tag t : swallow.findTag(0, 50, null, null, null, null, null, null)){
			System.out.println(t.getTagName());
		}
		
		System.out.println("\n-----\n");
		
		/*
		 * ふぁぼと一覧取得
		 */
		swallow.createFavorite(1, 100);
		for(Swallow.Favorite f : swallow.findFavorite(0, 50, null, null, null, null, null, null)){
			System.out.println(f.getFavNum());
		}
		
		System.out.println("\n-----\n");
		
		/*
		 * アンケート回答と一覧取得
		 */
		swallow.createAnswer(16, "選択肢A");
		for(Swallow.Answer a : swallow.findAnswer(0, 50, null, null, null, null)){
			System.out.println(a.getAnswer());
		}
		
		System.out.println("\n-----\n");
		
		/*
		 * 既読追加と一覧取得
		 */
		swallow.createReceived(6);
		for(Swallow.Received r : swallow.findReceived(0, 50, null, null, null, null)){
			System.out.println(r.getUpdated());
		}
	}

}
