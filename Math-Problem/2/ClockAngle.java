public class ClockAngle {
    public static double angleClock(int hour, int minutes) {
        // Calculate the angle of the minute hand (360 degrees / 60 minutes = 6 degrees per min)
        double minuteAngle = minutes * 6;
        
        // Calculate the angle of the hour hand 
        // (360 degrees / 12 hours = 30 degrees per hour)
        // Plus the extra movement caused by the minutes (30 degrees / 60 mins = 0.5 degrees per min)
        double hourAngle = (hour % 12 * 30) + (minutes * 0.5);
        
        // Find the absolute difference between the two angles
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // Return the smaller angle (it can't be more than 180 degrees)
        return Math.min(diff, 360 - diff);
    }

    public static void main(String[] args) {
        int h = 3, m = 15;
        System.out.println("Angle at " + h + ":" + m + " is " + angleClock(h, m) + " degrees.");
    }
}