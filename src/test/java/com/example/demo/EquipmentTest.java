package com.example.demo;

import com.example.demo.dto.EquipmentDTOResponse;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.Equipment;
import com.example.demo.repository.EquipmentRepository;
import com.example.demo.service.EquipmentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EquipmentTest {

    @TestConfiguration
    static  class equipmentServiceTestConfiguration{
        static EquipmentDTOResponse equipmentDTOResponse = new EquipmentDTOResponse();
        static Equipment novoEquipment = new Equipment();

        @InjectMocks
        EquipmentService equipmentService;

        @Mock
        EquipmentRepository equipmentRepository;

        @BeforeEach
        public void initMock(){
            MockitoAnnotations.initMocks(this);
        }

        @BeforeAll
        public static void setup(){
            equipmentDTOResponse.setId(1l);
            equipmentDTOResponse.setNome("Slide");
            equipmentDTOResponse.setNumSerie("001");
            novoEquipment.setId(1l);
            novoEquipment.setNome("Slide");
            novoEquipment.setNumSerie("001");

        }

        @Test
        public void inserirEquipment(){
//            Mockito.when(equipmentRepository.save(novoEquipment)).thenReturn(novoEquipment);
//
//            BusinessException resposta = equipmentService.createEquipment(novoEquipment);

            //Assertions.assertEquals(resposta.getMessage(), 201);
        }
    }
}
