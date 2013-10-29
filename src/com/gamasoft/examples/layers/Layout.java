package com.gamasoft.examples.layers;

import java.util.ArrayList;
import java.util.List;

public class Layout {

    public Layout() {


    }

    public static Layout buildFromXml(String layoutPage) {
        return new Layout();
        //parse xml and set attribute
    }

    public List<Module> prepareModules(Repository repo, UserData context) {
        return new ArrayList<>();
    }
}
