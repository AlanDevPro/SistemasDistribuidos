<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Prestamo extends Model
{
    /** @use HasFactory<\Database\Factories\PrestamoFactory> */
    use HasFactory;

    protected $fillable = [
        'fecha_prestamo',
        'fecha_devolucion',
        'lector_id',
    ];

    public function detalles(){
        return $this->hasMany(DetallePrestamo::class);
    }
}
