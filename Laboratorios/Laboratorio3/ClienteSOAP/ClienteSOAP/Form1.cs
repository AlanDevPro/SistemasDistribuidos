using System;
using System.Windows.Forms;
using ClienteSOAP.CotizacionServiceClient; // Asegúrate de que este espacio de nombres coincida con el generado por la referencia al servicio

namespace ClienteSOAP
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnObtener_Click(object sender, EventArgs e)
        {
            try
            {
                // Instancia del cliente generado por la referencia al servicio
                CotizacionServiceSoapClient cliente = new CotizacionServiceSoapClient();

                // Obtener la fecha desde el TextBox
                string fecha = textBoxFecha.Text;

                // Llamar al método obtenerCotizacion con la fecha como parámetro
                string resultado = cliente.obtenerCotizacion(fecha);

                // Mostrar el resultado en el label
                labelResultado.Text = "Cotización: " + resultado;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al obtener cotización: " + ex.Message);
            }
        }

        private void btnRegistrar_Click(object sender, EventArgs e)
        {
            try
            {
                // Instancia del cliente generado por la referencia al servicio
                CotizacionServiceSoapClient cliente = new CotizacionServiceSoapClient();

                // Obtener la fecha y monto desde los TextBox
                string fecha = textBoxFecha.Text;
                decimal monto = Convert.ToDecimal(textBoxMonto.Text);

                // Llamar al método registrarCotizacion con la fecha y el monto como parámetros
                string resultado = cliente.registrarCotizacion(fecha, monto);

                // Mostrar el resultado en el label
                labelResultado.Text = "Registro de cotización: " + resultado;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al registrar cotización: " + ex.Message);
            }
        }
    }
}
