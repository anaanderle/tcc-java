INSERT INTO usuario(nome, email, apelido, data_nascimento, senha, imagem_url, ativo)
	VALUES ('Ana Clara Anderle', 'ana.anderle@cwi.com.br', 'ana.anderle', '2003-09-25',
			'$2a$10$LUvBjDcSSb3raUulKtm6MeyfY70WNonbn9RIqzj.Lk41aOZDugqsK', 'https://blog.cobasi.com.br/wp-content/uploads/2021/08/caracteristicas-do-gato-1-1.png', TRUE);

INSERT INTO usuario(nome, email, apelido, data_nascimento, senha, imagem_url, ativo)
	VALUES ('José Silveira', 'jose.silveira@cwi.com.br', 'jose.silveira', '2003-09-25',
			'$2a$10$QnJBjw1cGRyhgRPNm6l1uOGJ5EwT2pfpZRBGGNwWW1Mz2AUc3fp9C', 'https://blog-static.petlove.com.br/wp-content/uploads/2021/10/gato-sumiu-Petlove.jpg', TRUE);

INSERT INTO usuario(nome, email, apelido, data_nascimento, senha, imagem_url, ativo)
	VALUES ('Maria de Oliveira', 'maria.oliveira@cwi.com.br', 'maria.oliveira', '2003-09-25',
			'$2a$10$4EJ/OS.VuG/vj2VN0ci/K.cLrb1Lfmf1niACor0tiLpkAKLt24NDW', 'https://recursos.tudodebicho.com.br/i/guiaderacas/gato-siames/gato-siames-jovem.jpg', TRUE);

INSERT INTO usuario(nome, email, apelido, data_nascimento, senha, imagem_url, ativo)
	VALUES ('Julia Carvalhos', 'julia.carvalhos@cwi.com.br', 'julia.carvalhos', '2003-09-25',
			'$2a$10$C7fNptBlhE6inmVkfXwARu9Djvri5K2Z7oOblowWbJ1ZCGEIqrCM6', 'https://conteudo.imguol.com.br/c/entretenimento/28/2022/10/28/gato-preto-1666974144631_v2_3x4.jpg', TRUE);

INSERT INTO permissao(nome, usuario_id)
	VALUES ('USUARIO', 1);

INSERT INTO permissao(nome, usuario_id)
	VALUES ('USUARIO', 2);

INSERT INTO permissao(nome, usuario_id)
	VALUES ('USUARIO', 3);

INSERT INTO permissao(nome, usuario_id)
	VALUES ('USUARIO', 4);

INSERT INTO amizade(usuario_id, amigo_id, situacao)
	VALUES (2, 1, 'SOLICITADA');

INSERT INTO amizade(usuario_id, amigo_id, situacao)
	VALUES (3, 1, 'SOLICITADA');

INSERT INTO amizade(usuario_id, amigo_id, situacao)
	VALUES (4, 1, 'SOLICITADA');

INSERT INTO post(usuario_id, imagem_url, descricao, situacao, data_postagem)
	VALUES (1, 'https://super.abril.com.br/wp-content/uploads/2020/09/04-09_gato_SITE.jpg?quality=90&strip=info&w=1024&resize=1200,800',
			'Gatinho laranja muito fofinho', 'PRIVADO', '2023-02-20');

INSERT INTO post(usuario_id, imagem_url, descricao, situacao, data_postagem)
	VALUES (2, 'https://static.nationalgeographicbrasil.com/files/styles/image_3200/public/75552.jpg?w=1600&h=1067',
			'Gatinho mal humorado', 'PRIVADO', '2023-02-18');

INSERT INTO post(usuario_id, imagem_url, descricao, situacao, data_postagem)
	VALUES (3, 'https://saude.abril.com.br/wp-content/uploads/2020/04/gato-coronavc3adrus.jpg?quality=85&strip=info&w=1280&h=720&crop=1',
			'Gatinho triste', 'PUBLICO', '2023-02-16');

INSERT INTO comentario(post_id, usuario_id, descricao)
	VALUES (3, 1, 'Que lindo!');

INSERT INTO comentario(post_id, usuario_id, descricao)
	VALUES (3, 2, 'Fofo');

INSERT INTO comentario(post_id, usuario_id, descricao)
	VALUES (3, 3, 'Awn!!');