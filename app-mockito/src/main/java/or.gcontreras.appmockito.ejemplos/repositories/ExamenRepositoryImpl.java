package or.gcontreras.appmockito.ejemplos.repositories;

import or.gcontreras.appmockito.ejemplos.models.Examen;
import java.util.Arrays;
import java.util.List;

public class ExamenRepositoryImpl implements ExamenRepository{
    @Override
    public Examen guardar(Examen examen) {
        return null;
    }

    @Override
    public List<Examen> buscarTodos() {
        return Arrays.asList(new Examen(5L, "Matemáticas"),
                                new Examen(6L, "Lenguaje"), new Examen(7L, "Historia"));
    }
}
