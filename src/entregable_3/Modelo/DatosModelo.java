package entregable_3.Modelo;

import entregable_3.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosModelo {

    

    public void cargarDatosIniciales() throws SQLException {
        try (Connection con = ConexionBD.getConnection()) {
            // Insertar datos iniciales en la tabla MONEDA
            String sqlMoneda = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK, NOMBRE_ICONO) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sqlMoneda)) {
            	
            	pstmt.setString(1, "C");
                pstmt.setString(2, "Bitcoin");
                pstmt.setString(3, "BTC");
                pstmt.setDouble(4, 35000.00);
                pstmt.setDouble(5, 0.05);
                pstmt.setDouble(6, 21000000);
                pstmt.setString(7, "bitcoin_icon.png");
                pstmt.executeUpdate();

                
                pstmt.setString(1, "F");
                pstmt.setString(2, "Dólar Estadounidense");
                pstmt.setString(3, "USD");
                pstmt.setDouble(4, 1.00);
                pstmt.setDouble(5, 0.0);
                pstmt.setDouble(6, 1000000000);
                pstmt.setString(7, "usd_icon.png");
                pstmt.executeUpdate();
            }

            // Insertar datos iniciales en la tabla PERSONA
            String sqlPersona = "INSERT INTO PERSONA (NOMBRES, APELLIDOS) VALUES (?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sqlPersona)) {
                pstmt.setString(1, "Juan");
                pstmt.setString(2, "Pérez");
                pstmt.executeUpdate();

                pstmt.setString(1, "Ana");
                pstmt.setString(2, "Gómez");
                pstmt.executeUpdate();
            }

            // Insertar datos iniciales en la tabla USUARIO
            String sqlUsuario = "INSERT INTO USUARIO (ID_PERSONA, EMAIL, PASSWORD, ACEPTA_TERMINOS) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sqlUsuario)) {
                pstmt.setInt(1, 1);
                pstmt.setString(2, "juanperez@gmail.com");
                pstmt.setString(3, "1234");
                pstmt.setBoolean(4, true);
                pstmt.executeUpdate();

                pstmt.setInt(1, 2);
                pstmt.setString(2, "anagomez@gmail.com");
                pstmt.setString(3, "1234");
                pstmt.setBoolean(4, true);
                pstmt.executeUpdate();
            }
        }
    }
}
