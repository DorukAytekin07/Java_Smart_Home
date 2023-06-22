class LightBulb implements ILightDevice,IDevice,ISwitchableDevice {

    private int lightTemperature;
    private boolean state;
    private String name;
    public LightBulb (String name){
        lightTemperature = 2700;
        state = false;
        this.name = name;
    }

    @Override
    public int getColorTemperature() {
        return lightTemperature;
    }
    @Override
    public void setColor(int colorTemperature) {
        lightTemperature = colorTemperature;
    }
    @Override
    public int adjustColor(ITemperatureDevice device) {
        if (device.getTemperature() < 19){
            lightTemperature = 2700;
        }
        else if(device.getTemperature() > 19 && device.getTemperature() <= 23){
            lightTemperature = 3500;
        }
        else{
            lightTemperature = 4000;
        }
        return lightTemperature;
    }

    @Override
    public boolean isTurnedOn() {
        return state;
    }

    @Override
    public void turnOn() {
        state = true;
    }

    @Override
    public void turnOff() {
        state = false;
    }
    public String getName() {
        return name;
    }
}
