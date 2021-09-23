#-----------------------创建表-----------------------
create table if not exists logi_dept
(
    id          int auto_increment
    primary key,
    dept_name   varchar(10) not null comment '部门名',
    parent_id   int         not null comment '父部门id',
    leaf        tinyint(1)  not null comment '是否叶子部门',
    level       tinyint     not null comment 'parentId为0的层级为1',
    description varchar(20) null comment '描述',
    app_name    varchar(16) null comment '应用名称'
    )
    comment '部门信息表';

create table if not exists logi_message
(
    id          int auto_increment
    primary key,
    title       varchar(60)                          not null comment '标题',
    content     varchar(256)                         null comment '内容',
    read_tag    tinyint(1) default 0                 null comment '是否已读',
    oplog_id    int                                  null comment '操作日志id',
    user_id     int                                  null comment '这条消息属于哪个用户的，用户id',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint(1) default 0                 null comment '逻辑删除',
    app_name    varchar(16)                          null comment '应用名称'
    )
    comment '消息中心';

create table if not exists logi_oplog
(
    id                int auto_increment
    primary key,
    operator_ip       varchar(20)                          not null comment '操作者ip',
    operator_username varchar(20)                          null comment '操作者账号',
    operate_page      varchar(16)                          not null comment '操作页面',
    operate_type      varchar(16)                          not null comment '操作类型',
    target_type       varchar(16)                          not null comment '对象分类',
    target            varchar(20)                          not null comment '操作对象',
    detail            text                                 null comment '日志详情',
    create_time       timestamp  default CURRENT_TIMESTAMP null,
    update_time       timestamp  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete         tinyint(1) default 0                 not null comment '逻辑删除',
    app_name          varchar(16)                          null comment '应用名称'
    )
    comment '操作日志';

create table if not exists logi_oplog_extra
(
    id       int auto_increment
    primary key,
    info     varchar(16) null comment '信息',
    type     tinyint     not null comment '哪种信息：
1：操作页面
2：操作类型
3：对象分类',
    app_name varchar(16) null comment '应用名称'
    )
    comment '操作日志信息（操作页面、操作类型、对象分类）';

create table if not exists logi_permission
(
    id              int auto_increment
    primary key,
    permission_name varchar(40) not null comment '权限名字',
    parent_id       int         not null comment '父权限id',
    leaf            tinyint(1)  not null comment '是否叶子权限点（具体的操作）',
    level           tinyint     not null comment '权限点的层级（parentId为0的层级为1）',
    description     varchar(64) null comment '权限点描述',
    app_name        varchar(16) null comment '应用名称'
    )
    comment '权限表';

create table if not exists logi_project
(
    id           int auto_increment comment '项目id'
    primary key,
    project_code varchar(128)                           not null comment '项目编号',
    project_name varchar(128)                           not null comment '项目名',
    description  varchar(512) default ''                not null comment '项目描述',
    dept_id      int                                    not null comment '部门id',
    running      tinyint(1)   default 1                 not null comment '启用 or 停用',
    is_delete    tinyint(1)   default 0                 not null comment '逻辑删除',
    create_time  timestamp    default CURRENT_TIMESTAMP null comment '创建时间',
    update_time  timestamp    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    app_name     varchar(16)                            null comment '应用名称'
    );

create table if not exists logi_resource_type
(
    id        int auto_increment
    primary key,
    type_name varchar(16) null comment '资源类型名',
    app_name  varchar(16) null comment '应用名称'
    )
    comment '资源类型表';

create table if not exists logi_role
(
    id           int auto_increment
    primary key,
    role_code    varchar(128)                         not null comment '角色编号',
    role_name    varchar(128)                         not null comment '名称',
    description  varchar(128)                         null comment '角色描述',
    last_reviser varchar(30)                          null comment '最后修改人',
    is_delete    tinyint(1) default 0                 not null comment '逻辑删除',
    create_time  timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time  timestamp  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    app_name     varchar(16)                          null comment '应用名称',
    constraint logi_role_role_name_uindex
    unique (role_name)
    )
    comment '角色信息';

create table if not exists logi_role_permission
(
    role_id       int         not null comment '角色id',
    permission_id int         not null comment '权限id',
    app_name      varchar(16) null comment '应用名称'
    )
    comment '角色权限表（只保留叶子权限与角色关系）';

create table if not exists logi_user
(
    id          int auto_increment
    primary key,
    username    varchar(64)                            not null comment '用户账号',
    password    char(32)                               not null comment '用户密码',
    salt        char(5)                                not null comment '密码盐',
    real_name   varchar(128) default ''                not null comment '真实姓名',
    phone       char(11)     default ''                not null comment 'mobile',
    email       varchar(30)  default ''                not null comment 'email',
    dept_id     int                                    null comment '所属部门id',
    is_delete   tinyint(1)   default 0                 not null comment '逻辑删除',
    create_time timestamp    default CURRENT_TIMESTAMP null comment '注册时间',
    update_time timestamp    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    app_name    varchar(16)                            null comment '应用名称',
    constraint logi_user_username_uindex
    unique (username)
    )
    comment '用户信息';

create table if not exists logi_user_project
(
    user_id    int         not null comment '用户id',
    project_id int         not null comment '项目id',
    app_name   varchar(16) null comment '应用名称'
    )
    comment '用户项目关系表（项目负责人）';

create table if not exists logi_user_resource
(
    user_id          int         not null comment '用户id',
    project_id       int         not null comment '资源所属项目id',
    resource_type_id int         not null comment '资源类别id',
    resource_id      int         not null comment '资源id',
    control_level    tinyint     not null comment '管理级别：
1（查看权限）
2（管理权限）',
    app_name         varchar(16) null comment '应用名称'
    )
    comment '用户和资源关系表';

create table if not exists logi_user_role
(
    user_id  int         not null comment '用户id',
    role_id  int         not null comment '角色id',
    app_name varchar(16) null comment '应用名称'
    )
    comment '用户角色表';