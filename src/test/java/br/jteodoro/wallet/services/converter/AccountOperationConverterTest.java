package br.jteodoro.wallet.services.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.jteodoro.wallet.models.AccountOperationEnum;

public class AccountOperationConverterTest {

    @Test
    public void shouldLoadCode1Properly() {
        AccountOperationEnum op = new AccountOperationConverter().convert(1l);
        assertNotNull(op);
        assertEquals(AccountOperationEnum.COMPRA_A_VISTA, op);
    }

    @Test
    public void shouldLoadCode2Properly() {
        AccountOperationEnum op = new AccountOperationConverter().convert(2l);
        assertNotNull(op);
        assertEquals(AccountOperationEnum.COMPRA_PARCELADA, op);
    }

    @Test
    public void shouldLoadCode3Properly() {
        AccountOperationEnum op = new AccountOperationConverter().convert(3l);
        assertNotNull(op);
        assertEquals(AccountOperationEnum.SAQUE, op);
    }

    @Test
    public void shouldLoadCode4Properly() {
        AccountOperationEnum op = new AccountOperationConverter().convert(4l);
        assertNotNull(op);
        assertEquals(AccountOperationEnum.PAGAMENTO, op);
    }

    @Test
    public void shouldGetNullForUnknow() {
        AccountOperationEnum op = new AccountOperationConverter().convert(40000l);
        assertNull(op);
    }
    
}
