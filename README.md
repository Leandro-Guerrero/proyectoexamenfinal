# Sistema de Cajero Automático

## Descripción
Sistema de cajero automático desarrollado en Java que permite la gestión integral de clientes y cuentas bancarias. El sistema soporta la creación de cuentas corrientes y de ahorro, permite realizar depósitos, retiros, transferencias y consultar el historial de transacciones, todo con validaciones robustas y manejo adecuado de errores.

## Características Principales
- Registro y administración de clientes
- Creación de cuentas corrientes con límite de sobregiro
- Creación de cuentas de ahorro con tasa de interés
- Depósitos y retiros con control de saldo y sobregiro
- Transferencias entre cuentas
- Consulta de saldo y visualización del historial de movimientos
- Validación de datos de entrada para evitar inconsistencias

## Estructura del Proyecto
### Clases Principales

1. *Cuenta* (Clase Base)  
   - *Atributos:* número de cuenta, saldo, titular, historial de transacciones  
   - *Métodos:* depositar, retirar, obtener saldo, gestión del historial

2. *CuentaCorriente* (Hereda de Cuenta)  
   - *Atributos adicionales:* límite de sobregiro  
   - *Métodos sobrescritos:* retiro con validación de sobregiro permitido

3. *CuentaAhorros* (Hereda de Cuenta)  
   - *Atributos adicionales:* tasa de interés  
   - *Métodos específicos:* aplicación de intereses

4. *Cliente*  
   - *Atributos:* nombre, ID (5 dígitos), lista de cuentas  
   - *Funcionalidad:* gestión y asociación de cuentas con el cliente

5. *Cajero*  
   - *Funcionalidad:* almacenamiento y búsqueda de clientes y cuentas, coordinación central del sistema

6. *Main*  
   - *Funcionalidad:* interfaz de usuario basada en consola, menú interactivo, manejo de excepciones y validaciones

## Instrucciones de Uso

1. *Crear un Cliente*  
   - Seleccionar opción 1 en el menú  
   - Ingresar un ID válido de 5 dígitos  
   - Ingresar un nombre válido con solo letras y espacios

2. *Crear una Cuenta*  
   - Seleccionar opción 2  
   - Ingresar el ID del cliente registrado  
   - Elegir el tipo de cuenta (Corriente o Ahorros)  
   - Ingresar un número de cuenta de 10 dígitos  
   - Para cuenta corriente: definir límite de sobregiro positivo  
   - Para cuenta de ahorro: definir tasa de interés (valor entre 0 y 1)

3. *Operaciones*  
   - Depósito: opción 3, ingresar número de cuenta y monto positivo  
   - Retiro: opción 4, ingresar número de cuenta y monto (se valida saldo y sobregiro)  
   - Consulta de saldo: opción 5  
   - Ver historial de transacciones: opción 6  
   - Transferencia: opción 7, ingresar cuentas origen y destino, y monto

4. *Mostrar datos del cliente*  
   - Opción 8, ingresar ID del cliente para visualizar sus datos y cuentas

5. *Salir*  
   - Opción 9 para terminar el programa

## Validaciones Implementadas
- *ID del cliente:* debe ser un número de exactamente 5 dígitos  
- *Número de cuenta:* debe tener exactamente 10 dígitos numéricos  
- *Montos:* deben ser valores positivos para depósitos y retiros  
- *Nombre del cliente:* solo letras y espacios  
- *Saldo:* no permite retiros que excedan saldo + límite de sobregiro (en cuentas corrientes)  
- *Existencia:* se valida la existencia de clientes y cuentas antes de operaciones

## Ejemplos de Uso

1. *Crear cliente*  

ID: 12345
Nombre: Ana Pérez


2. *Crear cuenta corriente*  

Tipo: 1 (Corriente)
Número: 1234567890
Límite de sobregiro: 500


3. *Realizar depósito*  

Cuenta: 1234567890
Monto: 1000.00


4. *Realizar retiro*  

Cuenta: 1234567890
Monto: 200.00


5. *Transferir dinero*  

Cuenta origen: 1234567890
Cuenta destino: 0987654321
Monto: 150.00


## Tecnologías Utilizadas
- Lenguaje Java  
- Programación Orientada a Objetos (POO)  
- Estructuras de datos: ArrayList, HashMap  
- Manejo de excepciones y validaciones en entrada de datos

## Integrantes del Proyecto
1. *LEANDRO GUERRERO (192416) - Líder y Documentación*  
- Coordinación general  
- Documentación y README  
- Presentación final

2. *ANGEL SALCEDO (192514) - Desarrollo de Clases Base*  
- Implementación de Cuenta, CuentaCorriente y CuentaAhorros  
- Implementación de herencia y métodos

3. *ISAAC GARCIA (192535) - Desarrollo de Interfaz*  
- Programación de la clase Main  
- Diseño y validación del menú de usuario

4. *YORDIN FIGUEROA (192268) - Gestión de Datos*  
- Implementación de Cliente y Cajero  
- Manejo de colecciones y búsqueda  
- Manejo de excepciones

## Presentación
![alt text](image-1.png)