package cn.com.agree.aweb.entity.enums.type;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhongyi@agree.com.cn
 */

@AllArgsConstructor
public enum RestErrorMessageType {
    //后台处理异常
    COMMON_ERROR("0_00_1000", Exception.class, Collections.emptyList()),
    //输入参数异常
    INVALID_PARAMS("0_00_0000", IllegalArgumentException.class, Collections.singletonList("请检查输入参数"));

	@Getter@Setter
    private String code;
	@Getter@Setter
	private Class<? extends Throwable> cause;
	@Getter@Setter
	private List<String> tips;
	
	
	public static Optional<RestErrorMessageType> getType(Class<? extends Throwable> clazz) {
		return Stream.of(RestErrorMessageType.values()).filter(item -> item.getCause().getName().equals(clazz.getName())).findFirst();
	}
}