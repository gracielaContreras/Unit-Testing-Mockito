package or.gcontreras.appmockito.ejemplos.services;

import or.gcontreras.appmockito.ejemplos.models.Examen;
import or.gcontreras.appmockito.ejemplos.repositories.ExamenRepository;
import or.gcontreras.appmockito.ejemplos.repositories.PreguntaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import static org.mockito.Mockito.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) //Al agregar esta anotación ya no es necesario usar el método setUp().
// Además para poder usarla se debe tener la dependencia de mockito-junit-jupiter en el pom.xml
class ExamenServiceImplTest {
    @Mock
    ExamenRepository repository;
    @Mock
    PreguntaRepository preguntaRepository;

    @InjectMocks //Debe inyectar de la clase concreta, no de ExamenService
    ExamenServiceImpl service;

/*    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //Habilitamos el uso de anotaciones para esta clase.
*//*        repository = mock(ExamenRepository.class);
        preguntaRepository = mock(PreguntaRepository.class);
        service = new ExamenServiceImpl(repository,preguntaRepository);*//*
    }*/
    @Test
    void testbuscarExamenPorNombre() {
        when(repository.buscarTodos()).thenReturn(Datos.EXAMENES);
        Optional<Examen> examen = service.buscarExamenPorNombre("Lenguaje");

        assertTrue(examen.isPresent());
        assertEquals(6, examen.orElseThrow().getId());
        assertEquals("Lenguaje", examen.orElseThrow().getNombre());
    }
    @Test
    void testbuscarExamenPorNombreListaVacia() {
        List<Examen> datos = Collections.emptyList();

        when(repository.buscarTodos()).thenReturn(datos);
        Optional<Examen> examen = service.buscarExamenPorNombre("Lenguaje");

        assertFalse(examen.isPresent());
    }

    @Test
    void testPreguntasExamen() {
        when(repository.buscarTodos()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.buscarPreguntasPorExamenId(5L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.buscarExamenPorNombreConPreguntas("Matemáticas");
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Integrales"));
    }
    @Test
    void testPreguntasExamenVerify() {
        when(repository.buscarTodos()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.buscarPreguntasPorExamenId(5L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.buscarExamenPorNombreConPreguntas("Matemáticas");
        verify(repository).buscarTodos(); //verify como dice verifica que sé allá pasado por ese método
        verify(preguntaRepository).buscarPreguntasPorExamenId(5L);
    }

    @Test
    void testGuardarExamen() {
        when(repository.guardar(any(Examen.class))).thenReturn(Datos.EXAMEN);
        Examen examen = service.guardar(Datos.EXAMEN);
        assertNotNull(examen);
        assertEquals("Física", examen.getNombre());
    }
    @Test
    void testGuardarExamen2() {
        Examen newExamen = Datos.EXAMEN;
        newExamen.setPreguntas(Datos.PREGUNTAS);

        when(repository.guardar(any(Examen.class))).thenReturn(Datos.EXAMEN);
        Examen examen = service.guardar(newExamen);
        assertNotNull(examen.getId());
        assertEquals(8L, examen.getId());
        assertEquals("Física", examen.getNombre());
        verify(repository).guardar(any(Examen.class));
        verify(preguntaRepository).guardarVarias(anyList());
    }
    @Test
    void testGuardarExamen3() {
        //Desarrollo impulsado al comportamiento

        //Given = son las precondiciones en un entorno de pruebas
        Examen newExamen = Datos.EXAMEN2;
        newExamen.setPreguntas(Datos.PREGUNTAS);

        when(repository.guardar(any(Examen.class))).then(new Answer<Examen>(){

            Long secuencia = 8L;

            @Override
            public Examen answer(InvocationOnMock invocationOnMock) throws Throwable {
                Examen examen = invocationOnMock.getArgument(0);
                examen.setId(secuencia++);
                return examen;
            }
        });

        //When = invocamos
        Examen examen = service.guardar(newExamen);

        //Then = probamos
        assertNotNull(examen.getId());
        assertEquals(8L, examen.getId());
        assertEquals("Física", examen.getNombre());
        verify(repository).guardar(any(Examen.class));
        verify(preguntaRepository).guardarVarias(anyList());
    }

}