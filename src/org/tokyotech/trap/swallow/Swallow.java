package org.tokyotech.trap.swallow;

import java.io.InputStream;

/*
 * リクエストメソッドの引数や、レスポンスクラスのフィールドはすべてAPIアクションのparametersおよびresultsに対応しています。
 * 下記のページ(APIアクション一覧)を参考にするとわかりやすい……かもしれません。
 * https://gist.github.com/kazsw/5b425e2b1c62b32bdcc0
 */
public interface Swallow {
	/*
	 * ユーザを探す
	 */
	public User[] findUser(Integer startIndex, Integer endIndex, Long fromTime,
			Long toTime, Integer[] userIDs, String userNamePattern,
			String profilePattern, Boolean hasImage);

	/*
	 * ユーザ情報を編集
	 */
	public User modifyUser(String userName, String profile,
			Integer imageFileID, String password, String email, String web,
			String twitter, String gcm, String apns, Integer[] observeTagIDs,
			Boolean observeMention);

	/*
	 * 投稿を取得
	 */
	public Message[] findMessage(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] postIDs,
			Integer[] postedUserIDs, Integer[] tagIDs, Integer[] replyPostIDs,
			Integer[] destUserIDs, String messagePattern,
			Boolean hasAttachment, Boolean isEnquete, Boolean convertToKana);

	/*
	 * 投稿する
	 */
	public Message createMessage(String message, String[] attributes,
			Integer[] fileIDs, Integer[] replyPostIDs, Integer[] tagIDs,
			Integer[] destUserIDs, String[] enquetes, Integer overwritePostID);

	/*
	 * ファイルを探す
	 */
	public File[] findFile(Integer startIndex, Integer endIndex, Long fromTime,
			Long toTime, Integer[] fileIDs, Integer[] tagIDs,
			String fileNamePattern, String fileTypePattern);

	/*
	 * ファイルを取得
	 */
	public InputStream getFile(Integer fileID);

	/*
	 * サムネイルを取得
	 */
	public InputStream getThumbnail(Integer fileID, Integer width,
			Integer height);

	/*
	 * ファイルを投稿
	 */
	public File createFile(String fileName, String fileType, Integer[] tagIDs,
			Integer[] folderContent, Integer overwriteFileID,
			InputStream fileData);

	/*
	 * タグを探す
	 */
	public Tag[] findTag(Integer startIndex, Integer endIndex, Long fromTime,
			Long toTime, Integer[] tagIDs, Integer minPostNum,
			Integer maxPostNum, String tagNamePattern);

	/*
	 * タグを作成
	 */
	public Tag createTag(String tagName);

	/*
	 * Favを探す
	 */
	public Favorite[] findFavorite(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer minFavNum, Integer maxFavNum,
			Integer[] userIDs, Integer[] postIDs);

	/*
	 * ふぁぼる
	 */
	public Favorite createFavorite(Integer postID, Integer favNum);

	/*
	 * アンケート回答を探す
	 */
	public Answer[] findAnswer(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] userIDs, Integer[] postIDs);

	/*
	 * アンケートに回答する
	 */
	public Answer createAnswer(Integer postID, String answer);

	/*
	 * 既読情報確認
	 */
	public Received[] findReceived(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] userIDs, Integer[] postIDs);

	/*
	 * 既読をつける
	 */
	public Received createReceived(Integer postID);

	/*
	 * レスポンス: ユーザ情報
	 */
	class User {
		private Integer UserID;
		private Long Joined;
		private String UserName;
		private String Profile;
		private Integer Image;

		public User(Integer userID, Long joined, String userName,
				String profile, Integer image) {
			UserID = userID;
			Joined = joined;
			UserName = userName;
			Profile = profile;
			Image = image;
		}

		public Integer getUserID() {
			return UserID;
		}

		public Long getJoined() {
			return Joined;
		}

		public String getUserName() {
			return UserName;
		}

		public String getProfile() {
			return Profile;
		}

		public Integer getImage() {
			return Image;
		}
	}

	/*
	 * レスポンス: ユーザ詳細情報
	 */
	class UserDetail extends User {
		private String Email;
		private String Web;
		private String Twitter;
		private String GCM;
		private String APNs;
		private Integer[] ObserveTag;
		private Boolean ObserveMention;

		public UserDetail(Integer userID, Long joined, String userName,
				String profile, Integer image, String email, String web,
				String twitter, String gcm, String apns, Integer[] observeTag,
				Boolean observeMention) {
			super(userID, joined, userName, profile, image);
			Email = email;
			Web = web;
			Twitter = twitter;
			GCM = gcm;
			APNs = apns;
			ObserveTag = observeTag;
			ObserveMention = observeMention;
		}

		public String getEmail() {
			return Email;
		}

		public String getWeb() {
			return Web;
		}

		public String getTwitter() {
			return Twitter;
		}

		public String getGCM() {
			return GCM;
		}

		public String getAPNs() {
			return APNs;
		}

		public Integer[] getObserveTag() {
			return ObserveTag;
		}

		public Boolean getObserveMention() {
			return ObserveMention;
		}
	}

	/*
	 * レスポンス: 投稿
	 */
	class Message {
		private Integer PostID;
		private Long Posted;
		private Integer UserID;
		private String Message;
		private Integer FavNum;
		private String[] Attribute;
		private Integer[] FileID;
		private Integer[] TagID;
		private Integer[] Reply;
		private Integer[] Dest;
		private String[] Enquete;

		public Message(Integer postID, Long posted, Integer userID,
				String message, Integer favNum, String[] attribute,
				Integer[] fileID, Integer[] tagID, Integer[] reply,
				Integer[] dest, String[] enquete) {
			PostID = postID;
			Posted = posted;
			UserID = userID;
			Message = message;
			FavNum = favNum;
			Attribute = attribute;
			FileID = fileID;
			TagID = tagID;
			Reply = reply;
			Dest = dest;
			Enquete = enquete;
		}

		public Integer getPostID() {
			return PostID;
		}

		public Long getPosted() {
			return Posted;
		}

		public Integer getUserID() {
			return UserID;
		}

		public String getMessage() {
			return Message;
		}

		public Integer getFavNum() {
			return FavNum;
		}

		public String[] getAttribute() {
			return Attribute;
		}

		public Integer[] getFileID() {
			return FileID;
		}

		public Integer[] getTagID() {
			return TagID;
		}

		public Integer[] getReply() {
			return Reply;
		}

		public Integer[] getDest() {
			return Dest;
		}

		public String[] getEnquete() {
			return Enquete;
		}
	}

	/*
	 * レスポンス: ファイル
	 */
	class File {
		private Integer FileID;
		private Long Created;
		private String FileName;
		private String FileType;
		private Integer[] TagID;
		private Integer[] FolderContent;

		public File(Integer fileID, Long created, String fileName,
				String fileType, Integer[] tagID, Integer[] folderContent) {
			FileID = fileID;
			Created = created;
			FileName = fileName;
			FileType = fileType;
			TagID = tagID;
			FolderContent = folderContent;
		}

		public Integer getFileID() {
			return FileID;
		}

		public Long getCreated() {
			return Created;
		}

		public String getFileName() {
			return FileName;
		}

		public String getFileType() {
			return FileType;
		}

		public Integer[] getTagID() {
			return TagID;
		}

		public Integer[] getFolderContent() {
			return FolderContent;
		}
	}

	/*
	 * レスポンス: タグ
	 */
	class Tag {
		private Integer TagID;
		private Long Updated;
		private String TagName;
		private Integer PostNum;

		public Tag(Integer tagID, Long updated, String tagName, Integer postNum) {
			TagID = tagID;
			Updated = updated;
			TagName = tagName;
			PostNum = postNum;
		}

		public Integer getTagID() {
			return TagID;
		}

		public Long getUpdated() {
			return Updated;
		}

		public String getTagName() {
			return TagName;
		}

		public Integer getPostNum() {
			return PostNum;
		}
	}

	/*
	 * レスポンス: お気に入り
	 */
	class Favorite {
		private Integer UserID;
		private Integer PostID;
		private Long Updated;
		private Integer FavNum;

		public Favorite(Integer userID, Integer postID, Long updated,
				Integer favNum) {
			UserID = userID;
			PostID = postID;
			Updated = updated;
			FavNum = favNum;
		}

		public Integer getUserID() {
			return UserID;
		}

		public Integer getPostID() {
			return PostID;
		}

		public Long getUpdated() {
			return Updated;
		}

		public Integer getFavNum() {
			return FavNum;
		}
	}

	/*
	 * レスポンス: アンケート回答
	 */
	class Answer {
		private Integer UserID;
		private Integer PostID;
		private Long Updated;
		private String Answer;

		public Answer(Integer userID, Integer postID, Long updated,
				String answer) {
			UserID = userID;
			PostID = postID;
			Updated = updated;
			Answer = answer;
		}

		public Integer getUserID() {
			return UserID;
		}

		public Integer getPostID() {
			return PostID;
		}

		public Long getUpdated() {
			return Updated;
		}

		public String getAnswer() {
			return Answer;
		}
	}

	/*
	 * レスポンス: 既読
	 */
	class Received {
		private Integer UserID;
		private Integer PostID;
		private Long Updated;

		public Received(Integer userID, Integer postID, Long updated) {
			UserID = userID;
			PostID = postID;
			Updated = updated;
		}

		public Integer getUserID() {
			return UserID;
		}

		public Integer getPostID() {
			return PostID;
		}

		public Long getUpdated() {
			return Updated;
		}
	}
}