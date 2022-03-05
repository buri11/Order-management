package model;

/**
 * The type Client.
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private int age;

    /**
     * Instantiates a new Client.
     */
    public Client(){

    }

    /**
     * Instantiates a new Client.
     *
     * @param name  the name
     * @param email the email
     * @param age   the age
     */
    public Client(String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return "Client: [id: " + id + ", name: " + name + ", email: " + email + ", age: " + age + "]";
    }
}
