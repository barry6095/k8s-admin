package cn.com.agree.aweb.property;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhongyi@agree.com.cn
 */
@ConfigurationProperties("cn.com.agree.spring.data.extend")
@Data
@Accessors(chain = true)
public class AgreeSpringDataExtendProperties {

    private List<String> suffix = Arrays.asList("sftl");
    private List<String> basePath = Arrays.asList("classpath*:query-templates/**");
    private boolean enableNativeQuery = false;
}
