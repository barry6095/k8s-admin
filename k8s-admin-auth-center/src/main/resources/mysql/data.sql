truncate table aweb_menu_auth;
truncate table aweb_role_menu;
truncate table aweb_user_role;
truncate table aweb_user_group;
truncate table aweb_role_system;
truncate table aweb_role;
truncate table aweb_menu;
truncate table aweb_user;
truncate table aweb_authority;
truncate table aweb_group;

INSERT INTO aweb_user(ID, NAME,NICKNAME, PASSWORD, EMAIL, STATUS) VALUES('usr-admin', 'admin','管理员', '{bcrypt}$2a$10$f8tXbjOcseYj4/qcx3bxheQIwp30GkbcxzHySMnni.eNLF.NRETO6', 'test@test.com', '1');
INSERT INTO aweb_user(ID, NAME,NICKNAME, PASSWORD, EMAIL, STATUS) VALUES('usr-dev', 'dev','开发用户', '{bcrypt}$2a$10$f8tXbjOcseYj4/qcx3bxheQIwp30GkbcxzHySMnni.eNLF.NRETO6', 'test@test.com', '1');
INSERT INTO aweb_user(ID, NAME,NICKNAME, PASSWORD, EMAIL, STATUS) VALUES('usr-ops', 'ops','运维用户', '{bcrypt}$2a$10$f8tXbjOcseYj4/qcx3bxheQIwp30GkbcxzHySMnni.eNLF.NRETO6', 'test@test.com', '1');
INSERT INTO aweb_user(ID, NAME,NICKNAME, PASSWORD, EMAIL, STATUS) VALUES('usr-test', 'test','测试用户', '{bcrypt}$2a$10$f8tXbjOcseYj4/qcx3bxheQIwp30GkbcxzHySMnni.eNLF.NRETO6', 'test@test.com', '1');
INSERT INTO aweb_user(ID, NAME,NICKNAME, PASSWORD, EMAIL, STATUS) VALUES('usr-default', 'default','默认用户', '{bcrypt}$2a$10$f8tXbjOcseYj4/qcx3bxheQIwp30GkbcxzHySMnni.eNLF.NRETO6', 'test@test.com', '1');

INSERT INTO aweb_role(ID, NAME) VALUES('rol-dev', 'ROLE_DEV');
INSERT INTO aweb_role(ID, NAME) VALUES('rol-ops', 'ROLE_OPS');
INSERT INTO aweb_role(ID, NAME) VALUES('rol-test', 'ROLE_TEST');
INSERT INTO aweb_role(ID, NAME) VALUES('rol-admin', 'ROLE_ADMIN');
INSERT INTO aweb_role(ID, NAME) VALUES('rol-default', 'ROLE_DEFAULT');

INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-workbench-manage', 'Workbench','','工作台','','100',null);
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-view-manage', 'view','pages/overview/Overview','总览','icon icon-view','110','menu-workbench-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-myTask-manage', 'myTask','','我的任务','icon icon-task','120','menu-workbench-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-workbench-taskView-manage', 'taskView','','任务总览','icon icon-star','121','menu-myTask-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-myTaskView-manage', 'myTaskView','pages/task/MyTask','我的任务','icon icon-star','122','menu-myTask-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-service-myService-manage', 'myService','service','我的服务','icon icon-server','130','menu-workbench-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-service-draft-manage', 'serviceDraft','pages/service/ServiceDraft','服务草稿','icon icon-star','131','menu-service-myService-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-service-publish-manage', 'publishedService','pages/service/ServicePublished','已发布服务','icon icon-star','132','menu-service-myService-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-script-myScript-manage', 'myScript','script','我的脚本','icon icon-shell','140','menu-workbench-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-script-draft-manage', 'scriptDraft','pages/script/ScriptDraft','脚本草稿','icon icon-star','141','menu-script-myScript-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-script-publish-manage', 'publishedScript','pages/script/ScriptPublished','已发布脚本','icon icon-star','142','menu-script-myScript-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-myApprovalProcess-manage', 'myApprovalProcess','','我的审批','icon icon-approval','150','menu-workbench-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-waitApprovalProcess-manage', 'waitApprovalProcess','pages/approval/waitApproval','待审批','icon icon-star','151','menu-myApprovalProcess-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-finish-manage', 'finish','pages/approval/finishedApproval','已完成','icon icon-star','152','menu-myApprovalProcess-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-launchApprovalProcess-manage', 'launchApprovalProcess','','我发起的审批','icon icon-to-approval','160','menu-workbench-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-inApprovalProcess-manage', 'inApprovalProcess','pages/launchApproval/inApproval','审批中','icon icon-star','161','menu-launchApprovalProcess-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-approvalProcessRecord-manage', 'approvalProcessRecord','pages/launchApproval/approvalRecord','审批记录','icon icon-star','162','menu-launchApprovalProcess-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-resourcesApproval-manage', 'resourcesApproval','pages/launchApproval/resourcePublish','资源发版','icon icon-publish','170','menu-workbench-manage');

INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-taskCenter-manage', 'taskCenter','','任务中心','','200',null);
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-taskView-manage', 'taskView-o','','任务总览','icon icon-config-map','210','menu-taskCenter-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-taskCenter-o-manage', 'taskCenter-o','pages/task/MyTask','任务中心','icon icon-config-map','220','menu-taskCenter-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-taskExecutionLog-manage', 'taskExecutionLog','pages/task/TaskExecutionLog','任务执行记录','icon icon-config-map','230','menu-taskCenter-manage');

INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-resourceCenter-manage', 'resourceCenter','','资源中心','','300',null);
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-scriptCenter-manage', 'scriptCenter','pages/script/ScriptPublished','脚本中心','icon icon-config-map','310','menu-resourceCenter-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-serviceCenter-manage', 'serviceCenter','pages/service/ServicePublished','服务中心','icon icon-config-map','320','menu-resourceCenter-manage');

INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-parameterCenter-manage', 'parameterCenter','','参数中心','','500',null);
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-server-manage', 'server','pages/serverManage/ServerManage','服务器管理','icon icon-config-map','510','menu-parameterCenter-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-dataDictionary-manage', 'dataDictionary','pages/dataDictionaryManage/DataDictionaryManage','数据字典管理','icon icon-config-map','520','menu-parameterCenter-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-variable-manage', 'variable','pages/variableManage/VariableManage','通用变量管理','icon icon-config-map','530','menu-parameterCenter-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-system-manage', 'system','pages/system/System','系统管理','icon icon-config-map','540','menu-parameterCenter-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-environment-manage', 'environment','pages/enviromentManage/EnviromentManage','环境管理','icon icon-config-map','550','menu-parameterCenter-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-authority-manage', 'authority','auth','用户权限管理','icon icon-operation','560','menu-parameterCenter-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-user-manage', 'user','pages/authority/User','用户管理','icon icon-user','561','menu-authority-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-role-manage', 'role','pages/authority/Role','角色管理','icon icon-role','562','menu-authority-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-group-manage', 'group','pages/authority/Group','组织管理','icon icon-config-map','563','menu-authority-manage');
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-script-param-manage', 'scriptParam','pages/script/ScriptParam','脚本参数管理','icon icon-star','570','menu-parameterCenter-manage');

INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-resourcesMarket-manage', 'resourcesMarket','','资源市场','','600',null);
INSERT INTO aweb_menu(ID, NAME, PATH, TITLE, ICON, SEQ,PARENT_ID) VALUES('menu-resourcesMarket-o-manage', 'resourcesMarket-o','','资源市场','','610','resourcesMarket');

INSERT INTO aweb_authority(ID, NAME) VALUES('auth-user-manage-read', 'USER_MANAGE_READ');
INSERT INTO aweb_authority(ID, NAME) VALUES('auth-user-manage-write', 'USER_MANAGE_WRITE');
INSERT INTO aweb_authority(ID, NAME) VALUES('auth-task-manage-read', 'TASK_MANAGE_READ');
INSERT INTO aweb_authority(ID, NAME) VALUES('auth-task-manage-write', 'TASK_MANAGE_WRITE');

INSERT INTO aweb_user_role(USER_ID, ROLE_ID) VALUES('usr-admin', 'rol-admin');
INSERT INTO aweb_user_role(USER_ID, ROLE_ID) VALUES('usr-dev', 'rol-dev');
INSERT INTO aweb_user_role(USER_ID, ROLE_ID) VALUES('usr-ops', 'rol-ops');
INSERT INTO aweb_user_role(USER_ID, ROLE_ID) VALUES('usr-test', 'rol-test');
INSERT INTO aweb_user_role(USER_ID, ROLE_ID) VALUES('usr-default', 'rol-default');

INSERT INTO aweb_role_menu VALUES ('rol-test', 'menu-authority-manage');
INSERT INTO aweb_role_menu VALUES ('rol-test', 'menu-user-manage');
INSERT INTO aweb_role_menu VALUES ('rol-test', 'menu-role-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-parameterCenter-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-authority-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-role-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-user-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-dataDictionary-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-environment-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-server-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-system-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-group-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-variable-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-resourcesMarket-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-resourceCenter-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-scriptCenter-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-serviceCenter-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-taskCenter-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-taskCenter-o-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-taskExecutionLog-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-taskView-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-workbench-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-launchApprovalProcess-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-approvalProcessRecord-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-inApprovalProcess-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-resourcesApproval-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-myApprovalProcess-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-finish-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-waitApprovalProcess-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-myTask-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-myTaskView-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-workbench-taskView-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-script-myScript-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-script-draft-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-script-publish-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-service-myService-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-service-draft-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-service-publish-manage');
INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-view-manage');

INSERT INTO aweb_role_menu(MENU_ID, ROLE_ID) VALUES('menu-authority-manage', 'rol-test');
INSERT INTO aweb_role_menu(MENU_ID, ROLE_ID) VALUES('menu-user-manage', 'rol-test');
INSERT INTO aweb_role_menu(MENU_ID, ROLE_ID) VALUES('menu-role-manage', 'rol-test');

INSERT INTO aweb_role_menu VALUES ('rol-admin', 'menu-script-param-manage');

INSERT INTO aweb_menu_auth(AUTH_ID, MENU_ID) VALUES('auth-user-manage-read', 'menu-user-manage');
INSERT INTO aweb_menu_auth(AUTH_ID, MENU_ID) VALUES('auth-user-manage-write', 'menu-user-manage');
INSERT INTO aweb_menu_auth(AUTH_ID, MENU_ID) VALUES('auth-task-manage-read', 'menu-task-manage');
INSERT INTO aweb_menu_auth(AUTH_ID, MENU_ID) VALUES('auth-task-manage-write', 'menu-task-manage');

INSERT INTO aweb_group(ID, NAME,DESCRIPTION) VALUES('aweb-group-k8s-admin-dev', 'AOPS开发组','AOPS开发组');
INSERT INTO aweb_group(ID, NAME,DESCRIPTION) VALUES('aweb-group-k8s-admin-test', 'AOPS测试组','AOPS测试组');
INSERT INTO aweb_group(ID, NAME,DESCRIPTION) VALUES('aweb-group-k8s-admin-ops', 'AOPS运维组','AOPS运维组');
INSERT INTO aweb_group(ID, NAME,DESCRIPTION) VALUES('aweb-group-default', '默认小组','默认小组');

INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-default', 'usr-admin');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-k8s-admin-dev', 'usr-admin');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-k8s-admin-ops', 'usr-admin');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-k8s-admin-test', 'usr-admin');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-default', 'usr-dev');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-default', 'usr-ops');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-default', 'usr-test');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-default', 'usr-default');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-k8s-admin-dev', 'usr-dev');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-k8s-admin-ops', 'usr-ops');
INSERT INTO aweb_user_group(GROUP_ID,USER_ID) VALUES ('aweb-group-k8s-admin-test', 'usr-test');

INSERT INTO aweb_role_system(ID,SYSTEM_ID,ROLE_ID) VALUES ('role-system-1','sys-k8s-admin','rol-admin');
INSERT INTO aweb_role_system(ID,SYSTEM_ID,ROLE_ID) VALUES ('role-system-2','sys-ctl-test','rol-admin');
INSERT INTO aweb_role_system(ID,SYSTEM_ID,ROLE_ID) VALUES ('role-system-3','sys-ctl-3','rol-admin');
INSERT INTO aweb_role_system(ID,SYSTEM_ID,ROLE_ID) VALUES ('role-system-4','sys-ctl-4','rol-admin');
INSERT INTO aweb_role_system(ID,SYSTEM_ID,ROLE_ID) VALUES ('role-system-5','sys-ctl-5','rol-admin');
INSERT INTO aweb_role_system(ID,SYSTEM_ID,ROLE_ID) VALUES ('role-system-6','sys-ctl-6','rol-admin');
INSERT INTO aweb_role_system(ID,SYSTEM_ID,ROLE_ID) VALUES ('role-system-7','sys-k8s-admin','rol-test');
INSERT INTO aweb_role_system(ID,SYSTEM_ID,ROLE_ID) VALUES ('role-system-8','sys-ctl-test','rol-test');


insert  into aweb_user(id,create_time,create_user_id,create_user_name,name,update_time,update_user_id,update_user_name,email,nickname,password,phone,status) values ('usr-yesijun','1561104545063','usr-admin','admin','ysj','1561104545063','usr-admin','admin','yesijun@agree.com.cn','叶思君','{bcrypt}$2a$10$dL9lca64axlvwNfj9ly4ze9OedRlLQO5m/jxK0MvKR5wkfuTKhKVu','','1');
insert  into aweb_user_group(group_id,user_id) values ('aweb-group-default','usr-yesijun');
insert  into aweb_user_group(group_id,user_id) values ('aweb-group-k8s-admin-dev','usr-yesijun');
insert  into aweb_user_role(user_id,role_id) values ('usr-yesijun','rol-admin');
insert  into aweb_user_role(user_id,role_id) values ('usr-yesijun','rol-dev');
