-- procedure_buscar_apontamentos_feriados.sql

DELIMITER $$

CREATE PROCEDURE BuscarApontamentosEmFeriados(
    IN aprovado_status TINYINT
)
BEGIN
    SELECT 
        f.id_funcionario,
        u.username AS nome_usuario,
        fer.nome_feriado,
        fer.data_feriado,
        ap.data_apontamento,
        ap.hora_inicio,
        ap.hora_fim,
        ap.aprovado
    FROM Apontamento ap
    INNER JOIN Funcionario f ON ap.fk_Funcionario_id_funcionario = f.id_funcionario
    INNER JOIN Users u ON f.fk_users_id_user = u.id_user
    INNER JOIN Feriado fer ON ap.fk_Feriado_id_feriado = fer.id_feriado
    WHERE ap.aprovado = aprovado_status;
END $$

DELIMITER ;
