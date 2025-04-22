/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockettcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Dell
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteHolaMundo {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5555;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Ingresa una palabra: ");
            String palabra = scanner.nextLine();

            // Enviar la palabra al servidor
            salida.println(palabra);

            // Recibir la respuesta
            String respuesta = entrada.readLine();
            System.out.println("Palabra invertida desde el servidor: " + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
