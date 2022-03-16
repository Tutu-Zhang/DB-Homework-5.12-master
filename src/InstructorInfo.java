public class InstructorInfo {
    private int id;
    private String name;
    private String departName;
    private String course_id;
    private String title;
    private String sec_id;
    private String semester;
    private String year;

    public InstructorInfo(int id, String name, String departName, String course_id, String title, String sec_id, String semester, String year) {
        this.id = id;
        this.name = name;
        this.departName = departName;
        this.course_id = course_id;
        this.title = title;
        this.sec_id = sec_id;
        this.semester = semester;
        this.year = year;
    }

    public InstructorInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSec_id() {
        return sec_id;
    }

    public void setSec_id(String sec_id) {
        this.sec_id = sec_id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
