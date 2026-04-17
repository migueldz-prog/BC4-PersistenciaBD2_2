import java.sql.*;
import java.util.Scanner;


public class ejercicio3 {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        Scanner sc = new Scanner(System.in);
        int opcion;

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             Statement statement = conexion.createStatement()) {

            do {
                System.out.println("Elige una opcion: ");
                System.out.println("1- Clasificacion general");
                System.out.println("2- Clasificacion por equipos");
                System.out.println("3- Ranking 3 etapas mas largas");
                System.out.println("0- salir");
                opcion = sc.nextInt();

                switch (opcion) {
                    //con el switch elegimos cual de las 3 selects queremos ejecutar
                    //en todas ellas la salida de las sentencias se meten en variables para el print y lo sacamos en consola

                    case 1:

                        String sql = "SELECT CICLISTA.NOMBRE AS CICLISTA, EQUIPO.NOMBRE AS EQUIPO, SUM(PARTICIPACION.PUNTOS) AS PUNTOS_SUM " +
                                "FROM CICLISTA JOIN PARTICIPACION USING (ID_CICLISTA) " +
                                "JOIN EQUIPO USING (ID_EQUIPO) " +
                                "GROUP BY CICLISTA.NOMBRE, EQUIPO.NOMBRE " +
                                "ORDER BY PUNTOS_SUM DESC ";
                        ResultSet rs = statement.executeQuery(sql);

                        while (rs.next()) {

                            String ciclista = rs.getString("ciclista");
                            String equipo = rs.getString("equipo");
                            int puntos_sum = rs.getInt("puntos_sum");
                            System.out.println(ciclista + " - " + equipo + " - " + puntos_sum);
                        }
                        break;

                    case 2:

                        String sql2 = "SELECT EQUIPO.NOMBRE as EQUIPO, PAIS, SUM(PUNTOS) AS SUMAPUNTOS " +
                                "FROM CICLISTA JOIN EQUIPO USING(ID_EQUIPO) JOIN PARTICIPACION USING(ID_CICLISTA) " +
                                "GROUP BY EQUIPO.NOMBRE, PAIS " +
                                "ORDER BY SUMAPUNTOS DESC ";
                        ResultSet rs2 = statement.executeQuery(sql2);

                        while (rs2.next()) {

                            String equipo = rs2.getString("equipo");
                            String pais = rs2.getString("pais");
                            int SUMAPUNTOS = rs2.getInt("SUMAPUNTOS");
                            System.out.println(equipo + " - " + pais + " - " + SUMAPUNTOS);
                        }
                        break;


                    case 3:   //POR HACER

                        String sql3 = "SELECT * " +
                                "FROM ETAPA " +
                                "ORDER BY DISTANCIA_KM DESC " +
                                "FETCH FIRST 3 ROWS ONLY ";
                        ResultSet rs3 = statement.executeQuery(sql3);

                        System.out.println("numero - origen - destino - distancia_km - fecha ");
                        while (rs3.next()) {

                            int numero = rs3.getInt("numero");
                            String origen = rs3.getString("origen");
                            String destino = rs3.getString("destino");
                            int distancia_km = rs3.getInt("distancia_km");
                            Date fecha = rs3.getDate("fecha");
                            System.out.println(numero + " - " + origen + " - " + destino + " - " + distancia_km + " - " + fecha);
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo");
                        break;

                    default:
                        System.out.println("Opcion no valida");
                }

            } while (opcion != 0);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



