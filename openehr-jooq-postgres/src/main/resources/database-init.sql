create sequence tenant_id_seq;
alter sequence tenant_id_seq owner to "openehr-admin";
grant select on sequence tenant_id_seq to "openehr-user";

/**
 * Custom types and enumerations.
 */
create type contribution_change_type as enum ('creation', 'amendment', 'modification', 'synthesis', 'Unknown', 'deleted');
alter type "contribution_change_type" owner to "openehr-admin";

create type contribution_data_type as enum ('composition', 'folder', 'ehr', 'system', 'other');
alter type contribution_data_type owner to "openehr-admin";

create type ehr_item_tag_target_type as enum ('ehr_status', 'composition');
alter type ehr_item_tag_target_type owner to ehrbase;


/*
 * The plugin table is used to store key value pairs for the plugin sub system.
 */
create table "plugin"
(
    id        uuid default uuid_generate_v4() not null,
    plugin_id text not null,
    key       text not null,
    value     text,
    constraint pk_plugin primary key (id)
);

comment on table "plugin" is 'key value store for plugin sub system';
alter table "plugin"
    owner to "openehr-admin";
grant delete, insert, select, update on "plugin" to "openehr-user";

/*
 * The ehr table is used to store the EHRs.
 */
create table "ehr"
(
    id            uuid not null,
    creation_date timestamp(6) with time zone,
    constraint pk_ehr primary key (id)
);

comment on table "ehr" is 'Stores the EHRs';
alter table "ehr" owner to "openehr-admin";
create index "idx_ehr_time_created" on "ehr" (creation_date desc, id asc);
grant delete, insert, select, update on "ehr" to "openehr-user";

/*
 * The users table is used to store openEHR users.
 */
create table "users"
(
    id       uuid not null,
    username text not null,
    constraint pk_users primary key (id)
);

comment on table "users" is 'Stores the openEHR users';
alter table "users" owner to "openehr-admin";
create unique index "idx_users_username_idx" on "users" (username);
grant delete, insert, select, update on "users" to "openehr-user";

/*
 * The audit_details table is used to store the audit details related to EHRs.
 */
create table "audit_details"
(
    id             uuid not null,
    change_type    contribution_change_type not null,
    description    text,
    time_committed timestamp(6) with time zone not null,
    committer      jsonb,
    user_id        uuid  not null,
    target_type    varchar not null,
    constraint pk_audit_details primary key (id),
    constraint fk_audit_details_user foreign key (user_id) references users(id)
);

comment on table "audit_details" is 'Stores the audit details related to EHRs';
alter table "audit_details" owner to "openehr-admin";
grant delete, insert, select, update on "audit_details" to "openehr-user";

/**
 * The contribution table is used to store the contributions made to the EHRs.
 */
create table "contribution"
(
    id                uuid not null,
    ehr_id            uuid,
    contribution_type contribution_data_type,
    signature         text,
    has_audit         uuid,
    constraint pk_contribution primary key (id),
    constraint fk_contribution_ehr foreign key (ehr_id) references ehr(id) on delete cascade,
    constraint fk_contribution_audit foreign key (has_audit) references "audit_details" on delete cascade
);

comment on table "contribution" is 'Stores the contributions made to the EHRs';
alter table "contribution" owner to "openehr-admin";
create index "idx_contribution_ehr" on "contribution" (ehr_id);
grant delete, insert, select, update on "contribution" to "openehr-user";

/**
 * The stored_query table is used to store openEHR queries.
 */
create table "stored_query"
(
    reverse_domain_name varchar not null,
    semantic_id         varchar not null,
    semver              varchar default '0.0.0'::character varying not null,
    query_text          varchar not null,
    type                varchar default 'AQL'::character varying,
    creation_date       timestamp(6) with time zone not null,
    constraint pk_stored_query primary key (reverse_domain_name, semantic_id, semver)
);

comment on table "stored_query" is 'Stores the openEHR queries';
alter table "stored_query" owner to "openehr-admin";
grant delete, insert, select, update on "stored_query" to "openehr-user";

/**
 * The template_store table is used to store the openEHR templates.
 */
create table "template_store"
(
    id            uuid not null,
    template_id   text not null,
    content       text,
    creation_time timestamp(6) with time zone not null,
    constraint pk_template_store primary key (id)
);

comment on table "template_store" is 'Stores the openEHR templates';
alter table "template_store" owner to "openehr-admin";
create unique index "idx_template_store_template" on "template_store" (template_id);
grant delete, insert, select, update on "template_store" to "openehr-user";

/**
 * The composition version table is used to store the versions of the compositions.
 */
create table comp_version
(
    vo_id uuid not null,
    ehr_id           uuid not null,
    contribution_id  uuid not null,
    audit_id         uuid not null,
    template_id      uuid not null,
    sys_version      integer not null,
    sys_period_lower timestamp with time zone not null,
    root_concept     text not null,
    constraint pk_com_version primary key (vo_id),
    constraint fk_comp_version_ehr foreign key (ehr_id) references ehr(id),
    constraint fk_comp_version_contribution foreign key (contribution_id) references contribution(id),
    constraint fk_comp_version_audit foreign key (audit_id) references audit_details(id),
    constraint fk_comp_version_template foreign key (template_id) references template_store(id)
);

comment on table comp_version is 'Stores the versions of the compositions';
alter table "comp_version" owner to "openehr-admin";
create index idx_comp_version_ehr on comp_version (ehr_id, template_id) include (vo_id, sys_version);
create index idx_comp_version_root_concept on comp_version (ehr_id, root_concept) include (vo_id, sys_version);
create index idx_comp_version_sys_period_lower_idx on comp_version (sys_period_lower desc, vo_id asc);
grant delete, insert, select, update on comp_version to "openehr-user";


/**
 * The composition data table is used to store the data of the compositions.
 */
create table comp_data
(
    vo_id            uuid not null,
    num              integer not null,
    citem_num        integer,
    rm_entity        text not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text not null,
    entity_idx_len   integer not null,
    data             jsonb not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint pk_comp_data primary key (vo_id, num),
    constraint fk_comp_data_comp_version foreign key (vo_id) references comp_version(vo_id) on delete cascade
);

comment on table comp_data is 'Stores the data of the compositions';
alter table comp_data owner to "openehr-admin";
create index idx_comp_data_leaf on comp_data (vo_id, entity_idx collate "C");
create index idx_comp_data_path on comp_data (vo_id, parent_num, entity_attribute, entity_concept, rm_entity, num, num_cap);
grant delete, insert, select, update on comp_data to "openehr-user";

/**
 * The composition version history table is used to store the history of the composition versions.
 */
create table comp_version_history
(
    vo_id            uuid not null,
    ehr_id           uuid not null,
    contribution_id  uuid not null,
    audit_id         uuid not null,
    template_id      uuid not null,
    sys_version      integer not null,
    sys_period_lower timestamp with time zone not null,
    sys_period_upper timestamp with time zone,
    sys_deleted      boolean not null,
    root_concept     text,
    constraint pk_com_version_history primary key (vo_id, sys_version),
    constraint fk_comp_version_history_ehr foreign key (ehr_id) references ehr(id),
    constraint fk_comp_version_history_contribution foreign key (contribution_id) references contribution(id),
    constraint fk_comp_version_history_audit foreign key (audit_id) references audit_details(id),
    constraint fk_comp_version_history_template foreign key (template_id) references template_store(id)
);

comment on table comp_version_history is 'Stores the history of the composition versions';
alter table comp_version_history owner to "openehr-admin";
grant delete, insert, select, update on comp_version_history to "openehr-user";

/**
 * The composition data history table is used to store the history of the composition data.
 */
create table comp_data_history
(
    vo_id            uuid not null,
    num              integer not null,
    citem_num        integer,
    rm_entity        text not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text not null,
    entity_idx_len   integer not null,
    data             jsonb not null,
    sys_version      integer not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint pk_comp_data_history primary key (vo_id, num, sys_version),
    constraint fk_com_data_history_comp_version_history foreign key (vo_id, sys_version) references comp_version_history(vo_id, sys_version)
        on delete cascade
);

comment on table comp_data_history is 'Stores the history of the composition data';
alter table comp_data_history owner to "openehr-admin";
grant delete, insert, select, update on comp_data_history to "openehr-user";

/**
 * The ehr_status_version table is used to store the versions of the EHR statuses.
 */
create table ehr_status_version
(
    vo_id            uuid not null,
    ehr_id           uuid not null,
    contribution_id  uuid not null,
    audit_id         uuid not null,
    sys_version      integer not null,
    sys_period_lower timestamp with time zone not null,
    constraint pk_ehr_status_version primary key (ehr_id, sys_version),
    constraint fk_ehr_status_version_ehr foreign key (ehr_id) references ehr(id),
    constraint fk_ehr_status_version_contribution foreign key (contribution_id) references contribution(id),
    constraint fk_ehr_status_version_audit foreign key (audit_id) references audit_details(id)
);

comment on table ehr_status_version is 'Stores the versions of the EHR statuses';
alter table ehr_status_version owner to "openehr-admin";
create index idx_ehr_status_version_sys_period_lower on ehr_status_version (sys_period_lower desc, ehr_id asc);
grant delete, insert, select, update on ehr_status_version to "openehr-user";

/*
 * The ehr_status_data table is used to store the data of the EHR statuses.
 */
create table ehr_status_data
(
    vo_id            uuid not null,
    num              integer not null,
    ehr_id           uuid not null,
    citem_num        integer,
    rm_entity        text not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text not null,
    entity_idx_len   integer not null,
    data             jsonb not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint pk_ehr_status primary key (ehr_id, num),
    constraint fk_ehr_status_data_ehr foreign key (ehr_id) references ehr(id),
    constraint fk_ehr_status_data_ehr_status_version foreign key (ehr_id, num) references ehr_status_version(ehr_id, sys_version) on delete cascade
);

comment on table ehr_status_data is 'Stores the data of the EHR statuses';
alter table ehr_status_data owner to "openehr-admin";
create unique index idx_ehr_status_subject on ehr_status_data ((((((data -> 'su'::text) -> 'er'::text) -> 'X'::text) -> 'V'::text) ->> 0),
    ((((data -> 'su'::text) -> 'er'::text) -> 'ns'::text) ->> 0)) include (ehr_id, num)
    where (rm_entity = 'ES'::text);
create index ehr_status_data_path_idx on ehr_status_data (ehr_id, parent_num, entity_attribute, entity_concept, rm_entity, num, num_cap);
grant delete, insert, select, update on ehr_status_data to "openehr-user";

/*
 * The ehr_status_version_history table is used to store the history of the EHR status versions.
 */
create table ehr_status_version_history
(
    vo_id            uuid not null,
    ehr_id           uuid not null,
    contribution_id  uuid not null,
    audit_id         uuid not null,
    sys_version      integer not null,
    sys_period_lower timestamp with time zone not null,
    sys_period_upper timestamp with time zone,
    sys_deleted      boolean not null,
    constraint pk_ehr_status_version_history primary key (ehr_id, sys_version),
    constraint fk_ehr_status_version_history_ehr foreign key (ehr_id) references ehr(id),
    constraint fk_ehr_status_version_history_contribution foreign key (contribution_id) references contribution(id),
    constraint fk_ehr_status_version_history_audit foreign key (audit_id) references audit_details(id)
);

comment on table ehr_status_version_history is 'Stores the history of the EHR status versions';
alter table ehr_status_version_history owner to "openehr-admin";
grant delete, insert, select, update on ehr_status_version_history to "openehr-user";

/**
 * The ehr_status_data_history table is used to store the history of the EHR status data.
 */
create table ehr_status_data_history
(
    vo_id            uuid not null,
    num              integer not null,
    ehr_id           uuid not null,
    citem_num        integer,
    rm_entity        text not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text not null,
    entity_idx_len   integer not null,
    data             jsonb not null,
    sys_version      integer not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint pk_ehr_status_history  primary key (ehr_id, num, sys_version),
    constraint fk_ehr_status_data_history_ehr foreign key (ehr_id) references ehr(id),
    constraint fk_ehr_status_data_history_ehr_status_version_history foreign key (ehr_id, sys_version) references ehr_status_version_history on delete cascade
);

comment on table ehr_status_data_history is 'Stores the history of the EHR status data';
alter table ehr_status_data_history owner to "openehr-admin";
grant delete, insert, select, update on ehr_status_data_history to "openehr-user";

/**
 * The ehr_folder_version table is used to store the versions of the EHR folders.
 */
create table ehr_folder_version
(
    vo_id            uuid not null,
    ehr_id           uuid not null,
    contribution_id  uuid not null,
    audit_id         uuid not null,
    sys_version      integer not null,
    sys_period_lower timestamp with time zone not null,
    ehr_folders_idx  integer not null,
    constraint pk_ehr_folder_version primary key (ehr_id, ehr_folders_idx),
    constraint fk_ehr_folder_version_ehr foreign key (ehr_id) references ehr(id),
    constraint fk_ehr_folder_version_contribution foreign key (contribution_id) references contribution(id),
    constraint fk_ehr_folder_version_audit foreign key (audit_id) references audit_details(id)
);

comment on table ehr_folder_version is 'Stores the versions of the EHR folders';
alter table ehr_folder_version owner to "openehr-admin";
grant delete, insert, select, update on ehr_folder_version to "openehr-user";


/**
 * The ehr_folder_data table is used to store the data of the EHR folders.
 */
create table ehr_folder_data
(
    vo_id            uuid not null,
    num              integer not null,
    ehr_id           uuid not null,
    ehr_folders_idx  integer not null,
    citem_num        integer,
    rm_entity        text not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text not null,
    entity_idx_len   integer not null,
    data             jsonb not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint pk_ehr_folder_data primary key (ehr_id, ehr_folders_idx, num),
    constraint fk_ehr_folder_data_ehr_folder_version foreign key (ehr_id, ehr_folders_idx) references ehr_folder_version on delete cascade,
    constraint fk_ehr_folder_data_ehr foreign key (ehr_id) references ehr(id)
);

comment on table ehr_folder_data is 'Stores the data of the EHR folders';
alter table ehr_folder_data owner to "openehr-admin";
grant delete, insert, select, update on ehr_folder_data to "openehr-user";

/**
 * The ehr_folder_version_history table is used to store the history of the EHR folder versions.
 */
create table ehr_folder_version_history
(
    vo_id            uuid not null,
    ehr_id           uuid not null,
    contribution_id  uuid not null,
    audit_id         uuid not null,
    sys_version      integer not null,
    sys_period_lower timestamp with time zone not null,
    ehr_folders_idx  integer not null,
    sys_period_upper timestamp with time zone,
    sys_deleted      boolean not null,
    constraint pk_ehr_folder_version_history primary key (ehr_id, ehr_folders_idx, sys_version),
    constraint fk_ehr_folder_version_history_ehr foreign key (ehr_id) references ehr(id),
    constraint fk_ehr_folder_version_history_contribution foreign key (contribution_id) references contribution(id),
    constraint fk_ehr_folder_version_history_audit foreign key (audit_id) references audit_details(id)
);

comment on table ehr_folder_version_history is 'Stores the history of the EHR folder versions';
alter table ehr_folder_version_history owner to "openehr-admin";
grant delete, insert, select, update on ehr_folder_version_history to "openehr-user";

/**
 * The ehr_folder_data_history table is used to store the history of the EHR folder data.
 */
create table ehr_folder_data_history
(
    vo_id            uuid not null,
    num              integer not null,
    ehr_id           uuid not null,
    ehr_folders_idx  integer not null,
    citem_num        integer,
    rm_entity        text not null,
    entity_concept   text,
    entity_name      text,
    entity_attribute text,
    entity_idx       text not null,
    entity_idx_len   integer not null,
    data             jsonb not null,
    sys_version      integer not null,
    parent_num       integer not null,
    num_cap          integer not null,
    constraint pk_ehr_folder_data_history primary key (ehr_id, ehr_folders_idx, num, sys_version),
    constraint fk_ehr_folder_data_history_verson_history foreign key (ehr_id, ehr_folders_idx, sys_version) references ehr_folder_version_history on delete cascade,
    constraint fk_ehr_folder_data_history_ehr foreign key (ehr_id) references ehr(id)
);

comment on table ehr_folder_data_history is 'Stores the history of the EHR folder data';
alter table ehr_folder_data_history owner to "openehr-admin";
grant delete, insert, select, update on ehr_folder_data_history to "openehr-user";

/**
 * The ehr_item_tag table is used to store the tags of the EHR items.
 */
create table ehr_item_tag
(
    id               uuid not null,
    ehr_id           uuid not null,
    target_vo_id     uuid not null,
    target_type      ehr_item_tag_target_type not null,
    key              text not null,
    value            text,
    target_path      text,
    creation_date    timestamp(6) with time zone  not null,
    sys_period_lower timestamp(6) with time zone  not null,
    constraint pk_ehr_item_tag primary key (id),
    constraint fk_ehr_item_tag_ehr foreign key (ehr_id) references ehr(id) on delete cascade
);

comment on table ehr_item_tag is 'Stores the tags of the EHR items';
alter table ehr_item_tag owner to "openehr-admin";
create index idx_ehr_item_tag_ehr_id_target_vo_id on ehr_item_tag (ehr_id, target_vo_id);
grant delete, insert, select, update on ehr_item_tag to "openehr-user";

