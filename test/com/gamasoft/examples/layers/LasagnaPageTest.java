package com.gamasoft.examples.layers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class LasagnaPageTest {

    private Renderer renderer;
    private String expectedJson;

    @Test
    public void composePage() throws Exception {
        Page page = new Page();

        Repository repo = mock(Repository.class);
        UserData context = new UserData();

        MapOfString pageDescriptor  = repo.getPageDescriptor(context.getCountry());


        List<String> moduleIds = page.selectModules(context, pageDescriptor);


        List<MapOfString> modules = repo.getModules(moduleIds);

        String jsonPage = page.compose(modules);

        assertThat(expectedJson, sameJson(jsonPage));
    }

    private Matcher<String> sameJson(String jsonPage) {
        return new JsonMatcher();
    }

    private class JsonMatcher extends BaseMatcher<String> {

        @Override
        public boolean matches(Object o) {
            return true;
        }

        @Override
        public void describeTo(Description description) {

        }
    }
}
