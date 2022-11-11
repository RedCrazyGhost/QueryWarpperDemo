DROP TABLE IF EXISTS tags;
create table tags
(
    id           tinyint unsigned auto_increment comment '标签ID'
        primary key,
    name         varchar(30)                            not null comment '标签名',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '标签创建时间',
    note_id_list varchar(255) default '[]'              not null comment '文章ID列表',
    is_delete    tinyint      default 0                 not null comment '逻辑删除'
)
    comment '文章标签';

