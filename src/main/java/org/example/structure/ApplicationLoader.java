package org.example.structure;

import org.example.structure.lib.Return;

import java.io.IOException;

public class ApplicationLoader {

    private Application application;

    public ApplicationLoader(Application application) {
        this.application = application;
    }

    public Return start() throws IOException {
        application.startApplication();
        return new Return.ReturnBuilder(true).build();
    }

}
