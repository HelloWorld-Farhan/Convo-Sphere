package com.example.letss_talk;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
//
//public class UserAdpter extends RecyclerView.Adapter<UserAdpter.viewholder> {
//    MainActivity mainActivity;
//    ArrayList<Users> arrayList;
//    public UserAdpter(MainActivity mainActivity, ArrayList<Users> arrayList) {
//        this.mainActivity = mainActivity;
//        this.arrayList = arrayList;
//    }
//
//    @NonNull
//    @Override
//    public UserAdpter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mainActivity).inflate(R.layout.user_item,parent,false);
//        return new viewholder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UserAdpter.viewholder holder, int position) {
//        Users users = arrayList.get(position);
//        holder.username.setText(users.userName);
//        holder.userstatus.setText(users.status);
//        Picasso.get().load(users.profilepic).into(holder.userimg);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    public class viewholder extends RecyclerView.ViewHolder {
//        CircleImageView userimg;
//        TextView username;
//        TextView userstatus;
//        public viewholder(@NonNull View itemView) {
//            super(itemView);
//            userimg = itemView.findViewById(R.id.userimg);
//            username = itemView.findViewById(R.id.username);
//            userstatus = itemView.findViewById(R.id.userstatus);
//        }
//    }
//}
public class UserAdpter extends RecyclerView.Adapter<UserAdpter.ViewHolder> {
    MainActivity mainActivity;
    ArrayList<Users> arrayList;

    public UserAdpter(MainActivity mainActivity, ArrayList<Users> arrayList) {
        this.mainActivity = mainActivity;
        this.arrayList = arrayList != null ? arrayList : new ArrayList<>(); // Avoid null ArrayList
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users = arrayList.get(position);
        holder.username.setText(users.userName != null ? users.userName : "No Name");
        holder.userstatus.setText(users.status != null ? users.status : "No Status");

        // Load image with error handling
        try {
            Picasso.get()
                    .load(users.profilepic)
//                    .placeholder(R.drawable.placeholder) // Optional placeholder image
//                    .error(R.drawable.error)            // Optional error image
                    .into(holder.userimg);
        } catch (Exception e) {
            e.printStackTrace(); // Log error
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, chatwindo.class);
                intent.putExtra("nameeee",users.getUserName());
                intent.putExtra("reciverImg",users.getProfilepic());
                intent.putExtra("uid",users.getUserId());
                mainActivity.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userimg;
        TextView username;
        TextView userstatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userimg = itemView.findViewById(R.id.userimg);
            username = itemView.findViewById(R.id.username);
            userstatus = itemView.findViewById(R.id.userstatus);
        }
    }
}

