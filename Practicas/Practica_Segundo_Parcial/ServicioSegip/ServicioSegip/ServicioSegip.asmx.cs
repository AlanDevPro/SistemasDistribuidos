using ServicioSegip.Models;
using System.Web.Services;

namespace ServicioSegip
{
    [WebService(Namespace = "http://segipip.gob.bo/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    public class ServicioSegip : WebService
    {
        [WebMethod]
        public bool VerificarDatos(string CI)
        {
            // Simula validación en la base de datos
            // Supón que si CI termina en número par, es válido
            return int.TryParse(CI.Substring(CI.Length - 1), out int lastDigit) && lastDigit % 2 == 0;
        }

        [WebMethod]
        public Persona ObtenerDatos(string CI)
        {
            // Simulación de datos
            return new Persona
            {
                CI = CI,
                Nombres = "Juan Carlos",
                PrimerApellido = "Perez",
                SegundoApellido = "Lopez",
                Titulo = "Licenciado"
            };
        }
    }
}
