package com.example.studentsurvey.model;

import java.util.HashMap;
        import java.util.Map;

        import com.fasterxml.jackson.annotation.JsonAnyGetter;
        import com.fasterxml.jackson.annotation.JsonAnySetter;
        import com.fasterxml.jackson.annotation.JsonIgnore;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "created",
        "gender",
        "nationality",
        "place_of_birth",
        "department",
        "year",
        "institute",
        "time_of_group_study",
        "absent_in_a_semester",
        "ask_question_frequently",
        "use_additional_course_material",
        "result_of_last_semester",
        "meet_with_advisor",
        "parent_satisfied",
        "parent_education_status",
        "amount_of_drop_semester",
        "drop_reason",
        "due_amount",
        "result"
})

public class StudentDetails {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("created")
    private String created;
    @JsonProperty("gender")
    private Integer gender;
    @JsonProperty("nationality")
    private String nationality;
    @JsonProperty("place_of_birth")
    private String place_of_birth;
    @JsonProperty("department")
    private String department;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("institute")
    private String institute;
    @JsonProperty("time_of_group_study")
    private Integer time_of_group_study;
    @JsonProperty("absent_in_a_semester")
    private Integer absent_in_a_semester;
    @JsonProperty("ask_question_frequently")
    private Integer ask_question_frequently;
    @JsonProperty("use_additional_course_material")
    private Integer use_additional_course_material;
    @JsonProperty("result_of_last_semester")
    private String result_of_last_semester;
    @JsonProperty("meet_with_advisor")
    private Integer meet_with_advisor;
    @JsonProperty("parent_satisfied")
    private Integer parent_satisfied;
    @JsonProperty("parent_education_status")
    private Integer parent_education_status;
    @JsonProperty("amount_of_drop_semester")
    private Integer amount_of_drop_semester;
    @JsonProperty("drop_reason")
    private String drop_reason;
    @JsonProperty("due_amount")
    private String due_amount;
    @JsonProperty("result")
    private Integer result;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("gender")
    public Integer getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @JsonProperty("nationality")
    public String getNationality() {
        return nationality;
    }

    @JsonProperty("nationality")
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty("place_of_birth")
    public String getPlace_of_birth() {
        return place_of_birth;
    }

    @JsonProperty("place_of_birth")
    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    @JsonProperty("department")
    public String getDepartment() {
        return department;
    }

    @JsonProperty("department")
    public void setDepartment(String department) {
        this.department = department;
    }

    @JsonProperty("year")
    public Integer getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(Integer year) {
        this.year = year;
    }

    @JsonProperty("institute")
    public String getInstitute() {
        return institute;
    }

    @JsonProperty("institute")
    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @JsonProperty("time_of_group_study")
    public Integer getTime_of_group_study() {
        return time_of_group_study;
    }

    @JsonProperty("time_of_group_study")
    public void setTime_of_group_study(Integer time_of_group_study) {
        this.time_of_group_study = time_of_group_study;
    }

    @JsonProperty("absent_in_a_semester")
    public Integer getAbsent_in_a_semester() {
        return absent_in_a_semester;
    }

    @JsonProperty("absent_in_a_semester")
    public void setAbsent_in_a_semester(Integer absent_in_a_semester) {
        this.absent_in_a_semester = absent_in_a_semester;
    }

    @JsonProperty("ask_question_frequently")
    public Integer getAsk_question_frequently() {
        return ask_question_frequently;
    }

    @JsonProperty("ask_question_frequently")
    public void setAsk_question_frequently(Integer ask_question_frequently) {
        this.ask_question_frequently = ask_question_frequently;
    }

    @JsonProperty("use_additional_course_material")
    public Integer getUse_additional_course_material() {
        return use_additional_course_material;
    }

    @JsonProperty("use_additional_course_material")
    public void setUse_additional_course_material(Integer use_additional_course_material) {
        this.use_additional_course_material = use_additional_course_material;
    }

    @JsonProperty("result_of_last_semester")
    public String getResult_of_last_semester() {
        return result_of_last_semester;
    }

    @JsonProperty("result_of_last_semester")
    public void setResult_of_last_semester(String result_of_last_semester) {
        this.result_of_last_semester = result_of_last_semester;
    }

    @JsonProperty("meet_with_advisor")
    public Integer getMeet_with_advisor() {
        return meet_with_advisor;
    }

    @JsonProperty("meet_with_advisor")
    public void setMeet_with_advisor(Integer meet_with_advisor) {
        this.meet_with_advisor = meet_with_advisor;
    }

    @JsonProperty("parent_satisfied")
    public Integer getParent_satisfied() {
        return parent_satisfied;
    }

    @JsonProperty("parent_satisfied")
    public void setParent_satisfied(Integer parent_satisfied) {
        this.parent_satisfied = parent_satisfied;
    }

    @JsonProperty("parent_education_status")
    public Integer getParent_education_status() {
        return parent_education_status;
    }

    @JsonProperty("parent_education_status")
    public void setParent_education_status(Integer parent_education_status) {
        this.parent_education_status = parent_education_status;
    }

    @JsonProperty("amount_of_drop_semester")
    public Integer getAmount_of_drop_semester() {
        return amount_of_drop_semester;
    }

    @JsonProperty("amount_of_drop_semester")
    public void setAmount_of_drop_semester(Integer amount_of_drop_semester) {
        this.amount_of_drop_semester = amount_of_drop_semester;
    }

    @JsonProperty("drop_reason")
    public String getDrop_reason() {
        return drop_reason;
    }

    @JsonProperty("drop_reason")
    public void setDrop_reason(String drop_reason) {
        this.drop_reason = drop_reason;
    }

    @JsonProperty("due_amount")
    public String getDue_amount() {
        return due_amount;
    }

    @JsonProperty("due_amount")
    public void setDue_amount(String due_amount) {
        this.due_amount = due_amount;
    }

    @JsonProperty("result")
    public Integer getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(Integer result) {
        this.result = result;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "StudentDetails{" +
                ", gender=" + gender +
                ", nationality='" + nationality + '\'' +
                ", place_of_birth='" + place_of_birth + '\'' +
                ", department='" + department + '\'' +
                ", year=" + year +
                ", institute='" + institute + '\'' +
                ", time_of_group_study=" + time_of_group_study +
                ", absent_in_a_semester=" + absent_in_a_semester +
                ", ask_question_frequently=" + ask_question_frequently +
                ", use_additional_course_material=" + use_additional_course_material +
                ", result_of_last_semester='" + result_of_last_semester + '\'' +
                ", meet_with_advisor=" + meet_with_advisor +
                ", parent_satisfied=" + parent_satisfied +
                ", parent_education_status=" + parent_education_status +
                ", amount_of_drop_semester=" + amount_of_drop_semester +
                ", drop_reason='" + drop_reason + '\'' +
                ", due_amount='" + due_amount + '\'' +
                '}';
    }
}
