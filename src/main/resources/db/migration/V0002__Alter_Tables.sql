alter table tb_aluno add constraint fk_aluno_curso
foreign key (curso_id) references tb_curso(id);