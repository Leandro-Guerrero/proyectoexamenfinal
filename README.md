# Sistema de Cajero Automático

## Descripción
Sistema de cajero automático desarrollado en Java que permite gestionar cuentas bancarias, realizar transacciones y mantener un registro de operaciones. El sistema implementa conceptos de Programación Orientada a Objetos, manejo de excepciones y estructuras de datos.

## Características Principales
- Creación y gestión de clientes
- Creación de cuentas corrientes y de ahorros
- Realización de depósitos y retiros
- Consulta de saldo
- Historial de transacciones
- Transferencias entre cuentas
- Validación de datos y manejo de errores

## Estructura del Proyecto
### Clases Principales
1. **Cuenta** (Clase Base)
   - Atributos: número de cuenta, saldo, titular, historial
   - Métodos: depositar, retirar, consultar saldo

2. **CuentaCorriente** (Hereda de Cuenta)
   - Atributos adicionales: límite de sobregiro
   - Métodos específicos para manejo de sobregiro

3. **CuentaAhorros** (Hereda de Cuenta)
   - Atributos adicionales: tasa de interés
   - Métodos para cálculo de intereses

4. **Cliente**
   - Atributos: nombre, ID, lista de cuentas
   - Gestión de cuentas del cliente

5. **Cajero**
   - Gestión central del sistema
   - Búsqueda de cuentas y clientes
   - Registro de transacciones

6. **Main**
   - Interfaz de usuario
   - Menú principal
   - Manejo de excepciones

## Instrucciones de Uso
1. **Crear un Cliente**
   - Seleccionar opción 1
   - Ingresar ID (5 dígitos)
   - Ingresar nombre (solo letras)

2. **Crear una Cuenta**
   - Seleccionar opción 2
   - Ingresar ID del cliente
   - Seleccionar tipo de cuenta
   - Ingresar número de cuenta (10 dígitos)
   - Configurar límite de sobregiro o tasa de interés

3. **Realizar Operaciones**
   - Depósitos (opción 3)
   - Retiros (opción 4)
   - Consulta de saldo (opción 5)
   - Ver historial (opción 6)
   - Transferencias (opción 7)

## Validaciones Implementadas
- ID de cliente: exactamente 5 dígitos
- Número de cuenta: exactamente 10 dígitos
- Montos: valores positivos
- Nombres: solo letras y espacios
- Saldo suficiente para retiros
- Existencia de cuentas y clientes

## Ejemplos de Uso
1. Crear cliente:
   ```
   ID: 12345
   Nombre: Angel Salcedo
   ```

2. Crear cuenta:
   ```
   Tipo: 1 (Corriente)
   Número: 1234567890
   Límite: 1000
   ```

3. Realizar depósito:
   ```
   Cuenta: 1234567890
   Monto: 500
   ```

## Tecnologías Utilizadas
- Java
- Programación Orientada a Objetos
- Estructuras de datos (ArrayList, HashMap)
- Manejo de excepciones

## Integrantes del Proyecto
1. LEANDRO GUERRERO (192416)- Líder y Documentación**
   - Responsable del README.md
   - Coordinación del equipo
   - Presentación final
   - Subir el código a GitHub

2. ANGEL SALCEDO (192514)- Desarrollador de Clases Base**
   - Implementación de la clase `Cuenta`
   - Implementación de `CuentaCorriente`
   - Implementación de `CuentaAhorros`
   - Manejo de la herencia

3. ISAAC GARCIA (192535)- Desarrollador de Interfaz**
   - Implementación de la clase `Main`
   - Diseño del menú
   - Manejo de entrada/salida
   - Validaciones de datos

4. YORDIN FIGUEROA (192268)- Desarrollador de Datos**
   - Implementación de la clase `Cliente`
   - Implementación de la clase `Cajero`
   - Manejo de ArrayList y HashMap
   - Manejo de excepciones
## Presentación
