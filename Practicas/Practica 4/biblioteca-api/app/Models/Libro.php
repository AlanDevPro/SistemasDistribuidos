<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Libro extends Model
{
    /** @use HasFactory<\Database\Factories\LibroFactory> */
    use HasFactory;
    protected $fillable = [
        'titulo',
        'autor',
        'editorial',
        'isbn',
        'fecha_publicacion',
        'numero_paginas',
    ];
    public function prestamos()
    {
        return $this->belongsToMany(Prestamo::class, 'detalle_prestamos');
    }
}
