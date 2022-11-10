package or.gcontreras.appmockito.ejemplos.services;

import or.gcontreras.appmockito.ejemplos.models.Examen;

import java.util.Optional;

public interface ExamenService {
    Optional<Examen> buscarExamenPorNombre(String nombre);
    Examen buscarExamenPorNombreConPreguntas(String nombre);
    Examen guardar(Examen examen);
}
