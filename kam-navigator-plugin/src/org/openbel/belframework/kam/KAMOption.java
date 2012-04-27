package org.openbel.belframework.kam;

import com.selventa.belframework.ws.client.Kam;

public final class KAMOption implements Comparable<KAMOption> {
    private final Kam kam;
    
    public KAMOption(Kam kam) {
        this.kam = kam;
    }
    
    public Kam getKam() {
        return kam;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return kam.getName();
    }
    
    @Override
    public int compareTo(KAMOption o) {
        if (o == null) {
            return 1;
        }
        return this.toString().compareTo(o.toString());
    }

}
