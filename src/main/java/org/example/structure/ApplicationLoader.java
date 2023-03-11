package org.example.structure;

import java.io.IOException;

public class ApplicationLoader {

    private Application application;

    public ApplicationLoader(Application application) {
        this.application = application;
    }

    public Return start() throws IOException {
        application.startApplication();
        return new Return.ResultBuilder(true).build();
    }
}
