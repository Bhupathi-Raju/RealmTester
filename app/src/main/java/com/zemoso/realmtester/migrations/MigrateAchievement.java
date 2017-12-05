package com.zemoso.realmtester.migrations;

import android.support.annotation.NonNull;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by zemoso on 4/12/17.
 */

public class MigrateAchievement implements RealmMigration {

    @Override
    public void migrate(@NonNull DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema realmSchema = realm.getSchema();
        if(oldVersion ==0){
            RealmObjectSchema achievementSchema = realmSchema.get("Achievement");
            if (achievementSchema!=null) {
                achievementSchema
                        .addField("extraCurricular", String.class)
                        .transform(new RealmObjectSchema.Function() {
                            @Override
                            public void apply(@NonNull DynamicRealmObject obj) {
                                obj.set("extraCurricular","VolleyBall");
                            }
                        });
                oldVersion++;
            }
        }
        if(oldVersion == 1){
            RealmObjectSchema achievementSchema = realmSchema.get("Achievement");
            if(achievementSchema!= null){
                achievementSchema
                        .removeField("extraCurricular");
                oldVersion++;
            }
        }
    }

    @Override
    public int hashCode() {
        return 37;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof MigrateAchievement);
    }
}
