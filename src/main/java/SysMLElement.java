import org.omg.sysml.lang.sysml.Package;
import org.omg.sysml.lang.sysml.*;

import java.util.List;
import java.util.stream.Collectors;

public class SysMLElement {

    public final String name;
    public final SysMLKind kind;
    public final List<SysMLElement> ownedElements;
    public final String type;

    public SysMLElement(Element element) {
        name = element.getName();
        kind = getKind(element);
        ownedElements = element.getOwnedRelationship().stream()
                .flatMap(relationship -> relationship.getOwnedRelatedElement().stream())
                .map(SysMLElement::new).collect(Collectors.toList());
        type = getType(element);
    }

    private String getType(Element element) {
        switch(kind) {
            case ATTRIBUTE_USAGE:
                return ((AttributeUsage) element).getAttributeDefinition().stream().map(attribute -> attribute.getName()).findFirst().orElse(null);
            case PART_USAGE:
                return ((PartUsage) element).getPartDefinition().stream().map(attribute -> attribute.getName()).findFirst().orElse(null);
            case ITEM_USAGE:
            case ITEM_USAGE_IN:
            case ITEM_USAGE_OUT:
            case ITEM_USAGE_INOUT:
                return ((ItemUsage) element).getItemDefinition().stream().map(attribute -> attribute.getName()).findFirst().orElse(null);
        }
        return null;
    }

    private SysMLKind getKind(Element item) {
        if(item instanceof AttributeDefinition) {
            return SysMLKind.ATTRIBUTE_DEFINITION;
        } else if(item instanceof AttributeUsage) {
            return SysMLKind.ATTRIBUTE_USAGE;
        } else if(item instanceof PartDefinition) {
            return SysMLKind.PART_DEFINITION;
        } else if(item instanceof PartUsage) {
            return SysMLKind.PART_USAGE;
        } else if (item instanceof PortDefinition){
            return SysMLKind.PORT_DEFINITION;
        } else if(item instanceof PortUsage) {
            return SysMLKind.PORT_USAGE;
        } else if(item instanceof ItemDefinition) {
            return SysMLKind.ITEM_DEFINITION;
        } else if(item instanceof ItemUsage) {
            var usage = (ItemUsage)item;
            var dir = usage.getOwningFeatureMembership().getDirection();
            if(dir == FeatureDirectionKind.IN) {
                return SysMLKind.ITEM_USAGE_IN;
            } else if (dir == FeatureDirectionKind.OUT) {
                return SysMLKind.ITEM_USAGE_OUT;
            } else if (dir == FeatureDirectionKind.INOUT) {
                return SysMLKind.ITEM_USAGE_INOUT;
            }
            return SysMLKind.ITEM_USAGE;
        } else if(item instanceof Package) {
            return SysMLKind.PACKAGE;
        } else if (item instanceof Namespace) {
            return SysMLKind.NAMESPACE;
        }
        return SysMLKind.ELEMENT;
    }
}
