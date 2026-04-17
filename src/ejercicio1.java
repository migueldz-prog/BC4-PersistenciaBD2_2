import java.sql.*;
import java.util.Scanner;

public class ejercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {


            String sql = " select ID_CICLISTA, CICLISTA.NOMBRE as NOMBRE, CICLISTA.NACIONALIDAD as NACIONALIDAD, CICLISTA.EDAD as EDAD, EQUIPO.NOMBRE AS EQUIPO " +
                    " FROM ciclista JOIN EQUIPO USING (ID_EQUIPO) " +
                    " WHERE NACIONALIDAD = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println("Nacionalidad para filtrar a los ciclistas: ");
            String nacionalidad = sc.nextLine();   //pedimos la nacionalidad por la cual filtrar que sustituira a la ?

            ps.setString(1,nacionalidad);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {    //aqui introducimos la salida de la sentencia en variables para el print
                int id_ciclista=rs.getInt("ID_CICLISTA");
                String nombre=rs.getString("NOMBRE");
                String nnacionalidad=rs.getString("NACIONALIDAD");
                int edad=rs.getInt("EDAD");
                String equipo=rs.getString("EQUIPO");

                System.out.println(id_ciclista + " - " + nombre + " - " +
                        nnacionalidad + " - " + edad + " - " + equipo );
            }


        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}