/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenpacial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author TODO LAPTOP
 */
public class BCP {
    private static final String HOST = "localhost";
    private static final int PUERTO = 6000; // Puerto del banco BCP

    public static String buscarCuenta(String ci, String nombres, String apellidos) {
        String mensaje = "Buscar:" + ci + "-" + nombres + "-" + apellidos;
        try (Socket socket = new Socket(HOST, PUERTO);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(mensaje);
            return in.readLine();
        } catch (Exception e) {
            return "";
        }
    }

   public static String retenerMonto(String mensaje) {
        try (Socket socket = new Socket("localhost", 6000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(mensaje);
            return in.readLine();
        } catch (Exception e) {
            return "No-no encontrado";
        }
    }
}
