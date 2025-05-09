<?php

namespace Database\Seeders;

use App\Models\User;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {

        User::factory()->create([
            'nombre' => 'Administrador',
            'rol' => 'administrador',
            'email' => 'admin@sis258.com',
        ]);

        User::factory(10)->create();

        $this->call(LibroSeeder::class);

        $this->call(LectorSeeder::class);
    }
}
