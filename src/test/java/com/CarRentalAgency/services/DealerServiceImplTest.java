package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.DealerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class DealerServiceImplTest {

    /**
     * @InjectMocks creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.
     */
    @InjectMocks
    private DealerServiceImpl underTestService;

    /**
     * @Mock creates a mock implementation for the classes you need.
     */
    @Mock
    private DealerRepository dealerRepository;

    private Dealer dealer1;
    private Dealer dealer2;
    private final List<Car> carList = new ArrayList<>();

    @BeforeEach
    void setUp() {

        underTestService = new DealerServiceImpl(dealerRepository);

        dealer1 = Dealer.builder()
                .id(1L)
                .email("myEmail1@gmail.com")
                .firstName("myFirstName1")
                .lastName("myLastName1")
                .carList(carList)
                .build();

        dealer2 = Dealer.builder()
                .id(2L)
                .email("myEmail2@gmail.com")
                .firstName("myFirstName2")
                .lastName("myLastName2")
                .carList(carList)
                .build();

    }

    @Test
    void saveDealer_withNewEmail_shouldSaveDealerAndReturnIt() {
        // given
        Dealer dealerToSave = dealer1;
        String email = dealerToSave.getEmail();
        when(dealerRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(dealerRepository.save(dealerToSave)).thenReturn(dealerToSave);


        // when
        Dealer savedDealer = underTestService.saveDealer(dealerToSave);

        // then
        verify(dealerRepository, times(1)).save(dealerToSave);
        assertNotNull(savedDealer);
        assertEquals(dealerToSave.getEmail(), savedDealer.getEmail());
        assertEquals(dealerToSave.getFirstName(), savedDealer.getFirstName());
        assertEquals(dealerToSave.getLastName(), savedDealer.getLastName());
    }

    @Test
    void saveDealer_withExistingEmail_shouldThrowAlreadyExistsException() {
        // given
        Dealer existingDealer = dealer1;
        Dealer newDealer = dealer2;
        String newDealerEmail = newDealer.getEmail();
        when(dealerRepository.findByEmail(newDealerEmail)).thenReturn(Optional.of(existingDealer));

        // when
        DealerServiceImpl dealerService = new DealerServiceImpl(dealerRepository);

        // then
        assertThrows(AlreadyExistsException.class, () -> dealerService.saveDealer(newDealer));
    }


    @Test
    void testUpdateDealer_ShouldSuccess() {
        // given
        Dealer newDealer = dealer2;
        Dealer existingDealer = dealer1;
        Long idForTheExistingDealer = dealer1.getId();

        when(dealerRepository.findById(idForTheExistingDealer))
                .thenReturn(Optional.of(existingDealer));

        when(dealerRepository.findByEmail(newDealer.getEmail()))
                .thenReturn(Optional.empty());

        // when
        Dealer updatedDealer = underTestService.updateDealer(idForTheExistingDealer, newDealer);

        // then
        assertNotNull(updatedDealer);
        assertEquals(newDealer.getEmail(), updatedDealer.getEmail());
        assertEquals(newDealer.getFirstName(), updatedDealer.getFirstName());
        assertEquals(newDealer.getLastName(), updatedDealer.getLastName());
    }

    @Test
    void testUpdateDealer_ShouldThrowNoSuchElementException() {
        // given
        Dealer updatedDealer = dealer1;
        String expectedMessage = "Dealer with id " + 1L + " does not exist";
        when(dealerRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> underTestService.updateDealer(1L, updatedDealer));

        //then
        assertEquals(expectedMessage, exception.getMessage());
        verify(dealerRepository, times(0)).save(updatedDealer);
    }

    @Test
    void testUpdateDealer_ShouldThrowAlreadyExistsException() {
        // given
        Dealer newDealer = dealer2;
        Dealer existingDealer = dealer1;
        Long idForTheExistingDealer = dealer1.getId();
        String expectedMessage = "this email: " + newDealer.getEmail() + " already exists !!";

        when(dealerRepository.findById(idForTheExistingDealer))
                .thenReturn(Optional.of(existingDealer));

        when(dealerRepository.findByEmail(newDealer.getEmail()))
                .thenReturn(Optional.of(existingDealer));

        // when
        Exception AlreadyExistsException = assertThrows(AlreadyExistsException.class, () -> underTestService.updateDealer(idForTheExistingDealer, newDealer));


        // then
        assertEquals(expectedMessage, AlreadyExistsException.getMessage());
        verify(dealerRepository, times(0)).save(newDealer);
    }

    @Test
    void testDeletingDealer_ShouldDeleteIt() {

        // given - precondition or setup
        long existingCustomerID = dealer1.getId();

        when(dealerRepository.findById(existingCustomerID))
                .thenReturn(Optional.of(dealer1));

        willDoNothing().given(dealerRepository).deleteById(existingCustomerID);

        // when -  action or the behaviour that we are going test
        underTestService.deleteDealerById(existingCustomerID);

        // then - verify the output
        verify(dealerRepository, times(1)).deleteById(existingCustomerID);
    }

    @Test
    void testDeleteDealer_ShouldThrowNoSuchElementException() {
        //given
        Dealer dealer = dealer1;
        long nonExistingCustomerID = dealer.getId();
        final String expectedExceptionMessage =
                "CANNOT DELETE A NON-EXISTENT DEALER WITH THIS ID: " + nonExistingCustomerID
                + " PROBABLY IT DOESNT EXIST OR HE IS ALREADY DELETED ;(";

        when(dealerRepository.findById(nonExistingCustomerID))
                .thenReturn(Optional.empty());

        //when
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> underTestService.deleteDealerById(nonExistingCustomerID));

        //then
        verify(dealerRepository, times(1)).findById(anyLong());
        verify(dealerRepository, times(0)).deleteById(anyLong());
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testFindAll_GivenAListAsParam_ExpectingTheSameList() {


        List<Dealer> dealerList = Arrays.asList(dealer1, dealer2);


        given(dealerRepository.findAll())
                .willReturn(dealerList);

        assertThat(underTestService.findAllDealers())
                .isEqualTo(Arrays.asList(dealer1, dealer2));

        assertThat(underTestService.findAllDealers())
                .hasSize(2)
                .contains(dealer1, dealer2);
    }

    @Test()
    void testFindById_GivenId_ShouldReturnRecord() {

        given(dealerRepository.findById(1L))
                .willReturn(Optional.ofNullable(dealer1));

        assertThat(underTestService.findDealerById(1L))
                .isEqualTo(dealer1);

    }

    @Test()
    void testFindById_GivenWrongId_ShouldThrowException() {

        Long wrongID = 999_999_999L;

        //this will throw NoSuchElementException
        Assertions.assertThrows(NoSuchElementException.class,
                () -> underTestService.findDealerById(wrongID)
        );

    }

    @Test
    void testFindByEmail_GivenExistingEmail_ShouldReturnTheCorrectDealer() {


        given(dealerRepository.findByEmail(dealer1.getEmail()))
                .willReturn(Optional.ofNullable(dealer1));

        assertThat(underTestService.findDealerByEmail(dealer1.getEmail()))
                .isEqualTo(dealer1);

    }

    @Test
    void testFindByEmail_givenWrongEmail_ShouldThrowException() {

        String wrongEmail = "wrongEmail@gmail.com";

        assertThrows(NoSuchElementException.class,
                () -> underTestService.findDealerByEmail(wrongEmail)
        );
    }

    @Test
    void testFindDealerByFirstNameIgnoreCase_ShouldSuccess() {
        // given
        String firstName = "John";
        String firstNameUpperCase = "JOHN";
        dealer1.setFirstName(firstName);
        dealer2.setFirstName(firstNameUpperCase);

        List<Dealer> dealers = Arrays.asList(dealer1, dealer2);
        when(dealerRepository.findByFirstNameIgnoreCase(firstName)).thenReturn(dealers);

        // when
        List<Dealer> foundDealers = underTestService.findDealerByFirstNameIgnoreCase(firstName);

        // then
        assertNotNull(foundDealers);
        assertEquals(dealers.size(), foundDealers.size());
        assertEquals(dealers.get(0).getFirstName(), foundDealers.get(0).getFirstName());
        assertEquals(dealers.get(1).getFirstName(), foundDealers.get(1).getFirstName());
    }

    @Test
    void testFindDealerByFirstNameIgnoreCase_ShouldThrowNoSuchElementException() {
        // given
        when(dealerRepository.findByFirstNameIgnoreCase("joh"))
                .thenReturn(List.of());

        List<String> dealersNames = List.of("John", "JOHN");
        when(dealerRepository.approximateNames("joh")).thenReturn(dealersNames);

        // when
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> underTestService.findDealerByFirstNameIgnoreCase("joh")
        );

        // then
        assertThat(dealersNames).contains("John", "JOHN");
        assertThat(dealersNames).size().isEqualTo(2);
    }

}
