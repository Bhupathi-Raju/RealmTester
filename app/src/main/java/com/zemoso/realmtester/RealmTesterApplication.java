package com.zemoso.realmtester;

import android.app.Application;
import com.zemoso.realmtester.migrations.MigrateAchievement;
import com.zemoso.realmtester.models.Achievement;
import com.zemoso.realmtester.models.UserData;
import com.zemoso.realmtester.models.WorkDetails;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.annotations.RealmModule;

/**
 * Created by zemoso on 4/12/17.
 */

public class RealmTesterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

    }

    public static Realm getAchievementRealm(){
        RealmConfiguration achievementConfiguration = new RealmConfiguration.Builder()
                .name("achievements.realm")
                .schemaVersion(2)
                .modules(new AchievementModule())
                .migration(new MigrateAchievement())
                .build();
        return Realm.getInstance(achievementConfiguration);
    }

    public static Realm getUserRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("user.realm")
                .schemaVersion(1)
                .modules(new UserModule())
                .build();
        return Realm.getInstance(realmConfiguration);
    }

    @RealmModule(classes = {UserData.class, WorkDetails.class})
    private static class UserModule{

    }

    @RealmModule(classes = {Achievement.class})
    private static class AchievementModule{

    }
}
