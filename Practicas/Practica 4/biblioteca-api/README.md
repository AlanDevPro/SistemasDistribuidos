# Habilitar apis
php artisan install:api

# Hacer correr las migraciones
php artisan migrate

# Crear modelo, migracion, seeder, factory y controller para api
php artisan make:model Libro -msfc --api

# Luego de crear el modelo
# En la migracion crear los campos
# Crear los seeder
# Enlazar el seeder al databaseSeeder
# Hacer correr las migraciones y seeder

# Instalar JWT
# Dara seguridad a la api  vamos firebase/php-jwt
composer require firebase/php-jwt

# Crear el midleware 
php artisan make:middleware JwtMiddleware

# Escribir codigo del midleware

# Proteger las rutasincluyendo 
->middleware(JwtMiddleware::class)

# crear el controlador para login
php artisan make:controller LoginController