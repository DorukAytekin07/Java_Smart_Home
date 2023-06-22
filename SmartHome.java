import java.util.Scanner;

public class SmartHome implements IHome{
    
    private int arraySize = 10;
    private int index = 0;
    private IDevice[] devices = new IDevice[arraySize];
    
    public SmartHome(){
        Heater heater = new Heater("Main Heater");
        devices[index] = heater;
        index++;
    }
    @Override
    public IDevice[] getDevices() {
        return devices;
    }
    @Override
    public ITemperatureDevice getHeater() {
        for (int i = 0; i < devices.length; i++){
            if(Test.getAsITemparatureDevice(devices[i]) instanceof ITemperatureDevice){
                return (Heater) devices[i];
            } 
        }
        return null;
    }
    public void addBulb(String name){
        if(index < arraySize){
            LightBulb lightBulb = new LightBulb(name);
            devices[index] = lightBulb;
            index++;
        }     
    }
    public  void addFan(String name){
        if(index < arraySize){

            Fan fan = new Fan(name);
            devices[index] = fan;
            index++;
        }
    }
    public void adjustAll (boolean turnedOn){
        for (int i = 0; i < devices.length; i++){
            if(Test.getAsILightDevice(devices[i]) instanceof ILightDevice){
                LightBulb bulb = (LightBulb) devices[i];
                if(turnedOn){   
                    bulb.turnOn();
                    bulb.adjustColor(getHeater());
                }
                else{
                    bulb.turnOff();
                }
            }
            else if(Test.getAsISwitchableDevice(devices[i]) instanceof ISwitchableDevice){
                Fan fan = (Fan) devices[i];
                if(turnedOn){
                    fan.turnOn();
                }
                else{
                    fan.turnOff();
                }
            }
        }
    }
    public  void randomColor(){
        for (int i = 0; i < devices.length; i++){
            int max = 20000;
            int min = 1000;
            if(Test.getAsILightDevice(devices[i]) instanceof ILightDevice){
                int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
                LightBulb bulb = (LightBulb) devices[i];
                bulb.setColor(random_int);
            }
        }    
    }
    public  void makeComfortable(){
        for(int i = 0; i < devices.length; i++){
            if(Test.getAsILightDevice(devices[i]) instanceof ILightDevice){
                LightBulb bulb = (LightBulb) devices[i];
                bulb.turnOn();
                bulb.setColor(3500);
            }
            else if(Test.getAsISwitchableDevice(devices[i]) instanceof ISwitchableDevice){
                Fan fan = (Fan) devices[i];
                fan.turnOn();
            }
            else if(Test.getAsITemparatureDevice(devices[i]) instanceof ITemperatureDevice){
                Heater heater = (Heater) devices[i];
                heater.setTemperature(22);
            }
        }
    }
    public static void main(String[] args) {
        SmartHome home = new SmartHome(); 
        
        Scanner scanner = new Scanner(System.in);
        Scanner nameScanner = new Scanner(System.in);

        while(true){

            System.out.println("1:See Your Home State");
            System.out.println("2:adjust your all devices");
            System.out.println("3:random color your bulbs");
            System.out.println("4:make comfortable your's home");
            System.out.println("5:add new bulb");
            System.out.println("6:add new fan");
            System.out.println("7:set your's heater");
            System.out.println("8:set your's bulb");
            System.out.println("9:set your's fan");
            System.out.println("10:exit from program");

            int wish = scanner.nextInt();

            if(wish == 1){
                SmartHub.synchronize(home);
                System.out.println("exit form here press 1");
                while(true){
                    int state = scanner.nextInt();
                    if(state == 1){
                        break;
                    }
                }  
            }
            else if(wish == 2){
                System.out.println("open for all press 1 close for all press 0");
                int decide = scanner.nextInt();
                if(decide == 0){
                    home.adjustAll(false);
                    
                }
                else if(decide == 1){
                    home.adjustAll(true);
                    
                }
            }
            else if(wish == 3){
                home.randomColor();
            }
            else if(wish == 4){
                home.makeComfortable();
            }
            else if(wish == 5){
                System.out.println("set your's bulb name");
                String nameBulb = nameScanner.nextLine();
                home.addBulb(nameBulb);
                
            }
            else if(wish == 6){
                System.out.println("set your's fan name");
                String nameFan = nameScanner.nextLine();
                home.addFan(nameFan);
                
            }
            else if(wish == 7){
                System.out.println("enter your wish temparature 12-28\u00B0C");
                int temp = scanner.nextInt();
                home.getHeater().setTemperature(temp);
            }
            else if(wish == 8){
                boolean pass = false;
                int[] bulbs = new int[10];
                int bulbIndex = 0;
                for(int i = 0; i < home.devices.length; i++){
                    if(Test.getAsILightDevice(home.devices[i]) instanceof ILightDevice){
                        pass = true;
                        bulbs[bulbIndex] = i;
                        bulbIndex++;
                    }
                }
                if(pass){
                    System.out.println("Bulbs");
                    for (int i = 0; i < bulbIndex; i++){
                        LightBulb bulb = (LightBulb) home.devices[bulbs[i]];
                        System.out.print(i+1);
                        System.out.print(" " + bulb.getName());
                        System.out.println(" " + bulb.isTurnedOn());
                    }
                    System.out.println("Select your fan with ID number");
                    int selection = scanner.nextInt();
                    if(selection > bulbIndex || selection == 0){
                        System.out.println("You Dont have that bulb for security reasons you will retun main menu");
                        System.out.println("exit form here press 1");
                        while(true){
                            int state = scanner.nextInt();
                            if(state == 1){
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("press 1 for turn on the bulb press 0 turn off the bulb");
                        int selection2 = scanner.nextInt();
                        LightBulb bulb = (LightBulb) home.devices[bulbs[selection-1]];
                        if(selection2 == 1){
                            bulb.turnOn();
                            System.out.println("set your bolb temparature 1000-20000K");
                            int value = scanner.nextInt();
                            bulb.setColor(value);
                        }
                        else if(selection2 == 0){
                            bulb.turnOff();
                        }
                    }
                }
                else{
                    System.out.println("You Dont have any bulb");
                    System.out.println("exit form here press 1");
                    while(true){
                        int state = scanner.nextInt();
                        if(state == 1){
                            break;
                        }
                    }
                }
            }
            else if(wish == 9){
                boolean pass = false;
                int[] fans = new int[10];
                int fanIndex = 0;
                for(int i = 0; i < home.devices.length; i++){
                    if(Test.getAsISwitchableDevice(home.devices[i]) instanceof ISwitchableDevice){
                        pass = true;
                        fans[fanIndex] = i;
                        fanIndex++;
                    }
                }
                if(pass){
                    System.out.println("Fans");
                    for (int i = 0; i < fanIndex; i++){
                        Fan fan = (Fan) home.devices[fans[i]];
                        System.out.print(i+1);
                        System.out.print(" " + fan.getName());
                        System.out.println(" " + fan.isTurnedOn());
                        
                        
                    }
                    System.out.println("Select your fan with ID number");
                    int selection = scanner.nextInt();
                    if(selection > fanIndex || selection == 0){
                        System.out.println("You Dont have that fan for security reasons you will retun main menu");
                        System.out.println("exit form here press 1");
                        while(true){
                            int state = scanner.nextInt();
                            if(state == 1){
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("press 1 for turn on the fan press 0 turn off the fan");
                        int selection2 = scanner.nextInt();
                        Fan fan = (Fan) home.devices[fans[selection-1]];
                        if(selection2 == 1){
                            fan.turnOn();
                            
                        }
                        else if(selection2 == 0){
                            fan.turnOff();
                        }
                    }
                }
                else{
                    System.out.println("You Dont have any fan");
                    System.out.println("exit form here press 1");
                    while(true){
                        int state = scanner.nextInt();
                        if(state == 1){
                            break;
                        }
                    }
                }
            }
            else if(wish == 10){
                scanner.close();
                nameScanner.close();
                break;
            }
        }  
    }
}