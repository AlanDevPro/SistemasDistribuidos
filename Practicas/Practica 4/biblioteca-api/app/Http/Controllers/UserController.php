<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use Illuminate\Support\Facades\Hash;

class UserController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $users = User::all();
        return response()->json($users);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $rol = $request->attributes->get('rol');
        if ($rol != 'administrador') {
            return response()->json(['mensaje' => 'No tienes permisos para crear un usuario'], 403);
        }

        $input = $request->all();
        if(isset($input['password'])) {
            $input['password'] = Hash::make($input['password']);
        }
        $user = User::create($input);
        return response()->json($user, 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(User $user)
    {
        return response()->json($user);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, User $user)
    {
        $rol = $request->attributes->get('rol');
        if ($rol != 'administrador') {
            return response()->json(['mensaje' => 'No tienes permisos para actualizar un usuario'], 403);
        }

        $input = $request->all();
        $user->update($input);
        return response()->json($user);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(User $user, Request $request)
    {
        $rol = $request->attributes->get('rol');
        if ($rol != 'administrador') {
            return response()->json(['mensaje' => 'No tienes permisos para eliminar un usuario'], 403);
        }

        $user->delete();
        return response()->json($user, 204);
    }
}
