create extension if not exists "uuid-ossp";

create table if not exists users (
    user_name varchar,
    user_id bigint not null,
    first_name varchar,
    last_name varchar,
    bot_localization varchar not null,
    chat_state varchar not null,
    primary key (user_id)
);

create table if not exists goals (
    goal_id uuid default uuid_generate_v4(),
    goal_name varchar not null,
    goal_description varchar,
    goal_deadline timestamp,
    goal_reminder timestamp,
    user_id bigint not null,
    primary key (goal_id),
    foreign key(user_id) references users(user_id)
);