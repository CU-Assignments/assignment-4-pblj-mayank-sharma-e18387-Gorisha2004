class TicketBooking implements Runnable {
    private static int availableSeats = 10; // Number of available seats
    private boolean isVIP; // Flag to check if the user is a VIP
    private static final Object lock = new Object(); // Lock object for synchronization

    // Constructor to initialize VIP status
    public TicketBooking(boolean isVIP) {
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        synchronized (lock) { // Synchronizing the seat booking process
            if (availableSeats > 0) {
                // VIP bookings get higher priority
                if (isVIP) {
                    System.out.println(Thread.currentThread().getName() + " (VIP): Booking seat...");
                    availableSeats--;
                    System.out.println(Thread.currentThread().getName() + " (VIP): Seat booked successfully. Remaining seats: " + availableSeats);
                } else {
                    System.out.println(Thread.currentThread().getName() + " (Regular): Booking seat...");
                    availableSeats--;
                    System.out.println(Thread.currentThread().getName() + " (Regular): Seat booked successfully. Remaining seats: " + availableSeats);
                }
            } else {
                System.out.println(Thread.currentThread().getName() + ": Sorry, no seats available.");
            }
        }
    }

    public static void main(String[] args) {
        // Create threads for VIP and regular bookings
        TicketBooking vipBooking = new TicketBooking(true); // VIP booking
        TicketBooking regularBooking = new TicketBooking(false); // Regular booking

        Thread vipThread1 = new Thread(vipBooking, "VIP Booking 1");
        Thread vipThread2 = new Thread(vipBooking, "VIP Booking 2");
        Thread regularThread1 = new Thread(regularBooking, "Regular Booking 1");
        Thread regularThread2 = new Thread(regularBooking, "Regular Booking 2");
        
        // Set thread priorities: VIP bookings should have higher priority
        vipThread1.setPriority(Thread.MAX_PRIORITY);
        vipThread2.setPriority(Thread.MAX_PRIORITY);
        regularThread1.setPriority(Thread.NORM_PRIORITY);
        regularThread2.setPriority(Thread.NORM_PRIORITY);

        // Start the threads
        vipThread1.start();
        vipThread2.start();
        regularThread1.start();
        regularThread2.start();

        try {
            // Wait for all threads to finish
            vipThread1.join();
            vipThread2.join();
            regularThread1.join();
            regularThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Booking system closed.");
    }
}
