<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Registro;

class RegistroController extends Controller
{
    // Verifica si el CI ya está registrado
    public function verificar($ci)
    {
        $registro = Registro::where('CI', $ci)->first();
        if ($registro) {
            return response()->json(['mensaje' => 'Registrado', 'datos' => $registro]);
        }
        return response()->json(['mensaje' => 'No registrado'], 404);
    }

    // Registra un nuevo título
    public function registrar(Request $request)
    {
        $request->validate([
            'CI' => 'required|unique:registros',
            'Nombres' => 'required',
            'PrimerApellido' => 'required',
            'SegundoApellido' => 'nullable',
            'Titulo' => 'required',
        ]);

        $registro = Registro::create($request->all());

        return response()->json(['mensaje' => 'Título registrado exitosamente', 'registro' => $registro], 201);
    }
}
