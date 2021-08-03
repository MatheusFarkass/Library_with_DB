create database livraria;

use livraria;

create table livro(
	codLivro int auto_increment not null,
    titulo varchar(50),
    autor varchar(50),
    isbn varchar(13),
    codEditora integer,
    
	primary key(codLivro),
    foreign key(codEditora) references editora(codEditora)
    );

create table editora(
	codEditora int auto_increment not null,
    razaoSocial varchar(50),
    CNPJ varchar(14),
    telefone varchar(10),
    
	primary key(codEditora)
);