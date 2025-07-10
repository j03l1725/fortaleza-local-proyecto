// fortaleza-local-proyecto/src/main/java/com/miproyecto/seguro/HealthCheckServlet.java
package com.miproyecto.seguro;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/health")
public class HealthCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"status\":\"OK\", \"message\":\"Fortaleza en la Nube operativa\"}");
        out.flush();
    }
}
