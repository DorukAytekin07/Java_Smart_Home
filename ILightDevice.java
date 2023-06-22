public interface ILightDevice {
    int getColorTemperature();

    void setColor(int colorTemperature);
    
    int adjustColor(ITemperatureDevice device);
}