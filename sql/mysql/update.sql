# 工具箱菜单目录
INSERT INTO system_menu (id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted) VALUES (2167, '工具箱', '', 1, 5, 0, '/toolbox', 'tool', null, null, 0, true, true, true, '1', '2023-02-17 12:38:18', '1', '2023-02-17 15:15:36', false);


# 工具箱-任务清单
create table toolbox_checklist
(
    id          bigint auto_increment comment '编号'
        primary key,
    name        varchar(100)                          not null comment '名称',
    checklist_code   varchar(100)                          not null comment '编号',
    checklist_index  int                                   not null comment '顺序',
    checklist_type   tinyint     default 1                 not null comment '类型',
    checklist_cron   varchar(30)                           not null comment 'cron 表达式',
    status      tinyint                               not null comment '状态',
    remark      varchar(500)                          null comment '备注',
    creator     varchar(64) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除',
    tenant_id   bigint      default 0                 not null comment '租户编号'
)
    comment '工具箱-任务清单表' collate = utf8mb4_general_ci;

-- 菜单 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '任务清单', '', 2, 0, 2167,
    'checklist', 'list', 'toolbox/checklist/index', 0
);

-- 按钮父菜单ID
-- 暂时只支持 MySQL。如果你是 Oracle、PostgreSQL、SQLServer 的话，需要手动修改 @parentId 的部分的代码
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '任务清单查询', 'toolbox:checklist:query', 3, 1, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '任务清单创建', 'toolbox:checklist:create', 3, 2, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '任务清单更新', 'toolbox:checklist:update', 3, 3, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '任务清单删除', 'toolbox:checklist:delete', 3, 4, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '任务清单导出', 'toolbox:checklist:export', 3, 5, @parentId,
    '', '', '', 0
);
# 租户参数配置
create table system_tenant_config
(
    id          int auto_increment comment '参数主键'   primary key,
    category    varchar(50)                            not null comment '参数分组',
    type        tinyint                                not null comment '参数类型',
    name        varchar(100) default ''                not null comment '参数名称',
    config_key  varchar(100) default ''                not null comment '参数键名',
    value       varchar(500) default ''                not null comment '参数键值',
    visible     bit                                    not null comment '是否可见',
    remark      varchar(500)                           null comment '备注',
    creator     varchar(64)  default ''                null comment '创建者',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64)  default ''                null comment '更新者',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit          default b'0'              not null comment '是否删除',
    tenant_id   bigint       default 0                 not null comment '租户编号'
)
    comment '租户参数配置表';

-- 菜单 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '租户参数', '', 2, 0, 1224,
    'tenant-config', 'config', 'system/tenantConfig/index', 0
);

-- 按钮父菜单ID
-- 暂时只支持 MySQL。如果你是 Oracle、PostgreSQL、SQLServer 的话，需要手动修改 @parentId 的部分的代码
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '租户参数配置查询', 'system:tenant-config:query', 3, 1, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '租户参数配置创建', 'system:tenant-config:create', 3, 2, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '租户参数配置更新', 'system:tenant-config:update', 3, 3, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '租户参数配置删除', 'system:tenant-config:delete', 3, 4, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '租户参数配置导出', 'system:tenant-config:export', 3, 5, @parentId,
    '', '', '', 0
);
