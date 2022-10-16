package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.repository.DealerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class DealerServiceImplTest {
    @Mock
    private DealerRepository dealerRepository;

    @InjectMocks
    private DealerServiceImpl sellerService;

    private Dealer dealer1;
    private Dealer dealer2;
    private List<Car> carList = new ArrayList<>();

    @BeforeEach
    void setUp() {

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
    void findAll_GivenAListAsParam_ExpectingTheSameList() {


        List<Dealer> dealerList = Arrays.asList(dealer1, dealer2);


        given(dealerRepository.findAll())
                .willReturn(dealerList);

        assertThat(sellerService.findAllDealer())
                .isEqualTo(Arrays.asList(dealer1, dealer2));

        assertThat(sellerService.findAllDealer())
                .hasSize(2)
                .contains(dealer1, dealer2);
    }

    @Test()
    void findById_GivenId_ShouldReturnRecord() {

        given(dealerRepository.findById(1L))
                .willReturn(Optional.ofNullable(dealer1));

        assertThat(sellerService.findDealerById(1L))
                .isEqualTo(dealer1);

    }

    @Test()
    void findById_GivenWrongId_ShouldThrowException() {

        Long wrongID = 999_999_999L;

        //this will throw NoSuchElementException
        Assertions.assertThrows(NoSuchElementException.class,
                () -> sellerService.findDealerById(wrongID)
        );

    }

    @Test
    void findByEmail_GivenExistingEmail_ShouldReturnTheCorrectCarOwner() {


        given(dealerRepository.findByEmail(dealer1.getEmail()))
                .willReturn(Optional.ofNullable(dealer1));

        assertThat(sellerService.findDealerByEmail(dealer1.getEmail()))
                .isEqualTo(dealer1);

    }

    @Test
    void findByEmail_givenWrongEmail_ShouldThrowException() {

        String wrongEmail = "wrongEmail@gmail.com";

        assertThrows(NoSuchElementException.class,
                () -> sellerService.findDealerByEmail(wrongEmail)
        );
    }


    @Test
    void addCarOwner_IfExists_OrThrowException() {
        /*given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());*/

        given(dealerRepository.save(dealer1))
                .willReturn(dealer1);

        // when -  action or the behaviour that we are going test
        dealer1 = sellerService.saveDealer(dealer1);


        // then - verify the output
        assertThat(dealer1).isNotNull();

    }

    @Test
    @Disabled
    void deletingCarOwner_IfExists() {

        dealer1 = sellerService.saveDealer(dealer1);

        // given - precondition or setup
        Long correcID = 1L;


        willDoNothing().given(dealerRepository).deleteById(correcID);

        // when -  action or the behaviour that we are going test
        sellerService.deleteDealerById(correcID);

        // then - verify the output
        verify(dealerRepository, times(1)).deleteById(correcID);

    }

    @Test
    @Disabled
    void deletingCarOwner_IfIdDoesntExists_ThrowException() {

        Long correctID = 9999L;

        List<Car> carList = new ArrayList<>();

        Dealer expected = Dealer.builder()
                .id(correctID)
                .email("myEmail@gmail.com")
                .firstName("myFirstName")
                .lastName("myLastName")
                .carList(carList)
                .build();


        sellerService.saveDealer(expected) ;

        sellerService.deleteDealerById(expected.getId());

        verify(dealerRepository).delete(expected);

    }

}
