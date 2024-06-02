package com.aluracursos.gandalf.s_grimoire.principal;

import com.aluracursos.gandalf.s_grimoire.dto.DatosAutorDTO;
import com.aluracursos.gandalf.s_grimoire.dto.DatosConsultaDTO;
import com.aluracursos.gandalf.s_grimoire.dto.DatosLibroDTO;
import com.aluracursos.gandalf.s_grimoire.model.Autor;
import com.aluracursos.gandalf.s_grimoire.model.Idioma;
import com.aluracursos.gandalf.s_grimoire.model.Libro;
import com.aluracursos.gandalf.s_grimoire.repository.AutorRepository;
import com.aluracursos.gandalf.s_grimoire.service.ConsumoAPI;
import com.aluracursos.gandalf.s_grimoire.service.ConvierteDatos;
import kotlin.collections.EmptyList;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner userEntry = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private AutorRepository repositorio;
    private List<Autor> autores;
    private List<Libro> librosBuscados;

    public Principal(AutorRepository repository){this.repositorio = repository;}

    public void muestraElMenu() {
        var opcion = -1;
        var menu =("""
                Bienvenido a Gandalf's Grimoire
                ⠀⠀⠘⢤⣀⣀⣀⣀⣤⣤⣤⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠉⠛⠛⠿⠿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⣀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠛⠂⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠚⠉⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⠛⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢀⡀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠈⠓⠦⣀⠀⠀⠀
                ⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠈⠳⣼⡿
                ⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⠀⠀⠈⠁
                ⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⣼⣿⣿⣿⣿⣿⣿⡿⠟⠛⠉⠉⠉⠉⠙⠻⠿⣿⣿⣿⣿⣅⠀⠀⠀⠀⠀⠀⠀
                ⢰⣿⣿⣿⡿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⠁⠀⠀⠀⠀⠀⠀⠀
                ⡾⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀
                
                Elige una opción de nuestro menu
                
                1. Agregar un nuevo libro
                2. Agregar un nuevo autor
                3. Buscar libro por titulo
                4. Listar libros registrados
                5. Listar autores registrados
                6. Listar autores vivos en un determinado ano
                7. Listar libros por idioma
                8. Top 10 libros mas descargados
                9. Buscar autor por nombre
                
                0. Salir ⠀⠀⠀⠀⠀
                """);
        while (opcion != 0){
            System.out.println(menu);
            opcion = userEntry.nextInt();
            userEntry.nextLine();

            switch (opcion){
                case 1:
                    agregarUnNuevoLibro();
                    break;
                case 2:
                    agregarUnNuevoAutor();
                    break;
                case 3:
                    buscarLibroPorNombre();
                    break;
                case 4:
                    listarLibrosRegistrados();
                    break;
                case 5:
                    listarAutoresRegistrados();
                    break;
                case 6:
                    listarAutoresVivosPorPeriodo();
                    break;
                case 7:
                    listarLibrosPorIdioma();
                    break;
                case 8:
                    top10Libros();
                    break;
                case 9:
                    buscaAutorPorNombre();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private DatosConsultaDTO getDatos(String varBusqueda){
        var json = consumoAPI.obtenerDatos(URL_BASE + varBusqueda.replace(" ", "%20"));
        DatosConsultaDTO datos = convierteDatos.obtenerDatos(json, DatosConsultaDTO.class);
        return datos;
    }

    private void agregarUnNuevoLibro(){
        System.out.println("Ingrese el nombre del libro que desea agregar");
        var nombreLibro = userEntry.nextLine();
        DatosConsultaDTO datosConsulta = getDatos(nombreLibro);
        System.out.println(datosConsulta);
        Optional<DatosLibroDTO> libroBuscado = datosConsulta.libros().stream()
                .filter(e-> !e.genero().isEmpty())
                .findFirst();
        if (libroBuscado.isPresent()){
            System.out.println("Libro encontrado!");
            Libro libroSub = new Libro(libroBuscado.get());
            System.out.println(libroSub);
            String nombreAutor = datosConsulta.libros().stream()
                    .flatMap(libro -> libro.autores().stream().map(DatosAutorDTO::nombre))
                    .findFirst()
                    .orElse("Autor no encontrado");
            System.out.println(nombreAutor.split(",")[0].trim().toLowerCase());
            mostrarAutoresBuscados();
            System.out.println(autores);
            Optional<Autor> autorLook = autores.stream()
                            .filter(e -> e.getNombre().toLowerCase().contains(nombreAutor.split(",")[0].trim().toLowerCase()))
                            .findFirst();
            if (autorLook.isPresent()){
                var autorEncontrado = autorLook.get();
                System.out.println("Autor encontrado en la base de datos!");
                System.out.println("Agregando libro a la base de datos...");
                List<Libro> librosA = new ArrayList<>();
                librosA.add(libroSub);
                autorEncontrado.setLibros(librosA);
                repositorio.save(autorEncontrado);
            }else{
                System.out.println("Autor no encontrado!");
                System.out.println("Antes de agregar un libro debe agregar el autor del libro a la base de datos!");
            }

        }else{
            System.out.println("Libro no encontrado!");
        }
    }

    private void agregarUnNuevoAutor(){
        System.out.println("Ingrese el apellido del autor que desea agregar");
        var nombreAutor = userEntry.nextLine();
        DatosConsultaDTO datosConsulta = getDatos(nombreAutor);
        System.out.println(datosConsulta);
        Optional<DatosAutorDTO> autorBuscado = datosConsulta.libros().stream()
                .flatMap(e -> e.autores().stream()
                        .filter(f -> f.nombre().toUpperCase().contains(nombreAutor.split(" ")[0].trim().toUpperCase())))
                .findFirst();
        if(autorBuscado.isPresent()){
            System.out.println("Autor encontrado!");
            System.out.println(autorBuscado.get());
            Autor autorSub = new Autor(autorBuscado.get());
            System.out.println(autorSub);
            repositorio.save(autorSub);
        }else {
            System.out.println("Autor no encontrado");
        }
    }

    private void buscarLibroPorNombre() {
        System.out.println("Ingresa el titulo del libro que deseas buscar");
        var libroName = userEntry.nextLine();
        List<Libro> libroEncontrado= repositorio.libroPorNombre(libroName);
        Optional<Libro> libroDevuelto = libroEncontrado.stream()
                .findFirst();
        if (libroDevuelto.isPresent()){
            Libro libroPresente = libroDevuelto.get();
            System.out.println("Libro encontrado!");
            System.out.println(libroPresente);
        }else{
            System.out.println("No se encontro un libro con el titulo o fragmento de titulo ingresado!");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> todosLosLibros = repositorio.listarLibrosRegistrados();
        todosLosLibros.forEach(System.out::println);
    }

    private void mostrarAutoresBuscados(){
        autores = repositorio.findAll();
    }
    private void listarAutoresRegistrados(){
        mostrarAutoresBuscados();
        List <Autor> todosLosAutores = autores;
        todosLosAutores.forEach(System.out::println);

    }

    private void listarAutoresVivosPorPeriodo() {
        System.out.println("Ingrese el año sobre el cual desea consultar autores vivos");
        var anoConsulta = userEntry.nextInt();
        userEntry.nextLine();
        autores = repositorio.autoresVivos(anoConsulta);
        autores.forEach(System.out::println);
    }
    private void consultaLibrosIdioma(String varIdioma){
        Idioma varConverted = Idioma.fromEnglish(varIdioma);
        librosBuscados = repositorio.librosPorIdioma(varConverted);
    }

    private void listarLibrosPorIdioma() {
        String idiomaVar = "";
        System.out.println("""
                Ingresa el numero del idioma deseado:
                1. EN - Ingles 
                2. ES - Espanol
                3. FR - Frances
                """);
        var idiomaOp = userEntry.nextInt();
        userEntry.nextLine();
        switch (idiomaOp){
            case 1:
                idiomaVar = "en";
                consultaLibrosIdioma(idiomaVar);
                librosBuscados.forEach(System.out::println);
                break;
            case 2:
                idiomaVar = "es";
                consultaLibrosIdioma(idiomaVar);
                librosBuscados.forEach(System.out::println);
                break;
            case 3:
                idiomaVar = "fr";
                consultaLibrosIdioma(idiomaVar);
                librosBuscados.forEach(System.out::println);
                break;
            default:
                System.out.println("No ingreso una opcion valida!");
        }
    }

    private void top10Libros() {
        librosBuscados = repositorio.top10Libros();
        librosBuscados.forEach(System.out::println);
    }

    private void buscaAutorPorNombre() {
        System.out.println("Ingrese el nombre o apellido del autor que desea buscar: ");
        String nomAutor = userEntry.nextLine();
        autores = repositorio.autorPorNombre(nomAutor);

        Optional<Autor> autorDevuelto= autores.stream()
                .findFirst();

        if (autorDevuelto.isPresent()){
            Autor autorPresente = autorDevuelto.get();
            System.out.println("Autor encontado!");
            System.out.println(autorPresente);
        }else {
            System.out.println("No existe un autor con el nombre/apellido ingresado");
        }
    }

}
