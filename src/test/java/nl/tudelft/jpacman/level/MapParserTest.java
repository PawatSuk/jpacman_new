package nl.tudelft.jpacman.level;


import org.junit.jupiter.api.Test;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * This is a test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {
    @Mock
    private BoardFactory boardFactory;
    @Mock
    private LevelFactory levelFactory;
    @Mock
    private Blinky blinky;
}

/**
 * Test for the parseMap method (good map).
 */
@Test
public void testParseMapGood() {
    MockitoAnnotations.initMocks(this);
    assertNotNull(boardFactory);
    assertNotNull(levelFactory);
    Mockito.when(levelFactory.createGhost()).thenReturn(blinky);
    MapParser mapParser = new MapParser(levelFactory, boardFactory);
    ArrayList<String> map = new ArrayList<>();
    map.add("############");
    map.add("#P        G#");
    map.add("############");
    mapParser.parseMap(map);
}

