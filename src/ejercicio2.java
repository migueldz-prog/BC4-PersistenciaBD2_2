import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class ejercicio2 {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        Scanner sc = new Scanner(System.in);
        int opcion;

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {

            do {
                System.out.println("1. Insertar un nuevo ciclista");            //mostrarmos el menu
                System.out.println("2. Actualizar un ciclista existente");
                System.out.println("3. Eliminar un ciclista");
                System.out.println("0. Salir");
                System.out.print("Elige una opcion: ");
                opcion = sc.nextInt();

                switch (opcion) {


                    case 1:

                        int idCiclista=0;

                        sc.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Nacionalidad: ");
                        String nac = sc.nextLine();
                        System.out.print("Edad: ");
                        int edad = sc.nextInt();
                        System.out.print("ID equipo (1-5): ");
                        int idE = sc.nextInt(); //rellenar datos del nuevo ciclista

                        String sql2 = "SELECT MAX(ID_CICLISTA) AS IDMAX FROM CICLISTA";  //hallamos el id maximo en la tabla y le sumamos 1
                        ResultSet rs = conexion.createStatement().executeQuery(sql2);
                        while(rs.next()){
                            idCiclista=rs.getInt("IDMAX") + 1;
                        }

                        String sqlI = "INSERT INTO CICLISTA (ID_CICLISTA,NOMBRE, NACIONALIDAD, EDAD, ID_EQUIPO) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement psInsert = conexion.prepareStatement(sqlI);
                        psInsert.setInt(1, idCiclista);             //indico las variables que voy a meter en el insert
                        psInsert.setString(2, nombre);
                        psInsert.setString(3, nac);
                        psInsert.setInt(4, edad);
                        psInsert.setInt(5, idE);


                        int filasInsertadas = psInsert.executeUpdate();
                        System.out.println("Filas insertadas: " + filasInsertadas);
                        break;

                    case 2:
                        System.out.print("ID del ciclista: ");
                        int idActu = sc.nextInt();
                        System.out.print("Nuevo equipo: ");
                        int equipoV = sc.nextInt();
                        System.out.print("Nueva edad: ");
                        int nuevoSalario = sc.nextInt();
                        //PIDO EL ID Y LOS LOS NUEVOS DATOS PARA HACER EL UPDATE CON LA SQL

                        String sqlU = "UPDATE CICLISTA SET ID_EQUIPO = ?, EDAD= ? WHERE id_ciclista = ?";
                        PreparedStatement psUpdate = conexion.prepareStatement(sqlU);
                        psUpdate.setInt(1, equipoV);
                        psUpdate.setInt(2, nuevoSalario);
                        psUpdate.setInt(3, idActu);

                        int filasActualizadas = psUpdate.executeUpdate();
                        System.out.println("Filas actualizadas: " + filasActualizadas);

                        break;


                    case 3:
                        System.out.print("ID del ciclista a eliminar: ");
                        int idEliminar = sc.nextInt();

                        String sqlD = "DELETE FROM CICLISTA WHERE id_ciclista = ?";
                        PreparedStatement psDelete = conexion.prepareStatement(sqlD);
                        psDelete.setInt(1, idEliminar);

                        String sqlD2 = "DELETE FROM PARTICIPACION  WHERE id_ciclista = ?";
                        PreparedStatement psDelet = conexion.prepareStatement(sqlD2);

                        //aqui usamos dos sentencias para borra los datos de ambas tablas

                        int filasEliminadas = psDelete.executeUpdate();
                        System.out.println("Filas eliminadas: " + filasEliminadas);
                        break;

                    case 0:
                        System.out.println("Saliendo");
                        break;

                    default:
                        System.out.println("Opcion no valida");
                }

            } while (opcion != 0);

        } catch (SQLException e) {
            System.out.println("Error al conectar " + e.getMessage());
        }

        sc.close();
    }
}