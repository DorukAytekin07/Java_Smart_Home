class Test {
    static ILightDevice getAsILightDevice(IDevice device){
        if (device instanceof ILightDevice){
            return (LightBulb) device;
        }

        else{
            return null;
        } 

    }
    static ISwitchableDevice getAsISwitchableDevice(IDevice device){
        if(device instanceof ISwitchableDevice){
            return (ISwitchableDevice) device;
        }
        else{
            return null;
        }
    }
    static ITemperatureDevice getAsITemparatureDevice(IDevice device){
        if(device instanceof ITemperatureDevice){
            return (ITemperatureDevice) device;
        }
        else{
            return null;
        }
    }
}