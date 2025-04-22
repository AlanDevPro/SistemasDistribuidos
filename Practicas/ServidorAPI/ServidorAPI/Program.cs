using ServidorAPI.Services; // Asegúrate que esto coincida con la ruta de tu clase UsuarioService

var builder = WebApplication.CreateBuilder(args);

// Añadir servicios
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// Añadir servicio personalizado
builder.Services.AddScoped<UsuarioService>();

// Configurar CORS
builder.Services.AddCors(options =>
{
    options.AddDefaultPolicy(policy =>
    {
        policy.AllowAnyOrigin()
              .AllowAnyMethod()
              .AllowAnyHeader();
    });
});

// Si luego vas a usar DbContext con EF Core (opcional)
// builder.Services.AddDbContext<...>(options =>
//     options.UseMySql(builder.Configuration.GetConnectionString("MySqlConnection"),
//     ServerVersion.AutoDetect(builder.Configuration.GetConnectionString("MySqlConnection"))));

var app = builder.Build();

// Pipeline HTTP
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseCors();
app.UseAuthorization();

app.MapControllers();

app.Run();
