package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//test constructor, getter and setter in the Work class
class WorkTest {

    private Work testWork;

    @Test
    public void testConstructor(){
        testWork = new Work("CPSC 110 homework", "2021-10-01",true);
        assertEquals("CPSC 110 homework",testWork.getName());
        assertEquals("2021-10-01",testWork.getDueDate());
        assertEquals(true,testWork.isDone());
    }

    @Test
    public void testGetterAndSetter(){
        testWork = new Work("CPSC 110 homework", "2021-10-01",true);
        testWork.setDone(false);
        testWork.setDueDate("2021-11-01");
        testWork.setName("STAT 110 homework");
        assertEquals(false, testWork.isDone());
        assertEquals("2021-11-01", testWork.getDueDate());
        assertEquals("STAT 110 homework", testWork.getName());

    }


}