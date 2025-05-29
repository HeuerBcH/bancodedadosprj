-- Usa o banco estrategica
USE estrategica;

-- Criação da tabela de log para funcionários, se não existir
CREATE TABLE IF NOT EXISTS LogFuncionario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_funcionario INT,
    nome_funcionario VARCHAR(45),
    usuario_email VARCHAR(100),
    data_log DATETIME
);

-- Remove trigger antiga, se existir
DROP TRIGGER IF EXISTS trg_criar_funcionario_apos_usuario;

-- Remove trigger antiga de log, se existir
DROP TRIGGER IF EXISTS trg_log_funcionario_insert;

-- Trigger que cria um funcionário automaticamente após inserir um usuário
DELIMITER //
CREATE TRIGGER trg_criar_funcionario_apos_usuario
AFTER INSERT ON Users
FOR EACH ROW
BEGIN
    -- Insere funcionário vinculado ao usuário criado
    INSERT INTO Funcionario (fk_users_id_user, nome)
    VALUES (NEW.id_user, NEW.username);
END;
//
DELIMITER ;

-- Trigger que registra no log toda criação de funcionário
DELIMITER //
CREATE TRIGGER trg_log_funcionario_insert
AFTER INSERT ON Funcionario
FOR EACH ROW
BEGIN
    DECLARE v_email VARCHAR(100);
    
    -- Busca o email do usuário relacionado ao funcionário
    SELECT email INTO v_email FROM Users WHERE id_user = NEW.fk_users_id_user;
    
    -- Insere registro na tabela de log
    INSERT INTO LogFuncionario (id_funcionario, nome_funcionario, usuario_email, data_log)
    VALUES (NEW.id_funcionario, NEW.nome, v_email, NOW());
END;
//
DELIMITER ;
