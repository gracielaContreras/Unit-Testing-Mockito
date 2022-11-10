package or.gcontreras.appmockito.ejemplos.services;

import or.gcontreras.appmockito.ejemplos.models.Examen;
import java.util.Arrays;
import java.util.List;

public class Datos {
    public final static List<Examen> EXAMENES = Arrays.asList(new Examen(5L, "Matemáticas"),
            new Examen(6L, "Lenguaje"), new Examen(7L, "Historia"));
    public final static List<String> PREGUNTAS = Arrays.asList("Aritméticas", "Integrales"
                                                    , "Derivadas", "Trigonometría", "Geometría");
    public final static Examen EXAMEN = new Examen(8L, "Física");
    public final static Examen EXAMEN2 = new Examen(null, "Física");
}
