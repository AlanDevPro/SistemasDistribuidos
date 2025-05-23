using System;
using System.Windows.Forms;
using GraphQL;
using GraphQL.Client.Http;
using GraphQL.Client.Serializer.Newtonsoft;
using RestSharp;
using System.ServiceModel;
using System.Threading.Tasks;

namespace OficinaTramites
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // Puedes dejar esto vacío o inicializar algo si lo deseas
        }

        private async void btnEmitir_Click(object sender, EventArgs e)
        {
            // Obtener datos del formulario
            string ci = txtCI.Text.Trim();
            string nombres = txtNombres.Text.Trim();
            string primerApellido = txtPrimerApellido.Text.Trim();
            string segundoApellido = txtSegundoApellido.Text.Trim();
            string titulo = txtTitulo.Text.Trim();

            // Validación básica de campos
            if (string.IsNullOrEmpty(ci) || string.IsNullOrEmpty(nombres) ||
                string.IsNullOrEmpty(primerApellido) || string.IsNullOrEmpty(titulo))
            {
                lblResultado.Text = "Por favor complete todos los campos obligatorios";
                return;
            }

            try
            {
                // 1. Verificación con SEGIP (SOAP)
                lblResultado.Text = "Verificando con SEGIP...";
                await Task.Delay(100);

                // Configuración del cliente SOAP
                var binding = new BasicHttpBinding
                {
                    Security = new BasicHttpSecurity
                    {
                        Mode = BasicHttpSecurityMode.None
                    },
                    MaxReceivedMessageSize = 6553600,
                    ReaderQuotas = System.Xml.XmlDictionaryReaderQuotas.Max
                };

                var endpoint = new EndpointAddress("http://localhost:61526/ServicioSegip.asmx");
                var segipClient = new SegipService.ServicioSegipSoapClient(binding, endpoint);

                // Primero verificamos si la CI existe (corrección aquí)
                var verificadoResponse = await segipClient.VerificarDatosAsync(ci);
                bool verificado = verificadoResponse.Body.VerificarDatosResult;

                if (!verificado)
                {
                    lblResultado.Text = "La CI no existe en SEGIP";
                    return;
                }

                // Obtenemos los datos completos
                var datosResponse = await segipClient.ObtenerDatosAsync(ci);
                var personaSEGIP = datosResponse.Body.ObtenerDatosResult;

                // Verificamos coincidencia de datos con manejo de null
                if (personaSEGIP == null ||
                    string.IsNullOrEmpty(personaSEGIP.Nombres) ||
                    string.IsNullOrEmpty(personaSEGIP.PrimerApellido) ||
                    !personaSEGIP.Nombres.Equals(nombres, StringComparison.OrdinalIgnoreCase) ||
                    !personaSEGIP.PrimerApellido.Equals(primerApellido, StringComparison.OrdinalIgnoreCase))
                {
                    lblResultado.Text = "Los datos no coinciden con SEGIP";
                    return;
                }

                // 2. Consulta a SEDUCA (GraphQL)
                lblResultado.Text = "Consultando SEDUCA...";
                await Task.Delay(100);

                var seducaOptions = new GraphQLHttpClientOptions
                {
                    EndPoint = new Uri("http://localhost:4000/graphql")
                };

                using (var gqlClient = new GraphQLHttpClient(seducaOptions, new NewtonsoftJsonSerializer()))
                {
                    var gqlRequest = new GraphQLRequest
                    {
                        Query = @"
                            query ConsultarBachiller($ci: String!) {
                                estudiante(ci: $ci) {
                                    nombres
                                    primerApellido
                                    segundoApellido
                                    esBachiller
                                }
                            }",
                        Variables = new { ci }
                    };

                    var gqlResponse = await gqlClient.SendQueryAsync<dynamic>(gqlRequest);

                    if (gqlResponse.Errors != null && gqlResponse.Errors.Length > 0)
                    {
                        lblResultado.Text = "Error al consultar SEDUCA: " + gqlResponse.Errors[0].Message;
                        return;
                    }

                    var estudiante = gqlResponse.Data.estudiante;
                    if (estudiante == null)
                    {
                        lblResultado.Text = "No encontrado en SEDUCA";
                        return;
                    }

                    if (!estudiante.esBachiller)
                    {
                        lblResultado.Text = "El estudiante no tiene condición de bachiller";
                        return;
                    }
                }

                // 3. Registro en ACADEMICO (REST)
                lblResultado.Text = "Verificando registro académico...";
                await Task.Delay(100);

                var restClient = new RestClient("http://localhost:8000/api");

                // Primero verificamos si ya está registrado
                var checkRequest = new RestRequest($"titulos/{ci}", Method.Get);
                var checkResponse = await restClient.ExecuteAsync(checkRequest);

                if (checkResponse.StatusCode == System.Net.HttpStatusCode.OK)
                {
                    lblResultado.Text = "El título ya está registrado en ACADEMICO";
                    return;
                }

                // Si no está registrado, procedemos a registrarlo
                var registroRequest = new RestRequest("titulos", Method.Post);
                registroRequest.AddJsonBody(new
                {
                    ci,
                    nombres,
                    primerApellido,
                    segundoApellido,
                    titulo,
                    fechaEmision = DateTime.Now.ToString("yyyy-MM-dd")
                });

                var registroResponse = await restClient.ExecuteAsync(registroRequest);

                if (!registroResponse.IsSuccessful)
                {
                    lblResultado.Text = $"Error al registrar: {registroResponse.ErrorMessage}";
                    return;
                }

                lblResultado.Text = "¡Título registrado exitosamente!";
            }
            catch (EndpointNotFoundException)
            {
                lblResultado.Text = "Error de conexión con SEGIP. ¿El servicio está corriendo?";
            }
            catch (GraphQLHttpRequestException ex)
            {
                lblResultado.Text = "Error de conexión con SEDUCA: " + ex.Message;
            }
            catch (Exception ex)
            {
                lblResultado.Text = $"Error inesperado: {ex.Message}";
            }
        }
    }
}