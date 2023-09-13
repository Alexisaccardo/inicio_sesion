import java.sql.*;
import java.util.Scanner;

public class Iniciosesion {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("*****BIENVENIDOS*****");

        boolean inicio = true;

        while (inicio) {

            System.out.println("1. Iniciar sesion: ");
            System.out.println("2. Cerrar sesion: ");
            System.out.println("3. Contrataciones: ");
            System.out.println("4. Alteracion en contratacion: ");
            System.out.println("5. Terminar");

            System.out.println("Ingrese un numero entre 1 - 5: ");
            int respuesta = Integer.parseInt(scanner.nextLine());

            switch (respuesta) {

                case 1:
                    System.out.println("Ingrese el correo: ");
                    String correoini = scanner.nextLine();

                    System.out.println("Ingrese su password: ");
                    String password = scanner.nextLine();

                    Editar_sesion2(correoini, password);

                    break;

                case 2:
                    System.out.println("Ingrese el correo: ");
                    correoini = scanner.nextLine();

                    Editar_sesion(correoini);
                    break;

                case 3:

                    System.out.print("Ingrese su cedula: ");
                    String cedula = scanner.nextLine();

                    System.out.print("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese su estado: ");
                    String estado = scanner.nextLine();

                    System.out.print("Ingrese su celular: ");
                    String celular = scanner.nextLine();

                    System.out.println("Ingrese el rango asignado: ");
                    String rango = scanner.nextLine();

                    Insert(cedula, nombre, estado, celular, rango); //

                    break;

                case 4:
                    System.out.println("Ingrese el numero de cedula: ");
                    cedula = scanner.nextLine();

                    System.out.println("Actualice el estado del contrato: ");
                    estado = scanner.nextLine();

                    Editar_estado(cedula, estado);
                    break;

                case 5:

                    System.out.println("Finalizando...");

                    inicio = false;

                    break;

                default:
                    System.out.println("Ingrese un numero valido");


            }
        }
    }

    private static void Editar_sesion2(String correoini, String password) throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/sesion";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE usuario SET sesion = ? WHERE correo = ? AND clave = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, "sesion iniciada");
        preparedStatement.setString(2, correoini);
        preparedStatement.setString(3, password);


        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Se inicio sesión manera exitosa");
        } else {
            System.out.println("No se pudo iniciar sesion, correo o password invalidos");
        }

        preparedStatement.close();
        connection2.close();

    }

    private static void Editar_sesion(String correoini) throws SQLException, ClassNotFoundException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/sesion";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE usuario SET sesion = ? WHERE correo = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, "Sesion cerrada");
        preparedStatement.setString(2, correoini);


        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Se cerró sesión manera exitosa");
        } else {
            System.out.println("No se pudo cerrar sesion");
        }

        preparedStatement.close();
        connection2.close();

    }

    private static void Editar_estado(String cedula, String estado) throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/sesion";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE contratos SET estado = ? WHERE cedula = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, estado);
        preparedStatement.setString(2, cedula);


        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Se actualizo el estado de manera exitosa");
        } else {
            System.out.println("No se encontró un numero de documento para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }

    private static void Insert(String cedula, String nombre, String estado, String celular, String rango) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sesion";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contratos");


            // Sentencia INSERT
            String sql = "INSERT INTO contratos (cedula, nombre, estado, celular, rango) VALUES (?, ?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, estado);
            preparedStatement.setString(4, celular);
            preparedStatement.setString(5, rango);

            // Ejecutar la sentencia
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Empleado agregado exitosamente.");
            } else {
                System.out.println("No se pudo agregar el empleado.");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void Editar(String sesionini, String correoini) throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/sesion";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE usuario SET sesion = ? WHERE correo = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, sesionini);
        preparedStatement.setString(2, correoini);


        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Inicio sesion de manera exitosa");
        } else {
            System.out.println("No se encontró un correo registrado");
        }

        preparedStatement.close();
        connection2.close();
    }

    private static void Select_One(String correoini) throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sesion";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM usuario WHERE correo = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, correoini); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            String correo = resultSet.getString("correo");
            String contraseña = resultSet.getString("contraseña");
            String sesion = resultSet.getString("sesion");


            System.out.println("Este es su correo: " + correo + " contraseña: " + contraseña);

        } else {
            System.out.println("No se encontró un registro con el codigo especificado.");
        }

        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
    }
}