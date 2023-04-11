package org.example;

import org.example.structure.*;
import org.example.structure.lib.Return;
import org.example.view.UIWindow;


public class TestApplication implements Application {
    private UIWindow window;

    public TestApplication() {
        this.window = new UIWindow();
    }

    @Override
    public Return startApplication(){

        return new Return.ReturnBuilder(true).build();
    }


}
