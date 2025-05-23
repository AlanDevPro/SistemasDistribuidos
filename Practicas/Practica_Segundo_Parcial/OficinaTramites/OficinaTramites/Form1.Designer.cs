namespace OficinaTramites
{
    partial class Form1
    {
        private System.ComponentModel.IContainer components = null;

        // Controles usados en el formulario
        private System.Windows.Forms.TextBox txtCI;
        private System.Windows.Forms.TextBox txtNombres;
        private System.Windows.Forms.TextBox txtPrimerApellido;
        private System.Windows.Forms.TextBox txtSegundoApellido;
        private System.Windows.Forms.TextBox txtTitulo;
        private System.Windows.Forms.Button btnEmitir;
        private System.Windows.Forms.Label lblResultado;
        private System.Windows.Forms.Label lblCI;
        private System.Windows.Forms.Label lblNombres;
        private System.Windows.Forms.Label lblPrimerApellido;
        private System.Windows.Forms.Label lblSegundoApellido;
        private System.Windows.Forms.Label lblTitulo;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        private void InitializeComponent()
        {
            this.txtCI = new System.Windows.Forms.TextBox();
            this.txtNombres = new System.Windows.Forms.TextBox();
            this.txtPrimerApellido = new System.Windows.Forms.TextBox();
            this.txtSegundoApellido = new System.Windows.Forms.TextBox();
            this.txtTitulo = new System.Windows.Forms.TextBox();
            this.btnEmitir = new System.Windows.Forms.Button();
            this.lblResultado = new System.Windows.Forms.Label();
            this.lblCI = new System.Windows.Forms.Label();
            this.lblNombres = new System.Windows.Forms.Label();
            this.lblPrimerApellido = new System.Windows.Forms.Label();
            this.lblSegundoApellido = new System.Windows.Forms.Label();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.SuspendLayout();

            // 
            // lblCI
            // 
            this.lblCI.AutoSize = true;
            this.lblCI.Location = new System.Drawing.Point(50, 10);
            this.lblCI.Name = "lblCI";
            this.lblCI.Size = new System.Drawing.Size(85, 16);
            this.lblCI.TabIndex = 7;
            this.lblCI.Text = "Cédula de Identidad";

            // 
            // txtCI
            // 
            this.txtCI.Location = new System.Drawing.Point(50, 30);
            this.txtCI.Name = "txtCI";
            this.txtCI.Size = new System.Drawing.Size(200, 22);
            this.txtCI.TabIndex = 0;

            // 
            // lblNombres
            // 
            this.lblNombres.AutoSize = true;
            this.lblNombres.Location = new System.Drawing.Point(50, 55);
            this.lblNombres.Name = "lblNombres";
            this.lblNombres.Size = new System.Drawing.Size(64, 16);
            this.lblNombres.TabIndex = 8;
            this.lblNombres.Text = "Nombres";

            // 
            // txtNombres
            // 
            this.txtNombres.Location = new System.Drawing.Point(50, 75);
            this.txtNombres.Name = "txtNombres";
            this.txtNombres.Size = new System.Drawing.Size(200, 22);
            this.txtNombres.TabIndex = 1;

            // 
            // lblPrimerApellido
            // 
            this.lblPrimerApellido.AutoSize = true;
            this.lblPrimerApellido.Location = new System.Drawing.Point(50, 100);
            this.lblPrimerApellido.Name = "lblPrimerApellido";
            this.lblPrimerApellido.Size = new System.Drawing.Size(98, 16);
            this.lblPrimerApellido.TabIndex = 9;
            this.lblPrimerApellido.Text = "Primer Apellido";

            // 
            // txtPrimerApellido
            // 
            this.txtPrimerApellido.Location = new System.Drawing.Point(50, 120);
            this.txtPrimerApellido.Name = "txtPrimerApellido";
            this.txtPrimerApellido.Size = new System.Drawing.Size(200, 22);
            this.txtPrimerApellido.TabIndex = 2;

            // 
            // lblSegundoApellido
            // 
            this.lblSegundoApellido.AutoSize = true;
            this.lblSegundoApellido.Location = new System.Drawing.Point(50, 145);
            this.lblSegundoApellido.Name = "lblSegundoApellido";
            this.lblSegundoApellido.Size = new System.Drawing.Size(112, 16);
            this.lblSegundoApellido.TabIndex = 10;
            this.lblSegundoApellido.Text = "Segundo Apellido";

            // 
            // txtSegundoApellido
            // 
            this.txtSegundoApellido.Location = new System.Drawing.Point(50, 165);
            this.txtSegundoApellido.Name = "txtSegundoApellido";
            this.txtSegundoApellido.Size = new System.Drawing.Size(200, 22);
            this.txtSegundoApellido.TabIndex = 3;

            // 
            // lblTitulo
            // 
            this.lblTitulo.AutoSize = true;
            this.lblTitulo.Location = new System.Drawing.Point(50, 190);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(39, 16);
            this.lblTitulo.TabIndex = 11;
            this.lblTitulo.Text = "Título";

            // 
            // txtTitulo
            // 
            this.txtTitulo.Location = new System.Drawing.Point(50, 210);
            this.txtTitulo.Name = "txtTitulo";
            this.txtTitulo.Size = new System.Drawing.Size(200, 22);
            this.txtTitulo.TabIndex = 4;

            // 
            // btnEmitir
            // 
            this.btnEmitir.Location = new System.Drawing.Point(50, 250);
            this.btnEmitir.Name = "btnEmitir";
            this.btnEmitir.Size = new System.Drawing.Size(200, 30);
            this.btnEmitir.TabIndex = 5;
            this.btnEmitir.Text = "Emitir Título";
            this.btnEmitir.UseVisualStyleBackColor = true;
            this.btnEmitir.Click += new System.EventHandler(this.btnEmitir_Click);

            // 
            // lblResultado
            // 
            this.lblResultado.Location = new System.Drawing.Point(50, 290);
            this.lblResultado.Name = "lblResultado";
            this.lblResultado.Size = new System.Drawing.Size(400, 50);
            this.lblResultado.TabIndex = 6;
            this.lblResultado.Text = "Resultado...";

            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(600, 350);
            this.Controls.Add(this.lblCI);
            this.Controls.Add(this.txtCI);
            this.Controls.Add(this.lblNombres);
            this.Controls.Add(this.txtNombres);
            this.Controls.Add(this.lblPrimerApellido);
            this.Controls.Add(this.txtPrimerApellido);
            this.Controls.Add(this.lblSegundoApellido);
            this.Controls.Add(this.txtSegundoApellido);
            this.Controls.Add(this.lblTitulo);
            this.Controls.Add(this.txtTitulo);
            this.Controls.Add(this.btnEmitir);
            this.Controls.Add(this.lblResultado);
            this.Name = "Form1";
            this.Text = "Oficina de Trámites";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion
    }
}