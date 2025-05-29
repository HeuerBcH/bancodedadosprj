DELIMITER $$

CREATE PROCEDURE sp_registrar_apontamento (
    IN p_hora_inicio TIME,
    IN p_hora_fim TIME,
    IN p_centro_de_custo VARCHAR(30),
    IN p_data_apontamento DATE,
    IN p_data_preenchimento DATE,
    IN p_id_atividade INT,
    IN p_id_funcionario INT
)
BEGIN
    DECLARE v_id_feriado INT DEFAULT NULL;
    DECLARE v_permite_lancamento TINYINT DEFAULT 1;

    -- Verificar se o funcionário existe
    IF NOT EXISTS (SELECT 1 FROM Funcionario WHERE id_funcionario = p_id_funcionario) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Funcionário não encontrado.';
    END IF;

    -- Verificar se a atividade existe
    IF NOT EXISTS (SELECT 1 FROM Atividade WHERE id_atividade = p_id_atividade) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Atividade não encontrada.';
    END IF;

    -- Verificar se a data do apontamento é feriado
    SELECT id_feriado, permite_lancamento INTO v_id_feriado, v_permite_lancamento
    FROM Feriado
    WHERE data_feriado = p_data_apontamento
    LIMIT 1;

    -- Se não permite lançamento nesse feriado
    IF v_id_feriado IS NOT NULL AND v_permite_lancamento = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Não é permitido lançar apontamento nesse feriado.';
    END IF;

    -- Inserir apontamento
    INSERT INTO Apontamento (
        hora_inicio,
        hora_fim,
        centro_de_custo,
        data_apontamento,
        data_preenchimento,
        fk_Atividade_id_atividade,
        fk_Funcionario_id_funcionario,
        fk_Feriado_id_feriado
    ) VALUES (
        p_hora_inicio,
        p_hora_fim,
        p_centro_de_custo,
        p_data_apontamento,
        p_data_preenchimento,
        p_id_atividade,
        p_id_funcionario,
        v_id_feriado
    );

END$$

DELIMITER ;
