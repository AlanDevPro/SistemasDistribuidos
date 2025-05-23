using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Web.Services;
using Newtonsoft.Json;
using ServidorPagos;

namespace ServidorPagos
{
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    public class PagosService : WebService
    {

        [WebMethod]
        public List<Deuda> VerDeudas(string ci)
        {
            try
            {
                // Consulta las deudas directamente desde GraphQL
                var deudas = ObtenerDeudasDesdeGraphQL(ci);
                return deudas.FindAll(d => !d.Pagada);
            }
            catch (Exception ex)
            {
                // Loguear el error
                return new List<Deuda>();
            }
        }

        [WebMethod]
        public string PagarDeuda(string ci, string deudaId)
        {
            try
            {
                // Primero verifica que el cliente existe
                if (!ClienteExisteSync(ci))
                    return "El CI no corresponde a un cliente válido.";

                // Obtiene las deudas no pagadas
                var deudas = ObtenerDeudasDesdeGraphQL(ci);
                var deuda = deudas.Find(d => !d.Pagada && d.Id == deudaId);

                if (deuda != null)
                {
                    // Aquí deberías llamar a una mutación GraphQL para marcar como pagada
                    if (MarcarDeudaComoPagadaEnGraphQL(deudaId))
                    {
                        return $"Deuda pagada con éxito para {deuda.Nombres} {deuda.PrimerApellido}.";
                    }
                    return "Error al actualizar el estado de la deuda.";
                }
                return "No se encontró deuda pendiente para este CI.";
            }
            catch (Exception ex)
            {
                return $"Error al procesar el pago: {ex.Message}";
            }
        }

        private List<Deuda> ObtenerDeudasDesdeGraphQL(string ci)
        {
            using (HttpClient client = new HttpClient())
            {
                var queryObject = new
                {
                    query = @"query ($ci: String!) { 
                        buscarDeudasPorCI(ci: $ci) { 
                            /id
                            CI 
                            nombres 
                            primerApellido 
                            segundoApellido 
                            monto
                            fechaVencimiento
                            pagada 
                        } 
                    }",
                    variables = new { ci = ci }
                };

                var json = JsonConvert.SerializeObject(queryObject);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                var response = client.PostAsync("http://localhost:4000/graphql", content).Result;
                response.EnsureSuccessStatusCode();

                var responseBody = response.Content.ReadAsStringAsync().Result;
                dynamic result = JsonConvert.DeserializeObject(responseBody);

                var deudasGraphQL = result.data.buscarDeudasPorCI;
                var deudas = new List<Deuda>();

                foreach (var d in deudasGraphQL)
                {
                    deudas.Add(new Deuda
                    {
                        Id = d.id,
                        CI = d.CI,
                        Nombres = d.nombres,
                        PrimerApellido = d.primerApellido,
                        SegundoApellido = d.segundoApellido,
                        Monto = d.monto,
                        FechaVencimiento = d.fechaVencimiento,
                        Pagada = d.pagada
                    });
                }

                return deudas;
            }
        }

        private bool MarcarDeudaComoPagadaEnGraphQL(string deudaId)
        {
            using (HttpClient client = new HttpClient())
            {
                var mutationObject = new
                {
                    query = @"mutation ($id: String!) { 
                        pagarDeuda(id: $id) { 
                            success
                            message
                        } 
                    }",
                    variables = new { id = deudaId }
                };

                var json = JsonConvert.SerializeObject(mutationObject);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                var response = client.PostAsync("http://localhost:4000/graphql", content).Result;
                response.EnsureSuccessStatusCode();

                var responseBody = response.Content.ReadAsStringAsync().Result;
                dynamic result = JsonConvert.DeserializeObject(responseBody);

                return result.data.pagarDeuda.success;
            }
        }

        private bool ClienteExisteSync(string ci)
        {
            try
            {
                using (HttpClient client = new HttpClient())
                {
                    var queryObject = new
                    {
                        query = @"query ($ci: String!) { 
                            buscarPorCI(ci: $ci) { 
                                CI 
                            } 
                        }",
                        variables = new { ci = ci }
                    };

                    var json = JsonConvert.SerializeObject(queryObject);
                    var content = new StringContent(json, Encoding.UTF8, "application/json");

                    var response = client.PostAsync("http://localhost:4000/graphql", content).Result;
                    response.EnsureSuccessStatusCode();

                    var responseBody = response.Content.ReadAsStringAsync().Result;
                    dynamic result = JsonConvert.DeserializeObject(responseBody);

                    return result.data.buscarPorCI != null;
                }
            }
            catch
            {
                return false;
            }
        }
    }
}