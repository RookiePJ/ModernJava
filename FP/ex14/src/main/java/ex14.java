import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static utils.ExceptionUtils.rethrowConsumer;

/**
 * This example shows how a modern Java {@link Consumer} interface can
 * be used with the {@code forEach()} method to print out the values
 * in a {@link List} by binding the {@code System.out.println()} method to
 * the {@link Consumer} parameter on the {@code forEach()} method. It also
 * shows how to sort a {@link List} in ascending and descending order
 * using a {@link Comparator} and a {@link Function} functional interface.
 */
public class ex14 {
    /**
     * A simple wrapper around the {@link Thread} class.
     */
    static class MyThread extends Thread {
        /**
         * Constructor initializes the name of the {@link Thread}.
         * @param name The name of the {@link Thread}
         */
        MyThread(String name) {
            super(name);
        }

        /**
         * Print the name of the {@link Thread} and the thread's id.
         */
        @Override
        public void run() {
            System.out.println("[Thread "
                               + threadId()
                               + "] "
                               + getName());
        }
    }

    /**
     * The main entry point into the test program.
     */
    static public void main(String[] argv) {
        // Create a List of Thread objects.
        var threads = Arrays
            .asList(new MyThread("Larry"),
                    new MyThread("Curly"),
                    new MyThread("Moe"));

        System.out.println("Original List:");

        // forEach() takes a Consumer, which is bound to the
        // System.out println() method.
        threads.forEach(System.out::println);

        // Sort the Thread objects by their names in ascending order.
        threads.sort(Comparator.comparing(Thread::getName));

        System.out.println("List sorted in ascending order:");

        // forEach() takes a Consumer, which is bound to the
        // System.out println() method.
        threads.forEach(System.out::println);

        // Sort the Thread objects by their names in descending order.
        threads.sort(Comparator.comparing(Thread::getName).reversed());

        System.out.println("List sorted in descending order:");

        // forEach() takes a Consumer, which is bound to the
        // System.out println() method.
        threads.forEach(System.out::println);

        System.out.println("Starting the Thread objects:");
        // Iterate through the List of Thread objects and pass a method
        // reference that starts each Thread.
        threads.forEach(Thread::start);

        // This concise solution iterates through the threads and pass
        // the Thread.join() method reference as a barrier
        // synchronizer to wait for all threads to finish.  Note how
        // rethrowConsumer() converts a checked exception to an
        // unchecked exception.
        threads
            .forEach(rethrowConsumer(Thread::join));
    }
}

