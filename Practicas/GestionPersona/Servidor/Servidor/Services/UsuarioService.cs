using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using MySql.Data.MySqlClient;
using Servidor.Models;

namespace Servidor.Services
{
    public class UsuarioService
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["MySqlConnection"].ConnectionString;

        public List<Usuario> GetAllUsuarios()
        {
            var usuarios = new List<Usuario>();

            using (var connection = new MySqlConnection(_connectionString))
            {
                connection.Open();
                var query = "SELECT * FROM Usuarios";
                using (var command = new MySqlCommand(query, connection))
                using (var reader = command.ExecuteReader())
                {
                    while (reader.Read())
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

            return usuarios;
        }

        // Métodos GetUsuarioById, CreateUsuario, UpdateUsuario, DeleteUsuario igual que en .NET Core

        public Usuario GetUsuarioById(int id)
        {
            Usuario usuario = null;

            using (var connection = new MySqlConnection(_connectionString))
            {
                connection.Open();
                var query = "SELECT * FROM Usuarios WHERE Id = @Id";
                using (var command = new MySqlCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@Id", id);

                    using (var reader = command.ExecuteReader())
                    {
                        if (reader.Read())
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

        public int CreateUsuario(Usuario usuario)
        {
            using (var connection = new MySqlConnection(_connectionString))
            {
                connection.Open();
                var query = @"INSERT INTO Usuarios (Nombre, Apellido, Email, Telefono, FechaCreacion)
                      VALUES (@Nombre, @Apellido, @Email, @Telefono, @FechaCreacion);
                      SELECT LAST_INSERT_ID();";
                using (var command = new MySqlCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@Nombre", usuario.Nombre);
                    command.Parameters.AddWithValue("@Apellido", usuario.Apellido);
                    command.Parameters.AddWithValue("@Email", usuario.Email);
                    command.Parameters.AddWithValue("@Telefono", usuario.Telefono);
                    command.Parameters.AddWithValue("@FechaCreacion", usuario.FechaCreacion);

                    return Convert.ToInt32(command.ExecuteScalar());
                }
            }
        }


        public bool UpdateUsuario(Usuario usuario)
        {
            using (var connection = new MySqlConnection(_connectionString))
            {
                connection.Open();
                var query = @"UPDATE Usuarios SET 
                        Nombre = @Nombre,
                        Apellido = @Apellido,
                        Email = @Email,
                        Telefono = @Telefono
                      WHERE Id = @Id";
                using (var command = new MySqlCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@Id", usuario.Id);
                    command.Parameters.AddWithValue("@Nombre", usuario.Nombre);
                    command.Parameters.AddWithValue("@Apellido", usuario.Apellido);
                    command.Parameters.AddWithValue("@Email", usuario.Email);
                    command.Parameters.AddWithValue("@Telefono", usuario.Telefono);

                    int rowsAffected = command.ExecuteNonQuery();
                    return rowsAffected > 0;
                }
            }
        }


        public bool DeleteUsuario(int id)
        {
            using (var connection = new MySqlConnection(_connectionString))
            {
                connection.Open();
                var query = "DELETE FROM Usuarios WHERE Id = @Id";
                using (var command = new MySqlCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@Id", id);
                    int rowsAffected = command.ExecuteNonQuery();
                    return rowsAffected > 0;
                }
            }
        }

    }
}
