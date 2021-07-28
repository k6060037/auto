package com.yeah.auto.projections;

import java.util.UUID;

public interface AutoProjection {
    UUID getId();
    String getPlate();
    String getMark();
    String getModel();
    UUID getAutoGroupId();

}
