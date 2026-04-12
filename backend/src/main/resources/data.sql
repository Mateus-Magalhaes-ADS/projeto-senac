-- ==========================================
-- ROLES
-- ==========================================
INSERT INTO tb_role (authority) VALUES ('ROLE_USER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

-- ==========================================
-- USERS (senha: 123456 em BCrypt)
-- user_id=1 é o ADMIN, os alunos ficam de 2 a 11
-- ==========================================
INSERT INTO tb_user (name, email, password) VALUES ('Admin',           'admin@senac.com',            '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Caio Silva',      'caio.silva@email.com',       '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Souza',     'maria.souza@email.com',      '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('João Pereira',    'joao.pereira@email.com',     '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Ana Lima',        'ana.lima@email.com',         '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Pedro Santos',    'pedro.santos@email.com',     '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Juliana Costa',   'juliana.costa@email.com',    '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Lucas Almeida',   'lucas.almeida@email.com',    '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Fernanda Rocha',  'fernanda.rocha@email.com',   '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Rafael Mendes',   'rafael.mendes@email.com',    '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Beatriz Oliveira','beatriz.oliveira@email.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

-- ==========================================
-- USER_ROLE
-- admin (id=1) recebe ROLE_USER + ROLE_ADMIN
-- alunos (id=2..11) recebem apenas ROLE_USER
-- ==========================================
INSERT INTO tb_user_role (user_id, role_id) VALUES (1,  1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1,  2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2,  1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3,  1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4,  1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (5,  1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (6,  1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (7,  1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (8,  1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (9,  1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (10, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (11, 1);

-- ==========================================
-- CURSOS
-- ==========================================
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('Python Básico',           'Curso introdutório de Python',             '3 meses', 'Online', 110.00);
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('Java Intermediário',      'Aprofundamento em Java com POO',           '3 meses', 'Online', 150.00);
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('Banco de Dados SQL',      'Modelagem e consultas SQL',                '2 meses', 'Online', 130.00);
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('Spring Boot',             'Desenvolvimento de APIs com Spring Boot',  '4 meses', 'Online', 180.00);
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('JavaScript Essencial',    'Fundamentos de JavaScript para web',       '2 meses', 'Online', 100.00);
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('HTML e CSS',              'Criação de páginas web modernas',          '1 mês',   'Online',  80.00);
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('React Básico',            'Introdução ao React JS',                   '3 meses', 'Online', 140.00);
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('Git e GitHub',            'Controle de versão na prática',            '1 mês',   'Online',  70.00);
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('Clean Code',              'Boas práticas de programação',             '2 meses', 'Online', 160.00);
INSERT INTO tb_curso(nome, descricao, duracao, modalidade, valor_mensalidade) VALUES ('Arquitetura de Software', 'Fundamentos de arquitetura e padrões',     '4 meses', 'Online', 200.00);

-- ==========================================
-- ALUNOS (user_id começa em 2, pois 1 é o admin)
-- ==========================================
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('Caio Silva',       '123.456.789-01', 'caio.silva@email.com',       '81988887777', '123456', 'Rua das Flores, 100',    2);
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('Maria Souza',      '987.654.321-00', 'maria.souza@email.com',      '81997776666', '123456', 'Av. Brasil, 250',         3);
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('João Pereira',     '111.222.333-44', 'joao.pereira@email.com',     '81996665555', '123456', 'Rua Central, 45',         4);
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('Ana Lima',         '555.666.777-88', 'ana.lima@email.com',         '81995554444', '123456', 'Rua São João, 300',       5);
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('Pedro Santos',     '222.333.444-55', 'pedro.santos@email.com',     '81994443333', '123456', 'Av. Norte, 600',          6);
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('Juliana Costa',    '333.444.555-66', 'juliana.costa@email.com',    '81993332222', '123456', 'Rua das Acácias, 80',     7);
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('Lucas Almeida',    '444.555.666-77', 'lucas.almeida@email.com',    '81992221111', '123456', 'Rua Verde, 150',          8);
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('Fernanda Rocha',   '666.777.888-99', 'fernanda.rocha@email.com',   '81991110000', '123456', 'Av. Recife, 900',         9);
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('Rafael Mendes',    '777.888.999-00', 'rafael.mendes@email.com',    '81990001111', '123456', 'Rua do Sol, 12',         10);
INSERT INTO tb_aluno(nome, cpf, email, telefone, senha, endereco, user_id) VALUES ('Beatriz Oliveira', '888.999.000-11', 'beatriz.oliveira@email.com', '81989998888', '123456', 'Rua das Palmeiras, 77',  11);

-- ==========================================
-- MATRÍCULAS
-- ==========================================
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-10', 'ATIVA',  1,  1);
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-11', 'ATIVA',  2,  2);
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-12', 'ATIVA',  3,  3);
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-13', 'ATIVA',  4,  4);
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-14', 'ATIVA',  5,  5);
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-15', 'ATIVA',  6,  6);
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-16', 'ATIVA',  7,  7);
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-17', 'ATIVA',  8,  8);
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-18', 'ATIVA',  9,  9);
INSERT INTO tb_matricula(data_matricula, status, aluno_id, curso_id) VALUES ('2026-01-19', 'ATIVA', 10, 10);