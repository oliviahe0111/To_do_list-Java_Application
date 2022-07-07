package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkListTest {
    WorkList testWorkList;
    Work mathHomework;
    Work statHomework;
    Work englishHomework;
    Work csHomework;


    @BeforeEach
    public void runBefore() {
        testWorkList = new WorkList();
        mathHomework = new Work("Math", "2020-01-10", true);
        statHomework = new Work("Stat", "2023-01-01", false);
        englishHomework = new Work("English", "2021-08-27", true);
        csHomework = new Work("CS", "2022-01-11", false);
    }

    @Test
    public void testAddWork() {
        assertEquals(0, testWorkList.size());
        testWorkList.addWork(mathHomework);
        assertTrue(testWorkList.contains(mathHomework));
        assertEquals(1,testWorkList.size());
    }

    @Test
    public void testGetUnfinishedWork(){
        testWorkList.addWork(mathHomework);
        testWorkList.addWork(statHomework);
        testWorkList.addWork(englishHomework);
        testWorkList.addWork(csHomework);
        List<Work> unfinishedWorkList = testWorkList.getUnfinishedWork();
        assertEquals(2, unfinishedWorkList.size());
        assertEquals(statHomework,unfinishedWorkList.get(0));
        assertEquals(csHomework,unfinishedWorkList.get(1));
    }

    @Test
    public void testNoUnfinishedWork(){
        testWorkList.addWork(mathHomework);
        testWorkList.addWork(englishHomework);
        List<Work> unfinishedWorkList = testWorkList.getUnfinishedWork();
        assertEquals(0, unfinishedWorkList.size());
    }

    @Test
    public void testDeleteSelectedWork(){
        testWorkList.addWork(mathHomework);
        testWorkList.addWork(statHomework);
        assertEquals(2, testWorkList.size());
        testWorkList.deleteSelectedWork("Math");
        assertEquals(1, testWorkList.size());
        assertFalse(testWorkList.contains(mathHomework));

    }

    @Test
    public void testDeleteSelectedWorkWithWrongName(){
        testWorkList.addWork(mathHomework);
        testWorkList.addWork(statHomework);
        assertEquals(2, testWorkList.size());
        testWorkList.deleteSelectedWork("MATH");
        assertEquals(2, testWorkList.size());
        assertTrue(testWorkList.contains(mathHomework));

    }

    @Test
    public void testMarkDone(){
        assertFalse(csHomework.isDone());
        testWorkList.addWork(csHomework);
        testWorkList.markDone("CS");
        assertTrue(csHomework.isDone());

    }

    @Test
    public void testMarkDoneWithWrongName(){
        assertFalse(csHomework.isDone());
        testWorkList.addWork(csHomework);
        testWorkList.markDone("S");
        assertFalse(csHomework.isDone());

    }

    @Test
    public void testPrintUnfinishedWorkMessage(){
        testWorkList.addWork(mathHomework);
        testWorkList.addWork(statHomework);
        testWorkList.addWork(englishHomework);
        testWorkList.addWork(csHomework);
        String message = "Here are the unfinished work:\n" +
                "Stat" + " is due on " + "2023-01-01" + "\n" +
                "CS" + " is due on " + "2022-01-11" + "\n";
        assertEquals(message,testWorkList.printUnfinishedWorkMessage());
    }

    @Test
    public void testPrintEmptyUnfinishedWorkMessage(){
        assertEquals("You have finished all the work!",testWorkList.printUnfinishedWorkMessage());
    }

    @Test
    public void testPrintAllWorkMessage(){
        testWorkList.addWork(mathHomework);
        testWorkList.addWork(statHomework);
        testWorkList.addWork(englishHomework);
        testWorkList.addWork(csHomework);
        String message = "Here are all the work:\n" +
                "Math" + " is due on " + "2020-01-10" + ", and you have " + "finished it." + "\n" +
                "Stat" + " is due on " + "2023-01-01" + ", and you have " + "not finished it." + "\n" +
                "English" + " is due on " + "2021-08-27" + ", and you have " + "finished it." + "\n" +
                "CS" + " is due on " + "2022-01-11" + ", and you have " + "not finished it." + "\n";
        assertEquals(message,testWorkList.printAllWorkMessage());
    }

    @Test
    public void testPrintEmptyAllWorkMessage(){
        assertEquals("No work is in the list!",testWorkList.printAllWorkMessage());
    }

}

