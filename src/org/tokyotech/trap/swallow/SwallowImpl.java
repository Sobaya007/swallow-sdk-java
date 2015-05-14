package org.tokyotech.trap.swallow;

public class SwallowImpl implements Swallow {
	private String sessionID;
	private SwallowSecurity security;

	public SwallowImpl(String sessionID, SwallowSecurity security) {
		this.sessionID = sessionID;
		this.security = security;
	}

	@Override
	public User[] findUser(Integer startIndex, Integer endIndex, Long fromTime,
			Long toTime, Integer[] userIDs, String userNamePattern,
			String profilePattern, Boolean hasImage) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User modifyUser(String userName, String profile,
			Integer imageFileID, String password, String email, String web,
			String twitter, String gcm, String apns, Integer[] observeTagIDs,
			Boolean observeMention) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Message[] findMessage(Integer startIndex, Integer endIndex,
			Long fromTime, Long toTime, Integer[] postIDs,
			Integer[] postedUserIDs, Integer[] tagIDs, Integer[] replyPostIDs,
			Integer[] destUserIDs, String messagePattern,
			Boolean hasAttachment, Boolean isEnquete, Boolean convertToKana) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Message createMessage(String message, String[] attributes,
			Integer[] fileIDs, Integer[] replyPostIDs, Integer[] tagIDs,
			Integer[] destUserIDs, String[] enquetes, Integer overwritePostID) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public File[] findFile(Integer startIndex, Integer endIndex, Long fromTime,
			Long toTime, Integer[] fileIDs, Integer[] tagIDs,
			String fileNamePattern, String fileTypePattern) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public byte[] getFile(Integer fileID) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public byte[] getThumbnail(Integer fileID, Integer width, Integer height) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public File createFile(String fileName, String fileType, Integer[] tagIDs,
			Integer[] folderContent, Integer overwriteFileID, byte[] fileData) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Tag[] findTag(Integer startIndex, Integer endIndex, Long fromTime,
			Long toTime, Integer[] tagIDs, Integer minPostNum,
			Integer maxPostNum, String tagNamePattern) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Tag createTag(String tagName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Favorite createFavorite(Integer postID, Integer favNum) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Answer createAnswer(Integer postID, Integer answerIndex) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Received createReceived(Integer postID) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
