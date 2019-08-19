package cn.com.agree.aweb.entity.vo;

import cn.com.agree.aweb.entity.enums.type.RestErrorMessageType;
import java.util.Collection;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zhongyi@agree.com.cn
 */
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RestResultMessage<T> {

	private T content;
	private boolean success;
	private String errorCode;
	private String errorMessage;
    private Collection<String> errorTips;

	public RestResultMessage(Supplier<? extends Throwable> supplier) {
		this.setContent(null);
		this.setSuccess(false);
		RestErrorMessageType type = RestErrorMessageType
        .getType(supplier.get().getClass()).orElse(RestErrorMessageType.COMMON_ERROR);
		this.setErrorCode(type.getCode());
		this.setErrorMessage(supplier.get().getLocalizedMessage());
		this.setErrorTips(type.getTips());
	}

	public RestResultMessage(T t) {
		this.setContent(t);
		this.setSuccess(true);
	}

}
