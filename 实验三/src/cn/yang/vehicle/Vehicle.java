package cn.yang.vehicle;

public class Vehicle {

    private int speed;
    private int size;
    private String name;

    Vehicle() {

    }

    Vehicle(String name, int speed, int size) {
        setName(name);
        setSize(size);
        setSpeed(speed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0 ) {
            System.out.println("Error : speed < 0.");
        } else {
            this.speed = speed;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size <= 0) {
            System.out.println("Error : size <= 0.");
        }else {
            this.size = size;
        }
    }

    public void speedUP(int speed) {
        this.speed += speed;
    }

    public void speedDown(int speed) {
        this.speed -= speed;
        if (this.speed < 0) {
            this.speed = 0;
        }
    }

    public void show() {
        System.out.println("*******************");
        System.out.println("Name:" + this.name);
        System.out.println("Size:" + this.size);
        System.out.println("Speed:" + this.speed);
        System.out.println("*******************\n");
    }

    public static void main(String[] args) {
        Vehicle car = new Vehicle("Muscle Car", 200, 10);
        car.show();
        car.speedUP(10);
        car.show();
        car.speedDown(40);
        car.show();
    }
}