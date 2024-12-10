package Pojos;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a signal with the type and the list of integer values.
 */
public class Signal {
    /**
     * List of integer values representing the signal data.
     */
    private List<Integer> values;

    /**
     * Type of the signal, indicating whether it is an EMG or EDA signal.
     */
    private SignalType signalType;
    /**
     * Enum representing the type of the signal (EDA or EMG).
     */
    public enum SignalType {
        /**
         * Electromyography (EMG) signal type.
         */
        EMG,

        /**
         * Electrodermal Activity (EDA) signal type.
         */
        EDA
    }

    /**
     * Constructs a Signal with the specified type.
     *
     * @param signalType the type of the signal, either {@link SignalType#EMG} or {@link SignalType#EDA}.
     */
    public Signal(SignalType signalType) {
        this.values = new LinkedList<>();
        this.signalType = signalType;
    }

    /**
     * Gets the values of the signal.
     *
     * @return a list of integer values representing the signal.
     */
    public List<Integer> getValues() {
        return values;
    }

    /**
     * Sets the signal values for an EMG signal by parsing a space-separated string of integers.
     *
     * @param stringEMG the space-separated string representing EMG values.
     */
    public void setValuesEMG(String stringEMG) {
        this.values = stringToValues(stringEMG);
    }

    /**
     * Sets the signal values for an EDA signal by parsing a space-separated string of integers.
     *
     * @param stringEDA the space-separated string representing EDA values.
     */
    public void setValuesEDA(String stringEDA) {
        this.values = stringToValues(stringEDA);
    }

    /**
     * Gets the type of the signal.
     *
     * @return the type of the signal, either {@link SignalType#EMG} or {@link SignalType#EDA}.
     */
    public SignalType getSignalType() {
        return signalType;
    }

    /**
     * Sets the type of the signal.
     *
     * @param signalType the new type of the signal, either {@link SignalType#EMG} or {@link SignalType#EDA}.
     */
    public void setSignalType(SignalType signalType) {
        this.signalType = signalType;
    }

    /**
     * Converts a space-separated string of integers into a list of integer values.
     *
     * @param str the space-separated string to be converted.
     * @return a list of integers parsed from the string.
     *         If the string contains invalid integers, those values will be ignored.
     */
    public List<Integer> stringToValues(String str) {
        values.clear(); // Clears the list before adding new values.
        String[] tokens = str.split(" "); // Splits the string by spaces.

        int size = tokens.length;
        if (size > 2) {
            for (int i = 0; i < size; i++) {
                try {
                    values.add(Integer.parseInt(tokens[i])); // Converts each fragment to an Integer and adds it to the list.
                } catch (NumberFormatException e) {
                    // Handles errors if any value is not a valid Integer.
                    System.out.println("Error converting the values: " + tokens[i]);
                }
            }
        }

        return values;
    }

    /**
     * Returns a string representation of the signal.
     *
     * @return a string containing the signal's values and type.
     */
    @Override
    public String toString() {
        return "Signal{" +
                "values=" + values +
                ", signalType=" + signalType +
                '}';
    }
}









