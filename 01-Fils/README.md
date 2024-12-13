# Activitat 1
Per fer aquesta activitat hem creat dues classes: la classe Principal, que serà l’encarregada de gestionar els fils. I la classe Fil.
Comportament 1
```java
public class Principal {
    public static void main(String[] args) {
        Fil Juan = new Fil("Juan");
        Fil Pepe = new Fil("Pepe");
        Juan.start();
        Pepe.start();
        System.out.println("Termina thread main");
    }
}
```
En aquest primer comportament en la classe Principal hi crearem dues instàncies de Fil, una es dirà Juan i l’altre Pepe. 
Aquestes dues instàncies les iniciarem amb el mètode start(). I per acabar mostrarem quan s’acaba el fil principal. 
```java
public class Fil extends Thread {
    private String nom;

    public Fil(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Error al fil " + nom);
            }
        }
        System.out.println("Termina el fil " + nom);
    }
}
```
La classe Fil extendrà la classe Threads, on hi estarà l’atribut nom i el constructor de la classe.  Sobreescriurà el mètode run per definir el comportament del Fil i la seva lògica.
## Resultat del Comportament 1:


## Comportament 2
```java
public class Principal {
    public static void main(String[] args) {
        Fil Juan = new Fil("Juan");
        Fil Pepe = new Fil("Pepe");
        Juan.start();
        System.out.println("Termina thread main");
        try {
            Juan.join();
        } catch (InterruptedException e) {
            System.out.println("Error " + e);
        }
        Pepe.start();
        try {
            Pepe.join();
        } catch (InterruptedException e) {
            System.out.println("Error " + e);
        }
        System.out.println("Termina el fil Juan");
        System.out.println("Termina el fil Pepe");
    }
}
```

En el comportament 2, primer s’iniciarà el fil Juan, després mostrarà que el fil principal ha acabat. El fil Juan s'aturarà amb el mètode join(). 
Més tard s’iniciarà el fil Pepe i s'aturarà també amb el join().
Per acabar es mostraran els missatges dels dos Fils.
## Resultat del Comportament 2:
## Comportament 3
```java
public class Principal {
    public static void main(String[] args) {
        Fil Juan = new Fil("Juan");
        Fil Pepe = new Fil("Pepe");
        Pepe.start();
        Juan.start();
        System.out.println("Termina thread main");
        try {
            Pepe.join();
            Juan.join();
        } catch (InterruptedException e) {
            System.out.println("Error " + e);
        }
    }
}
```
Al comportament 3 s’iniciaran els dos Fils Juan i Pepe amb el metode strat(). Tot seguit es mostrarà que el fil principal ha acabat. Per acabar, s’aturan els dos fils amb el metode join();
## Resultat del Comportament 3

