package cn.com.agree.aweb.entity.enums.constant;

public enum RoleContant {

    //管理员id
    ADMIN_ID("1", "rol-admin");

    public String code;
    private String name;

    private RoleContant(String code, String name) {
        this.code = code;
        this.name = name;
    }
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
}
