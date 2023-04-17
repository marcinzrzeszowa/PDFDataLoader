package org.example.machineservice;

import java.util.LinkedHashSet;

public abstract class Machine implements MachineRequirements {

    public static Machine MachineInstance(MachineType type){
        switch (type){
            case COORDINATE:
                return new CMMachine();
            case OPTICAL:
                return new OMMachine();
            case MICROSCOPE:
                return new MMachine();
        }
        return null;
    }
}
