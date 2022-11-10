package or.gcontreras.appmockito.ejemplos.repositories;

import java.util.List;

public interface PreguntaRepository {
    List<String> buscarPreguntasPorExamenId(Long id);
    void guardarVarias(List<String> preguntas);
}
