package com.example.inceptive.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inceptive.fragment.EmployeeHome1;

import java.util.ArrayList;

/**
 * Created by inceptive on 2/8/18.
 */

public class EmployeeHomeAdapter extends RecyclerView.Adapter{


    Context ctx;
    ArrayList<CountModel> countlist;



    public EmployeeHomeAdapter(Context context, ArrayList<CountModel> countlist)
    {
        this.countlist = countlist;
        this.ctx = context;
    }

    public EmployeeHomeAdapter(EmployeeHome1 employeeHome1, ArrayList<CountModel> countlist) {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_employee_home1, parent, false);
        com.example.inceptive.navigation.EmployeeHomeAdapter.My_ViewHolder vh = new com.example.inceptive.navigation.EmployeeHomeAdapter.My_ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        try {
            if (holder instanceof com.example.inceptive.navigation.EmployeeHomeAdapter.My_ViewHolder) {
                com.example.inceptive.navigation.EmployeeHomeAdapter.My_ViewHolder holder1 = (com.example.inceptive.navigation.EmployeeHomeAdapter.My_ViewHolder) holder;
                holder1.bindView(position);
                setData(holder1, position);
            }
//            else if (holder instanceof OptionalViewHolder) {
//                OptionalViewHolder holder = (OptionalViewHolder) holder;
//                holder.noBarberFound.setText(ctx.getString(R.string.no_barber_available));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData(com.example.inceptive.navigation.EmployeeHomeAdapter.My_ViewHolder holder, int position)
    {


        CountModel model1=countlist.get(position);
        holder.textstatus.setText(countlist.size());



    }

    @Override
    public int getItemCount()
    {
        if (countlist.size() == 0) {
            return 1;
        } else {
            return countlist.size();
        }
    }

    private class My_ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textfrom_date,textto_date,textstatus,textreason;

        public My_ViewHolder(View itemView)
        {
            super(itemView);
            textfrom_date = (TextView)itemView.findViewById(R.id.leavestatus_fromDate);
            textto_date = (TextView)itemView.findViewById(R.id.leavestatus_toDate);
            textreason = (TextView)itemView.findViewById(R.id.reasonstatus);
            textstatus = (TextView)itemView.findViewById(R.id.statusleave);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });


        }



        public void bindView(int position)
        {

        }
    }
}


