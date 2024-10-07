create sequence tenant_id_seq;
alter sequence tenant_id_seq owner to ehrbase;
grant select on sequence tenant_id_seq to ehrbase_restricted;
create type contribution_data_type as enum ('composition', 'folder', 'ehr', 'system', 'other');
alter type contribution_data_type owner to ehrbase;
create type contribution_change_type as enum ('creation', 'amendment', 'modification', 'synthesis', 'Unknown', 'deleted');

create type ehr_item_tag_target_type as enum ('ehr_status', 'composition');
alter type ehr_item_tag_target_type owner to ehrbase;


alter type "contribution_change_type" owner to "openehr-admin";

create table "plugin"
(
    id          uuid default uuid_generate_v4() not null
                primary key,
    plugin_id   text                            not null,
    key         text                            not null,
    value       text
);

comment on table "plugin" is 'key value store for plugin sub system';
alter table "plugin" owner to "openehr-admin";
grant delete, insert, select, update on "plugin" to "ehr-user";

create table "ehr"
(
    id              uuid not null
                    primary key,
    creation_date   timestamp(6) with time zone
);

alter table "ehr" owner to "openehr-admin";
create index "ehr_time_created_idx" on "ehr" (creation_date desc, id asc);
grant delete, insert, select, update on "ehr" to "openehr-user";

create table "users"
(
    id          uuid not null
                primary key,
    username    text not null
);

alter table "users" owner to "openehr-admin";
create unique index "users_username_idx" on "users" (username);
grant delete, insert, select, update on "users" to "openehr-user";

create table "audit_details"
(
    id              uuid not null
                    primary key,
    change_type     ehr.contribution_change_type not null,
    description     text,
    time_committed  timestamp(6) with time zone not null,
    committer       jsonb,
    user_id         uuid not null
                    references users,
    target_type     varchar not null
);

alter table "audit_details" owner to "openehr-admin";
grant delete, insert, select, update on "audit_details" to "openehr-user";

create table "contribution"
(
    id                  uuid not null
                        primary key,
    ehr_id              uuid
                        references ehr
                        on delete cascade,
    contribution_type   ehr.contribution_data_type,
    signature           text,
    has_audit           uuid
                        references "audit_details"
                        on delete cascade
);

alter table "contribution" owner to "openehr-admin";
create index "contribution_ehr_idx" on "contribution" (ehr_id);
grant delete, insert, select, update on "contribution" to "openehr-user";

create table "stored_query"
(
    reverse_domain_name varchar not null,
    semantic_id         varchar not null,
    semver              varchar default '0.0.0'::character varying not null,
    query_text          varchar not null,
    type                varchar default 'AQL'::character varying,
    creation_date       timestamp(6) with time zone not null,
    primary key (reverse_domain_name, semantic_id, semver)
);

alter table "stored_query" owner to "openehr-admin";
grant delete, insert, select, update on "stored_query" to "openehr-user";

create table "template_store"
(
    id            uuid not null
                  primary key,
    template_id   text not null,
    content       text,
    creation_time timestamp(6) with time zone not null
);

alter table "template_store" owner to "openehr-admin";
create unique index "template_store_id_unq" on "template_store" (template_id);
grant delete, insert, select, update on "template_store" to "openehr-user";

create table comp_version
(
    vo_id               uuid not null
                        primary key,
    ehr_id              uuid not null
                        references "ehr",
    contribution_id     uuid not null
                        references "contribution",
    audit_id            uuid not null
                        references "audit_details",
    template_id         uuid not null
                        references "template_store",
    sys_version         integer not null,
    sys_period_lower    timestamp with time zone not null,
    root_concept        text not null
);

alter table "comp_version" owner to "openehr-admin";

create table comp_data
(
    vo_id            uuid    not null
        references comp_version
            on delete cascade,
    num              integer not null,
    citem_num        integer,
    rm_entity        text    not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text    not null,
    entity_idx_len   integer not null,
    data             jsonb   not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint comp_pkey
        primary key (vo_id, num)
);

alter table comp_data
    owner to ehrbase;

create index comp_data_leaf_idx
    on comp_data (vo_id, entity_idx collate "C");

create index comp_data_path_idx
    on comp_data (vo_id, parent_num, entity_attribute, entity_concept, rm_entity, num, num_cap);

grant delete, insert, select, update on comp_data to ehrbase_restricted;

create index comp_version_ehr_idx
    on comp_version (ehr_id, template_id) include (vo_id, sys_version);

create index comp_version_root_concept_idx
    on comp_version (ehr_id, root_concept) include (vo_id, sys_version);

create index comp_version_sys_period_lower_idx
    on comp_version (sys_period_lower desc, vo_id asc);

grant delete, insert, select, update on comp_version to ehrbase_restricted;

create table comp_version_history
(
    vo_id            uuid                     not null,
    ehr_id           uuid                     not null
        references ehr,
    contribution_id  uuid                     not null
        references contribution,
    audit_id         uuid                     not null
        references audit_details,
    template_id      uuid                     not null
        references template_store,
    sys_version      integer                  not null,
    sys_period_lower timestamp with time zone not null,
    sys_period_upper timestamp with time zone,
    sys_deleted      boolean                  not null,
    root_concept     text,
    primary key (vo_id, sys_version)
);

alter table comp_version_history
    owner to ehrbase;

create table comp_data_history
(
    vo_id            uuid    not null,
    num              integer not null,
    citem_num        integer,
    rm_entity        text    not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text    not null,
    entity_idx_len   integer not null,
    data             jsonb   not null,
    sys_version      integer not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint comp_history_pkey
        primary key (vo_id, num, sys_version),
    foreign key (vo_id, sys_version) references comp_version_history
        on delete cascade
);

alter table comp_data_history
    owner to ehrbase;

grant delete, insert, select, update on comp_data_history to ehrbase_restricted;

grant delete, insert, select, update on comp_version_history to ehrbase_restricted;

create table ehr_status_version
(
    vo_id            uuid                     not null,
    ehr_id           uuid                     not null
        primary key
        references ehr,
    contribution_id  uuid                     not null
        references contribution,
    audit_id         uuid                     not null
        references audit_details,
    sys_version      integer                  not null,
    sys_period_lower timestamp with time zone not null
);

alter table ehr_status_version
    owner to ehrbase;

create table ehr_status_data
(
    vo_id            uuid    not null,
    num              integer not null,
    ehr_id           uuid    not null
        constraint ehr_status_ehr_id_fkey
            references ehr
        references ehr_status_version
            on delete cascade,
    citem_num        integer,
    rm_entity        text    not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text    not null,
    entity_idx_len   integer not null,
    data             jsonb   not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint ehr_status_pkey
        primary key (ehr_id, num)
);

alter table ehr_status_data
    owner to ehrbase;

create unique index ehr_status_subject_idx
    on ehr_status_data ((((((data -> 'su'::text) -> 'er'::text) -> 'X'::text) -> 'V'::text) ->> 0),
                        ((((data -> 'su'::text) -> 'er'::text) -> 'ns'::text) ->> 0)) include (ehr_id, num)
    where (rm_entity = 'ES'::text);

create index ehr_status_data_path_idx
    on ehr_status_data (ehr_id, parent_num, entity_attribute, entity_concept, rm_entity, num, num_cap);

grant delete, insert, select, update on ehr_status_data to ehrbase_restricted;

create index ehr_status_version_sys_period_lower_idx
    on ehr_status_version (sys_period_lower desc, ehr_id asc);

grant delete, insert, select, update on ehr_status_version to ehrbase_restricted;

create table ehr_status_version_history
(
    vo_id            uuid                     not null,
    ehr_id           uuid                     not null
        references ehr,
    contribution_id  uuid                     not null
        references contribution,
    audit_id         uuid                     not null
        references audit_details,
    sys_version      integer                  not null,
    sys_period_lower timestamp with time zone not null,
    sys_period_upper timestamp with time zone,
    sys_deleted      boolean                  not null,
    primary key (ehr_id, sys_version)
);

alter table ehr_status_version_history
    owner to ehrbase;

create table ehr_status_data_history
(
    vo_id            uuid    not null,
    num              integer not null,
    ehr_id           uuid    not null
        constraint ehr_status_history_ehr_id_fkey
            references ehr,
    citem_num        integer,
    rm_entity        text    not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text    not null,
    entity_idx_len   integer not null,
    data             jsonb   not null,
    sys_version      integer not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint ehr_status_history_pkey
        primary key (ehr_id, num, sys_version),
    foreign key (ehr_id, sys_version) references ehr_status_version_history
        on delete cascade
);

alter table ehr_status_data_history
    owner to ehrbase;

grant delete, insert, select, update on ehr_status_data_history to ehrbase_restricted;

grant delete, insert, select, update on ehr_status_version_history to ehrbase_restricted;

create table ehr_folder_version
(
    vo_id            uuid                     not null,
    ehr_id           uuid                     not null
        references ehr,
    contribution_id  uuid                     not null
        references contribution,
    audit_id         uuid                     not null
        references audit_details,
    sys_version      integer                  not null,
    sys_period_lower timestamp with time zone not null,
    ehr_folders_idx  integer                  not null,
    primary key (ehr_id, ehr_folders_idx)
);

alter table ehr_folder_version
    owner to ehrbase;

create table ehr_folder_data
(
    vo_id            uuid    not null,
    num              integer not null,
    ehr_id           uuid    not null
        constraint ehr_folder_ehr_id_fkey
            references ehr,
    ehr_folders_idx  integer not null,
    citem_num        integer,
    rm_entity        text    not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text    not null,
    entity_idx_len   integer not null,
    data             jsonb   not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint ehr_folder_pkey
        primary key (ehr_id, ehr_folders_idx, num),
    foreign key (ehr_id, ehr_folders_idx) references ehr_folder_version
        on delete cascade
);

alter table ehr_folder_data
    owner to ehrbase;

grant delete, insert, select, update on ehr_folder_data to ehrbase_restricted;

grant delete, insert, select, update on ehr_folder_version to ehrbase_restricted;

create table ehr_folder_version_history
(
    vo_id            uuid                     not null,
    ehr_id           uuid                     not null
        references ehr,
    contribution_id  uuid                     not null
        references contribution,
    audit_id         uuid                     not null
        references audit_details,
    sys_version      integer                  not null,
    sys_period_lower timestamp with time zone not null,
    ehr_folders_idx  integer                  not null,
    sys_period_upper timestamp with time zone,
    sys_deleted      boolean                  not null,
    primary key (ehr_id, ehr_folders_idx, sys_version)
);

alter table ehr_folder_version_history
    owner to ehrbase;

create table ehr_folder_data_history
(
    vo_id            uuid    not null,
    num              integer not null,
    ehr_id           uuid    not null
        constraint ehr_folder_history_ehr_id_fkey
            references ehr,
    ehr_folders_idx  integer not null,
    citem_num        integer,
    rm_entity        text    not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text    not null,
    entity_idx_len   integer not null,
    data             jsonb   not null,
    sys_version      integer not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint ehr_folder_history_pkey
        primary key (ehr_id, ehr_folders_idx, num, sys_version),
    foreign key (ehr_id, ehr_folders_idx, sys_version) references ehr_folder_version_history
        on delete cascade
);

alter table ehr_folder_data_history
    owner to ehrbase;

grant delete, insert, select, update on ehr_folder_data_history to ehrbase_restricted;

grant delete, insert, select, update on ehr_folder_version_history to ehrbase_restricted;

create table ehr_item_tag
(
    id               uuid                         not null
        primary key,
    ehr_id           uuid                         not null
        references ehr,
    target_vo_id     uuid                         not null,
    target_type      ehr.ehr_item_tag_target_type not null,
    key              text                         not null,
    value            text,
    target_path      text,
    creation_date    timestamp(6) with time zone  not null,
    sys_period_lower timestamp(6) with time zone  not null
);

alter table ehr_item_tag
    owner to ehrbase;

create index ehr_item_tag_ehr_id_target_vo_id_idx
    on ehr_item_tag (ehr_id, target_vo_id);

grant delete, insert, select, update on ehr_item_tag to ehrbase_restricted;

