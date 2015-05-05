package org.tokyotech.trap.swallow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.util.Base64;

/*
 * テスト用実装
 * find***系の検索機能は使えません！
 */
@SuppressWarnings("unused")
public class SwallowTest implements Swallow {
	/*
	 * ダミーデータ　ここから
	 */
	private long dummyTime = System.currentTimeMillis() - 12 * 60 * 60 * 1000;
	private long getDummyTime() {
		return dummyTime += 123 * 1000;
	};
	
	private ArrayList<Swallow.User> dummyUser = new ArrayList<Swallow.User>(Arrays.asList(new Swallow.User[]{
		new Swallow.User(1, getDummyTime(), "trap", "画像がある人", 1),
		new Swallow.User(2, getDummyTime(), "titech", "ない人", null),
		new Swallow.User(3, getDummyTime(), "n", "test", 2),
		new Swallow.User(4, getDummyTime(), "longnamelongname", "名前最大長(16文字)", 3),
		new Swallow.User(5, getDummyTime(), "me", "テストユーザ", null),
	}));
	private ArrayList<Swallow.Message> dummyMessage = new ArrayList<Swallow.Message>(Arrays.asList(new Swallow.Message[]{
		new Swallow.Message(1, getDummyTime(), 1, "普通の投稿", 0, null, null, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(2, getDummyTime(), 2, "改行\nが\n入った\n投稿", 0, null, null, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(3, getDummyTime(), 3, "ふぁぼられた投稿", 10, null, null, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(4, getDummyTime(), 4, "編集された投稿", 0, new String[]{"edited"}, null, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(5, getDummyTime(), 1, null, 0, new String[]{"deleted"}, null, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(6, getDummyTime(), 2, "既読確認付き投稿", 0, new String[]{"check"}, null, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(7, getDummyTime(), 3, "属性が複数ついた投稿", 0, new String[]{"edited", "check"}, null, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(8, getDummyTime(), 4, "クライアントが定義した属性付き投稿", 0, new String[]{"sample-attr"}, null, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(9, getDummyTime(), 1, "ファイル付き投稿", 0, null, new Integer[]{ 4 }, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(10, getDummyTime(), 2, "ファイル複数付き投稿", 0, null, new Integer[]{ 5, 6 }, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(11, getDummyTime(), 3, "フォルダ付き投稿", 0, null, new Integer[]{ 7 }, new Integer[]{ 1 }, null, null, null),
		new Swallow.Message(12, getDummyTime(), 4, "タグ複数付き投稿", 0, null, null, new Integer[]{ 1, 2 }, null, null, null),
		new Swallow.Message(13, getDummyTime(), 1, "タグなし投稿", 0, null, null, null, null, null, null),
		new Swallow.Message(14, getDummyTime(), 2, "ある投稿への返信", 0, null, null, new Integer[]{ 1 }, new Integer[]{ 1 }, null, null),
		new Swallow.Message(15, getDummyTime(), 3, "ユーザ宛投稿", 0, null, null, new Integer[]{ 1 }, null, new Integer[]{ 1 }, null),
		new Swallow.Message(16, getDummyTime(), 4, "アンケート付き投稿", 0, null, null, new Integer[]{ 1 }, null, null, new String[]{"選択肢A", "選択肢B"})
	}));
	private ArrayList<Swallow.File> dummyFile = new ArrayList<Swallow.File>(Arrays.asList(new Swallow.File[]{
		new Swallow.File(1, getDummyTime(), "profile1.jpg", "image/jpeg", null, null),
		new Swallow.File(2, getDummyTime(), "profile2.jpg", "image/jpeg", null, null),
		new Swallow.File(3, getDummyTime(), "aaaa.jpg", "image/jpeg", null, null),
		new Swallow.File(4, getDummyTime(), "test.pdf", "application/pdf", new Integer[]{ 1 }, null),
		new Swallow.File(5, getDummyTime(), "sample.zip", "application/zip", new Integer[]{ 1 }, null),
		new Swallow.File(6, getDummyTime(), "テキスト.txt", "text/plain", new Integer[]{ 1 }, null),
		new Swallow.File(7, getDummyTime(), "サンプルフォルダ", "swallow/folder", new Integer[]{ 1 }, new Integer[]{ 1, 2, 3 })
	}));
	private ArrayList<Swallow.Tag> dummyTag = new ArrayList<Swallow.Tag>(Arrays.asList(new Swallow.Tag[]{
			new Swallow.Tag(1, getDummyTime(), "サンプル", 16),
			new Swallow.Tag(2, getDummyTime(), "tesuto", 1),
			new Swallow.Tag(3, getDummyTime(), "あああ", 0)
	}));
	private ArrayList<Swallow.Favorite> dummyFavorite = new ArrayList<Swallow.Favorite>(Arrays.asList(new Swallow.Favorite[]{
		new Swallow.Favorite(1, 3, getDummyTime(), 1),
		new Swallow.Favorite(2, 3, getDummyTime(), 2),
		new Swallow.Favorite(3, 3, getDummyTime(), 3),
		new Swallow.Favorite(4, 3, getDummyTime(), 4)
	}));
	private ArrayList<Swallow.Answer> dummyAnswer = new ArrayList<Swallow.Answer>(Arrays.asList(new Swallow.Answer[]{
		new Swallow.Answer(1, 16, getDummyTime(), "選択肢A"),
		new Swallow.Answer(2, 16, getDummyTime(), null), //まだ回答していない！
		new Swallow.Answer(3, 16, getDummyTime(), "選択肢A"),
		new Swallow.Answer(4, 16, getDummyTime(), "選択肢B"),
		new Swallow.Answer(5, 16, getDummyTime(), null) //まだ回答していない！
	}));
	private ArrayList<Swallow.Received> dummyReceived = new ArrayList<Swallow.Received>(Arrays.asList(new Swallow.Received[]{
		new Swallow.Received(1, 6, getDummyTime()),
		new Swallow.Received(2, 6, getDummyTime()),
		new Swallow.Received(3, 6, getDummyTime()),
		new Swallow.Received(4, 6, null), //まだ読んでない！
		new Swallow.Received(5, 6, null), //まだ読んでない！
	}));
	
	private HashMap<Integer, InputStream> fileEntity = new HashMap<Integer, InputStream>();
	public SwallowTest() {
		fileEntity.put(1, new ByteArrayInputStream(Base64.decode("/9j/4AAQSkZJRgABAQEASABIAAD//gATQ3JlYXRlZCB3aXRoIEdJTVD/2wBDAP//////////////////////////////////////////////////////////////////////////////////////2wBDAf//////////////////////////////////////////////////////////////////////////////////////wgARCACAAIADAREAAhEBAxEB/8QAFQABAQAAAAAAAAAAAAAAAAAAAAH/xAAWAQEBAQAAAAAAAAAAAAAAAAAAAQL/2gAMAwEAAhADEAAAAZNAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD/xAAUEAEAAAAAAAAAAAAAAAAAAACA/9oACAEBAAEFAgB//8QAFBEBAAAAAAAAAAAAAAAAAAAAgP/aAAgBAwEBPwEAf//EABQRAQAAAAAAAAAAAAAAAAAAAID/2gAIAQIBAT8BAH//xAAUEAEAAAAAAAAAAAAAAAAAAACA/9oACAEBAAY/AgB//8QAFBABAAAAAAAAAAAAAAAAAAAAgP/aAAgBAQABPyEAf//aAAwDAQACAAMAAAAQSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS/8QAFBEBAAAAAAAAAAAAAAAAAAAAgP/aAAgBAwEBPxAAf//EABQRAQAAAAAAAAAAAAAAAAAAAID/2gAIAQIBAT8QAH//xAAUEAEAAAAAAAAAAAAAAAAAAACA/9oACAEBAAE/EAB//9k=", Base64.DEFAULT)));
		fileEntity.put(2, new ByteArrayInputStream(Base64.decode("/9j/4AAQSkZJRgABAQEASABIAAD//gATQ3JlYXRlZCB3aXRoIEdJTVD/2wBDAP//////////////////////////////////////////////////////////////////////////////////////2wBDAf//////////////////////////////////////////////////////////////////////////////////////wgARCACAAIADAREAAhEBAxEB/8QAFAABAAAAAAAAAAAAAAAAAAAAAP/EABUBAQEAAAAAAAAAAAAAAAAAAAAC/9oADAMBAAIQAxAAAAFMgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD/8QAFBABAAAAAAAAAAAAAAAAAAAAgP/aAAgBAQABBQIAf//EABQRAQAAAAAAAAAAAAAAAAAAAID/2gAIAQMBAT8BAH//xAAUEQEAAAAAAAAAAAAAAAAAAACA/9oACAECAQE/AQB//8QAFBABAAAAAAAAAAAAAAAAAAAAgP/aAAgBAQAGPwIAf//EABQQAQAAAAAAAAAAAAAAAAAAAID/2gAIAQEAAT8hAH//2gAMAwEAAgADAAAAEP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP8A/wD/AP/EABQRAQAAAAAAAAAAAAAAAAAAAID/2gAIAQMBAT8QAH//xAAUEQEAAAAAAAAAAAAAAAAAAACA/9oACAECAQE/EAB//8QAFBABAAAAAAAAAAAAAAAAAAAAgP/aAAgBAQABPxAAf//Z", Base64.DEFAULT)));
		fileEntity.put(3, new ByteArrayInputStream(Base64.decode("/9j/4AAQSkZJRgABAQEASABIAAD//gATQ3JlYXRlZCB3aXRoIEdJTVD/2wBDAP//////////////////////////////////////////////////////////////////////////////////////2wBDAf//////////////////////////////////////////////////////////////////////////////////////wgARCACAAIADAREAAhEBAxEB/8QAFQABAQAAAAAAAAAAAAAAAAAAAAL/xAAWAQEBAQAAAAAAAAAAAAAAAAAAAQL/2gAMAwEAAhADEAAAAZ1AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB//8QAFBABAAAAAAAAAAAAAAAAAAAAgP/aAAgBAQABBQIAf//EABQRAQAAAAAAAAAAAAAAAAAAAID/2gAIAQMBAT8BAH//xAAUEQEAAAAAAAAAAAAAAAAAAACA/9oACAECAQE/AQB//8QAFBABAAAAAAAAAAAAAAAAAAAAgP/aAAgBAQAGPwIAf//EABQQAQAAAAAAAAAAAAAAAAAAAID/2gAIAQEAAT8hAH//2gAMAwEAAgADAAAAELbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbf/EABQRAQAAAAAAAAAAAAAAAAAAAID/2gAIAQMBAT8QAH//xAAUEQEAAAAAAAAAAAAAAAAAAACA/9oACAECAQE/EAB//8QAFBABAAAAAAAAAAAAAAAAAAAAgP/aAAgBAQABPxAAf//Z", Base64.DEFAULT)));
		fileEntity.put(4, new ByteArrayInputStream(Base64.decode("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MCBvYmoKPDwvRjEgOSAwIFIKPj4KZW5kb2JqCgoxMSAwIG9iago8PC9Gb250IDEwIDAgUgovUHJvY1NldFsvUERGL1RleHRdCj4+CmVuZG9iagoKMSAwIG9iago8PC9UeXBlL1BhZ2UvUGFyZW50IDQgMCBSL1Jlc291cmNlcyAxMSAwIFIvTWVkaWFCb3hbMCAwIDU5NSA4NDJdL0dyb3VwPDwvUy9UcmFuc3BhcmVuY3kvQ1MvRGV2aWNlUkdCL0kgdHJ1ZT4+L0NvbnRlbnRzIDIgMCBSPj4KZW5kb2JqCgo0IDAgb2JqCjw8L1R5cGUvUGFnZXMKL1Jlc291cmNlcyAxMSAwIFIKL01lZGlhQm94WyAwIDAgNTk1IDg0MiBdCi9LaWRzWyAxIDAgUiBdCi9Db3VudCAxPj4KZW5kb2JqCgoxMiAwIG9iago8PC9UeXBlL0NhdGFsb2cvUGFnZXMgNCAwIFIKL09wZW5BY3Rpb25bMSAwIFIgL1hZWiBudWxsIG51bGwgMF0KL0xhbmcoamEtSlApCj4+CmVuZG9iagoKMTMgMCBvYmoKPDwvQ3JlYXRvcjxGRUZGMDA1NzAwNzIwMDY5MDA3NDAwNjUwMDcyPgovUHJvZHVjZXI8RkVGRjAwNEMwMDY5MDA2MjAwNzIwMDY1MDA0RjAwNjYwMDY2MDA2OTAwNjMwMDY1MDAyMDAwMzQwMDJFMDAzND4KL0NyZWF0aW9uRGF0ZShEOjIwMTUwNTAzMTcyNDE2KzA5JzAwJyk+PgplbmRvYmoKCnhyZWYKMCAxNAowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDU0ODQgMDAwMDAgbiAKMDAwMDAwMDAxOSAwMDAwMCBuIAowMDAwMDAwMTg5IDAwMDAwIG4gCjAwMDAwMDU2MjcgMDAwMDAgbiAKMDAwMDAwMDIwOCAwMDAwMCBuIAowMDAwMDA0NzA2IDAwMDAwIG4gCjAwMDAwMDQ3MjcgMDAwMDAgbiAKMDAwMDAwNDkyMiAwMDAwMCBuIAowMDAwMDA1MjI2IDAwMDAwIG4gCjAwMDAwMDUzOTcgMDAwMDAgbiAKMDAwMDAwNTQyOSAwMDAwMCBuIAowMDAwMDA1NzI2IDAwMDAwIG4gCjAwMDAwMDU4MjMgMDAwMDAgbiAKdHJhaWxlcgo8PC9TaXplIDE0L1Jvb3QgMTIgMCBSCi9JbmZvIDEzIDAgUgovSUQgWyA8QTNBNDIyMjA5OTA1NURGQzlDNEZCRUM2REI3OTM0M0Y+CjxBM0E0MjIyMDk5MDU1REZDOUM0RkJFQzZEQjc5MzQzRj4gXQovRG9jQ2hlY2tzdW0gL0NEQjUwQTNFMzc4QkJGRkEzQzE3RDhFQjJCMTNFMUJDCj4+CnN0YXJ0eHJlZgo1OTk4CiUlRU9GCg==", Base64.DEFAULT)));
		fileEntity.put(5, new ByteArrayInputStream(Base64.decode("UEsDBAoAAAAIAMWKo0ZJGQqCtQAAAOQCAAAFAAAAYS5qcGf7f+P/AwYBLzdPNwZGRkYGDyBk+P+PQdi5KDWxJDVFoTyzJEPB3dM34P9tBmeG/5QCoCGMFBtyiEGQg6GBoYGZUZCBSZCRWZDx/xEGUaD7GVABSFiMkRFdnJHp/y0GHmZGBiYBZgEgd7IDA20B0BkiAuiOawA6ggPoNEZWJoZ6kApB7CqYGRntGfGqYEKowGkLmz0TARWM9opAFdCAYWAGygh4qkyiKSLgawGCvhYg6CeQipsAUEsDBAoAAAAIANKKo0a65LrrrwAAAEIDAAAFAAAAYi5qcGfVkD0KwkAQhd/bRUlhsYvax5OkCEQjCl5BMKCtBCzds4mH8KfxJI67IIhBkyJY+Kab+Zh58+QkN5hplmcgiYkvyB39dFssy2IV7zblOh7n84VckULayi9h6yUH2AgOTtNCWWpLOWIAoiLfHYan3qXkgp4mlNEG4Mzhp9LBm6macN5E5K2xo7APhP1MaDJhLaFexNcr3UQ1EExGnngGA+0nRvDnVZ+qaUzVNGYWiPMDUEsDBAoAAAAIANqKo0ZrXHL9tQAAAOUCAAAFAAAAYy5qcGf7f+P/AwYBLzdPNwZGRkYGDyBk+P+PQdi5KDWxJDVFoTyzJEPB3dM34P9tBmeG/5QCoCGMFBtyiEGQg6GBoYGZUZCBSZCRWZDx/xEGUaD7GVABE1BYjJERXZyR6f8tBh5mRgYmAWYBIHeuAwNtQT3QHSIC6K5rALqCA+g2RlYmiApB7CqYGRntGfGqYEKowGkLmz0TIXfYKwJVQEOGgRkoI7Dtdi5NEQFfCxD0tQBBP4FU3AQAUEsBAgoACgAAAAgAxYqjRkkZCoK1AAAA5AIAAAUAAAAAAAAAAAAgAAAAAAAAAGEuanBnUEsBAgoACgAAAAgA0oqjRrrkuuuvAAAAQgMAAAUAAAAAAAAAAAAgAAAA2AAAAGIuanBnUEsBAgoACgAAAAgA2oqjRmtccv21AAAA5QIAAAUAAAAAAAAAAAAgAAAAqgEAAGMuanBnUEsFBgAAAAADAAMAmQAAAIICAAAAAA==", Base64.DEFAULT)));
		fileEntity.put(6, new ByteArrayInputStream(Base64.decode("c2FubnB1cnU=", Base64.DEFAULT)));
		fileEntity.put(7, null);
	}
	/*
	 * ダミーデータ　ここまで
	 */
	
	private String sessionID;
	private SwallowSecurity security;
	
	public SwallowTest(String sessionID, SwallowSecurity security) {
		this.sessionID = sessionID;
		this.security = security;
	}
	
	@Override
	public User[] findUser(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] userIDs, String userNamePattern,
			String profilePattern, Boolean hasImage) {
		return dummyUser.toArray(new User[0]);
	}
	@Override
	public User modifyUser(String userName, String profile,
			Integer imageFileID, String password, String email, String web,
			String twitter, String gcm, String apns, Integer[] observeTagIDs,
			Boolean observeMention) {
		ArrayList<Swallow.User> list = new ArrayList<Swallow.User>();
		
		Swallow.UserDetail det = null;
		for(Swallow.User entry : dummyUser){
			if(entry.getUserID() == 5){
				list.add(new Swallow.User(entry.getUserID(), System.currentTimeMillis(), userName != null ? userName : entry.getUserName(), profile != null ? profile : entry.getProfile(), imageFileID != null ? imageFileID : entry.getImage()));
				det = new Swallow.UserDetail(entry.getUserID(), System.currentTimeMillis(), userName != null ? userName : entry.getUserName(), profile != null ? profile : entry.getProfile(), imageFileID != null ? imageFileID : entry.getImage(), email, web, twitter, gcm, apns, observeTagIDs, observeMention);
			}else{
				list.add(entry);
			}
		}
		
		dummyUser = list;
		return det;
	}
	@Override
	public Message[] findMessage(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] postIDs, Integer[] postedUserIDs,
			Integer[] tagIDs, Integer[] replyPostIDs,
			Integer[] destUserIDs, String messagePattern,
			Boolean hasAttachment, Boolean isEnquete, Boolean convertToKana) {
		return dummyMessage.toArray(new Swallow.Message[0]);
	}
	@Override
	public Message createMessage(String message, String[] attributes,
			Integer[] fileIDs, Integer[] replyPostIDs, Integer[] tagIDs,
			Integer[] destUserIDs, String[] enquetes,
			Integer overwritePostID) {
		Swallow.Message newm = new Swallow.Message(1 + dummyMessage.size(), System.currentTimeMillis(), 5, message, 0, attributes, fileIDs, tagIDs, replyPostIDs, destUserIDs, enquetes);
		dummyMessage.add(newm);
		return newm;
	}
	@Override
	public File[] findFile(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] fileIDs, Integer[] tagIDs,
			String fileNamePattern, String fileTypePattern) {
		return dummyFile.toArray(new Swallow.File[0]);
	}
	@Override
	public InputStream getFile(Integer fileID) {
		return fileEntity.get(fileID);
	}
	@Override
	public File createFile(String fileName, String fileType,
			Integer[] tagIDs, Integer[] folderContent,
			Integer overwriteFileID, InputStream fileData) {
		Swallow.File newf = new Swallow.File(1 + dummyFile.size(), System.currentTimeMillis(), fileName, fileType, tagIDs, folderContent);
		fileEntity.put(1 + dummyFile.size(), fileData);
		dummyFile.add(newf);
		return newf;
	}
	@Override
	public Tag[] findTag(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] tagIDs, Integer minPostNum,
			Integer maxPostNum, String tagNamePattern) {
		return dummyTag.toArray(new Swallow.Tag[0]);
	}
	@Override
	public Tag createTag(String tagName) {
		Swallow.Tag newt = new Swallow.Tag(1 + dummyTag.size(), System.currentTimeMillis(), tagName, 0);
		dummyTag.add(newt);
		return newt;
	}
	@Override
	public Favorite[] findFavorite(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer minFavNum,
			Integer maxFavNum, Integer[] userIDs, Integer[] postIDs) {
		return dummyFavorite.toArray(new Swallow.Favorite[0]);
	}
	@Override
	public Favorite createFavorite(Integer postID, Integer favNum) {
		Swallow.Favorite fav = new Swallow.Favorite(5, postID, System.currentTimeMillis(), favNum);
		dummyFavorite.add(fav);
		return fav;
	}
	@Override
	public Answer[] findAnswer(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] userIDs,
			Integer[] postIDs) {
		return dummyAnswer.toArray(new Swallow.Answer[0]);
	}
	@Override
	public Answer createAnswer(Integer postID, String answer) {
		ArrayList<Swallow.Answer> list = new ArrayList<Swallow.Answer>();
		
		Swallow.Answer newa = null;
		for(Swallow.Answer entry : dummyAnswer){
			if(entry.getUserID() == 5){
				newa = new Swallow.Answer(5, postID, System.currentTimeMillis(), answer);
				list.add(newa);
			}else{
				list.add(entry);
			}
		}
		
		dummyAnswer = list;
		return newa;
	}
	@Override
	public Received[] findReceived(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] userIDs,
			Integer[] postIDs) {
		return dummyReceived.toArray(new Swallow.Received[0]);
	}
	@Override
	public Received createReceived(Integer postID) {
		ArrayList<Swallow.Received> list = new ArrayList<Swallow.Received>();
		
		Swallow.Received newr = null;
		for(Swallow.Received entry : dummyReceived){
			if(entry.getUserID() == 5){
				newr = new Swallow.Received(5, postID, System.currentTimeMillis());
				list.add(newr);
			}else{
				list.add(entry);
			}
		}
		
		dummyReceived = list;
		return newr;
	}
}
