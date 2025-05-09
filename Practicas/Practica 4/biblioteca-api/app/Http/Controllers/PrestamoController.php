<?php

namespace App\Http\Controllers;

use App\Models\DetallePrestamo;
use App\Models\Prestamo;
use Illuminate\Http\Request;

class PrestamoController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $prestamos = Prestamo::with('detalles.libro')->get();
        return response()->json($prestamos);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $rol = $request->attributes->get('rol');
        if ($rol != 'administrador') {
            return response()->json(['mensaje' => 'No tienes permisos para crear un préstamo'], 403);
        }

        // Validar los datos de entrada
        $validated = $request->validate([
            'fecha_prestamo' => 'required|date',
            'fecha_devolucion' => 'nullable|date|after_or_equal:fecha_prestamo',
            'lector_id' => 'required|integer|exists:lectors,id',
            'libros' => 'required|array',
            'libros.*' => 'integer|exists:libros,id',
        ]);

        // Crear el préstamo
        $prestamo = Prestamo::create([
            'fecha_prestamo' => $validated['fecha_prestamo'],
            'fecha_devolucion' => $validated['fecha_devolucion'] ?? null,
            'lector_id' => $validated['lector_id'],
        ]);

        // Crear los detalles del préstamo
        foreach ($validated['libros'] as $libroId) {
            DetallePrestamo::create([
                'prestamo_id' => $prestamo->id,
                'libro_id' => $libroId,
            ]);
        }

        // Retornar el préstamo con los detalles cargados
        return response()->json($prestamo->load('detalles'), 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(Prestamo $prestamo)
    {
        // Cargar los detalles del préstamo
        $prestamo->load('detalles.libro');
        return response()->json($prestamo);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Prestamo $prestamo)
    {
        $rol = $request->attributes->get('rol');
        if ($rol != 'administrador') {
            return response()->json(['mensaje' => 'No tienes permisos para actualizar un préstamo'], 403);
        }

        // Validar los datos de entrada
        $validated = $request->validate([
            'fecha_prestamo' => 'required|date',
            'fecha_devolucion' => 'nullable|date|after_or_equal:fecha_prestamo',
            'lector_id' => 'required|integer|exists:lectors,id',
        ]);

        // Actualizar el préstamo
        $prestamo->update($validated);

        // Retornar el préstamo actualizado
        return response()->json($prestamo->load('detalles'), 200);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Prestamo $prestamo, Request $request)
    {
        $rol = $request->attributes->get('rol');
        if ($rol != 'administrador') {
            return response()->json(['mensaje' => 'No tienes permisos para eliminar un préstamo'], 403);
        }

        // Eliminar el préstamo y sus detalles
        $prestamo->detalles()->delete();
        $prestamo->delete();

        return response()->json(['mensaje' => 'Préstamo eliminado con éxito'], 200);
    }
}
