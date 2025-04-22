using Microsoft.AspNetCore.Mvc;
using ServidorAPI.Models;
using ServidorAPI.Services;

namespace ServidorAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class UsuariosController : ControllerBase
{
    private readonly UsuarioService _usuarioService;

    public UsuariosController(UsuarioService usuarioService)
    {
        _usuarioService = usuarioService;
    }

    // GET: api/Usuarios
    [HttpGet]
    public async Task<ActionResult<IEnumerable<Usuario>>> GetUsuarios()
    {
        try
        {
            var usuarios = await _usuarioService.GetAllUsuarios();
            return Ok(usuarios);
        }
        catch (Exception ex)
        {
            return StatusCode(500, $"Error interno: {ex.Message}");
        }
    }

    // GET: api/Usuarios/5
    [HttpGet("{id}")]
    public async Task<ActionResult<Usuario>> GetUsuario(int id)
    {
        try
        {
            var usuario = await _usuarioService.GetUsuarioById(id);
            if (usuario == null)
            {
                return NotFound($"Usuario con ID {id} no encontrado");
            }
            return Ok(usuario);
        }
        catch (Exception ex)
        {
            return StatusCode(500, $"Error interno: {ex.Message}");
        }
    }

    // POST: api/Usuarios
    [HttpPost]
    public async Task<ActionResult<Usuario>> PostUsuario(Usuario usuario)
    {
        try
        {
            int id = await _usuarioService.CreateUsuario(usuario);
            usuario.Id = id;

            return CreatedAtAction(nameof(GetUsuario), new { id = usuario.Id }, usuario);
        }
        catch (Exception ex)
        {
            return StatusCode(500, $"Error interno: {ex.Message}");
        }
    }

    // PUT: api/Usuarios/5
    [HttpPut("{id}")]
    public async Task<IActionResult> PutUsuario(int id, Usuario usuario)
    {
        if (id != usuario.Id)
        {
            return BadRequest("El ID proporcionado no coincide con el ID del usuario");
        }

        try
        {
            bool result = await _usuarioService.UpdateUsuario(usuario);
            if (!result)
            {
                return NotFound($"Usuario con ID {id} no encontrado");
            }

            return NoContent();
        }
        catch (Exception ex)
        {
            return StatusCode(500, $"Error interno: {ex.Message}");
        }
    }

    // DELETE: api/Usuarios/5
    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteUsuario(int id)
    {
        try
        {
            bool result = await _usuarioService.DeleteUsuario(id);
            if (!result)
            {
                return NotFound($"Usuario con ID {id} no encontrado");
            }

            return NoContent();
        }
        catch (Exception ex)
        {
            return StatusCode(500, $"Error interno: {ex.Message}");
        }
    }
}