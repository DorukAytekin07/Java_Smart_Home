class Fan implements IDevice,ISwitchableDevice{
    private boolean fanState;
    private String name;
    public Fan(String name){
        fanState = false;
        this.name = name; 
    }
    @Override
    public boolean isTurnedOn() {
        return fanState;
    }
    @Override
    public void turnOn() {
        fanState = true;
    }
    @Override
    public void turnOff() {
        fanState = false;
    }
    public String getName() {
        return name;
    }
    
}
