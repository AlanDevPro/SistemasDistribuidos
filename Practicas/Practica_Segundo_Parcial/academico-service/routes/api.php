<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\RegistroController;

Route::get('/registro/{ci}', [RegistroController::class, 'verificar']);
Route::post('/registro', [RegistroController::class, 'registrar']);
