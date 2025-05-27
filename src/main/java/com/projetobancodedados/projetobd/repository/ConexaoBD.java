package com.projetobancodedados.projetobd.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/estrategica?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = System.getenv("DB_PASSWORD");
        return DriverManager.getConnection(url, user, password);
    }
}
