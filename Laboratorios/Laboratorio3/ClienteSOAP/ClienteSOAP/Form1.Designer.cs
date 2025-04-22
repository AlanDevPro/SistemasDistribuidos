namespace ClienteSOAP
{
    partial class Form1
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Button btnObtener;
        private System.Windows.Forms.Button btnRegistrar;
        private System.Windows.Forms.TextBox textBoxFecha;
        private System.Windows.Forms.TextBox textBoxMonto;
        private System.Windows.Forms.Label labelResultado;
        private System.Windows.Forms.Label labelFecha;
        private System.Windows.Forms.Label labelMonto;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        private void InitializeComponent()
        {
            this.btnObtener = new System.Windows.Forms.Button();
            this.btnRegistrar = new System.Windows.Forms.Button();
            this.textBoxFecha = new System.Windows.Forms.TextBox();
            this.textBoxMonto = new System.Windows.Forms.TextBox();
            this.labelResultado = new System.Windows.Forms.Label();
            this.labelFecha = new System.Windows.Forms.Label();
            this.labelMonto = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // btnObtener
            // 
            this.btnObtener.Location = new System.Drawing.Point(50, 120);
            this.btnObtener.Name = "btnObtener";
            this.btnObtener.Size = new System.Drawing.Size(100, 23);
            this.btnObtener.TabIndex = 0;
            this.btnObtener.Text = "Obtener";
            this.btnObtener.UseVisualStyleBackColor = true;
            this.btnObtener.Click += new System.EventHandler(this.btnObtener_Click);
            // 
            // btnRegistrar
            // 
            this.btnRegistrar.Location = new System.Drawing.Point(200, 120);
            this.btnRegistrar.Name = "btnRegistrar";
            this.btnRegistrar.Size = new System.Drawing.Size(100, 23);
            this.btnRegistrar.TabIndex = 1;
            this.btnRegistrar.Text = "Registrar";
            this.btnRegistrar.UseVisualStyleBackColor = true;
            this.btnRegistrar.Click += new System.EventHandler(this.btnRegistrar_Click);
            // 
            // textBoxFecha
            // 
            this.textBoxFecha.Location = new System.Drawing.Point(120, 30);
            this.textBoxFecha.Name = "textBoxFecha";
            this.textBoxFecha.Size = new System.Drawing.Size(180, 20);
            this.textBoxFecha.TabIndex = 2;
            // 
            // textBoxMonto
            // 
            this.textBoxMonto.Location = new System.Drawing.Point(120, 70);
            this.textBoxMonto.Name = "textBoxMonto";
            this.textBoxMonto.Size = new System.Drawing.Size(180, 20);
            this.textBoxMonto.TabIndex = 3;
            // 
            // labelResultado
            // 
            this.labelResultado.AutoSize = true;
            this.labelResultado.Location = new System.Drawing.Point(50, 170);
            this.labelResultado.Name = "labelResultado";
            this.labelResultado.Size = new System.Drawing.Size(0, 13);
            this.labelResultado.TabIndex = 4;
            // 
            // labelFecha
            // 
            this.labelFecha.AutoSize = true;
            this.labelFecha.Location = new System.Drawing.Point(50, 33);
            this.labelFecha.Name = "labelFecha";
            this.labelFecha.Size = new System.Drawing.Size(40, 13);
            this.labelFecha.TabIndex = 5;
            this.labelFecha.Text = "Fecha:";
            // 
            // labelMonto
            // 
            this.labelMonto.AutoSize = true;
            this.labelMonto.Location = new System.Drawing.Point(50, 73);
            this.labelMonto.Name = "labelMonto";
            this.labelMonto.Size = new System.Drawing.Size(40, 13);
            this.labelMonto.TabIndex = 6;
            this.labelMonto.Text = "Monto:";
            // 
            // Form1
            // 
            this.ClientSize = new System.Drawing.Size(360, 220);
            this.Controls.Add(this.labelMonto);
            this.Controls.Add(this.labelFecha);
            this.Controls.Add(this.labelResultado);
            this.Controls.Add(this.textBoxMonto);
            this.Controls.Add(this.textBoxFecha);
            this.Controls.Add(this.btnRegistrar);
            this.Controls.Add(this.btnObtener);
            this.Name = "Form1";
            this.Text = "Cliente SOAP";
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion
    }
}
