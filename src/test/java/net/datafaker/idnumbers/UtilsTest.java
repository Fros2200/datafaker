package net.datafaker.idnumbers;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.Bool;
import net.datafaker.providers.base.IdNumber;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;

import static net.datafaker.idnumbers.Utils.digit;
import static net.datafaker.idnumbers.Utils.digitAt;
import static net.datafaker.idnumbers.Utils.multiply;
import static org.assertj.core.api.Assertions.assertThat;
import net.datafaker.providers.base.PersonIdNumber.Gender;

class UtilsTest {
    @Test
    void digit_parsesGivenCharToNumber() {
        assertThat(digit('0')).isEqualTo(0);
        assertThat(digit('1')).isEqualTo(1);
        assertThat(digit('2')).isEqualTo(2);
        assertThat(digit('8')).isEqualTo(8);
        assertThat(digit('9')).isEqualTo(9);
    }

    @Test
    void digitAt_parsesGivenCharToNumber() {
        assertThat(digitAt("12345", 0)).isEqualTo(1);
        assertThat(digitAt("12345", 1)).isEqualTo(2);
        assertThat(digitAt("12345", 2)).isEqualTo(3);
        assertThat(digitAt("12345", 3)).isEqualTo(4);
        assertThat(digitAt("12345", 4)).isEqualTo(5);
    }

    @Test
    void multiply_digits() {
        assertThat(multiply("1", new int[]{1})).isEqualTo(1);
        assertThat(multiply("1", new int[]{2})).isEqualTo(2);
        assertThat(multiply("23", new int[]{4, 5})).isEqualTo(2 * 4 + 3 * 5);
    }
    @Test
    void testGenderReturnsFemale() {
      
        BaseProviders mockFaker = mock(BaseProviders.class);
      
        IdNumberRequest mockRequest = mock(IdNumberRequest.class);
        when(mockRequest.gender()).thenReturn(IdNumber.GenderRequest.FEMALE);
        
        Gender gender = Utils.gender(mockFaker, mockRequest);
        assertThat(gender).isEqualTo(Gender.FEMALE); // Kontrollerar att det blir FEMALE
    }
    @Test
    void testGenderReturnsMale() {
       
        BaseProviders mockFaker = mock(BaseProviders.class);
    
        IdNumberRequest mockRequest = mock(IdNumberRequest.class);
        when(mockRequest.gender()).thenReturn(IdNumber.GenderRequest.MALE);
    
        Gender gender = Utils.gender(mockFaker, mockRequest);
        assertThat(gender).isEqualTo(Gender.MALE); // Kontrollerar att det blir FEMALE
    }
    @Test
        void testGenderReturnsRandomForAny() {
        
            BaseProviders mockFaker = mock(BaseProviders.class);
            Bool mockBool = mock(Bool.class);
            when(mockFaker.bool()).thenReturn(mockBool);

            
            when(mockBool.bool()).thenReturn(true);
            IdNumberRequest mockRequest = mock(IdNumberRequest.class);
            when(mockRequest.gender()).thenReturn(IdNumber.GenderRequest.ANY);
            
            Gender gender = Utils.gender(mockFaker, mockRequest);
            assertThat(gender).isEqualTo(Gender.FEMALE);

           
            when(mockBool.bool()).thenReturn(false);
            gender = Utils.gender(mockFaker, mockRequest);
            assertThat(gender).isEqualTo(Gender.MALE);
}
}
