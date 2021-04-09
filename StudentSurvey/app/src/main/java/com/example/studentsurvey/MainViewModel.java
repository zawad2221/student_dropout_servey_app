package com.example.studentsurvey;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.studentsurvey.model.StudentDetails;
import com.example.studentsurvey.model.TeamMember;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends ViewModel {
    Repository repository;

    public MediatorLiveData<StudentDetails> result = new MediatorLiveData<>();

    public void saveInfo(Context context, StudentDetails studentDetails){
        repository = Repository.getInstance();
        result = repository.saveInfo(context,studentDetails);
    }

    public static List<TeamMember> teamMembers = new ArrayList<>();
    public void initTeamMember(){
        TeamMember teamMemberZawad = new TeamMember();
        teamMemberZawad.name="Zawad Hossain";
        teamMemberZawad.role="Android App Development";
        teamMemberZawad.facebookIdLink="https://www.facebook.com/rifat.zawadhossain";
        teamMemberZawad.twitterIdLink="https://twitter.com/ZawadHossain12";
        teamMemberZawad.linkedInIdLink="https://www.linkedin.com/in/zawadhossain/";
        teamMemberZawad.imageDrawableResource=R.drawable.zawad;

        teamMembers.add(teamMemberZawad);
    }

    public static int page1nextObserver,page2nextObserver,page3nextObserver,page4nextObserver;

    public MainViewModel() {
        initNextButtonClick();
    }

    public enum FRAGMENT_TAGS{
        FRAGMENT1,
        FRAGMENT2,
        FRAGMENT3,
        FRAGMENT_SUBMIT, FRAGMENT4
    }
    public enum SOCIAL_APP{
        FACEBOOK,
        LINKED_IN,
        TWITTER
    }

    public MutableLiveData<FRAGMENT_TAGS> nextButtonClick;
    public static boolean callNextButtonClickObserver=false,
            callNextButtonClickObserver2=false,callNextButtonClickObserver3=false,
            callNextButtonClickObserver4=false;
    public void initNextButtonClick(){
        if(nextButtonClick==null) nextButtonClick = new MutableLiveData<>();
    }

    public StudentDetails studentDetails = new StudentDetails();




    public MutableLiveData<FRAGMENT_TAGS> currentFragment = new MutableLiveData<>(FRAGMENT_TAGS.FRAGMENT1);


    public List<String> nationalityArrayList = Arrays.asList(
            new String[]{"Bangladesh","North Korea", "India","Somalia", "Nigeria","Nepal", "China","South Korea","Malaysia"}
            );
//    public List<String> districtArrayList = Arrays.asList(
//            "Dhaka", "Pabna","Chittagong", "Barisal","Bogra", "Sylhet");
    public List<String> districtArrayList;
    public List<String> instituteNameArrayList = Arrays.asList(
            "Daffodil International University",
            "Dhaka University",
            "North South University",
            "Brac University",
            "City University",
            "Jahangirnagar University",
            "Bangladesh University of Engineering and Technology",
            "Khulna University of Engineering and Technology",
            "Chittagong University of Engineering and Technology",
            "Jagannath University",
            "Bangladesh University of Business and Technology (BUBT)",
            "Green University of Bangladesh",
            "Mawlana Bhashani Science and Technology University(MBSTU)",
            "Hajee Mohammad Danesh Science & Technology University",
            "American International University-Bangladesh (AIUB)",
            "United International University",
            "Bangabandhu Sheikh Mujibur Rahman Science & Technology University",
            "University of Barisal",
            "University of Liberal Arts Bangladesh",
            "Dhaka International University: DIU",
            "Barisal University",
            "European University of Bangladesh",
            "Ranada Prasad Saha University"
            );
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
