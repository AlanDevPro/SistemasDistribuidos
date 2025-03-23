/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author luisc
 */
public class ServidorSeduca {
    public static void main(String[] args) {
        int port = 5002;
        ServerSocket server;
        String rude = "";
        String mensaje = "";
        
        try {
            server = new ServerSocket(port);
            System.out.println("Servidor SEDUCA iniciado en puerto " + port);
            
            while (true) {
                Socket client;
                PrintStream toClient;
                client = server.accept(); // Conexión entre cliente y servidor para comunicación bidireccional
                
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("Cliente se conectó al servidor SEDUCA");
                
                String operacion = fromClient.readLine(); // 'verificar-rude'
                String[] comandos = operacion.split("-");
                
                if (comandos.length == 2 && comandos[0].equals("verificar")) {
                    rude = comandos[1];
                    System.out.println("SEDUCA: Verificando RUDE: " + rude);
                    
                    // Lógica para verificar si tiene título de bachiller
                    // En este ejemplo, verificamos si RUDE comienza con 'W' o 'S'
                    if (rude.startsWith("W") || rude.startsWith("S")) {
                        mensaje = "si"; // Tiene título de bachiller
                    } else {
                        mensaje = "no"; // No tiene título de bachiller
                    }
                    
                    System.out.println("SEDUCA: Resultado de verificación RUDE: " + mensaje);
                } else {
                    mensaje = "error en formato de solicitud";
                }
                
                System.out.println("SEDUCA: Mensaje recibido: " + operacion);
                
                toClient = new PrintStream(client.getOutputStream());
                toClient.println("respuesta:" + mensaje);
                client.close();
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

}
    

