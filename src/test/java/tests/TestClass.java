package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass {

    @Test(groups = "login")
    public void loginTest() {
        Assert.assertEquals(1,1);
    }

    @Test(groups = "regression")
    public void failureTest() {
        Assert.assertTrue( true );
    }

    @Test(groups = {"regression", "login"})
    public void testTrue() {
        Assert.assertFalse( false );
    }

    @Test(groups = "login")
    public void testFalse() {
        Assert.assertNotEquals( 1,2 );
    }

    @Test
    public void dummyTest() {
        Assert.assertTrue( 1 > 0 );
    }

}


