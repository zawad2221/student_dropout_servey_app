package com.example.studentsurvey;

import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends ViewModel {
    public enum FRAGMENT_TAGS{
        FRAGMENT1,
        FRAGMENT2,
        FRAGMENT3,
        FRAGMENT4
    }
    public FRAGMENT_TAGS currentFragment = FRAGMENT_TAGS.FRAGMENT1;
    public List<String> nationalityArrayList = Arrays.asList(
            new String[]{"Bangladesh", "India","Somalia", "Nigeria","Nepal", "China"}
            );
    public List<String> districtArrayList = Arrays.asList(
            "Dhaka", "Pabna","Chittagong", "Barisal","Bogra", "Sylhet");
    public List<String> instituteNameArrayList = Arrays.asList(
            "Daffodil International University", "Dhaka University","North South University", "Brac University","City University", "Jahangirnagar University");
    public List<String> departmentNameArrayList = Arrays.asList(
            "Computer Science and Engineering",
            "Software Engineering",
            "Multimedia and Creative Technology",
            "General Education Development",
            "Environmental Science and Disaster Management",
            "Computing and Information System",
            "Information Technology Management",
            "Nutrition and Food Engineering",
            "Public Health",
            "English",
            "Journalism and Mass Communication",
            "Development Studies",
            "ETE",
            "TE",
            "EEE",
            "Architecture",
            "Civil Engineering",
            "Business Administration",
            "Business Studies",
            "Real State",
            "Tourism & Hospital Management",
            "Innovation & Entrepreneurship",
            "Agricultural Science",
            "IBA",
            "MBA",
            "Political Science",
            "Physics",
            "Chemistry",
            "Botany",
            "Zoology",
            "Mathematics",
            "Religion",
            "Statistics",
            "Urban",
            "Language",
            "Medicine",
            "Medical",
            "Finance",
            "Accounting",
            "Economics",
            "Fisheries & Marine Bioscience",
            "Mechanical Engineering",
            "Civil Engineering"
    );
    public List<String> dropReasonArrayList = Arrays.asList(
            "Financial", "Depression","Personal", "Irregularity","Poor Academic Result");
}
