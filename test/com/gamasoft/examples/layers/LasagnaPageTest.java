package com.gamasoft.examples.layers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class LasagnaPageTest {

    private static final List<MapOfString> STUB_MODULES = new ArrayList<>();
    private Renderer renderer;
    private String expectedJson = "{}";

    @Test
    public void retrieveModules() throws Exception {
        Page page = new Page();

        Repository repo = mock(Repository.class);
        UserData context = new UserData("gb");

        //first let's get the page layout for the user country in a parsed xml
        MapOfString pageDescriptor  = repo.getPageDescriptor(context.getCountry());

        //then get the id of actual modules needed matching user context with page layout
        List<String> moduleIds = page.selectModules(context, pageDescriptor);

        //get the modules as string properties from parsed xml
        List<MapOfString> modules = repo.getModules(moduleIds);

        //to be safe we need to make sure we are using same stub in this test and the next
        assertEquals(STUB_MODULES, modules);
    }

    @Test
    public void composePage() throws Exception {
        //here we continue the flow from the previous test
        Page page = new Page();

        //get the modules as string properties from parsed xml
        List<MapOfString> modules = STUB_MODULES;

        //compose json page from properties
        String jsonPage = page.compose(modules);


        assertEquals(expectedJson, jsonPage);
    }




}
