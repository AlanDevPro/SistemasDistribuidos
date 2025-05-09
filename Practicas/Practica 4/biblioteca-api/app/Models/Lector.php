<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Lector extends Model
{
    /** @use HasFactory<\Database\Factories\LectorFactory> */
    use HasFactory;
    protected $fillable = [
        'nombre',
        'apellido',
        'sexo',
        'correo',
        'telefono'
    ];
}
