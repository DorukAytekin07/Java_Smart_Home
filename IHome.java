interface IHome {
    ITemperatureDevice getHeater();
    
    IDevice[] getDevices(); 
}