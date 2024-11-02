import java.util.Random;

public class Proyecto1 {
   public static void main(String[] args) {
       // Variables para el inicio de sesión
       String usuario, contrasenya, usuarioIntroducido, contrasenyaIntroducida;
       short menu, seleccion; // Menú principal y selección de dispositivo
       int filas = 10; // Máximo de dispositivos
       int contador = 0, descuento = 20;
       boolean again = true; // Control de repetición del menú principal

       // Datos de dispositivos
       String[][] datos = new String[filas][2]; // Arreglo para nombre y tipo de dispositivo
       float[] vatios = new float[filas];       // Array para vatios de cada dispositivo
       int[] horas = new int[filas];            // Horas de uso aleatorias para cada dispositivo
       Random rnd = new Random(System.nanoTime());

       // Inicio de sesión
       System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
       System.out.println("\t\t\tRegístrese para comenzar.");
       System.out.print("Cree usuario: ");
       usuario = leer.dato();
       System.out.print("Cree contraseña: ");
       contrasenya = leer.dato();
       System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

       System.out.println("\t\t\tIniciar sesión");
       System.out.print("USUARIO: ");
       usuarioIntroducido = leer.dato();
       System.out.print("CONTRASEÑA: ");
       contrasenyaIntroducida = leer.dato();

       // Validación de inicio de sesión
       while (!usuario.equals(usuarioIntroducido) || !contrasenya.equals(contrasenyaIntroducida)) {
           System.out.println("\nEl usuario o la contraseña son incorrectos\n");
           System.out.print("USUARIO: ");
           usuarioIntroducido = leer.dato();
           System.out.print("CONTRASEÑA: ");
           contrasenyaIntroducida = leer.dato();
       }
       System.out.println("Usted se ha logueado correctamente\n");

       // Menú principal
       while (again) {
           System.out.println("═══════════════════════════════");
           System.out.println("║        Menú Principal       ║");
           System.out.println("╠═════════════════════════════╣");
           System.out.println("║ 1. Añadir dispositivo       ║");
           System.out.println("║ 2. Mis dispositivos         ║");
           System.out.println("║ 3. Gastos y uso             ║");
           System.out.println("║ 4. Gasto mensual total      ║");
           System.out.println("║ 5. Nuestra tienda online    ║");
           System.out.println("║ 6. Salir                    ║");
           System.out.println("═══════════════════════════════");

           menu = leer.datoShort();
           switch (menu) {
               case 1:
                   boolean agregarMas = true;
                   while (agregarMas && contador < filas) {
                       System.out.print("Introduzca el nombre del dispositivo: ");
                       datos[contador][0] = leer.dato();
                       System.out.print("Introduzca el tipo de dispositivo: ");
                       datos[contador][1] = leer.dato();
                       System.out.print("Introduzca los W que consume: ");
                       vatios[contador] = leer.datoFloat();

                       // Generar horas de uso aleatorias
                       horas[contador] = rnd.nextInt(25);
                       contador++;

                       if (contador < filas) {
                           System.out.print("¿Desea añadir otro dispositivo? (s/n): ");
                           if (leer.dato().equalsIgnoreCase("n")) {
                               agregarMas = false;
                           }
                       } else {
                           System.out.println("No hay espacio para agregar más dispositivos.");
                           agregarMas = false;
                       }
                   }
                   break;

               case 2:
                   if (contador > 0) {
                       System.out.println("Dispositivos introducidos:");
                       for (int i = 0; i < contador; i++) {
                           System.out.println("Dispositivo: " + datos[i][0] + ", Tipo: " + datos[i][1] + ", Vatios: " + vatios[i]);
                       }
                   } else {
                       System.out.println("No hay dispositivos añadidos.");
                   }
                   break;

               case 3:
                   if (contador > 0) {
                       System.out.println("Elija el dispositivo del que quiere ver el gasto:");
                       for (int i = 0; i < contador; i++) {
                           System.out.println("Dispositivo " + (i + 1) + ": " + datos[i][0] + ", Tipo: " + datos[i][1] + ", Vatios: " + vatios[i] + "W");
                       }

                       int eleccion = leer.datoInt();
                       System.out.println("El dispositivo " + datos[eleccion - 1][0] + " ha estado encendido durante " + horas[eleccion - 1] + " horas.");

                       System.out.print("Introduzca el precio de la electricidad: ");
                       float precio = leer.datoFloat();

                       System.out.println("Pulse:" +
                               "\n1. Si es un dispositivo de iluminación" +
                               "\n2. Si es otro tipo de dispositivo");
                       seleccion = leer.datoShort();

                       float consumo, costo;
                       String respuesta;

                       switch (seleccion) {
                           case 1:
                               System.out.print("¿Es un dispositivo led? (s/n): ");
                               respuesta = leer.dato();
                               consumo = (vatios[eleccion - 1] * horas[eleccion - 1]) / 1000;
                               costo = consumo * precio;
                               if (respuesta.equalsIgnoreCase("s")) {
                                   costo -= (descuento * costo) / 100;
                               }
                               System.out.printf("El precio de la electricidad generada por el dispositivo es de: %.2f€%n", costo);
                               break;

                           case 2:
                               System.out.print("¿Es un dispositivo de bajo consumo? (s/n): ");
                               respuesta = leer.dato();
                               consumo = (vatios[eleccion - 1] * horas[eleccion - 1]) / 1000;
                               costo = consumo * precio;
                               if (respuesta.equalsIgnoreCase("s")) {
                                   costo -= (descuento * costo) / 100;
                               }
                               System.out.printf("El precio de la electricidad generada por el dispositivo es de: %.2f€%n", costo);
                               break;
                       }
                   } else {
                       System.out.println("No hay dispositivos añadidos.");
                   }
                   break;

               case 6:
                   System.out.println("Gracias por usar Smartify. ¡Hasta pronto!");
                   again = false;
                   break;

               default:
                   System.out.println("Opción no válida. Intente de nuevo.");
                   break;
           }
       }
   }
}
