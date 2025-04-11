create database estrategica

use estrategica;

create table Users (
id_user INT primary key not null auto_increment unique,
email VARCHAR(100) unique not null,
senha VARCHAR(45) not null,
nivel tinyint(1) not null default 0,
-- 0 = FALSO e 1 = VERDADEIRO
username VARCHAR(45) not null,
setor VARCHAR(45) not null,
ccpadrao INT not null
);

create table Funcionario (
id_funcionario INT not null auto_increment unique primary key,
fk_users_id_user int not null,
nome varchar(45),
foreign key (fk_users_id_user) references Users(id_user)
ON DELETE CASCADE ON UPDATE CASCADE
);

create table Gestor(
id_gestor int not null unique primary key,
grupo_gerido varchar(30),
foreign key (id_gestor) references Funcionario(id_funcionario)
ON DELETE CASCADE ON UPDATE CASCADE
);

create table Atividade(
id_atividade int not null auto_increment primary key,
descricao text,
interna tinyint(1) not null default 0
);

create table Feriado(
id_feriado int not null primary key,
nome_feriado VARCHAR(30),
data_feriado date,
permite_lancamento tinyint(1) not null default 0
);

create table Apontamento(
id_apontamento int not null auto_increment unique primary key,
hora_inicio time not null,
hora_fim time not null,
centro_de_custo VARCHAR(30),
data_apontamento date not null,
data_preenchimento date not null,
aprovado tinyint(1) not null default 0,
fk_Atividade_id_atividade int not null,
fk_Funcionario_id_funcionario int not null,
fk_Feriado_id_feriado int not null,
foreign key (fk_Atividade_id_atividade) references Atividade(id_atividade) ON DELETE CASCADE ON UPDATE CASCADE,
foreign key (fk_Funcionario_id_funcionario) references Funcionario(id_funcionario) ON DELETE CASCADE ON UPDATE CASCADE,
foreign key (fk_Feriado_id_feriado) references Feriado(id_feriado) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Escala_de_Trabalho(
id_escala_de_trabalho int not null auto_increment primary key,
horas_semanais int not null,
dias_semana VARCHAR(100)
);

create table Funcionario_Escala(
fk_Funcionario_id_funcionario int not null,
fk_Escala_de_Trabalho_id_escala_de_trabalho int not null,
foreign key (fk_Funcionario_id_funcionario) references Funcionario(id_funcionario) ON DELETE CASCADE ON UPDATE CASCADE,
foreign key (fk_Escala_de_Trabalho_id_escala_de_trabalho) references Escala_de_Trabalho(id_escala_de_trabalho) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Cliente(
id_cliente int not null auto_increment unique primary key,
nome_cliente varchar(45) not null,
rua varchar (45) not null,
numero varchar(20) not null,
bairro varchar(40) not null,
cidade varchar(40) not null,
estado varchar(20) not null,
CEP varchar(10) not null,
FAX varchar(40) not null,
telefone varchar(15) not null,
email varchar(50) not null
);

create table Contrato (
    id_contrato integer primary key auto_increment,
    obj_contratado varchar(255) not null,
    data_inicio date not null,
    data_fim date not null,
    estado varchar(50) not null,
    fk_Cliente_id_cliente integer not null,
    fk_Funcionario_id_funcionario integer not null,
    fk_Gestor_id_gestor integer not null,
	FOREIGN KEY (fk_Cliente_id_cliente) REFERENCES Cliente(id_cliente)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (fk_Gestor_id_gestor) REFERENCES Gestor(id_gestor)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (fk_Funcionario_id_funcionario) REFERENCES Funcionario(id_funcionario)
	ON DELETE CASCADE ON UPDATE CASCADE
);