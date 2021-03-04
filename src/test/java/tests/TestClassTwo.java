package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClassTwo {

    @Test
    public void loginTest() {
        System.out.println("This is the test for TestClassTwo");
        Assert.assertEquals(1,1);
    }
}
