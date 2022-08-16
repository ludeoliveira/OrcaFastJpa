INSERT INTO fornecedor(razaosocial, cnpj, email, telefone)
values ('Macro Materiais para construção','15375991/0001-94','mmmat_construcao@gmail.com','(65)36825544');
INSERT INTO fornecedor(razaosocial, cnpj, email, telefone)
values ('Apache Materiais para construção','20395775/0001-60','apachemat_construcao@gmail.com','(71)44568574');
INSERT INTO fornecedor(razaosocial, cnpj, email, telefone)
values ('Roma Materiais para construção','20395775/0001-60','romamat_construcao@gmail.com','(71)44568574');

insert into categoria (idfornecedor, descricaoc) values(1,'Basico');
insert into categoria (idfornecedor, descricaoc) values(1,'Eletrica e iluminação');
insert into categoria (idfornecedor, descricaoc) values(1,'Revestimento e tintas');
insert into categoria (idfornecedor, descricaoc) values(1,'Hidraulica');
insert into categoria (idfornecedor, descricaoc) values(1,'Louças e metais');
insert into categoria (idfornecedor, descricaoc) values(2,'Basico');
insert into categoria (idfornecedor, descricaoc) values(2,'Eletrica e iluminação');
insert into categoria (idfornecedor, descricaoc) values(2,'Revestimento e tintas');
insert into categoria (idfornecedor, descricaoc) values(2,'Hidraulica');
insert into categoria (idfornecedor, descricaoc) values(2,'Louças e metais');

insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(1,'Cimento 50kg','Itau', 28.90, 1000);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(1,'Ferro 3/8','Ferragens', 79.90, 100);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(2,'Lampada Led bulbo 8w','Brilia', 8.90, 2000);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(2,'Plafon Led 18w','Avant', 36.90, 150);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(3,'Tinta Acrilica 18L','Suvinil', 364.90, 40);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(3,'Tinta acetinada 900ML','Brasilux', 99.90, 120);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(3,'Piso porcelanato 90x90 branco','Embramaco', 129.90, 500);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(3,'Piso azulejo 60x30 branco','Eliane', 49.90, 1000);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(4,'Tubo 100mmx6m','Amanco', 89.90, 1000);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(4,'Joelho Forma 3/4','Tigre', 5.90, 5000);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(5,'Torneira de mesa cromada','Jolie', 79.90, 40);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(5,'Torneira bica alta','Deca', 99.90, 90);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(5,'Ducha Higienica cromada','Maruja', 99.90, 40);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(5,'Bacia c/ caixa acoplada Axis','Deca', 899.90, 40);
insert into produto (idcategoria, descricaop, marca, preco, estoque)
values(5,'Bacia caixa acoplada Quadra','Deca', 1299.90, 30);

insert into usuario(razaosocial, cnpj)
values('mmconstrutora@gmail.com.br','12.128.319/1111-99');
insert into usuario(razaosocial, cnpj)
values('Isabella e Raimunda Advocacia Ltda','52.128.319/0001-09');

insert into orcamento (idusuario, data)
values(1,'2022-08-09');
insert into orcamento (idusuario, data)
values(2,'2022-07-10');

insert into selecao(idorcamento, idproduto, quantidade, preco)
values (1,1,8,28.45);
insert into selecao(idorcamento, idproduto, quantidade, preco)
values (1,2,2,79.90);
insert into selecao(idorcamento,idproduto, quantidade, preco)
values (1,3,5,8.90);
insert into selecao(idorcamento,idproduto, quantidade, preco)
values (1,6,1,99.90);
insert into selecao(idorcamento,idproduto, quantidade, preco)
values (1,8,100,49.90);

insert into selecao(idorcamento, idproduto, quantidade, preco)
values (2,1,8,28.45);
insert into selecao(idorcamento, idproduto, quantidade, preco)
values (2,2,2,79.90);
insert into selecao(idorcamento,idproduto, quantidade, preco)
values (2,3,5,8.90);
insert into selecao(idorcamento,idproduto, quantidade, preco)
values (2,6,1,99.90);
insert into selecao(idorcamento,idproduto, quantidade, preco)
values (2,8,100,49.90);

