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
                    String email = scanner.nextLine();

                    System.out.println("Ingrese su password: ");
                    String password = scanner.nextLine();

                    Editar_sesion2(email, password);

                    break;

                case 2:
                    System.out.println("Ingrese el correo: ");
                    email = scanner.nextLine();

                    Editar_sesion(email);
                    break;

                case 3:

                    System.out.print("Ingrese su cedula: ");
                    String identification = scanner.nextLine();

                    System.out.print("Ingrese su nombre: ");
                    String name = scanner.nextLine();

                    System.out.print("Ingrese su estado: ");
                    String state = scanner.nextLine();

                    System.out.print("Ingrese su celular: ");
                    String cellphone = scanner.nextLine();

                    System.out.println("Ingrese el rango asignado: ");
                    String range = scanner.nextLine();

                    Insert(identification, name, state, cellphone, range); //

                    break;

                case 4:
                    System.out.println("Ingrese el numero de cedula: ");
                    identification = scanner.nextLine();

                    System.out.println("Actualice el estado del contrato: ");
                    state = scanner.nextLine();

                    Editar_estado(identification, state);
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

    private static void Editar_sesion2(String email, String password) throws ClassNotFoundException, SQLException {
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
        preparedStatement.setString(2, email);
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

    private static void Editar_sesion(String email) throws SQLException, ClassNotFoundException {

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
        preparedStatement.setString(2, email);


        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Se cerró sesión manera exitosa");
        } else {
            System.out.println("No se pudo cerrar sesion");
        }

        preparedStatement.close();
        connection2.close();

    }

    private static void Editar_estado(String identification, String state) throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/sesion";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE contratos SET estado = ? WHERE cedula = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, state);
        preparedStatement.setString(2, identification);


        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Se actualizo el estado de manera exitosa");
        } else {
            System.out.println("No se encontró un numero de documento para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }

    private static void Insert(String identification, String name, String state, String cellphone, String range) {

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
            preparedStatement.setString(1, identification);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, state);
            preparedStatement.setString(4, cellphone);
            preparedStatement.setString(5, range);

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

    private static void Editar(String session, String email) throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/sesion";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE usuario SET sesion = ? WHERE correo = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, session);
        preparedStatement.setString(2, email);


        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Inicio sesion de manera exitosa");
        } else {
            System.out.println("No se encontró un correo registrado");
        }

        preparedStatement.close();
        connection2.close();
    }

    private static void Select_One(String email) throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sesion";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM usuario WHERE correo = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, email); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            email = resultSet.getString("correo");
            password = resultSet.getString("contraseña");
            String session = resultSet.getString("sesion");


            System.out.println("Este es su correo: " + email + " contraseña: " + password);

        } else {
            System.out.println("No se encontró un registro con el codigo especificado.");
        }

        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
    }
}