// fortaleza-local-proyecto/src/main/java/com/miproyecto/seguro/VisitCounterServlet.java
package com.miproyecto.seguro;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/")
public class VisitCounterServlet extends HttpServlet {

    // --- MÉTODO MODIFICADO ---
    // Añadimos un bucle de reintento para dar tiempo a la base de datos a inicializarse.
    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");

        String dbHost = System.getenv("DB_HOST");
        String dbName = System.getenv("DB_NAME");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");
        String url = "jdbc:postgresql://" + dbHost + "/" + dbName;

        int maxRetries = 10; // Número máximo de intentos
        int retryDelay = 2000; // 2 segundos de espera entre intentos
        
        for (int i = 0; i < maxRetries; i++) {
            try {
                // Intenta conectar
                return DriverManager.getConnection(url, dbUser, dbPassword);
            } catch (Exception e) {
                System.out.println("Fallo al conectar a la base de datos. Intento " + (i + 1) + "/" + maxRetries);
                // Si no es el último intento, espera antes de reintentar
                if (i < maxRetries - 1) {
                    Thread.sleep(retryDelay);
                } else {
                    // Si es el último intento, lanza la excepción
                    throw e;
                }
            }
        }
        // Este punto no debería alcanzarse, pero es requerido por el compilador
        throw new RuntimeException("No se pudo conectar a la base de datos después de " + maxRetries + " intentos.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        long visitCount = 0;

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("UPDATE visits SET count = count + 1 WHERE id = 1");
            
            ResultSet rs = stmt.executeQuery("SELECT count FROM visits WHERE id = 1");
            if (rs.next()) {
                visitCount = rs.getLong("count");
            }

            out.println("<html><body style='font-family: sans-serif; text-align: center; margin-top: 50px;'>");
            out.println("<h1>Fortaleza Local v2 Operativa</h1>");
            out.println("<h2>N&uacute;mero de Visitas: " + visitCount + "</h2>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<html><body><h1>Error de Conexi&oacute;n a la Base de Datos</h1>");
            out.println("<p>" + e.getMessage() + "</p></body></html>");
            e.printStackTrace(out); // Imprime el error en la página para un mejor diagnóstico
        }
    }
}
