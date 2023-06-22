class SmartHub {
    public static void synchronize(IHome home) {
        IDevice[] devices = home.getDevices(); 
        for(int i=0; i < devices.length; i++){
            if(Test.getAsILightDevice(devices[i]) instanceof ILightDevice){
                LightBulb bulb = (LightBulb) devices[i];
                System.out.print(bulb.getName() + " ");
                System.out.print(bulb.getColorTemperature() + " ");
                System.out.println(bulb.isTurnedOn());
            }
            
            else if(Test.getAsISwitchableDevice(devices[i]) instanceof ISwitchableDevice){
                Fan fan = (Fan) devices[i];
                System.out.print(fan.getName() + " ");
                System.out.println(fan.isTurnedOn());
            }
            
            else if(Test.getAsITemparatureDevice(devices[i]) instanceof ITemperatureDevice){
                Heater heater = (Heater) devices[i];
                System.out.print(heater.getName() + " ");
                System.out.println(heater.getTemperature());
            }
            
        } 
    }    
}