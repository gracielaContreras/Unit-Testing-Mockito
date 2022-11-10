package or.gcontreras.appmockito.ejemplos.services;

import or.gcontreras.appmockito.ejemplos.models.Examen;
import or.gcontreras.appmockito.ejemplos.repositories.ExamenRepository;
import or.gcontreras.appmockito.ejemplos.repositories.PreguntaRepository;
import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements ExamenService {

    private ExamenRepository repository;
    private PreguntaRepository preguntaRepository;

    public ExamenServiceImpl(ExamenRepository repository, PreguntaRepository preguntaRepository) {
        this.repository = repository;
        this.preguntaRepository = preguntaRepository;
    }

    @Override
    public Optional<Examen> buscarExamenPorNombre(String nombre) {
        return repository.buscarTodos()
                                        .stream()
                                        .filter(e -> e.getNombre().contains(nombre))
                                        .findFirst();
    }

    @Override
    public Examen buscarExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> encontrado = buscarExamenPorNombre(nombre);
        Examen examen = null;
        if(encontrado.isPresent()) {
            examen = encontrado.orElseThrow();
            List<String> preguntas = preguntaRepository.buscarPreguntasPorExamenId(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }

    @Override
    public Examen guardar(Examen examen) {
        if(!examen.getPreguntas().isEmpty()){
            preguntaRepository.guardarVarias(examen.getPreguntas());
        }
        return repository.guardar(examen);
    }
}
