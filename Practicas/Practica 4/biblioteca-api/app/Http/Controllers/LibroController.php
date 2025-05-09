<?php

namespace App\Http\Controllers;

use App\Models\Libro;
use Illuminate\Http\Request;

class LibroController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $libros = Libro::all();
        return response()->json($libros);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        // recuperar los datos del request parametro usuario
        $rol = $request->attributes->get('rol');

        if($rol != 'administrador') {
            return response()->json(['mensaje' => 'No tienes permisos para crear un libro'], 403);
        }

        $input = $request->all();
        $libro = Libro::create($input);
        return response()->json($libro, 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(Libro $libro)
    {
        return response()->json($libro);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Libro $libro)
    {

        // recuperar los datos del request parametro usuario
        $rol = $request->attributes->get('rol');
        if($rol != 'administrador') {
            return response()->json(['mensaje' => 'No tienes permisos para actualizar un libro'], 403);
        }

        $input = $request->all();
        $libro->update($input);
        return response()->json($libro);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Libro $libro, Request $request)
    {
        // recuperar los datos del request parametro usuario
        $rol = $request->attributes->get('rol');
        if($rol != 'administrador') {
            return response()->json(['mensaje' => 'No tienes permisos para eliminar un libro'], 403);
        }
        
        $libro->delete();
        return response()->json($libro, 204);
    }
}
