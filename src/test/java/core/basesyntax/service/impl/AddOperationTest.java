package core.basesyntax.service.impl;

import core.basesyntax.database.Storage;
import core.basesyntax.model.Fruit;
import core.basesyntax.model.dto.FruitRecordDto;
import core.basesyntax.service.FruitOperationHandler;
import core.basesyntax.service.Operation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddOperationTest {
    private FruitOperationHandler operationHandler = new AddOperation();

    @BeforeClass
    public static void beforeClass() throws Exception {
        Storage.fruits.put(new Fruit("banana"), 50);
    }

    @Test
    public void apply_addOperation_Ok() {
        int expected = 70;
        int actual = operationHandler
                .apply(new FruitRecordDto(Operation.SUPPLY, "banana", 20));
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void apply_addOperation_NotOk() {
        operationHandler.apply(new FruitRecordDto(Operation.SUPPLY, "banana", null));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        Storage.fruits.clear();
    }
}