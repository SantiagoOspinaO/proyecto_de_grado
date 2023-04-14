/*package com.crud.democrud.ServicesTest;

import com.crud.democrud.models.RequisitoModel;
import com.crud.democrud.repositories.RequisitoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static  org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class RequisitoServiceTest {

    @Autowired
    RequisitoRepository requisitoRepository;

    @Test
    public void testGuardarRequisito(){
        RequisitoModel requisitoModel=new RequisitoModel("Requisito funcional prueba","Esta es la prueba de una descripcion para un ensayo de un requisito");
        RequisitoModel requisitoModelRegistrado = requisitoRepository.save(requisitoModel);
        assertNotNull(requisitoModelRegistrado);
    }

    @Test
    public void testBuscarrequisitoPorId(){
        Long idBuscado=1L;
        Optional<RequisitoModel> requisitoModelBuscado=requisitoRepository.findById(idBuscado);
        assertThat(requisitoModelBuscado.get().getId()).isEqualTo(idBuscado);
    }

    @Test
    public void testListarUsuarios(){
        List<RequisitoModel> requisitoModelList=(List<RequisitoModel>) requisitoRepository.findAll();
        assertThat(requisitoModelList).size().isGreaterThan(0);
    }
    }
     */

