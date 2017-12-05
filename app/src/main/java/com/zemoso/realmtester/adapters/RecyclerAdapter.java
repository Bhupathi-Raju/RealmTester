package com.zemoso.realmtester.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zemoso.realmtester.R;
import com.zemoso.realmtester.models.UserData;

import java.util.List;

/**
 * Created by zemoso on 4/12/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.UserViewHolder> {

    private List<UserData> userDataList;

    private AdapterInterface adapterInterface;

    public interface AdapterInterface{
         void openFragment(int id);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        public TextView userName;

        public UserViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
        }
    }

    public RecyclerAdapter(List<UserData> userDataList,AdapterInterface adapterInterface){
        this.userDataList = userDataList;
        this.adapterInterface = adapterInterface;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
            holder.userName.setText(userDataList.get(holder.getAdapterPosition()).getFirstName());
            holder.userName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                       adapterInterface.openFragment(userDataList.get(holder.getAdapterPosition()).getId());
                }
            });
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }


}
