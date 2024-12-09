package Pojos;

/**
 * Represents a symptom with a unique identifier and a name.
 */
public class Symptoms {
    private int id;
    private String name;

    /**
     * Constructs a Symptoms object with the specified name.
     *
     * @param name the name of the symptom.
     */
    public Symptoms(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier of the symptom.
     *
     * @return the ID of the symptom.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the symptom.
     *
     * @param id the new ID of the symptom.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the symptom.
     *
     * @return the name of the symptom.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the symptom.
     *
     * @param name the new name of the symptom.
     */
    public void setNombre(String name) {
        this.name = name;
    }

    /**
     * Returns the string representation of the symptom.
     *
     * @return the name of the symptom.
     */
    @Override
    public String toString() {
        return name;
    }
}
