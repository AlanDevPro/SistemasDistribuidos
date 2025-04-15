using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using ClienteUsuarios.Models;
using Newtonsoft.Json;
using System.Text;
using System;

namespace ClienteUsuarios.Services
{
    public class UsuarioClient
    {
        private readonly HttpClient _httpClient;

        public UsuarioClient()
        {
            var handler = new HttpClientHandler();
            handler.ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true; // Ignora errores de SSL para localhost

            _httpClient = new HttpClient(handler);
            _httpClient.BaseAddress = new Uri("https://localhost:44339/"); // ✔️ Usar https y solo la base
        }


        public async Task<List<Usuario>> GetUsuariosAsync()
        {
            try
            {
                var response = await _httpClient.GetAsync("api/usuarios");
                response.EnsureSuccessStatusCode();
                var json = await response.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<List<Usuario>>(json);
            }
            catch (HttpRequestException ex)
            {
                // Log o manejar el error
                Console.WriteLine($"Error al obtener usuarios: {ex.Message}");
                return null;
            }
        }

        public async Task<Usuario> GetUsuarioAsync(int id)
        {
            var response = await _httpClient.GetAsync($"api/usuarios/{id}");
            if (!response.IsSuccessStatusCode) return null;
            var json = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Usuario>(json);
        }


        public async Task<bool> CreateUsuarioAsync(Usuario usuario)
        {
            var json = JsonConvert.SerializeObject(usuario);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync("api/usuarios", content);
            return response.IsSuccessStatusCode;
        }

        public async Task<bool> UpdateUsuarioAsync(Usuario usuario)
        {
            var json = JsonConvert.SerializeObject(usuario);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await _httpClient.PutAsync($"api/usuarios/{usuario.Id}", content);
            return response.IsSuccessStatusCode;
        }

        public async Task<bool> DeleteUsuarioAsync(int id)
        {
            var response = await _httpClient.DeleteAsync($"api/usuarios/{id}");
            return response.IsSuccessStatusCode;
        }
    }
}
