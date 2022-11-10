package or.gcontreras.appmockito.ejemplos.repositories;

import or.gcontreras.appmockito.ejemplos.models.Examen;
import java.util.List;

public interface ExamenRepository {
    Examen guardar(Examen examen);
    List<Examen> buscarTodos();
}
