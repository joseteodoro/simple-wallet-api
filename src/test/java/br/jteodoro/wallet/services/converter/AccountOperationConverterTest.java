package br.jteodoro.wallet.services.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.jteodoro.wallet.models.AccountOperation;

public class AccountOperationConverterTest {

    @Test
    public void shouldLoadCode1Properly() {
        AccountOperation op = new AccountOperationConverter().convert(1l);
        assertNotNull(op);
        assertEquals(AccountOperation.COMPRA_A_VISTA, op);
    }

    @Test
    public void shouldLoadCode2Properly() {
        AccountOperation op = new AccountOperationConverter().convert(2l);
        assertNotNull(op);
        assertEquals(AccountOperation.COMPRA_PARCELADA, op);
    }

    @Test
    public void shouldLoadCode3Properly() {
        AccountOperation op = new AccountOperationConverter().convert(3l);
        assertNotNull(op);
        assertEquals(AccountOperation.SAQUE, op);
    }

    @Test
    public void shouldLoadCode4Properly() {
        AccountOperation op = new AccountOperationConverter().convert(4l);
        assertNotNull(op);
        assertEquals(AccountOperation.PAGAMENTO, op);
    }

    @Test
    public void shouldGetNullForUnknow() {
        AccountOperation op = new AccountOperationConverter().convert(40000l);
        assertNull(op);
    }
    
}
