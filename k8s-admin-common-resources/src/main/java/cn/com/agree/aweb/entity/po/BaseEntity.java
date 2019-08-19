package cn.com.agree.aweb.entity.po;

import cn.com.agree.aweb.jpa.listener.EntityListener;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhongyi@agree.com.cn
 */
@Data
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(EntityListener.class)
public class BaseEntity implements Serializable {
	
    private static final long serialVersionUID = -1986607651687311115L;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CREATE_TIME", updatable = false)
    private String createTime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "CREATE_USER_NAME")
    private String createUserName;
    @Column(name = "UPDATE_TIME")
    private String updateTime;
    @Column(name = "UPDATE_USER_ID")
    private String updateUserId;
    @Column(name = "UPDATE_USER_NAME")
    private String updateUserName;
}
