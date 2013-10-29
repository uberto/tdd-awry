package com.gamasoft.examples.layers;

import java.util.List;

public interface Repository {
    MapOfString getPageDescriptor(String country);

    List<MapOfString> getModules(List<String> moduleIds);

    String getLayoutPage(String country);
}
