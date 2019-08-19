package cn.com.agree.aweb.entity.enums.status;

import java.util.Optional;
import java.util.stream.Stream;

public enum UserStatus {

	NEW("0", "新用户"), AVAILABLE("1", "可用"), LOCKED("2", "锁定");
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private UserStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public static Optional<UserStatus> fromCode(String code) {
		return Stream.of(UserStatus.values()).filter(item -> item.getCode().equals(code)).findFirst();
	}
}
