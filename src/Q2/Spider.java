import java.util.Set;

public class Spider {
    public enum Property {
        IS_BLACK,
        HAS_RED_AREA,
        HAS_BLUE_AREA,
        HAS_YELLOW_AREA,
        OBSERVED_AT_HEIGHT,
        OBSERVED_ON_GROUND,
        OBSERVED_IN_FOREST,
        OBSERVED_IN_GARDEN,
        IS_SMALL,
        MAKES_WEB,
        FANGS_SHAPED_LIKE_PINCER,
        HAS_SIX_SPINNERETS
    };
    public static String propertyToString(Property property) {
        return switch(property) {
            case IS_BLACK -> "Is black";
            case HAS_RED_AREA -> "Has red area";
            case HAS_BLUE_AREA -> "Has blue area";
            case HAS_YELLOW_AREA -> "Has yellow area";
            case OBSERVED_AT_HEIGHT -> "Found high-up";
            case OBSERVED_ON_GROUND -> "Found on ground";
            case OBSERVED_IN_FOREST -> "Observed in forest";
            case OBSERVED_IN_GARDEN -> "Observed in garden";
            case IS_SMALL -> "Is small";
            case MAKES_WEB -> "Makes web";
            case FANGS_SHAPED_LIKE_PINCER -> "Fangs shaped like pincer";
            case HAS_SIX_SPINNERETS -> "Has six spinnerets";
        };
    }

    public final String speciesName;
    private final Set<Property> properties;

    public Spider(Set<Property> properties) {
        this.speciesName = null;
        this.properties = properties;
    }
    public Spider(Set<Property> properties, String speciesName) {
        this.speciesName = speciesName;
        this.properties = properties;
    }
    public boolean testProperty(Property property) {
        return properties.contains(property);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(speciesName == null ? "UNKNOWN" : speciesName);
        builder.append(" (");
        boolean first = true;
        for (Property property : properties) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(propertyToString(property));
        }
        builder.append(")");
        return builder.toString();
    }
}
