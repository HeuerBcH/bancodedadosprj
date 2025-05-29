-- Inserir Users (funcionários, gestores)
INSERT INTO Users (email, senha, nivel, username, setor, ccpadrao) VALUES
('joao.silva@engenharia.com', 'senha123', 1, 'joaosilva', 'Engenharia', 101),
('maria.alves@consultoria.com', 'senha123', 0, 'mariaalves', 'Consultoria', 102),
('pedro.lima@tecnologia.com', 'senha123', 0, 'pedrolima', 'TI', 103),
('ana.santos@engenharia.com', 'senha123', 1, 'anasantos', 'Engenharia', 101),
('carlos.moura@consultoria.com', 'senha123', 0, 'carlosmoura', 'Consultoria', 102),
('laura.pereira@tecnologia.com', 'senha123', 0, 'laurapereira', 'TI', 103),
('ricardo.ferreira@engenharia.com', 'senha123', 0, 'ricardoferreira', 'Engenharia', 101),
('paula.rodrigues@consultoria.com', 'senha123', 0, 'paularodrigues', 'Consultoria', 102),
('bruno.costa@tecnologia.com', 'senha123', 0, 'brunocosta', 'TI', 103),
('fernanda.silveira@engenharia.com', 'senha123', 0, 'fernandasilveira', 'Engenharia', 101);

-- Inserir Funcionários (todo usuário é funcionário)
INSERT INTO Funcionario (fk_users_id_user, nome) VALUES
(1, 'João Silva'),
(2, 'Maria Alves'),
(3, 'Pedro Lima'),
(4, 'Ana Santos'),
(5, 'Carlos Moura'),
(6, 'Laura Pereira'),
(7, 'Ricardo Ferreira'),
(8, 'Paula Rodrigues'),
(9, 'Bruno Costa'),
(10, 'Fernanda Silveira');

-- Inserir Gestores (gerentes de grupos)
INSERT INTO Gestor (id_gestor, grupo_gerido) VALUES
(1, 'Engenharia Civil'),
(4, 'Projetos de Consultoria'),
(3, 'Equipe de TI');

-- Inserir Atividades típicas
INSERT INTO Atividade (descricao, interna) VALUES
('Análise estrutural de edifícios', 0),
('Desenvolvimento de software interno', 1),
('Consultoria em processos empresariais', 0),
('Manutenção de servidores', 1),
('Reunião de alinhamento de projeto', 1),
('Visita técnica ao cliente', 0),
('Treinamento de segurança do trabalho', 1),
('Criação de documentação técnica', 1),
('Análise de requisitos do cliente', 0),
('Implementação de sistemas', 0);

-- Inserir Feriados nacionais e da empresa
INSERT INTO Feriado (id_feriado, nome_feriado, data_feriado, permite_lancamento) VALUES
(1, 'Ano Novo', '2025-01-01', 0),
(2, 'Tiradentes', '2025-04-21', 0),
(3, 'Dia do Trabalho', '2025-05-01', 0),
(4, 'Independência do Brasil', '2025-09-07', 0),
(5, 'Nossa Senhora Aparecida', '2025-10-12', 0),
(6, 'Finados', '2025-11-02', 0),
(7, 'Proclamação da República', '2025-11-15', 0),
(8, 'Natal', '2025-12-25', 0),
(9, 'Aniversário da Empresa', '2025-06-10', 1);

-- Inserir Escalas de Trabalho
INSERT INTO Escala_de_Trabalho (horas_semanais, dias_semana) VALUES
(40, 'Segunda,Terça,Quarta,Quinta,Sexta'),
(30, 'Segunda,Quarta,Sexta'),
(20, 'Terça,Quinta'),
(40, 'Segunda,Terça,Quarta,Quinta,Sexta');

-- Associar Funcionários às Escalas
INSERT INTO Funcionario_Escala (fk_Funcionario_id_funcionario, fk_Escala_de_Trabalho_id_escala_de_trabalho) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 1),
(5, 3),
(6, 2),
(7, 4),
(8, 3),
(9, 4),
(10, 1);

-- Inserir Clientes
INSERT INTO Cliente (nome_cliente, rua, numero, bairro, cidade, estado, CEP, FAX, telefone, email) VALUES
('Construtora Alfa', 'Rua das Flores', '123', 'Jardim Primavera', 'São Paulo', 'SP', '01010-000', '1133334444', '11988887777', 'contato@alfaconstrucoes.com'),
('Consultoria Beta', 'Av. Central', '456', 'Centro', 'Rio de Janeiro', 'RJ', '20020-000', null, '21999998888', 'beta@consultoria.com'),
('Tech Solutions', 'Rua da Tecnologia', '789', 'Bela Vista', 'Campinas', 'SP', '13010-000', '1933332222', '1933222111', 'suporte@techsolutions.com'),
('Engenharia Delta', 'Alameda Santos', '321', 'Jardim América', 'São Paulo', 'SP', '01419-000', null, '1133445566', 'contato@engenhariadelta.com'),
('Consultoria Gama', 'Rua das Acácias', '654', 'Vila Mariana', 'São Paulo', 'SP', '04108-000', null, '1133665544', 'contato@gama.com');

-- Inserir Contratos (ligando cliente, funcionário e gestor)
INSERT INTO Contrato (obj_contratado, data_inicio, data_fim, estado, fk_Cliente_id_cliente, fk_Funcionario_id_funcionario, fk_Gestor_id_gestor) VALUES
('Projeto estrutural para edifício comercial', '2025-01-15', '2025-07-15', 'Ativo', 1, 1, 1),
('Consultoria em otimização de processos', '2025-02-01', '2025-06-30', 'Ativo', 2, 2, 4),
('Desenvolvimento de sistema ERP', '2025-03-01', '2025-12-31', 'Ativo', 3, 3, 3),
('Reforma estrutural industrial', '2025-01-20', '2025-08-20', 'Ativo', 4, 7, 1),
('Auditoria e consultoria financeira', '2025-04-01', '2025-09-30', 'Ativo', 5, 5, 4);

-- Inserir Apontamentos (registro de horas e atividades)
INSERT INTO Apontamento (hora_inicio, hora_fim, centro_de_custo, data_apontamento, data_preenchimento, aprovado, fk_Atividade_id_atividade, fk_Funcionario_id_funcionario, fk_Feriado_id_feriado) VALUES
('08:00:00', '12:00:00', '101', '2025-05-20', '2025-05-20', 1, 1, 1, NULL),
('13:00:00', '17:00:00', '102', '2025-05-20', '2025-05-20', 1, 3, 2, NULL),
('09:00:00', '18:00:00', '103', '2025-05-20', '2025-05-20', 0, 2, 3, NULL),
('08:30:00', '12:30:00', '101', '2025-05-20', '2025-05-20', 1, 5, 4, NULL),
('13:00:00', '17:30:00', '102', '2025-05-20', '2025-05-20', 1, 6, 5, NULL),
('08:00:00', '12:00:00', '103', '2025-05-20', '2025-05-20', 1, 7, 6, NULL),
('13:00:00', '18:00:00', '101', '2025-05-20', '2025-05-20', 1, 1, 7, NULL),
('08:00:00', '11:00:00', '102', '2025-05-20', '2025-05-20', 1, 9, 8, NULL),
('11:30:00', '17:30:00', '103', '2025-05-20', '2025-05-20', 0, 10, 9, NULL),
('08:00:00', '12:00:00', '101', '2025-05-20', '2025-05-20', 1, 4, 10, NULL);
