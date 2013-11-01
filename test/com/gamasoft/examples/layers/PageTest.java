package com.gamasoft.examples.layers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageTest {

    public static final String COUNTRY = "gb";
    private static final List<String> MODULE_IDS = new ArrayList<>();
    private static final String LAYOUT_XML = "<xml>";
    private static final List<MapOfString> MODULES_XML = new ArrayList<>();
    public final String EXPECTED_JSON = "{}";
    private Renderer renderer = new SimpleRenderer();

    @Test
    public void composePage() throws Exception {
        //we can use a single test here from xml to json

        //only mock Repository, because implementation is in another sub-project
        Repository repo = mock(Repository.class);
        when(repo.getLayoutPage(COUNTRY)).thenReturn(LAYOUT_XML);
        when(repo.getModules(MODULE_IDS)).thenReturn(MODULES_XML);

        UserData context = new UserData(COUNTRY);

        //instead of MapOfStrings we use a proper object to keep layout
        Layout page  = Layout.buildFromXml(repo.getLayoutPage(context.getCountry()));

        //we use Module object to keep module properties and methods
        List<Module> moduleList = page.prepareModules(repo, context);

        //render the list of modules, easier than with strings properties
        String jsonPage = renderer.toJson(moduleList);

        //checking matcher
        assertThat(EXPECTED_JSON, sameJson(jsonPage));
    }

    private Matcher<String> sameJson(String jsonPage) {
        return new JsonMatcher(jsonPage);
    }

    private class JsonMatcher extends BaseMatcher<String> {

        private String expected;

        public JsonMatcher(String expected) {
            this.expected = expected;
        }

        @Override
        public boolean matches(Object o) {
            return expected.equals(o); //here some json parsing logic to match equivalent json
        }

        @Override
        public void describeTo(Description description) {
        //description of the current object
        }
    }

    private class SimpleRenderer implements Renderer {
        @Override
        public String toJson(List<Module> modules) {
            return "{}";
        }
    }
}
