package org.example;

import org.example.machine.CMMachineReport;
import org.example.machine.MachineReport;
import org.example.machine.MachineType;
import org.example.machine.OMMachineReport;
import org.example.report.*;
import org.example.structure.*;
import org.example.ui.UIWindow;

import java.io.IOException;


public class TestApplication implements Application {
    private UIWindow window;

    public TestApplication() {
        this.window = new UIWindow();
    }

    @Override
    public Return startApplication(){

        return new Return.ResultBuilder(true).build();
    }


}
