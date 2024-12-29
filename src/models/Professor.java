package models;

public class Professor {
    private String name;
    private String department;
    private int experience;

    public void setData(String name, String department, int experience) {
        this.name = name;
        this.department = department;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

//    public void displayProfessorInfo() {
//        System.out.println("Name: " + name + ", Department: " + department + ", Experience: " + experience + " years");
//    }


    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", experience=" + experience +
                '}';
    }
}
