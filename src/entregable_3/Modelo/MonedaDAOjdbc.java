package entregable_3.Modelo;
import entregable_3.*;
import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class MonedaDAOjdbc implements MonedaDAO {

	@Override
	public boolean guardarMoneda(Moneda moneda) {
		String sql = "INSERT INTO MONEDA (TIPO, ID, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK, NOMBRE_ICONO) VALUES (?,?,?,?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			if (moneda instanceof Fiat)
				pst.setString(1, String.valueOf('F'));
			else if (moneda instanceof Cripto)
				pst.setString(1, String.valueOf('C'));
			else
				return false;

			pst.setInt(2, moneda.getId());
			pst.setString(3, moneda.getNombre());
			pst.setString(4, moneda.getCodigo());
			pst.setDouble(5, moneda.getPrecioEnDolar());
			pst.setDouble(6,moneda.getVolatilidad());
			pst.setDouble(7, moneda.getStock());
			
			// PROVISORIO, CAMBIAR
			pst.setString(8, moneda.getCodigo());
			
			pst.executeUpdate();
			System.out.println("Se ha guardado exitosamente la moneda " + moneda.getCodigo() + " en la base de datos.");
			
			pst.close();
			con.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Moneda> listarMonedas() {
		List<Moneda> monedas = new ArrayList<>();
		String sql = "SELECT * FROM MONEDA";

		try {
			Connection con = ConexionBD.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Moneda m = new Moneda();
				m.setId(rs.getInt(1));
				m.setNombre(rs.getString(3));
				m.setCodigo(rs.getString(4));
				m.setPrecioEnDolar(rs.getDouble(5));
				m.setVolatilidad(6);
				m.setStock(rs.getDouble(7));
				monedas.add(m);
			}
			pst.close();
			con.close();
			return monedas;
		} catch (SQLException e) {
			System.out.println("Error de SQL al listar monedas: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Moneda getMoneda(String nomenclatura) throws SQLException {
		String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA = ?";
		Moneda moneda = null;

		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nomenclatura.toUpperCase());
			ResultSet rs = pst.executeQuery();

			if (rs.next())
				moneda = crearMoneda(rs);
			
			pst.close();
			con.close();
		}
		return moneda;
	}
	
	private Moneda crearMoneda(ResultSet rs) throws SQLException {
		String tipo = rs.getString("TIPO");
		if (!tipo.equals("F") && !tipo.equals("C"))
			return null;
		
		int id = rs.getInt("ID");
	    String nomenclatura = rs.getString("NOMENCLATURA");
	    double valorDolar = rs.getDouble("VALOR_DOLAR");
	    String nombre = rs.getString("NOMBRE");
	    double stock = rs.getDouble("STOCK");
	    double volatilidad = rs.getDouble("VOLATILIDAD");

	    switch (tipo) {
	        case "F":
	            return new Fiat(id,nomenclatura, valorDolar, nombre, stock, volatilidad);
	        case "C":
	            return new Cripto(id,nomenclatura, valorDolar, nombre, stock, volatilidad);
	        default:
	            throw new IllegalArgumentException("Tipo desconocido: " + tipo);
	    }
	}

	

	@Override
	public int stockRandomizer() {
		String sql = "UPDATE MONEDA SET STOCK = FLOOR(1 + (RAND() * 1000))";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			
			int generado = pst.executeUpdate();
			System.out.println("Stock generado");
			pst.close();
			con.close();
			return generado;
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
}
