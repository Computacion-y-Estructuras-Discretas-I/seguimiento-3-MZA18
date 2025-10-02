package ui;

import java.util.Scanner;
import structures.PilaGenerica;
import structures.TablasHash;

public class Main {

    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    public void ejecutar() throws Exception {
        while (true) {
            System.out.println("\nSeleccione la opcion:");
            System.out.println("1. Punto 1, Verificar balanceo de expresión");
            System.out.println("2. Punto 2, Encontrar pares con suma objetivo");
            System.out.println("3. Salir del programa");
            System.out.print("Opcion: ");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese expresion a verificar:");
                    String expresion = sc.nextLine();
                    boolean resultado = verificarBalanceo(expresion);
                    System.out.println("Resultado: " + (resultado ? "TRUE" : "FALSE"));
                    break;

                case 2:
                    System.out.println("Ingrese numeros separados por espacio: ");
                    String lineaNumeros = sc.nextLine();
                    System.out.println("Ingrese suma objetivo: ");
                    int objetivo = Integer.parseInt(sc.nextLine());

                    String[] partes = lineaNumeros.trim().split("\\s+");
                    int[] numeros = new int[partes.length];
                    for (int i = 0; i < partes.length; i++) {
                        numeros[i] = Integer.parseInt(partes[i]);
                    }

                    encontrarParesConSuma(numeros, objetivo);
                    break;

                case 3:
                    System.out.println("Chao");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opcion no permitida");
            }
        }
    }

    /**
     * Verifica si la expresion esta balanceada usando PilaGenerica
     * @param s expresion a verificar
     * @return true si esta balanceada, false si no
     */
    public boolean verificarBalanceo(String s) {
        // TODO: completar 
        return false;
    }

    /**
     * Encuentra y muestra todos los pares unicos de numeros que sumen objetivo usando TablasHash.
     * @param numeros arreglo de numeros enteros
     * @param objetivo suma objetivo
     */
    public void encontrarParesConSuma(int[] numeros, int objetivo) {
        // TODO: completar
    }

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        app.ejecutar();
    }

  
    public static boolean verificarBalanceo(String s) {
        estructuras.PilaGenerica<Character> pilaTmp = null;
        try {

            pilaTmp = new estructuras.PilaGenerica<Character>();
        } catch (Throwable t) {
            try {
                pilaTmp = (estructuras.PilaGenerica<Character>)(Object)null; // sólo para compilar si no existe
            } catch (Throwable ignore) {}
        }

        structures.PilaGenerica<Character> pila = new structures.PilaGenerica<>();
        if (s == null) s = "";
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                pila.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (pila.isEmpty()) return false;
                Character top = pila.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return pila.isEmpty();
    }


    public static java.util.List<String> paresQueSuman(int[] numeros, int objetivo) {
        java.util.ArrayList<String> pares = new java.util.ArrayList<>();
        if (numeros == null) return pares;

        structures.TablasHash th = new structures.TablasHash(Math.max(1, numeros.length * 2));


        for (int num : numeros) {
            try {
                th.insert(num, num);
            } catch (Exception e) {

            }
        }

        java.util.HashSet<String> vistos = new java.util.HashSet<>();
        for (int num : numeros) {
            int comp = objetivo - num;
            Integer encontrado = null;
            try {

                encontrado = th.buscar(comp);
            } catch (Exception e) {

            }
            if (encontrado != null) {
                int a = Math.min(num, comp);
                int b = Math.max(num, comp);
                String clave = a + ":" + b;
                if (!vistos.contains(clave)) {
                    vistos.add(clave);
                    pares.add("(" + a + ", " + b + ")");
                }
            }
        }
        return pares;
    }

}


// asi se compila :  :)
// javac -cp src src/ui/Main.java -d bin
//java -cp bin ui/Main