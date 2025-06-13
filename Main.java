import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

// Clase Cuenta
class Cuenta {
    private String numeroCuenta;
    private double saldo;
    private String titular;
    private ArrayList<String> historial;

    public Cuenta(String numeroCuenta, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0.0;
        this.historial = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            historial.add(String.format("Depósito: +$%.2f", monto));
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            this.saldo -= monto;
            historial.add(String.format("Retiro: -$%.2f", monto));
            return true;
        }
        return false;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }
}

// Clase CuentaCorriente
class CuentaCorriente extends Cuenta {
    private double limiteSobregiro;

    public CuentaCorriente(String numeroCuenta, String titular, double limiteSobregiro) {
        super(numeroCuenta, titular);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto > 0 && monto <= (getSaldo() + limiteSobregiro)) {
            super.retirar(monto);
            return true;
        }
        return false;
    }
}

// Clase CuentaAhorros
class CuentaAhorros extends Cuenta {
    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, String titular, double tasaInteres) {
        super(numeroCuenta, titular);
        this.tasaInteres = tasaInteres;
    }

    public void aplicarInteres() {
        double interes = getSaldo() * tasaInteres;
        depositar(interes);
    }
}

// Clase Cliente
class Cliente {
    private String nombre;
    private String id;
    private ArrayList<Cuenta> cuentas;

    public Cliente(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }
}

// Clase Cajero
class Cajero {
    private ArrayList<Cuenta> cuentas;
    private HashMap<String, Cliente> clientes;

    public Cajero() {
        this.cuentas = new ArrayList<>();
        this.clientes = new HashMap<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public Cliente buscarCliente(String id) {
        return clientes.get(id);
    }
}

// Clase Main
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cajero cajero = new Cajero();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== SISTEMA DE CAJERO AUTOMÁTICO ===");
            System.out.println("1. Crear nuevo cliente");
            System.out.println("2. Crear nueva cuenta");
            System.out.println("3. Realizar depósito");
            System.out.println("4. Realizar retiro");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Ver historial");
            System.out.println("7. Transferir dinero");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        crearCliente(scanner, cajero);
                        break;
                    case 2:
                        crearCuenta(scanner, cajero);
                        break;
                    case 3:
                        realizarDeposito(scanner, cajero);
                        break;
                    case 4:
                        realizarRetiro(scanner, cajero);
                        break;
                    case 5:
                        consultarSaldo(scanner, cajero);
                        break;
                    case 6:
                        verHistorial(scanner, cajero);
                        break;
                    case 7:
                        transferirDinero(scanner, cajero);
                        break;
                    case 8:
                        salir = true;
                        System.out.println("¡Gracias por usar nuestro sistema!");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer
            }
        }
        scanner.close();
    }

    private static void crearCliente(Scanner scanner, Cajero cajero) {
        System.out.println("\n=== CREAR NUEVO CLIENTE ===");
        
        // Validar ID del cliente
        String idCliente = "";
        boolean idValido = false;
        while (!idValido) {
            System.out.print("ID del cliente (5 dígitos): ");
            idCliente = scanner.nextLine();
            if (idCliente.matches("\\d{5}")) { // Verifica que sean exactamente 5 dígitos
                idValido = true;
            } else {
                System.out.println("Error: El ID debe tener exactamente 5 dígitos");
            }
        }

        // Verificar si el ID ya existe
        if (cajero.buscarCliente(idCliente) != null) {
            System.out.println("Error: Ya existe un cliente con ese ID");
            return;
        }

        // Validar nombre del cliente
        String nombre = "";
        boolean nombreValido = false;
        while (!nombreValido) {
            System.out.print("Nombre del cliente (solo letras y espacios): ");
            nombre = scanner.nextLine();
            if (nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) { // Verifica que sean solo letras y espacios
                nombreValido = true;
            } else {
                System.out.println("Error: El nombre solo debe contener letras y espacios");
            }
        }

        // Crear y agregar el cliente
        Cliente nuevoCliente = new Cliente(nombre, idCliente);
        cajero.agregarCliente(nuevoCliente);
        System.out.println("Cliente creado exitosamente");
    }

    private static void crearCuenta(Scanner scanner, Cajero cajero) {
        System.out.println("\n=== CREAR NUEVA CUENTA ===");
        
        // Validar ID del cliente
        System.out.print("Ingrese ID del cliente: ");
        String idCliente = scanner.nextLine();
        Cliente cliente = cajero.buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        // Validar tipo de cuenta
        int tipoCuenta = 0;
        boolean tipoValido = false;
        while (!tipoValido) {
            System.out.println("Tipo de cuenta:");
            System.out.println("1. Cuenta Corriente");
            System.out.println("2. Cuenta de Ahorros");
            System.out.print("Seleccione: ");
            
            try {
                String input = scanner.nextLine();
                tipoCuenta = Integer.parseInt(input);
                if (tipoCuenta == 1 || tipoCuenta == 2) {
                    tipoValido = true;
                } else {
                    System.out.println("Error: Debe seleccionar 1 o 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido (1 o 2)");
            }
        }

        // Validar número de cuenta
        String numeroCuenta = "";
        boolean numeroValido = false;
        while (!numeroValido) {
            System.out.print("Número de cuenta (10 dígitos): ");
            numeroCuenta = scanner.nextLine();
            if (numeroCuenta.matches("\\d{10}")) { // Verifica que sean exactamente 10 dígitos
                numeroValido = true;
            } else {
                System.out.println("Error: El número de cuenta debe tener exactamente 10 dígitos");
            }
        }

        // Crear la cuenta según el tipo
        Cuenta cuenta;
        if (tipoCuenta == 1) {
            double limite = 0;
            boolean limiteValido = false;
            while (!limiteValido) {
                System.out.print("Límite de sobregiro ($): ");
                try {
                    String input = scanner.nextLine();
                    limite = Double.parseDouble(input);
                    if (limite > 0) {
                        limiteValido = true;
                    } else {
                        System.out.println("Error: El límite debe ser mayor a 0");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ingresar un número válido");
                }
            }
            cuenta = new CuentaCorriente(numeroCuenta, cliente.getNombre(), limite);
        } else {
            double tasa = 0;
            boolean tasaValida = false;
            while (!tasaValida) {
                System.out.print("Tasa de interés (ej: 0.05 para 5%): ");
                try {
                    String input = scanner.nextLine();
                    tasa = Double.parseDouble(input);
                    if (tasa > 0 && tasa < 1) {
                        tasaValida = true;
                    } else {
                        System.out.println("Error: La tasa debe estar entre 0 y 1");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ingresar un número válido");
                }
            }
            cuenta = new CuentaAhorros(numeroCuenta, cliente.getNombre(), tasa);
        }

        cajero.agregarCuenta(cuenta);
        cliente.agregarCuenta(cuenta);
        System.out.println("Cuenta creada exitosamente");
    }

    private static void realizarDeposito(Scanner scanner, Cajero cajero) {
        System.out.println("\n=== REALIZAR DEPÓSITO ===");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        Cuenta cuenta = cajero.buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        System.out.print("Monto a depositar ($): ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        cuenta.depositar(monto);
        System.out.println("Depósito realizado exitosamente");
    }

    private static void realizarRetiro(Scanner scanner, Cajero cajero) {
        System.out.println("\n=== REALIZAR RETIRO ===");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        Cuenta cuenta = cajero.buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        System.out.print("Monto a retirar ($): ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        if (cuenta.retirar(monto)) {
            System.out.println("Retiro realizado exitosamente");
        } else {
            System.out.println("No se pudo realizar el retiro. Saldo insuficiente");
        }
    }

    private static void consultarSaldo(Scanner scanner, Cajero cajero) {
        System.out.println("\n=== CONSULTAR SALDO ===");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        Cuenta cuenta = cajero.buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        System.out.printf("Saldo actual: $%.2f\n", cuenta.getSaldo());
    }

    private static void verHistorial(Scanner scanner, Cajero cajero) {
        System.out.println("\n=== HISTORIAL DE TRANSACCIONES ===");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        Cuenta cuenta = cajero.buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        System.out.println("Historial de transacciones:");
        for (String transaccion : cuenta.getHistorial()) {
            System.out.println(transaccion);
        }
    }

    private static void transferirDinero(Scanner scanner, Cajero cajero) {
        System.out.println("\n=== TRANSFERIR DINERO ===");
        System.out.print("Número de cuenta origen: ");
        String cuentaOrigen = scanner.nextLine();
        
        System.out.print("Número de cuenta destino: ");
        String cuentaDestino = scanner.nextLine();
        
        Cuenta origen = cajero.buscarCuenta(cuentaOrigen);
        Cuenta destino = cajero.buscarCuenta(cuentaDestino);

        if (origen == null || destino == null) {
            System.out.println("Una o ambas cuentas no fueron encontradas");
            return;
        }

        System.out.print("Monto a transferir ($): ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        if (origen.retirar(monto)) {
            destino.depositar(monto);
            System.out.println("Transferencia realizada exitosamente");
        } else {
            System.out.println("No se pudo realizar la transferencia. Saldo insuficiente");
        }
    }
}