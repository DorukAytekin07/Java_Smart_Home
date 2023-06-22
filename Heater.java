class Heater implements IDevice,ITemperatureDevice {
    private double heaterTemparature;
    private String name;
    public Heater(String name){
        heaterTemparature = 18;
        this.name = name;
    }
    @Override
    public double getTemperature() {
        return heaterTemparature;
    }
    @Override
    public void setTemperature(double temperature) {
        if (temperature < 12){
            heaterTemparature = 12;
        }
        else if (temperature > 28){
            heaterTemparature = 28;
        }
        else{
            heaterTemparature = temperature;
        }
    }
    public String getName() {
        return name;
    }
}