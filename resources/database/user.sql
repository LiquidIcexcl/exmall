-- t_user
create table t_user_0
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_1
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_2
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_3
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_4
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_5
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_6
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_7
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_8
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_9
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_10
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_11
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_12
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_13
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_14
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

create table t_user_15
(
    uid           bigint auto_increment comment '用户ID'
        primary key,
    username      varchar(256) null comment '用户名称',
    password      varchar(512) null comment '用户密码',
    phone         varchar(128) null comment '用户手机号',
    email         varchar(512) null comment '用户邮箱',
    avatar_url    varchar(512) null comment '头像图片地址',
    isMerchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
    shop_id       bigint       null,
    deletion_time bigint       null comment '注销时间戳',
    create_time   datetime     null comment '创建时间',
    update_time   datetime     null comment '修改时间',
    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

-- t_user_configuration
create table t_user_configuration
(
    uid      bigint     not null comment '用户ID',
    is_night tinyint(1) null comment '用户是否使用深色模式'
);

create index idx_uid
    on t_user_configuration (uid);

-- t_user_wallet
CREATE TABLE t_user_wallet (
    uid BIGINT NOT NULL COMMENT '用户ID',
    balance DOUBLE NOT NULL DEFAULT 0 COMMENT '钱包余额',
    total_income DOUBLE NOT NULL DEFAULT 0 COMMENT '钱包总收入',
    total_expenditure DOUBLE NOT NULL DEFAULT 0 COMMENT '钱包总支出',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    del_flag TINYINT NOT NULL DEFAULT 0 COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户钱包表';
