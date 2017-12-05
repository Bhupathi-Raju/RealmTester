package com.zemoso.realmtester.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zemoso.realmtester.R;
import com.zemoso.realmtester.RealmTesterApplication;
import com.zemoso.realmtester.models.Achievement;
import com.zemoso.realmtester.models.WorkDetails;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    private int id;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments!=null){
            id= arguments.getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView companyName = view.findViewById(R.id.companyName);
        TextView yearsWorked = view.findViewById(R.id.years);
        TextView role = view.findViewById(R.id.role);
        TextView certification = view.findViewById(R.id.certification);
        TextView course = view.findViewById(R.id.courses);
        TextView extraCurricular = view.findViewById(R.id.extraCurricular);
        Realm realm = RealmTesterApplication.getUserRealm();
        WorkDetails workDetails = realm.where(WorkDetails.class).equalTo("id",id).findFirst();
        Achievement achievement = RealmTesterApplication.getAchievementRealm().where(Achievement.class).equalTo("id",id).findFirst();
        assert workDetails != null;
        companyName.append(workDetails.getNameOfCompany());
        yearsWorked.append(String.valueOf(workDetails.getYearsWorked()));
        role.append(workDetails.getCurrentRole());
        assert achievement != null;
        course.append(achievement.getCourses());
        certification.append(achievement.getCertification());
        extraCurricular.append(achievement.getExtraCurricular());
    }
}
