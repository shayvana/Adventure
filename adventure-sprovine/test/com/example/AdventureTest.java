package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdventureTest {

    @Test
    public void layoutParse() throws Exception {
        Layout layout = Adventure.jsonToLayout("https://courses.engr.illinois.edu/cs126/adventure/siebel.json");
        assertEquals("MatthewsStreet", layout.startingRoom);
        assertEquals("Siebel1314", layout.endingRoom);

        assertEquals("MatthewsStreet", layout.rooms[0].name);

        assertEquals("Northeast", layout.rooms[1].directions[1].directionName);
    }

}