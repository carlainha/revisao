create table tb_curso(
    id bigint not null auto_increment,
    nome_curso varchar(100),
    data_cadastro datetime not null,
    data_atualizacao datetime not null,

    primary key(id)
) engine=InnoDB default charset=utf8;

create table tb_aluno(
    id bigint not null auto_increment,
    nome_aluno varchar(100) not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    curso_id bigint not null

    primary key(id)
) engine=InnoDB default charset=utf8;