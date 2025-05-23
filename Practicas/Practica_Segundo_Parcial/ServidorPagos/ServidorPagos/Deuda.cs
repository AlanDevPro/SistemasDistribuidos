namespace ServidorPagos
{
    public class Deuda
    {
        public string Id { get; set; }
        public string CI { get; set; }
        public string Nombres { get; set; }
        public string PrimerApellido { get; set; }
        public string SegundoApellido { get; set; }
        public decimal Monto { get; set; }
        public string FechaVencimiento { get; set; }
        public bool Pagada { get; set; }
    }
}
