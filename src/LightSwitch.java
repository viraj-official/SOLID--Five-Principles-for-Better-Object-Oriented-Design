public class LightSwitch {
    private Switchable device;

    public LightSwitch(Switchable device) {
        this.device = device;
    }

    public void switchOn() {
        device.turnOn();
    }

    public void switchOff() {
        device.turnOff();
    }
}
