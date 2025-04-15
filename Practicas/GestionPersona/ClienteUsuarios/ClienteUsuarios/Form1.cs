using System;
using System.Threading.Tasks;
using System.Windows.Forms;
using ClienteUsuarios.Models;
using ClienteUsuarios.Services;

namespace ClienteUsuarios
{
    public partial class Form1 : Form
    {
        private UsuarioClient _usuarioClient = new UsuarioClient();

        public Form1()
        {
            InitializeComponent();
        }

        private async void Form1_Load(object sender, EventArgs e)
        {
            // Carga los usuarios al iniciar el formulario
            await Refrescar();
        }


        private async void btnAgregar_Click(object sender, EventArgs e)
        {
            // Crear un nuevo usuario
            var usuario = new Usuario
            {
                Nombre = textBox1.Text,       // Nombre
                Apellido = textBox2.Text,     // Apellido
                Email = textBox3.Text,        // Email
                Telefono = textBox4.Text,     // Telefono
                FechaCreacion = dateTimePicker1.Value
            };

            try
            {
                // Llama al servicio para agregar el usuario
                await _usuarioClient.CreateUsuarioAsync(usuario);
                await Refrescar(); // Refresca la lista de usuarios
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al agregar usuario: {ex.Message}");
            }
        }


        private async void btnCargar_Click(object sender, EventArgs e)
        {
            // Verificamos si el ID está presente y es válido
            string inputId = textBox6.Text;

            if (string.IsNullOrEmpty(inputId) || !int.TryParse(inputId, out int id))
            {
                MessageBox.Show("Por favor ingresa un ID válido para actualizar.");
                return;
            }

            try
            {
                // Verificar si el usuario existe
                var usuarioExistente = await _usuarioClient.GetUsuarioAsync(id);

                if (usuarioExistente != null)
                {
                    // Crear el objeto actualizado con los datos del formulario
                    var usuarioActualizado = new Usuario
                    {
                        Id = id,
                        Nombre = textBox1.Text,
                        Apellido = textBox2.Text,
                        Email = textBox3.Text,
                        Telefono = textBox4.Text,
                        FechaCreacion = dateTimePicker1.Value
                    };

                    // Llamar al método de actualización
                    bool actualizado = await _usuarioClient.UpdateUsuarioAsync(usuarioActualizado);

                    if (actualizado)
                    {
                        MessageBox.Show("Usuario actualizado correctamente.");
                        await Refrescar();
                    }
                    else
                    {
                        MessageBox.Show("No se pudo actualizar el usuario.");
                    }
                }
                else
                {
                    MessageBox.Show("No se encontró un usuario con ese ID.");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al actualizar usuario: {ex.Message}");
            }
        }


        private async void btnEliminar_Click(object sender, EventArgs e)
        {
            // Mostrar cuadro de texto para ingresar el id del usuario
            string inputId = textBox5.Text;

            if (string.IsNullOrEmpty(inputId) || !int.TryParse(inputId, out int id))
            {
                MessageBox.Show("Por favor ingresa un ID válido.");
                return;
            }

            try
            {
                // Buscar si el usuario con ese ID existe
                var usuario = await _usuarioClient.GetUsuarioAsync(id);

                if (usuario != null)
                {
                    // Eliminar el usuario si se encuentra
                    await _usuarioClient.DeleteUsuarioAsync(id);
                    await Refrescar(); // Refresca la lista de usuarios
                    MessageBox.Show("Usuario eliminado exitosamente.");
                }
                else
                {
                    // Si el usuario no existe
                    MessageBox.Show("No se encontró un usuario con ese ID.");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al eliminar usuario: {ex.Message}");
            }
        }


        private async Task Refrescar()
        {
            try
            {
                // Carga los usuarios y actualiza el DataGridView
                var usuarios = await _usuarioClient.GetUsuariosAsync();
                dataGridView1.DataSource = usuarios;
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al refrescar datos: {ex.Message}");
            }
        }
    }
}
