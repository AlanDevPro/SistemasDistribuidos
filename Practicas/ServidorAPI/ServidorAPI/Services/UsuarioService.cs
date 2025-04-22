using MySql.Data.MySqlClient;
using ServidorAPI.Models;
using System.Data;

namespace ServidorAPI.Services;

public class UsuarioService
{
    private readonly string _connectionString;

    public UsuarioService(IConfiguration configuration)
    {
        _connectionString = configuration.GetConnectionString("MySqlConnection");
    }

    // Obtener todos los usuarios
    public async Task<List<Usuario>> GetAllUsuarios()
    {
        List<Usuario> usuarios = new List<Usuario>();

        using (MySqlConnection connection = new MySqlConnection(_connectionString))
        {
            await connection.OpenAsync();

            string query = "SELECT * FROM Usuarios";
            using (MySqlCommand command = new MySqlCommand(query, connection))
            {
                using (MySqlDataReader reader = (MySqlDataReader)await command.ExecuteReaderAsync())
                {
                    while (await reader.ReadAsync())
                    {
                        usuarios.Add(new Usuario
                        {
                            Id = Convert.ToInt32(reader["Id"]),
                            Nombre = reader["Nombre"].ToString(),
                            Apellido = reader["Apellido"].ToString(),
                            Email = reader["Email"].ToString(),
                            Telefono = reader["Telefono"].ToString(),
                            FechaCreacion = Convert.ToDateTime(reader["FechaCreacion"])
                        });
                    }
                }
            }
        }

        return usuarios;
    }

    // Obtener un usuario por ID
    public async Task<Usuario> GetUsuarioById(int id)
    {
        Usuario usuario = null;

        using (MySqlConnection connection = new MySqlConnection(_connectionString))
        {
            await connection.OpenAsync();

            string query = "SELECT * FROM Usuarios WHERE Id = @Id";
            using (MySqlCommand command = new MySqlCommand(query, connection))
            {
                command.Parameters.AddWithValue("@Id", id);

                using (MySqlDataReader reader = (MySqlDataReader)await command.ExecuteReaderAsync())
                {
                    if (await reader.ReadAsync())
                    {
                        usuario = new Usuario
                        {
                            Id = Convert.ToInt32(reader["Id"]),
                            Nombre = reader["Nombre"].ToString(),
                            Apellido = reader["Apellido"].ToString(),
                            Email = reader["Email"].ToString(),
                            Telefono = reader["Telefono"].ToString(),
                            FechaCreacion = Convert.ToDateTime(reader["FechaCreacion"])
                        };
                    }
                }
            }
        }

        return usuario;
    }

    // Crear un nuevo usuario
    public async Task<int> CreateUsuario(Usuario usuario)
    {
        using (MySqlConnection connection = new MySqlConnection(_connectionString))
        {
            await connection.OpenAsync();

            string query = "INSERT INTO Usuarios (Nombre, Apellido, Email, Telefono) VALUES (@Nombre, @Apellido, @Email, @Telefono); SELECT LAST_INSERT_ID();";
            using (MySqlCommand command = new MySqlCommand(query, connection))
            {
                command.Parameters.AddWithValue("@Nombre", usuario.Nombre);
                command.Parameters.AddWithValue("@Apellido", usuario.Apellido);
                command.Parameters.AddWithValue("@Email", usuario.Email);
                command.Parameters.AddWithValue("@Telefono", usuario.Telefono ?? (object)DBNull.Value);

                return Convert.ToInt32(await command.ExecuteScalarAsync());
            }
        }
    }

    // Actualizar un usuario existente
    public async Task<bool> UpdateUsuario(Usuario usuario)
    {
        using (MySqlConnection connection = new MySqlConnection(_connectionString))
        {
            await connection.OpenAsync();

            string query = "UPDATE Usuarios SET Nombre = @Nombre, Apellido = @Apellido, Email = @Email, Telefono = @Telefono WHERE Id = @Id";
            using (MySqlCommand command = new MySqlCommand(query, connection))
            {
                command.Parameters.AddWithValue("@Id", usuario.Id);
                command.Parameters.AddWithValue("@Nombre", usuario.Nombre);
                command.Parameters.AddWithValue("@Apellido", usuario.Apellido);
                command.Parameters.AddWithValue("@Email", usuario.Email);
                command.Parameters.AddWithValue("@Telefono", usuario.Telefono ?? (object)DBNull.Value);

                int rowsAffected = await command.ExecuteNonQueryAsync();
                return rowsAffected > 0;
            }
        }
    }

    // Eliminar un usuario
    public async Task<bool> DeleteUsuario(int id)
    {
        using (MySqlConnection connection = new MySqlConnection(_connectionString))
        {
            await connection.OpenAsync();

            string query = "DELETE FROM Usuarios WHERE Id = @Id";
            using (MySqlCommand command = new MySqlCommand(query, connection))
            {
                command.Parameters.AddWithValue("@Id", id);

                int rowsAffected = await command.ExecuteNonQueryAsync();
                return rowsAffected > 0;
            }
        }
    }
}
