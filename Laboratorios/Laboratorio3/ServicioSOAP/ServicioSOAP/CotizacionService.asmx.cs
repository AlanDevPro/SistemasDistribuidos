using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using MySql.Data.MySqlClient;

namespace ServicioSOAP
{
    /// <summary>
    /// Descripción breve de CotizacionService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class CotizacionService : System.Web.Services.WebService
    {

        string connectionString = "server=localhost;user=root;password=;database=cotizaciones;";

        [WebMethod]
        public string obtenerCotizacion(string fecha)
        {
            try
            {
                using (MySqlConnection conn = new MySqlConnection(connectionString))
                {
                    conn.Open();
                    string query = "SELECT cotizacion, cotizacion_oficial FROM cotizacion WHERE fecha = @fecha";
                    MySqlCommand cmd = new MySqlCommand(query, conn);
                    cmd.Parameters.AddWithValue("@fecha", fecha);
                    var reader = cmd.ExecuteReader();

                    if (reader.Read())
                    {
                        return $"Cotización: {reader["cotizacion"]}, Oficial: {reader["cotizacion_oficial"]}";
                    }
                    else
                    {
                        return "Fecha no encontrada.";
                    }
                }
            }
            catch (Exception ex)
            {
                return $"Error: {ex.Message}";
            }
        }

        [WebMethod]
        public string registrarCotizacion(string fecha, decimal monto)
        {
            try
            {
                using (MySqlConnection conn = new MySqlConnection(connectionString))
                {
                    conn.Open();
                    string query = "INSERT INTO cotizacion (fecha, cotizacion, cotizacion_oficial) VALUES (@fecha, @monto, 6.97)";
                    MySqlCommand cmd = new MySqlCommand(query, conn);
                    cmd.Parameters.AddWithValue("@fecha", fecha);
                    cmd.Parameters.AddWithValue("@monto", monto);

                    int result = cmd.ExecuteNonQuery();
                    return result > 0 ? "Cotización registrada con éxito." : "No se pudo registrar.";
                }
            }
            catch (Exception ex)
            {
                return $"Error: {ex.Message}";
            }
        }
    }
}




