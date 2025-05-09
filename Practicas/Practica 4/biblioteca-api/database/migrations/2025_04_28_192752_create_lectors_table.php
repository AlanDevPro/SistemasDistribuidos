<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('lectors', function (Blueprint $table) {
            $table->id();
            $table->string('nombre', 50);
            $table->string('apellido',50);
            $table->enum('sexo', ['Masculino', 'Femenino']);
            $table->string('correo', 100)->unique();
            $table->bigInteger('telefono')->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('lectors');
    }
};
