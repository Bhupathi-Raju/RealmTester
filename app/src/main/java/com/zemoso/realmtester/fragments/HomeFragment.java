package com.zemoso.realmtester.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zemoso.realmtester.RealmTesterApplication;
import com.zemoso.realmtester.adapters.RecyclerAdapter;
import com.zemoso.realmtester.R;
import com.zemoso.realmtester.models.Achievement;
import com.zemoso.realmtester.models.UserData;
import com.zemoso.realmtester.models.WorkDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    private List<String> userFirstNameList = Arrays.asList("Bhupathi","Saikat","Atif","Anvesh","Jimmy");
    private List<String> userLastNameList = Arrays.asList("Raju","Dey","Mohammed","Anvesh","Jose");
    private List<String> userCompanyNameList = Arrays.asList("ABC","DEF","GHI","JKL","MNO");
    private List<Integer> userExperienceList = Arrays.asList(1,2,3,4,5);
    private List<String> userCertificationList = Arrays.asList("Amateur","Beginner","Semi-Pro","Pro","Legendary");
    private List<String> userCoursesList = Arrays.asList("C","C++","JAVA","SWIFT","JAVA SCRIPT");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Realm realm = RealmTesterApplication.getUserRealm();
        List<UserData> userDataList = realm.copyFromRealm(realm.where(UserData.class).findAll());
        List<WorkDetails> workDetailsList = realm.copyFromRealm(realm.where(WorkDetails.class).findAll());
        realm.close();
        Realm achievementRealm = RealmTesterApplication.getAchievementRealm();
        List<Achievement> achievementList = achievementRealm.copyFromRealm(
                achievementRealm.where(Achievement.class).findAll());
        if(userDataList.size()==0) {
            Log.d("User data","populating");
            userDataList = populateUserData();
        }
        if(workDetailsList.size()==0) {
            populateWorkDetails();
            Log.d("Work Details","populating");
        }
        if(achievementList.size()==0){
            populateAchievements();
            Log.d("achievements","populating");
        }

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(userDataList,
                                                            (RecyclerAdapter.AdapterInterface) getActivity());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    public List<UserData> populateUserData(){
        Realm realm = RealmTesterApplication.getUserRealm();
        int i=0;
        for (String firstName: userFirstNameList) {
            UserData userData = new UserData();
            userData.setFirstName(firstName);
            userData.setLastName(userLastNameList.get(i));
            userData.setId(i);
            realm.beginTransaction();
            realm.insertOrUpdate(userData);
            realm.commitTransaction();
            i++;
        }
        RealmResults<UserData> userDataList = realm.where(UserData.class).findAll();
        /*  realm.close();
        achievementRealm.close();*/
        return new ArrayList<>(userDataList);
    }

    public void populateWorkDetails(){
        Realm realm = RealmTesterApplication.getUserRealm();
        int i=0;
        for (String companyName: userCompanyNameList) {
            WorkDetails workDetails = new WorkDetails();
            workDetails.setId(i);
            workDetails.setNameOfCompany(companyName);
            workDetails.setYearsWorked(userExperienceList.get(i));
            workDetails.setCurrentRole("Software Developer");
            realm.beginTransaction();
            realm.insertOrUpdate(workDetails);
            realm.commitTransaction();
            i++;
        }
    }
    public void populateAchievements(){
        Realm achievementRealm = RealmTesterApplication.getAchievementRealm();
        int i=0;
        for (String courses: userCoursesList) {
            Achievement achievement = new Achievement();
            achievement.setCertification(userCertificationList.get(i));
            achievement.setId(i);
            achievement.setCourses(courses);
            achievement.setExtraCurricular("VolleyBall");
            achievementRealm.beginTransaction();
            achievementRealm.insertOrUpdate(achievement);
            achievementRealm.commitTransaction();
            i++;
        }
    }
}

