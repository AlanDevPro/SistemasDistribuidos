/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockettcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */

import java.io.*;
import java.net.*;

public class ServerHolaMundo {
    public static void main(String[] args) {
        int puerto = 5555;
        
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto + "...");

            while (true) {
                try (Socket socket = servidor.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("Cliente conectado.");

                    // Recibir la palabra
                    String palabra = entrada.readLine();
                    System.out.println("Palabra recibida: " + palabra);

                    // Invertir la palabra
                    String palabraInvertida = new StringBuilder(palabra).reverse().toString();

                    // Enviar la respuesta
                    salida.println(palabraInvertida);
                    System.out.println("Palabra invertida enviada: " + palabraInvertida);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
