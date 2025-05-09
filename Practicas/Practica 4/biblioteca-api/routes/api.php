<?php

use App\Http\Controllers\PrestamoController;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;
use App\Http\Controllers\LibroController;
use App\Http\Controllers\LectorController;
use App\Http\Middleware\JwtMiddleware;
use App\Http\Controllers\LoginController;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::middleware(JwtMiddleware::class)->apiResource('libros', LibroController::class);

Route::middleware(JwtMiddleware::class)->apiResource('lectors', LectorController::class);

Route::middleware(JwtMiddleware::class)->apiResource('users', UserController::class);

Route::middleware(JwtMiddleware::class)->apiResource('prestamos', PrestamoController::class);

Route::post('/login', [LoginController::class, 'login']);

