using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Web.Http;
using Servidor.Models;
using Servidor.Models;
using Servidor.Services;

namespace Servidor.Controllers
{
    [RoutePrefix("api/usuarios")]
    public class UsuariosController : ApiController
    {
        private readonly UsuarioService _usuarioService = new UsuarioService();

        [HttpGet]
        [Route("")]
        public IHttpActionResult GetUsuarios()
        {
            var usuarios = _usuarioService.GetAllUsuarios();
            return Ok(usuarios);
        }

        [HttpGet]
        [Route("{id:int}")]
        public IHttpActionResult GetUsuario(int id)
        {
            var usuario = _usuarioService.GetUsuarioById(id);
            if (usuario == null) return NotFound();
            return Ok(usuario);
        }

        [HttpPost]
        [Route("")]
        public IHttpActionResult PostUsuario([FromBody] Usuario usuario)
        {
            var id = _usuarioService.CreateUsuario(usuario);
            usuario.Id = id;
            return Created($"api/usuarios/{id}", usuario);
        }

        [HttpPut]
        [Route("{id:int}")]
        public IHttpActionResult PutUsuario(int id, [FromBody] Usuario usuario)
        {
            if (id != usuario.Id) return BadRequest("El ID no coincide.");
            var updated = _usuarioService.UpdateUsuario(usuario);
            if (!updated) return NotFound();
            return Ok();
        }

        [HttpDelete]
        [Route("{id:int}")]
        public IHttpActionResult DeleteUsuario(int id)
        {
            var deleted = _usuarioService.DeleteUsuario(id);
            if (!deleted) return NotFound();
            return Ok();
        }
    }
}
