package de.unidue.proxyapi.data;

import com.hp.hpl.jena.util.iterator.Filter;
import de.unidue.proxyapi.data.entities.EntityProperty;

/**
 * Der Property-Filter, der alle Eigenschaften akzeptiert
 */
public class AcceptAllFilter extends Filter<EntityProperty> {
    @Override
    public boolean accept(final EntityProperty o) {
        return true;
    }
}
