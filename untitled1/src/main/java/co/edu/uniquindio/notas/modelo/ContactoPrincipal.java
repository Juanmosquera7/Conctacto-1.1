package co.edu.uniquindio.notas.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
public class ContactoPrincipal {

    private ArrayList<Contacto> contactos;
    private Random random;


    public ContactoPrincipal(){
        this.contactos = new ArrayList<>();
        this.random = new Random();
    }
    public void agregarContacto(String nombre, String apellido, String correo, String numero, LocalDate fechaNacimiento, LocalDate fechaCreacion, String urlFoto) {
        // Generar un ID aleatorio


        // Crear un nuevo contacto utilizando el patrón builder
        Contacto nuevoContacto = Contacto.builder()

                .nombre(nombre)
                .apellido(apellido)
                .correo(correo)
                .numero(numero)
                .fechaNacimiento(fechaNacimiento)
                .fechaCreacion(fechaCreacion)
                .urlFoto(urlFoto)
                .build();

        // Agregar el nuevo contacto a la lista
        contactos.add(nuevoContacto);
    }



    public ArrayList<Contacto> listarNotas(){
        return this.contactos;
    }



}
