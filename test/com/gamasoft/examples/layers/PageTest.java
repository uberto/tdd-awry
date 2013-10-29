package com.gamasoft.examples.layers;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

public class PageTest {

    private Renderer render = new SimpleRenderer();
    private String expectedJson = "{}";

    @Test
    public void composePage() throws Exception {

        Repository repo = mock(Repository.class);
        UserData context = new UserData();

        Layout page  = Layout.buildFromXml(repo.getLayoutPage(context.getCountry()));

        List<Module> moduleList = page.prepareModules(repo, context);

        String jsonPage = render.toJson(moduleList);

        matchJson(expectedJson, jsonPage);
    }

    private void matchJson(String expectedJson, String jsonPage) {


    }

    private class SimpleRenderer implements Renderer {
        @Override
        public String toJson(List<Module> modules) {
            return "{}";
        }
    }
}
