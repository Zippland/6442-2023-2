import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ClassificationTests {
    @Test(timeout=1000)
    public void zeroLevelTreeClassification() {
        Node tree = new TerminalNode("Daddy long-legs");
        assertEquals("Daddy long-legs", tree.classify(new Spider(new HashSet<Spider.Property>())));
        assertEquals("Daddy long-legs", tree.classify(new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS))));
    }

    @Test(timeout=1000)
    public void oneLevelTreeClassification() {
        Node tree = new DecisionNode(Spider.Property.HAS_BLUE_AREA,
                new TerminalNode("Trapdoor spider"),
                new TerminalNode("Funnel-web spider")
        );

        assertEquals("Trapdoor spider", tree.classify(new Spider(Set.of(Spider.Property.HAS_BLUE_AREA))));
        assertEquals("Funnel-web spider", tree.classify(new Spider(Set.of())));
    }

    @Test(timeout=1000)
    public void oneLevelDistractorTreeClassification() {
        Node tree = new DecisionNode(Spider.Property.OBSERVED_ON_GROUND,
                new TerminalNode("Mouse spider"),
                new TerminalNode("House spider")
        );

        assertEquals("Mouse spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.FANGS_SHAPED_LIKE_PINCER))));
        assertEquals("House spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS))));
    }

    @Test(timeout=1000)
    public void twoLevelTreeClassification() {
        Node tree = new DecisionNode(Spider.Property.IS_BLACK,
                new DecisionNode(Spider.Property.HAS_RED_AREA,
                        new TerminalNode("Red-back spider"),
                        new TerminalNode("Huntsman")),
                new DecisionNode(Spider.Property.HAS_RED_AREA,
                        new TerminalNode("Banksia peacock"),
                        new TerminalNode("Daddy long-legs"))
        );
        assertEquals("Huntsman", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK))));
        assertEquals("Red-back spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA))));
        assertEquals("Daddy long-legs", tree.classify(new Spider(Set.of())));
        assertEquals("Banksia peacock", tree.classify(new Spider(Set.of(Spider.Property.HAS_RED_AREA))));
    }

    @Test(timeout=1000)
    public void degenerateTreeClassification() {
        Node tree = new DecisionNode(Spider.Property.OBSERVED_IN_GARDEN,
            new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                new DecisionNode(Spider.Property.HAS_RED_AREA,
                    new DecisionNode(Spider.Property.HAS_BLUE_AREA,
                        new TerminalNode("Trapdoor spider"),
                        new TerminalNode("Brushfooted spider")),
                    new TerminalNode("Funnel-web spider")),
                new TerminalNode("Flattened bark spider")),
            new TerminalNode("Silvery vagabond")
        );

        assertEquals("Trapdoor spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Brushfooted spider", tree.classify(new Spider(Set.of(Spider.Property.HAS_RED_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Funnel-web spider", tree.classify(new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Flattened bark spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Silvery vagabond", tree.classify(new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB, Spider.Property.HAS_SIX_SPINNERETS))));
    }

    @Test(timeout=1000)
    public void largeTreeClassification() {
        Node tree = new DecisionNode(Spider.Property.OBSERVED_AT_HEIGHT,
            new DecisionNode(Spider.Property.IS_BLACK,
                new DecisionNode(Spider.Property.OBSERVED_IN_GARDEN,
                    new DecisionNode(Spider.Property.HAS_RED_AREA,
                        new TerminalNode("Red-back spider"),
                        new TerminalNode("House spider")),
                    new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                        new TerminalNode("White-tail spider"),
                        new TerminalNode("Black wishbone spider"))),
                new DecisionNode(Spider.Property.OBSERVED_ON_GROUND,
                    new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                        new TerminalNode("Saint Andrew's cross"),
                        new TerminalNode("Garden orb-weaving")),
                    new DecisionNode(Spider.Property.HAS_RED_AREA,
                        new TerminalNode("Banksia peacock"),
                        new TerminalNode(("Whistling tarantula"))))),
            new DecisionNode(Spider.Property.FANGS_SHAPED_LIKE_PINCER,
                new DecisionNode(Spider.Property.IS_BLACK,
                    new DecisionNode(Spider.Property.HAS_RED_AREA,
                        new TerminalNode("Mouse spider"),
                        new TerminalNode("Flattened bark spider")),
                    new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                        new TerminalNode("Wolf spider"),
                        new TerminalNode("Daddy long-legs"))),
                new DecisionNode(Spider.Property.HAS_RED_AREA,
                    new DecisionNode(Spider.Property.IS_BLACK,
                        new TerminalNode("Trapdoor spider"),
                        new TerminalNode("Brushfooted spider")),
                    new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                        new DecisionNode(Spider.Property.MAKES_WEB,
                            new TerminalNode("Funnel-web spider"),
                            new TerminalNode("Huntsman")),
                        new TerminalNode("Silvery vagabond"))))
        );

        assertEquals("Funnel-web spider", tree.classify(new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Red-back spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB))));
        assertEquals("White-tail spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.IS_SMALL))));
        assertEquals("Mouse spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.FANGS_SHAPED_LIKE_PINCER))));
        assertEquals("House spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Wolf spider", tree.classify(new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.FANGS_SHAPED_LIKE_PINCER))));
        assertEquals("Trapdoor spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Garden orb-weaving", tree.classify(new Spider(Set.of(Spider.Property.HAS_YELLOW_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Saint Andrew's cross", tree.classify(new Spider(Set.of(Spider.Property.HAS_YELLOW_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB))));
        assertEquals("Huntsman", tree.classify(new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN))));
        assertEquals("Banksia peacock", tree.classify(new Spider(Set.of(Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.HAS_YELLOW_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Daddy long-legs", tree.classify(new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Whistling tarantula", tree.classify(new Spider(Set.of(Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER))));
        assertEquals("Brushfooted spider", tree.classify(new Spider(Set.of(Spider.Property.HAS_RED_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Flattened bark spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS))));
        assertEquals("Black wishbone spider", tree.classify(new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.IS_SMALL, Spider.Property.FANGS_SHAPED_LIKE_PINCER))));
        assertEquals("Silvery vagabond", tree.classify(new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB, Spider.Property.HAS_SIX_SPINNERETS))));
    }

    @Test(timeout=1000)
    public void oneLevelTreeFormatting() {
        Node tree = new DecisionNode(Spider.Property.HAS_BLUE_AREA,
                new TerminalNode("Trapdoor spider"),
                new TerminalNode("Funnel-web spider")
        );

        assertEquals("Has blue area?\n| Yes: Trapdoor spider\n| No: Funnel-web spider", tree.toString());
    }

    @Test(timeout=1000)
    public void twoLevelTreeFormatting() {
        Node tree = new DecisionNode(Spider.Property.IS_BLACK,
                new DecisionNode(Spider.Property.HAS_RED_AREA,
                        new TerminalNode("Red-back spider"),
                        new TerminalNode("Huntsman")),
                new DecisionNode(Spider.Property.HAS_RED_AREA,
                        new TerminalNode("Banksia peacock"),
                        new TerminalNode("Daddy long-legs"))
        );

        assertEquals("Is black?\n| Yes: Has red area?\n| | Yes: Red-back spider\n| | No: Huntsman\n| No: Has red area?\n| | Yes: Banksia peacock\n| | No: Daddy long-legs", tree.toString());
    }

    @Test(timeout=1000)
    public void degenerateTreeFormatting() {
        Node tree = new DecisionNode(Spider.Property.OBSERVED_IN_GARDEN,
                new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                        new DecisionNode(Spider.Property.HAS_RED_AREA,
                                new DecisionNode(Spider.Property.HAS_BLUE_AREA,
                                        new TerminalNode("Trapdoor spider"),
                                        new TerminalNode("Brushfooted spider")),
                                new TerminalNode("Funnel-web spider")),
                        new TerminalNode("Flattened bark spider")),
                new TerminalNode("Silvery vagabond")
        );

        assertEquals(tree.toString(), "Observed in garden?\n| Yes: Observed in forest?\n| | Yes: Has red area?\n| | | Yes: Has blue area?\n| | | | Yes: Trapdoor spider\n| | | | No: Brushfooted spider\n| | | No: Funnel-web spider\n| | No: Flattened bark spider\n| No: Silvery vagabond");
    }

    @Test(timeout=1000)
    public void largeTreeFormatting() {
        Node tree = new DecisionNode(Spider.Property.OBSERVED_AT_HEIGHT,
                new DecisionNode(Spider.Property.IS_BLACK,
                        new DecisionNode(Spider.Property.OBSERVED_IN_GARDEN,
                                new DecisionNode(Spider.Property.HAS_RED_AREA,
                                        new TerminalNode("Red-back spider"),
                                        new TerminalNode("House spider")),
                                new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                                        new TerminalNode("White-tail spider"),
                                        new TerminalNode("Black wishbone spider"))),
                        new DecisionNode(Spider.Property.OBSERVED_ON_GROUND,
                                new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                                        new TerminalNode("Saint Andrew's cross"),
                                        new TerminalNode("Garden orb-weaving")),
                                new DecisionNode(Spider.Property.HAS_RED_AREA,
                                        new TerminalNode("Banksia peacock"),
                                        new TerminalNode(("Whistling tarantula"))))),
                new DecisionNode(Spider.Property.FANGS_SHAPED_LIKE_PINCER,
                        new DecisionNode(Spider.Property.IS_BLACK,
                                new DecisionNode(Spider.Property.HAS_RED_AREA,
                                        new TerminalNode("Mouse spider"),
                                        new TerminalNode("Flattened bark spider")),
                                new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                                        new TerminalNode("Wolf spider"),
                                        new TerminalNode("Daddy long-legs"))),
                        new DecisionNode(Spider.Property.HAS_RED_AREA,
                                new DecisionNode(Spider.Property.IS_BLACK,
                                        new TerminalNode("Trapdoor spider"),
                                        new TerminalNode("Brushfooted spider")),
                                new DecisionNode(Spider.Property.OBSERVED_IN_FOREST,
                                        new DecisionNode(Spider.Property.MAKES_WEB,
                                                new TerminalNode("Funnel-web spider"),
                                                new TerminalNode("Huntsman")),
                                        new TerminalNode("Silvery vagabond"))))
        );

        assertEquals(tree.toString(), "Found high-up?\n| Yes: Is black?\n| | Yes: Observed in garden?\n| | | Yes: Has red area?\n| | | | Yes: Red-back spider\n| | | | No: House spider\n| | | No: Observed in forest?\n| | | | Yes: White-tail spider\n| | | | No: Black wishbone spider\n| | No: Found on ground?\n| | | Yes: Observed in forest?\n| | | | Yes: Saint Andrew's cross\n| | | | No: Garden orb-weaving\n| | | No: Has red area?\n| | | | Yes: Banksia peacock\n| | | | No: Whistling tarantula\n| No: Fangs shaped like pincer?\n| | Yes: Is black?\n| | | Yes: Has red area?\n| | | | Yes: Mouse spider\n| | | | No: Flattened bark spider\n| | | No: Observed in forest?\n| | | | Yes: Wolf spider\n| | | | No: Daddy long-legs\n| | No: Has red area?\n| | | Yes: Is black?\n| | | | Yes: Trapdoor spider\n| | | | No: Brushfooted spider\n| | | No: Observed in forest?\n| | | | Yes: Makes web?\n| | | | | Yes: Funnel-web spider\n| | | | | No: Huntsman\n| | | | No: Silvery vagabond");
    }

    @Test(timeout=1000)
    public void zeroLevelTreeCreation() {
        Set<Spider> spiders = Set.of(
                new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND), "Daddy long-legs")
        );

        Node generatedTree = ClassifierFactory.createClassifier(spiders);
        assertTrue(testAllClassifiedCorrectly(generatedTree, spiders));
        assertTrue(generatedTree.maximumDecisions() <= 0);
    }

    @Test(timeout=1000)
    public void oneLevelTreeCreation() {
        Set<Spider> spiders = Set.of(
            new Spider(Set.of(Spider.Property.HAS_BLUE_AREA), "Trapdoor spider"),
            new Spider(Set.of(), "Funnel-web spider")
        );

        Node generatedTree = ClassifierFactory.createClassifier(spiders);
        assertTrue(testAllClassifiedCorrectly(generatedTree, spiders));
        assertTrue(generatedTree.maximumDecisions() <= 1);
    }

    @Test(timeout=1000)
    public void oneLevelDistractorTreeCreation() {
        Set<Spider> spiders = Set.of(
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.FANGS_SHAPED_LIKE_PINCER), "Mouse spider"),
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS), "House spider")
        );

        Node generatedTree = ClassifierFactory.createClassifier(spiders);
        assertTrue(testAllClassifiedCorrectly(generatedTree, spiders));
        assertTrue(generatedTree.maximumDecisions() <= 1);
    }

    @Test(timeout=1000)
    public void twoLevelTreeCreation() {
        Set<Spider> spiders = Set.of(
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB), "Red-back spider"),
                new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN), "Huntsman"),
                new Spider(Set.of(Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.HAS_YELLOW_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.HAS_SIX_SPINNERETS), "Banksia peacock"),
                new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS), "Daddy long-legs")
        );

        Node generatedTree = ClassifierFactory.createClassifier(spiders);
        assertTrue(testAllClassifiedCorrectly(generatedTree, spiders));
        assertTrue(generatedTree.maximumDecisions() <= 2);
    }

    @Test(timeout=1000)
    public void completeTreeCreation() {
        Set<Spider> spiders = Set.of(
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB), "Red-back spider"),
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.IS_SMALL), "White-tail spider"),
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.FANGS_SHAPED_LIKE_PINCER), "Mouse spider"),
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS), "House spider"),
                new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.FANGS_SHAPED_LIKE_PINCER), "Wolf spider"),
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.HAS_SIX_SPINNERETS), "Trapdoor spider"),
                new Spider(Set.of(Spider.Property.HAS_YELLOW_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB, Spider.Property.HAS_SIX_SPINNERETS), "Garden orb-weaving"),
                new Spider(Set.of(Spider.Property.HAS_YELLOW_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB), "Saint Andrew's cross"),
                new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN), "Huntsman"),
                new Spider(Set.of(Spider.Property.HAS_RED_AREA, Spider.Property.HAS_BLUE_AREA, Spider.Property.HAS_YELLOW_AREA, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.HAS_SIX_SPINNERETS), "Banksia peacock"),
                new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS), "Daddy long-legs"),
                new Spider(Set.of(Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.MAKES_WEB, Spider.Property.FANGS_SHAPED_LIKE_PINCER), "Whistling tarantula"),
                new Spider(Set.of(Spider.Property.HAS_RED_AREA, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_FOREST, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.HAS_SIX_SPINNERETS), "Brushfooted spider"),
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_ON_GROUND, Spider.Property.OBSERVED_IN_GARDEN, Spider.Property.FANGS_SHAPED_LIKE_PINCER, Spider.Property.HAS_SIX_SPINNERETS), "Flattened bark spider"),
                new Spider(Set.of(Spider.Property.IS_BLACK, Spider.Property.OBSERVED_AT_HEIGHT, Spider.Property.IS_SMALL, Spider.Property.FANGS_SHAPED_LIKE_PINCER), "Black wishbone spider"),
                new Spider(Set.of(Spider.Property.OBSERVED_ON_GROUND, Spider.Property.IS_SMALL, Spider.Property.MAKES_WEB, Spider.Property.HAS_SIX_SPINNERETS), "Silvery vagabond")
        );

        Node generatedTree = ClassifierFactory.createClassifier(spiders);
        assertTrue(testAllClassifiedCorrectly(generatedTree, spiders));
        assertTrue(generatedTree.maximumDecisions() <= 4);
    }


    public boolean testAllClassifiedCorrectly(Node generatedTree, Set<Spider> population) {
        for (Spider spider : population) {
            if (!Objects.equals(generatedTree.classify(spider), spider.speciesName)) return false;
        }
        return true;
    }
}
