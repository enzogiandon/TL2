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
            	//BITCOIN ID = 1
            	pstmt.setString(1, "C");
                pstmt.setString(2, "Bitcoin");
                pstmt.setString(3, "BTC");
                pstmt.setDouble(4, 101559);
                pstmt.setDouble(5, 0.05);
                pstmt.setDouble(6, 10000);
                pstmt.setString(7, "bitcoin_icon.png");
                pstmt.executeUpdate();
                //ETHEREUM ID = 2
                pstmt.setString(1, "C");
                pstmt.setString(2, "Ethereum");
                pstmt.setString(3, "ETH");
                pstmt.setDouble(4, 3884.86);
                pstmt.setDouble(5, 0.7); //70%
                pstmt.setDouble(6, 1000000);
                pstmt.setString(7, "ethereum_icon.png");
                pstmt.executeUpdate();
                //USDC ID = 3
                pstmt.setString(1, "C");
                pstmt.setString(2, "USD Coin");
                pstmt.setString(3, "USDC");
                pstmt.setDouble(4, 0.999698);
                pstmt.setDouble(5, 0.001); 
                pstmt.setDouble(6, 1000000);
                pstmt.setString(7, "usdcoin_icon.png");
                pstmt.executeUpdate();
                //USDT ID = 4
                pstmt.setString(1, "C");
                pstmt.setString(2, "Tether");
                pstmt.setString(3, "USDT");
                pstmt.setDouble(4, 0.99953);
                pstmt.setDouble(5, 0.01); //1%
                pstmt.setDouble(6, 1000000);
                pstmt.setString(7, "tether_icon.png");
                pstmt.executeUpdate();
                //DOGE ID = 5
                pstmt.setString(1, "C");
                pstmt.setString(2, "Doge Coin");
                pstmt.setString(3, "DOGE");
                pstmt.setDouble(4, 0.39832);
                pstmt.setDouble(5, 0.2); //20%
                pstmt.setDouble(6, 1000000);
                pstmt.setString(7, "dogecoin_icon.png");
                pstmt.executeUpdate();
                //USD ID = 6
                pstmt.setString(1, "F");
                pstmt.setString(2, "Dólar Estadounidense");
                pstmt.setString(3, "USD");
                pstmt.setDouble(4, 1.00);
                pstmt.setDouble(5, 0.0);
                pstmt.setDouble(6, 1000000000);
                pstmt.setString(7, "usd_icon.png");
                pstmt.executeUpdate();
                //ARS ID = 7
                pstmt.setString(1, "F");
                pstmt.setString(2, "Peso Argentino");
                pstmt.setString(3, "ARS");
                pstmt.setDouble(4, 0.00098);
                pstmt.setDouble(5, 0.5); //50%?
                pstmt.setDouble(6, 500000);
                pstmt.setString(7, "ars_icon.png");
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
                
                pstmt.setString(1, "Enzo");
                pstmt.setString(2, "Giandon");
                pstmt.executeUpdate();
                
                pstmt.setString(1, "Martina");
                pstmt.setString(2, "Hübler");
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
                
                pstmt.setInt(1, 3);
                pstmt.setString(2, "enzogiandon@gmail.com");
                pstmt.setString(3, "1234");
                pstmt.setBoolean(4, true);
                pstmt.executeUpdate();
                
                pstmt.setInt(1, 4);
                pstmt.setString(2, "martinahubler@gmail.com");
                pstmt.setString(3, "1234");
                pstmt.setBoolean(4, true);
                pstmt.executeUpdate();
            }
        
            
            // Insertar datos iniciales en la tabla ACTIVO_CRIPTO
            String sqlCripto = "INSERT INTO ACTIVO_CRIPTO (ID_USUARIO, ID_MONEDA, CANTIDAD) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sqlCripto)) {
                // Juan Pérez tiene 0.4 BTC y 3 DOGE
                pstmt.setInt(1, 1); //ID DE JUAN
                pstmt.setInt(2, 1);  // ID de Bitcoin
                pstmt.setDouble(3, 0.4);
                pstmt.executeUpdate();
                pstmt.setInt(1, 1); //ID DE JUAN
                pstmt.setInt(2, 5);  // ID de DOGE
                pstmt.setDouble(3, 3);
                pstmt.executeUpdate();

                // Ana Gómez tiene 1.1 BTC
                pstmt.setInt(1, 2); //ID ANA
                pstmt.setInt(2, 1);  // ID de Bitcoin
                pstmt.setDouble(3, 1.1);
                pstmt.executeUpdate();
                
             // Enzo tiene 0.912 BTC; 2 ETH y 89 USDC
                pstmt.setInt(1, 3); //ID ENZO
                pstmt.setInt(2, 1);  // ID de Bitcoin
                pstmt.setDouble(3, 0.912);
                pstmt.executeUpdate();
                pstmt.setInt(1, 3); //ID ENZO
                pstmt.setInt(2, 2);  // ID de Ethereum
                pstmt.setDouble(3, 2);
                pstmt.executeUpdate();
                pstmt.setInt(1, 3); //ID ENZO
                pstmt.setInt(2, 3);  // ID de USD coin
                pstmt.setDouble(3, 89);
                pstmt.executeUpdate();
                
             // Martina Hubler tiene 0.5 BTC
                pstmt.setInt(1, 4); //ID MARTINA
                pstmt.setInt(2, 1);  // ID de Bitcoin
                pstmt.setDouble(3, 0.5);
                pstmt.executeUpdate();
            }

            // Insertar datos iniciales en la tabla ACTIVO_FIAT
            String sqlFiat = "INSERT INTO ACTIVO_FIAT (ID_USUARIO, ID_MONEDA, CANTIDAD) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sqlFiat)) {
                // Juan Pérez tiene 200 USD
                pstmt.setInt(1, 1); //ID DE JUAN
                pstmt.setInt(2, 6);  // ID de USD
                pstmt.setDouble(3, 200);
                pstmt.executeUpdate();

                // Ana Gómez tiene 300 USD
                pstmt.setInt(1, 2); //ID ANA
                pstmt.setInt(2, 6);  // ID de USD
                pstmt.setDouble(3, 300);
                pstmt.executeUpdate();
                
             // Enzo tiene 9888.2 USD y 4 ARS
                pstmt.setInt(1, 3); //ID ENZO
                pstmt.setInt(2, 6);  // ID de USD
                pstmt.setDouble(3, 9888.2);
                pstmt.executeUpdate();
                pstmt.setInt(1, 3); //ID ENZO
                pstmt.setInt(2, 7);  // ID de USD
                pstmt.setDouble(3, 4);
                pstmt.executeUpdate();
                
             // Martina Hubler tiene 40.3 USD y 6000 ARS
                pstmt.setInt(1, 4); //ID MARTI
                pstmt.setInt(2, 6);  // ID de USD
                pstmt.setDouble(3, 40.3);
                pstmt.executeUpdate();
                pstmt.setInt(1, 4); //ID MARTI
                pstmt.setInt(2, 7);  // ID de USD
                pstmt.setDouble(3, 6000);
                pstmt.executeUpdate();
                
            }
        }
    }

   
        
    
}