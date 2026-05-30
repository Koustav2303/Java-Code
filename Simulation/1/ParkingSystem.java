/**
 * PROBLEM: Design Parking System
 * * Design a parking system for a parking lot. The parking lot has three kinds of parking spaces: 
 * big, medium, and small, with a fixed number of slots for each size.
 * * Implement the ParkingSystem class:
 * - ParkingSystem(int big, int medium, int small) Initializes object with slots.
 * - bool addCar(int carType) Checks whether there is a parking space of carType (1=big, 2=med, 3=small).
 * * Approach:
 * Simulation using a simple state array.
 * We map the car type directly to array indices to keep the simulation O(1).
 */
public class ParkingSystem {
    // Index 0 is ignored. Index 1=big, 2=medium, 3=small.
    private int[] spaces;

    public ParkingSystem(int big, int medium, int small) {
        spaces = new int[]{0, big, medium, small};
    }
    
    public boolean addCar(int carType) {
        if (spaces[carType] > 0) {
            spaces[carType]--; // Take up a space
            return true;
        }
        return false; // Lot is full for this size
    }

    public static void main(String[] args) {
        ParkingSystem ps = new ParkingSystem(1, 1, 0);
        System.out.println("Park Big Car: " + ps.addCar(1));   // true
        System.out.println("Park Med Car: " + ps.addCar(2));   // true
        System.out.println("Park Small Car: " + ps.addCar(3)); // false (no space)
        System.out.println("Park Big Car: " + ps.addCar(1));   // false (already taken)
    }
}